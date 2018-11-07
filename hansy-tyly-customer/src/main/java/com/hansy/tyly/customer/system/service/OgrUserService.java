package com.hansy.tyly.customer.system.service;


import java.util.List;
import java.util.Map;

import com.hansy.tyly.admin.base.bean.UserProfile;
import com.hansy.tyly.customer.system.model.SysUser;


public interface OgrUserService {
	
	UserProfile login(SysUser sysUser,String userIp,String msgVerCode);
	
	List<Map<String,String>> selectUserList(Map<String, Object> inMap);
	
	SysUser selectSysUserByLoginId(String userId);
	
	boolean insertSysUser(UserProfile sysUser);
	
	boolean updateSysUser(SysUser sysUser,UserProfile userProFile);
	
	boolean deleteSysUsers(List<SysUser> userList);
	
	List<Map<String,String>> selectCustMangerByUserId(Map<String, Object> inMap);
	
	int updateLoginLog(String sysUuid);
	
	void getSmsMsgCode(String phoneNo,String msgCode);
    
}
