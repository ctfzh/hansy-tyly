package com.hansy.tyly.common.orders.service;
/**
 * 
* @ClassName: LoginLogService  
* @Description: TODO(登录日志)  
* @author ZCX  
* @date 2018年8月3日  
*
 */


import com.hansy.tyly.customer.system.model.SysLoginLog;

public interface LoginLogService {
	/**
	 * 插入登录日志
	 */
	void insertLoginLog(SysLoginLog loginLog);
}
