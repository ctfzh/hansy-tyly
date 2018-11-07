package com.hansy.tyly.admin.dao.mapper;

import com.hansy.tyly.admin.dao.model.SysUser;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysUserMapper extends Mapper<SysUser> {

    List<SysUser> listByRoles(Map<String, Object> params);

    List<SysUser> listByRolesAndOrgId(Map<String, Object> params);

    SysUser selectByLoginId(String loginId);
    /**
     * 根据登录ID获取机构Id
     *
     * @param loginId
     * @return
     */
    List<String> selectOrgIdByLoginId(String loginId);
}