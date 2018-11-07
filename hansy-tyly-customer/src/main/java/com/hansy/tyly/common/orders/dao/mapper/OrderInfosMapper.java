package com.hansy.tyly.common.orders.dao.mapper;


import com.hansy.tyly.common.orders.dao.model.RefundListMessage;
import com.hansy.tyly.common.orders.pojo.OrderDetails;
import com.hansy.tyly.common.orders.pojo.OrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderInfosMapper {
    List<OrderInfo> getOrdersListByParams(Map<String, Object> params)throws Exception;

    List<RefundListMessage> getRefundListByParams(Map<String, Object> params)throws Exception;

    OrderInfo getRefundInfoByKey(@Param("tableKey") Integer tableKey)throws Exception;

    OrderInfo getOrdersInfoByNo(@Param("orderNo")String orderNo)throws Exception;


    List<Map<String,Object>> getOrdersDetailListByParams(Map<String, Object> map);

    List<Map<String,Object>> getRefundGoodsByParams(Map<String, Object> map);

    Map<String,Object> getChangeDate(Map<String, Object> map);

    List<Map<String,Object>> getUnRefundGoodsByParams(Map<String, Object> map);
}
