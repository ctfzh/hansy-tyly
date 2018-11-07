package com.hansy.tyly.customer.system.service;


import java.util.List;
import java.util.Map;

import com.hansy.tyly.admin.base.bean.UserProfile;


public interface IndexService {
	
	//查询特定机构或者特定机构特定客户经理名下当前总体正常管理状态下的客户汇总数据
	Map<String, String> queryCustMngGeneralData(UserProfile userProfile);
 
	//查询过去一周内客户管理及更新数据记录列表
	List<Map<String, String>> queryLastWeekMngData(UserProfile userProfile);
	
	//获取当前最新风险概况统计情况 
	Map<String, String> queryRiskGeneralData(UserProfile userProfile);
	
	//获取历史预警趋势统计情况
	List<Map<String, String>> queryWarnTrendsData(UserProfile userProfile);
}
