package com.hansy.tyly.admin.sale.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hansy.tyly.admin.dao.mapper.TPubDistributorInfoMapper;
import com.hansy.tyly.admin.dao.mapper.TPubMerInfoMapper;
import com.hansy.tyly.admin.dao.model.TPubDistributorInfo;
import com.hansy.tyly.admin.dao.model.TPubMerInfo;
import com.hansy.tyly.admin.dealers.dao.mapper.PubFilesMapper;
import com.hansy.tyly.admin.dealers.dao.model.PubFiles;
import com.hansy.tyly.admin.sale.dao.mapper.AuditMapper;
import com.hansy.tyly.admin.sale.service.AuditService;
import com.hansy.tyly.admin.utils.AppPropertiesUtil;
import com.hansy.tyly.admin.utils.Context;
import com.hansy.tyly.admin.utils.QrCodeUtil;
import com.hansy.tyly.admin.utils.UUIDUtils;
import com.hansy.tyly.admin.utils.constant.SaleConstant;
import com.hansy.tyly.admin.utils.constant.SysCodeConstant;
import com.hansy.tyly.core.helper.NPageHelper;

@Service
@Transactional
public class AuditServiceImpl implements AuditService {
	private static final String REGISTERVIEW_URL = AppPropertiesUtil.getValue("mer_registerview");
	@Autowired
	private AuditMapper auditMapper;
	@Autowired
	private TPubDistributorInfoMapper tPubDistributorInfoMapper;
	@Autowired
	private TPubMerInfoMapper tPubMerInfoMapper;
	@Autowired
	private PubFilesMapper pubFilesMapper;

	@Override
	public List<Map<String, Object>> getAudits(Map<String, Object> params) {
		return auditMapper.getAudits(params);
	}

	@Override
	public int auditMerchant(String userNo, String status) {
		TPubMerInfo info = new TPubMerInfo();
		info.setMerNo(userNo);
		info.setMerStatus(status);
		info.setUpdateDate(new Date());
		return tPubMerInfoMapper.updateByPrimaryKeySelective(info);
	}

	@Override
	public int auditDealer(String userNo, String status) {
		TPubDistributorInfo info = new TPubDistributorInfo();
		info.setDistributorNo(userNo);
		info.setDistributorStatus(status);
		info.setUpdateDate(new Date());
		// 审核通过，生成经销商绑定二维码
		if (SysCodeConstant.MERCHANT_STATUS_NORMAL.equals(status)) {
			String path = Context.DEALERS_IMG + userNo + ".png";
			String fileUrl = null;
			try {
				fileUrl = QrCodeUtil.getQrCode(userNo, 300, 300, path);
				PubFiles pubFiles = new PubFiles();
				pubFiles.setFileNo(UUIDUtils.getUuid());
				pubFiles.setCustNo(userNo);
				pubFiles.setUploadDate(new Date());
				pubFiles.setFileStatus("1");
				pubFiles.setFilePath(fileUrl);
				pubFiles.setFileType(Context.FILE_TYPE_DEALER_TWODIMENSION);
				pubFilesMapper.insert(pubFiles);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return tPubDistributorInfoMapper.updateByPrimaryKeySelective(info);
	}

	@Override
	public String setTwoDimensionCode(String salesId) {
		String url = AuditServiceImpl.REGISTERVIEW_URL + salesId;
		String path = Context.SALES_IMG + salesId + ".png";
		String fileUrl = null;
		try {
			fileUrl = QrCodeUtil.getQrCode(url, 300, 300, path);
			PubFiles pubFiles = new PubFiles();
			pubFiles.setFileNo(UUIDUtils.getUuid());
			pubFiles.setCustNo(salesId);
			pubFiles.setUploadDate(new Date());
			pubFiles.setFileStatus("1");
			pubFiles.setFilePath(fileUrl);
			pubFiles.setFileType(Context.FILE_TYPE_MERCHANT_INVITE_TWODIMENSION);
			pubFilesMapper.insert(pubFiles);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileUrl;
	}

	@Override
	public String getTwoDimensionCode(String salesId) {
		String fileUrl = null;
		List<PubFiles> files = pubFilesMapper.selectTwoDimensionCode(salesId,
				Context.FILE_TYPE_MERCHANT_INVITE_TWODIMENSION);
		if (files != null && files.size() > 0) {
			fileUrl = files.get(0).getFilePath();
		}
		return fileUrl;
	}

	@Override
	public String getDealerTwoDimensionCode(String dealerID) {
		String fileUrl = null;
		List<PubFiles> files = pubFilesMapper.selectTwoDimensionCode(dealerID, Context.FILE_TYPE_DEALER_TWODIMENSION);
		if (files != null && files.size() > 0) {
			fileUrl = files.get(0).getFilePath();
		}
		return fileUrl;
	}

	@Override
	public String refreshTwoDimensionCode(String salesId) {
		String url = AuditServiceImpl.REGISTERVIEW_URL + salesId;
		String path = Context.SALES_IMG + salesId + ".png";
		String fileUrl = null;
		try {
			pubFilesMapper.deleteTwoDimensionCode(salesId, Context.FILE_TYPE_MERCHANT_INVITE_TWODIMENSION);
			fileUrl = QrCodeUtil.getQrCode(url, 300, 300, path);
			PubFiles pubFiles = new PubFiles();
			pubFiles.setFileNo(UUIDUtils.getUuid());
			pubFiles.setCustNo(salesId);
			pubFiles.setUploadDate(new Date());
			pubFiles.setFileStatus("1");
			pubFiles.setFilePath(fileUrl);
			pubFiles.setFileType(Context.FILE_TYPE_MERCHANT_INVITE_TWODIMENSION);
			pubFilesMapper.insert(pubFiles);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileUrl;
	}

	@Override
	public String refreshDealerBindCode(String userNo) {
		String path = Context.DEALERS_IMG + userNo + ".png";
		String fileUrl = null;
		try {
			fileUrl = QrCodeUtil.getQrCode(userNo, 300, 300, path);
			pubFilesMapper.deleteTwoDimensionCode(userNo, Context.FILE_TYPE_DEALER_TWODIMENSION);
			PubFiles pubFiles = new PubFiles();
			pubFiles.setFileNo(UUIDUtils.getUuid());
			pubFiles.setCustNo(userNo);
			pubFiles.setUploadDate(new Date());
			pubFiles.setFileStatus("1");
			pubFiles.setFilePath(fileUrl);
			pubFiles.setFileType(Context.FILE_TYPE_DEALER_TWODIMENSION);
			pubFilesMapper.insert(pubFiles);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileUrl;
	}

}
