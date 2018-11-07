package com.hansy.tyly.merchants.orders.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hansy.tyly.common.utils.Context;
import com.hansy.tyly.merchants.orders.dao.mapper.LoginMerchantsMapper;
import com.hansy.tyly.merchants.orders.dao.model.TpubMerInfo;
import com.hansy.tyly.merchants.orders.dao.model.TpubMerInfoExample;
import com.hansy.tyly.merchants.orders.dao.model.TuserBaseInfoExample;
import com.hansy.tyly.merchants.orders.service.LoginMerchantsService;

@Transactional
@Service
public class LoginMerchantsServiceImp implements LoginMerchantsService  {

	@Autowired
	  private LoginMerchantsMapper loginMerchantsMapper;
	@Override
	public void insertMerchantsInfo(Map<String, Object> map) {
		 TpubMerInfo tpubMerInfo =new TpubMerInfo();
		 tpubMerInfo.setMerNo(map.get("userNo").toString());
		 tpubMerInfo.setMerName(map.get("userName").toString()); 
		 tpubMerInfo.setStaffNo(map.get("saleId").toString());
		 tpubMerInfo.setInsertDate(new Date());
	     loginMerchantsMapper.insert(tpubMerInfo);
		
		 
	}


	@Override
	public Map getMerStus(String merNo) {
		List<TpubMerInfo> list = new ArrayList<>();
		TpubMerInfoExample tpubMerInfoExample = new TpubMerInfoExample();
		Map<String, Object> map = new HashMap<>();
		String stus = null;
		String addr = null;
		try {
			TpubMerInfoExample.Criteria info = tpubMerInfoExample.createCriteria();
			     info.andMerNoEqualTo(merNo);
		list= loginMerchantsMapper.selectByExample(tpubMerInfoExample);
		for(TpubMerInfo staus :list){
			stus = staus.getMerStatus();
			addr = staus.getMerAddre();
			if(StringUtils.isBlank(addr)){
				addr = "1";
			}
		}
		map.put("stus", stus);
		map.put("addr", addr);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return map;
	}


	@Override
	public void updateUserImge(String headimgurl,String userNo) {
		loginMerchantsMapper.updateUserImge(headimgurl,userNo);
	}
 
}
