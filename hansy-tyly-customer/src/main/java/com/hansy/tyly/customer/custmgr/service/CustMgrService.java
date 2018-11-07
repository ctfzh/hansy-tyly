package com.hansy.tyly.customer.custmgr.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hansy.tyly.admin.base.bean.UserProfile;
import com.hansy.tyly.admin.dao.model.TBusiCust;
import com.hansy.tyly.customer.custmgr.model.TBusiCustProd;
import com.hansy.tyly.customer.system.model.SysUser;


public interface CustMgrService {
	
	List<Map<String,String>> selectCustList(Map<String, Object> inMap);
	
	SysUser selectSysUserByLoginId(String userId);
	
	boolean insertCustInfo(TBusiCust custInfo);
	
	boolean updateCustInfo(TBusiCust custInfo);
	
	boolean deleteCusts(List<TBusiCust> custList);
	
	List<Map<String,String>> queryCustListByUserId(Map<String, Object> inMap);
	
	void saveCustTransferinfo(Map<String, Object> inMap);
	
	int insertCustInfoFormExcel(List<HashMap<String, Object>> excelList,UserProfile userProfile);
	
	void saveCustAndProds(List<TBusiCustProd> list,HashMap<String, String> tempMap,UserProfile userProfile);
	
	void cancelCustProds(List<TBusiCustProd> list,UserProfile userProfile);
	
	List<Map<String,String>> queryCustMngList(Map<String, Object> inMap);
    
}
