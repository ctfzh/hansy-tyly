package com.hansy.tyly.tempindicators.model;

import java.io.Serializable;

public class CustTempindicatorsModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String prodId;
	private String parentIndcId;
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getParentIndcId() {
		return parentIndcId;
	}
	public void setParentIndcId(String parentIndcId) {
		this.parentIndcId = parentIndcId;
	}

}
