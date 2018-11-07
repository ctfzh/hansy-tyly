package com.hansy.tyly.common.orders.dao.model;

import com.hansy.tyly.dealers.goods.dao.model.TGoodsBaseInfo;

import java.io.Serializable;
import java.util.List;

public class OrderInfo implements Serializable {

	//订单信息
	private TBusOrder tBusOrder;
    // 订单详情
    private List<TBusOrderDetail> tBusOrderDetail;
	private List<TGoodsBaseInfo> goodsBaseInfoList;
	private TBusRefund refund;

	public OrderInfo() {
	}

	public TBusOrder gettBusOrder() {
		return tBusOrder;
	}

	public void settBusOrder(TBusOrder tBusOrder) {
		this.tBusOrder = tBusOrder;
	}

	public List<TBusOrderDetail> gettBusOrderDetail() {
		return tBusOrderDetail;
	}

	public void settBusOrderDetail(List<TBusOrderDetail> tBusOrderDetail) {
		this.tBusOrderDetail = tBusOrderDetail;
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
