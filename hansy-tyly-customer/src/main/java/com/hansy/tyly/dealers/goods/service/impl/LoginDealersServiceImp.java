package com.hansy.tyly.dealers.goods.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hansy.tyly.common.utils.Context;
import com.hansy.tyly.dealers.goods.dao.mapper.LoginDealersMapper;
import com.hansy.tyly.dealers.goods.service.LoginDealersService;
import com.hansy.tyly.merchants.orders.dao.model.TpubDistributorInfo;
import com.hansy.tyly.merchants.orders.dao.model.TpubDistributorInfoExample;
import com.hansy.tyly.merchants.orders.dao.model.TpubMerInfo;
import com.hansy.tyly.merchants.orders.dao.model.TpubMerInfoExample;

@Transactional
@Service
public class LoginDealersServiceImp implements LoginDealersService {
	
  @Autowired
  private LoginDealersMapper loginDealersMapper;
	/*
	 * (non-Javadoc)
	 * @see com.hansy.tyly.merchants.orders.service.LoginDealersService#inserDealersInfo(java.util.Map)
	 */
	@Override
	public void inserDealersInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		 TpubDistributorInfo tpubDistributorInfo = new TpubDistributorInfo();
		  tpubDistributorInfo.setDistributorNo(map.get("userNo").toString());
		  tpubDistributorInfo.setDistributorName(map.get("userName").toString());    
		  tpubDistributorInfo.setInsertDate(new Date());  
		  tpubDistributorInfo.setStaffNo(map.get("saleId").toString());
		  loginDealersMapper.insert(tpubDistributorInfo);
	}
	@Override
	public Map<String, Object>   getMerStus(String merNo) {
		List<TpubDistributorInfo> list = new ArrayList<>();
		TpubDistributorInfoExample tpubDistributorInfoExample = new TpubDistributorInfoExample();
		Map<String, Object> map = new HashMap<>();
		String stus = null;
		String addr = null;
		try {
			TpubDistributorInfoExample.Criteria info = tpubDistributorInfoExample.createCriteria();
			  info.andDistributorNoEqualTo(merNo);
		list= loginDealersMapper.selectByExample(tpubDistributorInfoExample);
		for(TpubDistributorInfo staus :list){
			stus = staus.getDistributorStatus();
			addr = staus.getDistributorAddre();
			if(addr == null){
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
	
} 
