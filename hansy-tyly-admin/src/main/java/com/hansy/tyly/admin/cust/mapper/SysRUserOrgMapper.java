package com.hansy.tyly.admin.cust.mapper;

import com.hansy.tyly.admin.dao.model.SysRUserOrg;

import tk.mybatis.mapper.common.Mapper;

public interface SysRUserOrgMapper extends Mapper<SysRUserOrg> {

    int querySumOrgRelCusts(String orgId);

    String queryParam(String paraId);
}