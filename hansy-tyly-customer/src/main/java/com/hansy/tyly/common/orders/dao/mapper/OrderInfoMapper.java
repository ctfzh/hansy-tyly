package com.hansy.tyly.common.orders.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hansy.tyly.common.orders.pojo.OrderInfo;


public interface OrderInfoMapper {

    List<OrderInfo> getOrdersListByParams(Map<String, Object> params)throws Exception;

    List<OrderInfo> getRefundListByParams(Map<String, Object> params)throws Exception;

    OrderInfo getRefundInfoByKey(@Param("tableKey") Integer tableKey)throws Exception;
}
