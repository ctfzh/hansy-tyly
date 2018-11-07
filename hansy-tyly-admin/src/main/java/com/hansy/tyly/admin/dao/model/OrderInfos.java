package com.hansy.tyly.admin.dao.model;

import java.util.List;

public class OrderInfos {
    private TBusOrder order;
    private List<TBusOrderDetail> orderDetailList;
    private List<TGoodsBaseInfo> goodsBaseInfoList;
    private TBusRefund refund;


    public OrderInfos() {
    }

    public TBusOrder getOrder() {
        return order;
    }

    public void setOrder(TBusOrder order) {
        this.order = order;
    }

    public List<TBusOrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<TBusOrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public List<TGoodsBaseInfo> getGoodsBaseInfoList() {
        return goodsBaseInfoList;
    }

    public void setGoodsBaseInfoList(List<TGoodsBaseInfo> goodsBaseInfoList) {
        this.goodsBaseInfoList = goodsBaseInfoList;
    }

    public TBusRefund getRefund() {
        return refund;
    }

    public void setRefund(TBusRefund refund) {
        this.refund = refund;
    }
}
