package com.hansy.tyly.merchants.mer.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.hansy.tyly.admin.constant.SysCodeConstant;
import com.hansy.tyly.admin.utils.AppPropertiesUtil;
import com.hansy.tyly.admin.utils.RandomUtil;
import com.hansy.tyly.common.orders.dao.mapper.LoginCommomMapper;
import com.hansy.tyly.common.orders.dao.mapper.TUserNewsMapper;
import com.hansy.tyly.common.orders.dao.model.TUserNews;
import com.hansy.tyly.common.utils.Context;
import com.hansy.tyly.common.utils.UUIDUtils;
import com.hansy.tyly.merchants.mer.dao.mapper.MerchantsMangerDao;
import com.hansy.tyly.merchants.mer.service.MerchantsMangerService;
import com.hansy.tyly.merchants.orders.dao.mapper.LoginMerchantsMapper;
import com.hansy.tyly.merchants.orders.dao.model.TpubDistributorMer;
import com.hansy.tyly.merchants.orders.dao.model.TpubMerInfo;
import com.hansy.tyly.merchants.orders.dao.model.TuserBaseInfo;
import com.hansy.tyly.merchants.orders.dao.model.TuserBaseInfoExample;
@Repository
@Service
public class MerchantsMangerServiceImp implements MerchantsMangerService {
	private final static String AUDIT_MESSAGE = AppPropertiesUtil.getValue("message.SALE_AUDIT_NEWS_DEMO");
	private final static String AUDIT_MESSAGE_TITLE = AppPropertiesUtil.getValue("message.SALE_AUDIT_TITLE_DEMO");
	@Autowired
	private MerchantsMangerDao merchantsMangerDao;

	@Autowired
	private LoginMerchantsMapper loginMerchantsMapper;
	
	@Autowired
	private LoginCommomMapper loginCommomMapper;
	@Autowired
	private TUserNewsMapper tuserNewsMapper;

	@Override
	public int updateMerChants(Map<String, Object> map) {
		TpubMerInfo tpubMerInfo = new TpubMerInfo();
		tpubMerInfo.setMerNo(map.get("merNo").toString());
		tpubMerInfo.setMerName(map.get("merName").toString());
		tpubMerInfo.setLegalName(map.get("legalName").toString());
		tpubMerInfo.setMerRegNo(map.get("merRegNo").toString());
		tpubMerInfo.setCompanyType(map.get("companyType").toString());
		tpubMerInfo.setMerAddre(map.get("merAddre").toString());
		tpubMerInfo.setMerContact(map.get("merContact").toString());
		tpubMerInfo.setMerContactPhone(map.get("phone").toString());
		tpubMerInfo.setMerStatus("10025001");
		tpubMerInfo.setUpdateDate(new Date());
		int a = loginMerchantsMapper.updateByPrimaryKeySelective(tpubMerInfo);
		TpubMerInfo info = loginMerchantsMapper.selectByPrimaryKey(tpubMerInfo.getMerNo());
		TUserNews userNews = new TUserNews();
		userNews.setIsRead(Context.READ_FALSE);
		userNews.setNewsDate(new Date());
		userNews.setOrderNo(null);
		userNews.setTableKey(UUIDUtils.getUuid());
		userNews.setUserNo(info.getStaffNo());
		String message = AUDIT_MESSAGE;
		message = message.replace("userType", "商户");
		message = message.replace("userName",tpubMerInfo.getMerName());
		userNews.setNewsTitle(AUDIT_MESSAGE_TITLE);
		userNews.setNewsContent(message);
		userNews.setNewsType(SysCodeConstant.NEW_TYPE_SALE);
		userNews.setUserType(SysCodeConstant.USER_TYPE_SALE);
		tuserNewsMapper.insert(userNews);
		return a;

	}

	@Override
	public String getMerstatus(String merNo) {
		String a = null;
		List<TpubMerInfo> list = new ArrayList<>();
		TpubMerInfo tpubMerInfo = new TpubMerInfo();
		tpubMerInfo.setMerNo(merNo);
		try {
			list = loginMerchantsMapper.select(tpubMerInfo);
			for (TpubMerInfo stu : list) {
				a = stu.getMerStatus();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;

	}

	public Map<String, Object> updateMerChantsInfo(String mId, String dId) {
		Map<String, Object> resultMap = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		// 验证 是否已经绑定
		map = merchantsMangerDao.selectByMIdAndDId(mId, dId);
		// 已经绑定
		if (map != null && map.size() > 0) {
			resultMap.put("code", "0000");
			return resultMap;
		}
		// 未绑定进行绑定操作
		TpubDistributorMer tpubDistributorMer = new TpubDistributorMer();
		tpubDistributorMer.setTableKey(RandomUtil.uuid());
		tpubDistributorMer.setMerNo(mId);
		tpubDistributorMer.setDistributorNo(dId);
		tpubDistributorMer.setInsertDate(new Date());
		merchantsMangerDao.insert(tpubDistributorMer);
		// 绑定成功
		resultMap.put("code", "1111");
		return resultMap;
	}

	@Override
	public TpubMerInfo slelectPersonalInfo(String merNo) {
		TpubMerInfo tpubMerInfo = new TpubMerInfo();
		try {
			tpubMerInfo = loginMerchantsMapper.selectByPrimaryKey(merNo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return tpubMerInfo;
	}

	@Override
	public List<TuserBaseInfo> selectIstrue(String openid) {
		 TuserBaseInfoExample tuserBaseInfoExample = new TuserBaseInfoExample();
				 TuserBaseInfoExample.Criteria info = tuserBaseInfoExample.createCriteria();
				 info.andWxNoEqualTo(openid);
		List<TuserBaseInfo> list= new ArrayList<>();
		   try {
		    	  list = loginCommomMapper.selectByExample(tuserBaseInfoExample);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return list;
	}

	@Override
	public List<Map<String, Object>> MerchantsDealersInfo(String merNo) {
		return merchantsMangerDao.MerchantsDealersInfo(merNo);
	}

	@Override
	public void insertToken(String token,Integer count,String type) {
		 merchantsMangerDao.insertToken(token,count,type);
	}

	@Override
	public void updateToken(Integer count,String type) {
		merchantsMangerDao.updateToken(count,type);
		
	}
	
	@Override
	public Map<String,Object> selectToken(String type) {
	return	merchantsMangerDao.selectToken(type);
	}



}
