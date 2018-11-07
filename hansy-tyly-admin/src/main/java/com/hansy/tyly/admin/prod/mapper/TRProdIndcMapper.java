package com.hansy.tyly.admin.prod.mapper;

import com.hansy.tyly.admin.dao.model.TRProdIndc;

import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

public interface TRProdIndcMapper extends Mapper<TRProdIndc> {

    int deletRProdIndc(Map<String,Object> map);
}