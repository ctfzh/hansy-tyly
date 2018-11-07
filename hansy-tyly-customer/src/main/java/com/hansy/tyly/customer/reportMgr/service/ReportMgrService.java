package com.hansy.tyly.customer.reportMgr.service;

import java.util.List;
import java.util.Map;

public interface ReportMgrService {
	
	List<Map<String, String>> queryIndcInfoByBillId(Map<String, Object> inMap);
	
	List<Map<String, String>> queryRiskStatistics(Map<String, Object> inMap);
	
	List<Map<String, String>> queryRiskDetailList(Map<String, Object> inMap);
	
	List<Map<String, String>> queryRiskList(Map<String, Object> inMap);
	
	Map<String, Object> queryReportDeatil(Map<String, Object> inMap);

    List<Map<String, String>> queryCustRiskReportList(Map<String, Object> inMap);

}
