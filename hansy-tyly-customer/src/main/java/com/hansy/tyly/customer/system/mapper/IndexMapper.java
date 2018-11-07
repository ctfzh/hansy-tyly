package com.hansy.tyly.customer.system.mapper;

import java.util.List;
import java.util.Map;

import com.hansy.tyly.customer.system.model.SysRUserRole;

import tk.mybatis.mapper.common.Mapper;

public interface IndexMapper extends Mapper<SysRUserRole> {
	
	Map<String, String> queryCustMngGeneralData(Map<String, Object> inMap);
	
	List<Map<String, String>> queryLastWeekMngData(Map<String, Object> inMap);
	
	Map<String, String> queryRiskGeneralData(Map<String, Object> inMap);
	
	List<Map<String, String>> queryWarnTrendsData(Map<String, Object> inMap);
}