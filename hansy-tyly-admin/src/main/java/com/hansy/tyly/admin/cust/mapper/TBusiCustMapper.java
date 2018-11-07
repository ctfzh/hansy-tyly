package com.hansy.tyly.admin.cust.mapper;

import com.hansy.tyly.admin.dao.model.TBusiCust;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface TBusiCustMapper extends Mapper<TBusiCust> {

    List<Map<String,Object>> queryCustDetailByCondtion(Map<String, Object> map);

}