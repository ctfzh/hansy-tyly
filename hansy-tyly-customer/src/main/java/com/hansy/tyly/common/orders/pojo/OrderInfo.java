package com.hansy.tyly.common.orders.pojo;

import java.io.Serializable;
import java.util.List;

import com.hansy.tyly.common.orders.dao.model.TBusOrder;
import com.hansy.tyly.common.orders.dao.model.TBusOrderDetail;
import com.hansy.tyly.common.orders.dao.model.TBusRefund;
import com.hansy.tyly.dealers.goods.dao.model.TGoodsBaseInfo;
import com.hansy.tyly.dealers.goods.dao.model.TGoodsFiles;

public class OrderInfo  implements Serializable {

	//订单信息
	private TBusOrder tBusOrder;
    // 订单详情
    private List<TBusOrderDetail> tBusOrderDetail;
	private List<TGoodsBaseInfo> goodsBaseInfoList;
	private TBusRefund refund;
	private List<TGoodsFiles> filesList;

	public List<TGoodsFiles> getFilesList() {
		return filesList;
	}

	public void setFilesList(List<TGoodsFiles> filesList) {
		this.filesList = filesList;
	}

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
