package com.hansy.tyly.sale.personal.service;

import com.hansy.tyly.customer.system.model.SysUser;

import java.util.Map;

public interface LoginSaleService {
       
	 /**
	  * 查看销售基本信息
	  * @return
	  */
    SysUser getSysuserInfo(String userId);

    //Map<String,Object> getReport(String saleNo, Integer num, String dateType, String date);

	Map<String,Object> getReportOfVisit(String saleNo, Integer num, String dateType, String date)throws Exception;

	Map<String,Object> getReportOfAchievement(String saleNo, Integer num, String dateType, String date)throws Exception;

	Map<String,Object> getReportOfUserCount(Integer num, String dateType, String date)throws Exception;

	Map<String,Object> getReportOfConsume(Integer num, String dateType, String date)throws Exception;
}
