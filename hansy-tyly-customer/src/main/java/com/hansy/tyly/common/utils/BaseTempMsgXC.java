package com.hansy.tyly.common.utils;

import java.util.Map;

/**
 * 
 * @Author xiafei
 * @ClassName: BaseTempMsgBO 
 * @Description: TODO(小程序)
 *
 */
public class BaseTempMsgXC {
	private String touser;//消息通知对象 (必填)
	private String template_id;//模板消息编号(必填)
	private String page;//url
	private String form_id;//表单ID
	private Map<String,TempData> data;//模板消息内容(必填)
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getForm_id() {
		return form_id;
	}
	public void setForm_id(String form_id) {
		this.form_id = form_id;
	}
	public Map<String, TempData> getData() {
		return data;
	}
	public void setData(Map<String, TempData> data) {
		this.data = data;
	}
	
}
