package com.hansy.tyly.customer.prodMgr.service;

import java.util.List;
import java.util.Map;

public interface ProdMgrService {
	
	List<Map<String, String>> queryProdsByOrgId(Map<String, Object> inMap);

}
