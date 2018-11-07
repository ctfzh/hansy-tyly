package com.hansy.tyly.tempindicators.model;

import java.io.Serializable;
import java.util.Date;

public class CustIndcRstModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sysUuid;
	private String billId;
	private String indcId;
	private String indcValue;
	private Date insertTime;
	private String insertUserId;
	public String getSysUuid() {
		return sysUuid;
	}
	public void setSysUuid(String sysUuid) {
		this.sysUuid = sysUuid;
	}
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}
	public String getIndcId() {
		return indcId;
	}
	public void setIndcId(String indcId) {
		this.indcId = indcId;
	}
	public String getIndcValue() {
		return indcValue;
	}
	public void setIndcValue(String indcValue) {
		this.indcValue = indcValue;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	public String getInsertUserId() {
		return insertUserId;
	}
	public void setInsertUserId(String insertUserId) {
		this.insertUserId = insertUserId;
	}
	

}
