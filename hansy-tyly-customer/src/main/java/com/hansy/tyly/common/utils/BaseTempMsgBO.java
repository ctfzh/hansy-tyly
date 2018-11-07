package com.hansy.tyly.common.utils;

import java.util.Map;

/**
 * 
 * @Author xiafei
 * @ClassName: BaseTempMsgBO 
 * @Description: TODO(模板消息基础类)  
 *
 */
public class BaseTempMsgBO {
	private String touser;//消息通知对象 (必填)
	private String template_id;//模板消息编号(必填)
	private String url;//模板消息跳转URL
	//private String miniprogram;//跳小程序所需数据，不需跳小程序可不用传该数据
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Map<String, TempData> getData() {
		return data;
	}
	public void setData(Map<String, TempData> data) {
		this.data = data;
	}
}
