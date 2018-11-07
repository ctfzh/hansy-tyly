package com.hansy.tyly.admin.dao.mapper;

import com.hansy.tyly.admin.dao.model.OrderInfos;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderInfosMapper {
    List<OrderInfos> getOrdersListByParams(Map<String, Object> params)throws Exception;

    List<OrderInfos> getRefundListByParams(Map<String, Object> params)throws Exception;

    List<OrderInfos> getRefundInfoByKey(@Param("tableKey") Integer tableKey)throws Exception;

    List<Map<String,Object>> getOrdersDetailListByParams(Map<String, Object> map);

    List<Map<String,Object>> getRefundGoodsByParams(Map<String, Object> map);

    Map<String,Object> getChangeDate(Map<String, Object> map);

    List<Map<String,Object>> getUnRefundGoodsByParams(Map<String, Object> map);
}
