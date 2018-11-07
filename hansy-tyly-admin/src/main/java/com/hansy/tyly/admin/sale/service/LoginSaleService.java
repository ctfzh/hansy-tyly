package com.hansy.tyly.admin.sale.service;

import java.util.List;
import java.util.Map;

import com.hansy.tyly.admin.dao.model.SysUser;

public interface LoginSaleService {

	 List<SysUser> getSaleUserInfo(String OpenId);

    Map<String,Object> getReportOfVisit(String saleNo, Integer num, String dateType, String date)throws Exception;

    Map<String,Object> getReportOfAchievement(String saleNo, Integer num, String dateType, String date)throws Exception;

    Map<String,Object> getReportOfUserCount(String saleNo,Integer num, String dateType, String date)throws Exception;

    Map<String,Object> getReportOfConsume(String saleNo,Integer num, String dateType, String date)throws Exception;
}
