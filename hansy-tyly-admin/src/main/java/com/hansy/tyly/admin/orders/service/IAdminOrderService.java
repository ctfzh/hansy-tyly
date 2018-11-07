package com.hansy.tyly.admin.orders.service;

import com.hansy.tyly.admin.dao.model.OrderInfos;
import com.hansy.tyly.admin.dao.model.TBusOrder;

import java.util.List;
import java.util.Map;

public interface IAdminOrderService{

    List<OrderInfos> getOrdersList(Map<String, Object> params)throws Exception;

    void processOrders(String orderNo, String changeStatus)throws Exception;

    List<OrderInfos> getRefundOrders(Map<String, Object> params)throws Exception;

    List<OrderInfos> getRefundOrderInfo(Integer tableKey)throws Exception;

    void approveRefund(Integer tableKey, String apprStatus)throws Exception;

    Map<String,Object> getOrderDetailInfo(String orderNo)throws Exception;

    void editOrder(TBusOrder orderInfo)throws Exception;

    List<Map<String,Object>> doOrderList(List<OrderInfos> list)throws Exception;
    public void insertOrderNews(String orderNo,String userNo,String userType,String content,String title) throws Exception;
}