package com.hansy.tyly.customer.reportMgr.mapper;

import java.util.List;
import java.util.Map;

import tk.mybatis.mapper.common.Mapper;

import com.hansy.tyly.customer.reportMgr.model.TScCustIndcRst;

public interface ReportDetailMapper extends Mapper<TScCustIndcRst> {
	
	Map<String, String> queryRiskGeneralData(Map<String, Object> inMap);
	
	List<Map<String, String>> queryTop10RiskCustInfo(Map<String, Object> inMap);
	
	List<Map<String, String>> queryMultLoanHitInfo(Map<String, Object> inMap);
	
	List<Map<String, String>> queryBlackHitInfo(Map<String, Object> inMap);
	
}