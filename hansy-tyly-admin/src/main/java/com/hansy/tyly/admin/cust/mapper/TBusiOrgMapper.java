package com.hansy.tyly.admin.cust.mapper;

import com.hansy.tyly.admin.dao.model.TBusiOrg;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface TBusiOrgMapper extends Mapper<TBusiOrg> {

    TBusiOrg queryTBusiOrgByOrgName(String orgName);

    int queryCountCustOnOrg(String orgId);

    List<Map<String,Object>> queryBusiOrgByCondi(Map<String,Object> map);

    int getCountBind(Map<String,Object> map);
}