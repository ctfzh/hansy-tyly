package com.hansy.tyly.customer.system.mapper;

import java.util.List;

import com.hansy.tyly.customer.system.model.SysMenu;

import tk.mybatis.mapper.common.Mapper;

public interface SysMenuMapper extends Mapper<SysMenu> {
	
	/**
     * 根据状态查询所有菜单列表(默认查有效状态)
     */
    List<SysMenu> selectMenusByUserId(String userId);
}