package com.hansy.tyly.customer.custmgr.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;

import com.alibaba.fastjson.JSONObject;
import com.hansy.tyly.admin.base.bean.UserProfile;
import com.hansy.tyly.admin.dao.model.TBusiCust;
import com.hansy.tyly.core.excepiton.ServiceException;
import com.hansy.tyly.customer.custmgr.mapper.TBusiCustMapper;
import com.hansy.tyly.customer.custmgr.mapper.TBusiCustProdMapper;
import com.hansy.tyly.customer.custmgr.mapper.TBusiCustTransferMapper;
import com.hansy.tyly.customer.custmgr.model.TBusiCustProd;
import com.hansy.tyly.customer.custmgr.model.TBusiCustTransfer;
import com.hansy.tyly.customer.custmgr.service.CustMgrService;
import com.hansy.tyly.customer.system.mapper.SysUserMapper;
import com.hansy.tyly.customer.system.model.SysUser;
import com.hansy.tyly.customer.system.model.TBusiOpLog;
import com.hansy.tyly.customer.system.service.TBusiOpLogService;
import com.hansy.tyly.customer.utils.Bean2MapUtil;
import com.hansy.tyly.customer.utils.ConstantsUtil;
import com.hansy.tyly.customer.utils.RandomUtil;
import com.hansy.tyly.customer.utils.StringUtil;
import com.hansy.tyly.customer.utils.ValidUtil;


@Service
public class CustMgrServiceImpl implements CustMgrService {
	
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private TBusiCustMapper tbusiCustMapper;
	@Autowired
	private TBusiCustTransferMapper custTransfrMapper;
	@Autowired
	private TBusiCustProdMapper custProdMapper;
	@Autowired
	private TBusiOpLogService tBusiOpLogService;
	
	private static final Logger logger = LoggerFactory.getLogger(CustMgrServiceImpl.class);

	@Override
	public SysUser selectSysUserByLoginId(String loginId) {
		Example exp = new Example(SysUser.class);
		exp.createCriteria().andEqualTo("loginId", loginId);
		List<SysUser> list = sysUserMapper.selectByExample(exp);
		return list.size() > 0  ? list.get(0)  : null;
	}

