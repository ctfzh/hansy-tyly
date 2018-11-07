package com.hansy.tyly.dealers.login.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hansy.tyly.admin.constant.SysCodeConstant;
import com.hansy.tyly.admin.utils.AppPropertiesUtil;
import com.hansy.tyly.common.orders.dao.mapper.TPubFilesMapper;
import com.hansy.tyly.common.orders.dao.mapper.TUserNewsMapper;
import com.hansy.tyly.common.orders.dao.model.TPubFiles;
import com.hansy.tyly.common.orders.dao.model.TUserNews;
import com.hansy.tyly.common.utils.Context;
import com.hansy.tyly.common.utils.UUIDUtils;
import com.hansy.tyly.dealers.login.Dao.mapper.DealersManagerMapper;
import com.hansy.tyly.dealers.login.Dao.mapper.TUserRecAddrMapper;
import com.hansy.tyly.dealers.login.service.DealersmanagerService;
import com.hansy.tyly.merchants.orders.dao.model.TpubDistributorInfo;
import com.hansy.tyly.merchants.orders.dao.model.TpubMerInfo;

@Service
public class DealersManagerServiceImp implements DealersmanagerService {
private final static String AUDIT_MESSAGE = AppPropertiesUtil.getValue("message.SALE_AUDIT_NEWS_DEMO");
private final static String AUDIT_MESSAGE_TITLE = AppPropertiesUtil.getValue("message.SALE_AUDIT_TITLE_DEMO");
	
@Resource
private DealersManagerMapper  dealersManagerMapper;

@Resource
private TPubFilesMapper  tPubFilesMapper;

@Resource
private TUserRecAddrMapper tUserRecAddrMapper;

@Autowired
private TUserNewsMapper tuserNewsMapper;

	@Override
	public int updateMerChants(Map<String, Object> map) {
		TpubDistributorInfo  tpubDistributorInfo  = new TpubDistributorInfo();
		tpubDistributorInfo.setDistributorNo(map.get("distributorNo").toString());
		tpubDistributorInfo.setDistributorName(map.get("distributorName").toString());
		tpubDistributorInfo.setDistributorContact(map.get("distributorContact").toString());
		tpubDistributorInfo.setDistributorContactPhone(map.get("phone").toString());
		tpubDistributorInfo.setLegalName(map.get("legalName").toString());
		tpubDistributorInfo.setDistributorRegNo(map.get("distributorRegNo").toString());
		tpubDistributorInfo.setCompanyType(map.get("companyType").toString());
		tpubDistributorInfo.setDistributorAddre(map.get("distributorAddre").toString());
		tpubDistributorInfo.setDistributorStatus(Context.USER_STATUS_ENABLE_WW);
		tpubDistributorInfo.setUpdateDate(new Date());
		  int a = dealersManagerMapper.updateByPrimaryKeySelective(tpubDistributorInfo);
		  TpubDistributorInfo info = dealersManagerMapper.selectByPrimaryKey(tpubDistributorInfo.getDistributorNo());
			TUserNews userNews = new TUserNews();
			userNews.setIsRead(Context.READ_FALSE);
			userNews.setNewsDate(new Date());
			userNews.setOrderNo(null);
			userNews.setTableKey(UUIDUtils.getUuid());
			userNews.setUserNo(info.getStaffNo());
			String message = AUDIT_MESSAGE;
			message = message.replace("userType", "经销商");
			message = message.replace("userName",tpubDistributorInfo.getDistributorName());
			userNews.setNewsTitle(AUDIT_MESSAGE_TITLE);
			userNews.setNewsContent(message);
			userNews.setNewsType(SysCodeConstant.NEW_TYPE_SALE);
			userNews.setUserType(SysCodeConstant.USER_TYPE_SALE);
			tuserNewsMapper.insert(userNews);
		return a;
	}
	
	@Override
	public String getMerstatus(String distributorNo) {
		String a = null;
		List<TpubDistributorInfo> list = new ArrayList<>(); 
		TpubDistributorInfo  tpubDistributorInfo  = new TpubDistributorInfo();
		tpubDistributorInfo.setDistributorNo(distributorNo);
		try {
			list= dealersManagerMapper.select(tpubDistributorInfo);
			for(TpubDistributorInfo stu : list){
				   a = stu.getDistributorStatus();
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;

	}

	@Override
	public TpubDistributorInfo slelectPersonalInfo(String distributorNo) {
		TpubDistributorInfo tpubDistributorInfo = new TpubDistributorInfo();
		try {
			tpubDistributorInfo = dealersManagerMapper.selectByPrimaryKey(distributorNo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return tpubDistributorInfo;
	}

	@Override
	public void updateInfo(String url,String fileId, String distributorNo) {
		TPubFiles  tPubFiles = new TPubFiles();
		 tPubFiles.setFileNo(fileId);
		 tPubFiles.setFilePath(url);
		 tPubFiles.setCustNo(distributorNo);
		 tPubFiles.setUpdateDate(new Date());
		 tPubFilesMapper.updateByPrimaryKeySelective(tPubFiles);
	}

	@Override
	public void insertImgeName(String ImgeName,String ImageId) {
		TPubFiles  tPubFiles = new TPubFiles();	
		 tPubFiles.setFileNo(ImageId);
		 tPubFiles.setFileName(ImgeName);
		 tPubFiles.setFileStatus(Context.USER_STATUS_ENABLE);
		 tPubFiles.setUploadDate(new Date());
		 tPubFilesMapper.insertSelective(tPubFiles);


		
	}

	@Override
	public Map<String, Object> getuserNameAndAddr(String distributorNo) {
		return  dealersManagerMapper.getuserNameAndAddr(distributorNo);
	}

	@Override
	public Map<String, Object> getMapSaleInfo(String distributorNo) {
		return dealersManagerMapper.getSaleInfo(distributorNo);
	}

	@Override
	public Map<String, Object> getBuyInfo(String distributorNo) {
		return dealersManagerMapper.getBuyInfo(distributorNo);
	}

	@Override
	public Map<String, Object> getYuJinXinXi(String distributorNo) {
		return dealersManagerMapper.getYuJinXinXi(distributorNo);
	}
 
	@Override
	public Map<String, Object> getConsumption(String merNo,Date bigindate,Date enddate) {
		// TODO Auto-generated method stub
		return dealersManagerMapper.getConsumption(bigindate, enddate, merNo);
	}

	@Override
	public List<Map<String, Object>> getConsumptionList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dealersManagerMapper.getConsumptionList(map);
	}

	@Override
	public Map<String, Object> getuserNameAndAddres(String distributorNo) {
		// TODO Auto-generated method stub
		return dealersManagerMapper.getuserNameAndAddres(distributorNo);
	}

	@Override
	public Map<String, Object> getBuyInfoinfo(String distributorNo) {
		// TODO Auto-generated method stub
		return dealersManagerMapper.getBuyInfoinfo(distributorNo);
	}

	@Override
	public Map<String, Object> getNews(String distributorNo) {
		// TODO Auto-generated method stub
		return dealersManagerMapper.getNews(distributorNo);
	}

	@Override
	public Map<String, Object> getUserImge(String distributorNo) {
		// TODO Auto-generated method stub
		return dealersManagerMapper.getUserImg(distributorNo);
	}

}
