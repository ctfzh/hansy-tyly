package com.lemoncome.watchdog.riskbatch.model;

import java.io.Serializable;

public class A7DataNode implements Serializable{
	private static final long serialVersionUID = 1L;
	private String checkResult;
	private Object scoreMap;
	private String creditScore;
	private String appKey;
	private String orderId;
	private String[] riskName;
	public String getCheckResult() {
		return checkResult;
	}
	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}
	public Object getScoreMap() {
		return scoreMap;
	}
	public void setScoreMap(Object scoreMap) {
		this.scoreMap = scoreMap;
	}
	public String getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(String creditScore) {
		this.creditScore = creditScore;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String[] getRiskName() {
		return riskName;
	}
	public void setRiskName(String[] riskName) {
		this.riskName = riskName;
	}
	
}
