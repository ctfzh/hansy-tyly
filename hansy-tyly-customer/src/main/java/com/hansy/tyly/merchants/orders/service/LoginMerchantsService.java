package com.hansy.tyly.merchants.orders.service;

import java.util.Map;


public interface LoginMerchantsService  {
	
	 void insertMerchantsInfo(Map<String,Object> map);
	 
	 /**
	  * 用户已注册密码 但无具体信息
	  */
	 Map<String, Object>  getMerStus(String merNo);
	/**
	 * 修改用户微信头像
	 */
	void updateUserImge(String headimgurl,String userNo);
}
