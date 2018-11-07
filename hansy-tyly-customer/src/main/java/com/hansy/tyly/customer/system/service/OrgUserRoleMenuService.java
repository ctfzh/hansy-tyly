package com.hansy.tyly.customer.system.service;

import java.util.List;

import com.hansy.tyly.customer.system.model.SysMenu;


public interface OrgUserRoleMenuService {
	
	/**
	 * @Author:YuanYan
	 * @CreateAt:2017年10月24日上午10:35:13
	 * @Params:
	 * @Return:
	 * @Description:根据用户编号获取对应菜单列表
	 */
	List<SysMenu> queryMenusByUserId(String userId);

}
