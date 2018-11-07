package com.hansy.tyly.admin.prod.mapper;

import com.hansy.tyly.admin.dao.model.TBusiCustProd;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TBusiCustProdMapper extends Mapper<TBusiCustProd> {
    List<String> queryCustIdsByProdId(String prodId);
}