package com.hansy.tyly.admin.prod.mapper;

import com.hansy.tyly.admin.dao.model.TROrgProd;

import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

public interface TROrgProdMapper extends Mapper<TROrgProd> {
    int deletRProdOrg(Map<String,Object> map);
    int insertRPordOrg(Map<String,Object> map);

}