package com.hansy.tyly.customer.reportMgr.mapper;

import java.util.List;
import java.util.Map;

import tk.mybatis.mapper.common.Mapper;

import com.hansy.tyly.customer.reportMgr.model.TScCustIndcRst;

public interface TScCustIndcRstMapper extends Mapper<TScCustIndcRst> {
	
	List<Map<String, String>> queryIndcInfoByBillId(Map<String, Object> inMap);
	
	List<Map<String, String>> queryRiskStatistics(Map<String, Object> inMap);
	
	List<Map<String, String>> queryRiskDetailList(Map<String, Object> inMap);
	
	List<Map<String, String>> queryRiskList(Map<String, Object> inMap);

    List<Map<String, String>> queryCustRiskReportList(Map<String, Object> inMap);
}