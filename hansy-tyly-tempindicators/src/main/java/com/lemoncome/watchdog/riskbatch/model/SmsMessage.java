package com.lemoncome.watchdog.riskbatch.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SmsMessage implements Serializable{

	private String sysUuid;
	private String msgCfgId;
	private String orgId;
	private String userTel;
	private String orgName;
	private String accAmt;
	private String msgContent;
	private String overDraftAmt = "0.00";
	
	public String getSysUuid() {
		return sysUuid;
	}
	public void setSysUuid(String sysUuid) {
		this.sysUuid = sysUuid;
	}
	public String getMsgCfgId() {
		return msgCfgId;
	}
	public void setMsgCfgId(String msgCfgId) {
		this.msgCfgId = msgCfgId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getAccAmt() {
		return accAmt;
	}
	public void setAccAmt(String accAmt) {
		this.accAmt = accAmt;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public String getOverDraftAmt() {
		return overDraftAmt;
	}
	public void setOverDraftAmt(String overDraftAmt) {
		this.overDraftAmt = overDraftAmt;
	}
	
}
