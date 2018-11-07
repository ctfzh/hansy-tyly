package com.hansy.tyly.customer.prodMgr.mapper;

import java.util.List;
import java.util.Map;

import com.hansy.tyly.customer.prodMgr.model.TROrgProd;

import tk.mybatis.mapper.common.Mapper;

public interface TROrgProdMapper extends Mapper<TROrgProd> {
	
	List<Map<String, String>> queryProdsByOrgId(Map<String, Object> inMap);
}