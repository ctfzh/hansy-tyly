package com.hansy.tyly.admin.prod.mapper;

import com.hansy.tyly.admin.dao.model.TBusiProd;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface TBusiProdMapper extends Mapper<TBusiProd> {

    List<Map<String,Object>> getList(Map<String,Object> map);

    List<Map<String,Object>> getIndicatorOnProd(Map<String, Object> map);

    List<Map<String,Object>> getAllIndicator(Map<String, Object> map);

    List<Map<String,Object>> getAllIndicatorTree();

    List<Map<String, Object>> getIndiList(Map<String,Object> map);

    List<Map<String, Object>> queryOrgNoProdList(Map<String, Object> map);

    List<Map<String, Object>> queryOrgYesProdList(Map<String, Object> map);

}