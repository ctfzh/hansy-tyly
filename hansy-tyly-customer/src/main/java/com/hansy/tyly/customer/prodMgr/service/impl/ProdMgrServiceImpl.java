package com.hansy.tyly.customer.prodMgr.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hansy.tyly.customer.prodMgr.mapper.TROrgProdMapper;
import com.hansy.tyly.customer.prodMgr.service.ProdMgrService;

@Service
public class ProdMgrServiceImpl implements ProdMgrService {
	
	@Autowired
	private TROrgProdMapper orgProdMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(ProdMgrServiceImpl.class);

	@Override
	public List<Map<String, String>> queryProdsByOrgId(Map<String, Object> inMap) {
		
		List<Map<String, String>> prodsList = orgProdMapper.queryProdsByOrgId(inMap);
		return prodsList;
	}
	
	

}