	@Override
	public List<Map<String, String>> selectCustList(Map<String, Object> inMap) {
		return tbusiCustMapper.queryCustList(inMap);
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public boolean insertCustInfo(TBusiCust custInfo) {
		
		if(!ValidUtil.isIdcard(custInfo.getCustCertNo())){
			throw new ServiceException("当前客户身份证号码格式不正确，请核对！");
		}
		if(!ValidUtil.isMobile(custInfo.getCustTel())){
			throw new ServiceException("当前客户电话号码格式不正确，请核对！");
		}
		Example exp = new Example(TBusiCust.class);
		exp.createCriteria().andEqualTo("custCertNo", custInfo.getCustCertNo())
		.andEqualTo("status",custInfo.getStatus()).andEqualTo("orgId", custInfo.getOrgId());
		int tmpCount = tbusiCustMapper.selectCountByExample(exp);
		if(tmpCount > 0){
			throw new ServiceException("当前客户已纳入本机构管理，请勿重复添加！");
		}
		
		return tbusiCustMapper.insertSelective(custInfo) == 0 ? false : true;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public boolean updateCustInfo(TBusiCust custInfo) {
		Date currDate = new Date();
		//更新为无效状态时，将对应的有效产品记录也置为无效
		if(ConstantsUtil.STATUS_INVALID.equals(custInfo.getStatus())){
			custInfo.setMngStatus(ConstantsUtil.MNG_STATUS_NO);
			TBusiCustProd custProd = new TBusiCustProd();
			Example exp = new Example(TBusiCustProd.class);
			exp.createCriteria().andEqualTo("custId", custInfo.getCustId()).andEqualTo("status", ConstantsUtil.STATUS_NORMAL);
			custProd.setInsertUserId(custInfo.getInsertUserId());
			custProd.setUpdateTime(currDate);
			custProdMapper.updateByExampleSelective(custProd, exp);
		}
		custInfo.setUpdateTime(currDate);
		tbusiCustMapper.updateByPrimaryKeySelective(custInfo);
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public boolean deleteCusts(List<TBusiCust> custList) {
		Date currDate = new Date();
		for (TBusiCust tBusiCust : custList) {
			TBusiCustProd custProd = new TBusiCustProd();
			Example exp = new Example(TBusiCustProd.class);
			exp.createCriteria().andEqualTo("custId", tBusiCust.getCustId()).andEqualTo("status", ConstantsUtil.STATUS_NORMAL);
			custProd.setInsertUserId(tBusiCust.getInsertUserId());
			custProd.setUpdateTime(currDate);
			custProd.setStatus(ConstantsUtil.STATUS_INVALID);
			custProdMapper.updateByExampleSelective(custProd, exp);
			
			tBusiCust.setUpdateTime(currDate);
			tBusiCust.setStatus(ConstantsUtil.STATUS_INVALID);
			tBusiCust.setMngStatus(ConstantsUtil.MNG_STATUS_NO);
			tbusiCustMapper.updateByPrimaryKeySelective(tBusiCust);
		}
		return false;
	}

	@Override
	public List<Map<String, String>> queryCustListByUserId(
			Map<String, Object> inMap) {
		return tbusiCustMapper.queryCustListByUserId(inMap);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public void saveCustTransferinfo(Map<String, Object> inMap){
		TBusiCust custInfo = new TBusiCust();
		TBusiCustTransfer transferInfo = new TBusiCustTransfer();
		String oldUserId = inMap.get("oldUserId").toString();
		String newUserId = inMap.get("newUserId").toString();
		String optUserId = inMap.get("optUserId").toString();
		@SuppressWarnings("unchecked")
		List<String> custIds = (List<String>) inMap.get("custIds");
		Date currDate = new Date();
		for (String custId : custIds) {
			//插入客户转移流水记录
			transferInfo.setSysUuid(RandomUtil.UUID());
			transferInfo.setCustId(custId);
			transferInfo.setOldUserId(oldUserId);
			transferInfo.setNewUserId(newUserId);
			transferInfo.setInsertUserId(optUserId);
			transferInfo.setStatus(ConstantsUtil.STATUS_NORMAL);
			transferInfo.setInsertTime(currDate);
			custTransfrMapper.insertSelective(transferInfo);
			
			//更新客户归属经理
			custInfo.setCustId(custId);
			custInfo.setUserId(newUserId);
			custInfo.setUpdateTime(currDate);
			tbusiCustMapper.updateByPrimaryKeySelective(custInfo);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public int insertCustInfoFormExcel(List<HashMap<String, Object>> excelList, UserProfile userProfile) {
		TBusiCust custInfo = null;
		Date currDate = new Date();
		if(excelList != null && excelList.size() > 1000){
			throw new ServiceException("客户数据超限，一次最多上传1000条！");
		}
		int lineNo = 3 ;
		for (HashMap<String, Object> map : excelList) {
            logger.info("#################################################################");
            logger.info("####");
            logger.info("####");
			custInfo = new TBusiCust();
			if(map.get("loanAmt") == null){
				//map.put("loanAmt", "0");
			}else if(!ValidUtil.isAmtNumber(map.get("loanAmt").toString())){
                logger.info("#### loanAmt: "+map.get("loanAmt").toString());
				throw new ServiceException("第【"+lineNo+"】行客户贷款金额格式或精度有误，请核对！");
			}
			Bean2MapUtil.transMap2Bean2(map, custInfo);
            logger.info("#### CustInfo--> "+JSONObject.toJSONString(custInfo));
			if(StringUtil.isEmpty(custInfo.getCustCertNo()) || !ValidUtil.isIdcard(custInfo.getCustCertNo())){
                logger.info("#### custCertNo: "+custInfo.getCustCertNo());
				throw new ServiceException("第【"+lineNo+"】行客户身份证号码格式不正确，请核对！");
			}
			//手机号码特殊处理
			if(!StringUtil.isEmpty(custInfo.getCustTel())){
                custInfo.setCustTel(custInfo.getCustTel().trim().replace(".","").replace("E10",""));
            }
			if(StringUtil.isEmpty(custInfo.getCustTel()) || !ValidUtil.isMobile(custInfo.getCustTel())){
                logger.info("#### custTel: "+custInfo.getCustTel());
				throw new ServiceException("第【"+lineNo+"】行客户电话号码格式不正确，请核对！");
			}
			if(StringUtil.isEmpty(custInfo.getCustName()) || custInfo.getCustName().length() > 30 ){
				throw new ServiceException("第【"+lineNo+"】行客户姓名为空或超长，请核对！");
			}
			
			Example exp = new Example(TBusiCust.class);
			exp.createCriteria().andEqualTo("custCertNo", custInfo.getCustCertNo())
			.andEqualTo("status",ConstantsUtil.STATUS_NORMAL).andEqualTo("orgId", userProfile.getOrgId());
			int tmpCount = tbusiCustMapper.selectCountByExample(exp);
			if(tmpCount > 0){
				throw new ServiceException("身份证号【"+custInfo.getCustCertNo()+"】已纳入本机构管理，请勿重复添加！");
			}
            logger.info("####");
            logger.info("####");
            logger.info("#################################################################");
			custInfo.setCustId(RandomUtil.UUID());
			custInfo.setUserId(userProfile.getUserId());
			custInfo.setStatus(ConstantsUtil.STATUS_NORMAL);
			custInfo.setMngStatus(ConstantsUtil.MNG_STATUS_NO);
			custInfo.setOrgId(userProfile.getOrgId());
			custInfo.setInsertTime(currDate);
			custInfo.setInsertUserId(userProfile.getUserId());
			tbusiCustMapper.insertSelective(custInfo);
			lineNo ++;
		}
		
		return excelList.size();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public void saveCustAndProds(List<TBusiCustProd> list,HashMap<String, String> tempMap,UserProfile userProfile) {
		Example exp = null;
		int countNum = 0;
		Date currDate = new Date();
		TBusiCust custInfo = null;
		for (TBusiCustProd custProd : list) {
			exp = new Example(TBusiCustProd.class);
			exp.createCriteria().andEqualTo("custId", custProd.getCustId())
					.andEqualTo("prodId", custProd.getProdId())
					.andEqualTo("status", ConstantsUtil.STATUS_NORMAL);
			//.andEqualTo("freType", custProd.getFreType());
			countNum = custProdMapper.selectCountByExample(exp);
			if(countNum > 0){
				throw new ServiceException("客户【"+tempMap.get(custProd.getSysUuid())+"】已存在有效管理记录，请勿重复添加！");
			}
			custProd.setInsertTime(currDate);
			custProd.setStatus(ConstantsUtil.STATUS_NORMAL);
			
			switch (custProd.getFreType()) {
			case ConstantsUtil.FRE_TYPE_01:
				custProd.setFreNum(1);
				break;
			case ConstantsUtil.FRE_TYPE_02:
				custProd.setFreNum(7);
				break;
			case ConstantsUtil.FRE_TYPE_03:
				custProd.setFreNum(15);
				break;
			case ConstantsUtil.FRE_TYPE_04:
				custProd.setFreNum(30);
				break;
			default:
				throw new ServiceException("客户【"+tempMap.get(custProd.getSysUuid())+"】调用频次类型不合法，请核对！");
			}
			custProdMapper.insertSelective(custProd);

			TBusiOpLog tBusiOpLog=new TBusiOpLog();
			tBusiOpLog.setBusiOpType(ConstantsUtil.OP_MANGAMNET_YES);
			tBusiOpLog.setCustId(custProd.getCustId());
			tBusiOpLog.setUserId(userProfile==null?"":userProfile.getUserId());
			tBusiOpLog.setProdId(custProd.getProdId());
			tBusiOpLog.setOrgId(userProfile==null?"empty":userProfile.getOrgId());
			logger.info("记录操作日志："+ JSONObject.toJSON(tBusiOpLog));
			tBusiOpLogService.insertOpLogs(tBusiOpLog);
			
			//更新客户管理状态
			TBusiCust tempCust = tbusiCustMapper.selectByPrimaryKey(custProd.getCustId());
			if(!ConstantsUtil.MNG_STATUS_YES.equals(tempCust.getMngStatus())){
				custInfo = new TBusiCust();
				custInfo.setCustId( custProd.getCustId());
				custInfo.setMngStatus(ConstantsUtil.MNG_STATUS_YES);
				custInfo.setUpdateTime(currDate);
				custInfo.setUserId(custProd.getInsertUserId());
				tbusiCustMapper.updateByPrimaryKeySelective(custInfo);
			}
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public void cancelCustProds(List<TBusiCustProd> list,UserProfile userProfile) {
		Date currDate = new Date();
		TBusiCust custInfo = null;
		for (TBusiCustProd custProd : list) {
			custProd.setStatus(ConstantsUtil.STATUS_INVALID);
			custProd.setInsertTime(currDate);
			custProdMapper.updateByPrimaryKeySelective(custProd);
			TBusiOpLog tBusiOpLog=new TBusiOpLog();
			tBusiOpLog.setBusiOpType(ConstantsUtil.OP_MANGAMNET_NO);
			tBusiOpLog.setCustId(custProd.getCustId());
			tBusiOpLog.setUserId(userProfile==null?"":userProfile.getUserId());
			tBusiOpLog.setProdId(custProd.getProdId());
			tBusiOpLog.setOrgId(userProfile==null?"":userProfile.getOrgId());
			logger.info("记录操作日志："+ JSONObject.toJSON(tBusiOpLog));
			tBusiOpLogService.insertOpLogs(tBusiOpLog);
			//检查客户名下是否还有有效产品绑定记录
			TBusiCustProd temp = custProdMapper.selectByPrimaryKey(custProd.getSysUuid());
			Example exp = new Example(TBusiCustProd.class);
			exp.createCriteria().andEqualTo("custId", temp.getCustId()).andEqualTo("status", ConstantsUtil.STATUS_NORMAL);
			List<TBusiCustProd> lists = custProdMapper.selectByExample(exp);
			if(lists ==null || lists.size() == 0){
				//更新客户管理状态
				custInfo = new TBusiCust();
				custInfo.setCustId( temp.getCustId());
				custInfo.setMngStatus(ConstantsUtil.MNG_STATUS_NO);
				custInfo.setUpdateTime(currDate);
				tbusiCustMapper.updateByPrimaryKeySelective(custInfo);
			}
		}
		
	}

	@Override
	public List<Map<String, String>> queryCustMngList(Map<String, Object> inMap) {
		return tbusiCustMapper.queryCustMngList(inMap);
	}
	

}
