package com.hansy.tyly.customer.custmgr.mapper;

import java.util.List;
import java.util.Map;

import tk.mybatis.mapper.common.Mapper;

import com.hansy.tyly.admin.dao.model.TBusiCust;

public interface TBusiCustMapper extends Mapper<TBusiCust> {
	
	List<Map<String,String>> queryCustList(Map<String, Object> inMap);
	
	List<Map<String,String>> queryCustListByUserId(Map<String, Object> inMap);
	
	List<Map<String,String>> queryCustMngList(Map<String, Object> inMap);
}