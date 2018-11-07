package com.hansy.tyly.admin.system.mapper;

import com.hansy.tyly.admin.dao.model.SysRRoleMenu;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

public interface SysRRoleMenuMapper extends Mapper<SysRRoleMenu> {
    int updateMenuRoleStatus(@Param("roleId") String roleId, @Param("status") String status);
}