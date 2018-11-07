package com.hansy.tyly.admin.settlement.dao.mapper;

import com.hansy.tyly.admin.settlement.dao.model.TFinaDistributorSett;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface TFinaDistributorSettMapper extends Mapper<TFinaDistributorSett> {

    List<TFinaDistributorSett> listSettlements(Map<String, Object> map);

    int insertBacth(@Param("distributorSetts") List<TFinaDistributorSett> distributorSetts);

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