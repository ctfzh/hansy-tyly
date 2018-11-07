package com.hansy.tyly.dealers.settlement.dao.mapper;

import com.hansy.tyly.dealers.settlement.dao.model.TFinaDistributorSett;

import java.util.List;
import java.util.Map;

import tk.mybatis.mapper.common.Mapper;

public interface TFinaDistributorSettMapper extends Mapper<TFinaDistributorSett> {

    /**
     * 查看经销商下所有的结算
     */
    List<Map<String, Object>> getAll(Map<String, Object> params);

    /**
     * 根据条件查询经销商下的所有结算信息
     */
    List<Map<String, Object>> searchAll(Map<String, Object> params);

    /**
     * 查询经销商的所有结算的详细信息
     */
    List<Map<String, Object>> getAllDetail(Map<String, Object> params);

    /**
     * 根据条件查询经销商下的所有详细信息
     */
    List<Map<String, Object>> searchDetail(Map<String, Object> params);
}