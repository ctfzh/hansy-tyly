package com.hansy.tyly.dealers.goods.service;

import java.util.Map;

public interface LoginDealersService  {
	 void inserDealersInfo(Map<String,Object> map);

	 /**
	  * 用户已注册密码 但无具体信息
	  */
	 Map<String, Object>  getMerStus(String merNo);

}
