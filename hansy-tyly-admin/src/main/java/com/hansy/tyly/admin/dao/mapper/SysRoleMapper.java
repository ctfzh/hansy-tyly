package com.hansy.tyly.admin.dao.mapper;

import com.hansy.tyly.admin.dao.model.SysRole;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysRoleMapper extends Mapper<SysRole> {

    List<String> selectIdByUserLoginId(String loginId);

}