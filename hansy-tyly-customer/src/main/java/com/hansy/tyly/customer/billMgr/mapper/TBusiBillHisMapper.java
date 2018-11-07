package com.hansy.tyly.customer.billMgr.mapper;

import java.util.List;
import java.util.Map;

import tk.mybatis.mapper.common.Mapper;

import com.hansy.tyly.customer.billMgr.model.TBusiBill;
import com.hansy.tyly.customer.billMgr.model.TBusiBillHis;

public interface TBusiBillHisMapper extends Mapper<TBusiBillHis> {
	
	TBusiBill queryCostAmt(Map<String, Object> inMap);
	
	List<Map<String, String>> queryBillList(Map<String, Object> inMap);
}