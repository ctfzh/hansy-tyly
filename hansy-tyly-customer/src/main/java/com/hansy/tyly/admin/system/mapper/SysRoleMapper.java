package com.hansy.tyly.admin.system.mapper;

import com.hansy.tyly.admin.dao.model.SysRole;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysRoleMapper extends Mapper<SysRole> {
    List<SysRole> queryRoles(Map<String, Object> map);
    List<String> queryRoleHasMenuId(@Param("roleId") String roleId, @Param("status") String status);
}