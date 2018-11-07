package com.hansy.tyly.admin.system.mapper;

import com.hansy.tyly.admin.dao.model.SysMenu;
import com.hansy.tyly.admin.dao.model.SysRole;
import com.hansy.tyly.admin.dao.model.SysUser;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysUserMapper extends Mapper<SysUser>{

    List<SysRole> queryUserRolesByUserId(@Param("userId") String userId, @Param("roleType") String roleType, @Param
            ("status") String status);

    List<SysMenu> queryUserMenusByUserId(@Param("userId") String userId, @Param("menuType") String menuType, @Param
            ("status") String status);

    SysUser querySysUserByUserName(@Param("userName") String userName, @Param("userType") String userType, @Param
            ("status") String status);

    List<Map<String,Object>> queryUsersAndRole(Map<String, Object> map);

    Map<String,Object> queryRoleById(String userId);
    
    Map<String,Object> queryOrgById(String userId);

    String queryRoleIds(String userId);

    Object queryLastLogin(String userId);
    
    void updateSaleId(@Param("loginId") String LoginId,@Param("openId") String openId);

}