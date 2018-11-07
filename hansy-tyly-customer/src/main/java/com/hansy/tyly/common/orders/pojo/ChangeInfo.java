package com.hansy.tyly.common.orders.pojo;

import java.io.Serializable;

import com.hansy.tyly.common.orders.dao.model.TBusOrderChange;

public class ChangeInfo implements Serializable{

	//订单交易变动
	private TBusOrderChange tBusOrderChange;

	public TBusOrderChange gettBusOrderChange() {
		return tBusOrderChange;
	}

	public void settBusOrderChange(TBusOrderChange tBusOrderChange) {
		this.tBusOrderChange = tBusOrderChange;
	}
	
}
