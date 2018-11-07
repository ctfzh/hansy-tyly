package com.hansy.tyly.tempindicators.model;

import java.io.Serializable;

public class CustBillModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String billId;
	private String prodId;
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

}
