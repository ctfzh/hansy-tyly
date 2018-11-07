package com.hansy.tyly.customer.billMgr.mapper;

import java.util.List;
import java.util.Map;

import tk.mybatis.mapper.common.Mapper;

import com.hansy.tyly.customer.billMgr.model.TBusiBill;

public interface TBusiBillMapper extends Mapper<TBusiBill> {
	
	TBusiBill queryCostAmt(Map<String, Object> inMap);
	
	List<Map<String, String>> queryBillList(Map<String, Object> inMap);
}