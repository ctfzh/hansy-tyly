package com.hansy.tyly.customer.billMgr.service;

import java.util.List;
import java.util.Map;

public interface BillMgrService {
	
	Map<String, Object> queryBillInfo(Map<String, Object> inMap);
	
	
	List<Map<String, String>> queryBillList(Map<String, Object> inMap);
	

}
