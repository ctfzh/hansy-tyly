package com.hansy.tyly.core.shiro;

/**
 * @author hemf
 * shiro chain bean
 */
public class ShiroChainBean {
	/**
	 * 请求地址
	 */
	private String url;
	private String codes;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCodes() {
		return codes;
	}
	public void setCodes(String codes) {
		this.codes = codes;
	}
	
}
