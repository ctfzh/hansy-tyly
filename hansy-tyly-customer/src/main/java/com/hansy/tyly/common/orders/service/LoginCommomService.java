
package com.hansy.tyly.common.orders.service;

import java.util.List;
import java.util.Map;

import com.hansy.tyly.admin.dao.model.SysCodeInfo;
import com.hansy.tyly.merchants.orders.dao.model.TuserBaseInfo;

public interface LoginCommomService {
	void insertUserInfo(Map<String, Object> map);

	/** 用户登录 */
	Map<String, Object> login(String username, String password, String openId, String userType);
	
	/**
	 * 经销商平台登录
	 */
	Map<String, Object> loginPlatform(String username, String password,String inputcode,String code);


	// 判断用户是否注册
	Map<String, Object> isRegister(String openId);
	/**
	 * 修改密码
	 * @param 用户编号
	 * @param 旧密码
	 * @param 新密码
	 */
	Map<String, Object> alertPassword(String userNo,String oldPwd,String newPwd);
	/*
	 * 企业用户状态
	 */
    List<SysCodeInfo> getEnterpriseType();
    
    /*
     * 查询商户销售
     */
     List<Map<String, Object>> getmeriPHONE(String  merId);
     
     /*
      * 查询经销商销售
      */
     List<Map<String, Object>> getdealeriPHONE(String  dealerId);
     /*
      * 验证用户
      */
     Map<String, Object> isRegisterOrTrue(String username);
     
 	void updateUserInfo(String openId);


     
	
}
