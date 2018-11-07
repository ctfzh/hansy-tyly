package com.hansy.tyly.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.hansy.tyly.core.crypto.CryptoHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返回对象
 * @author qiub
 *
 */
public class Respones {

	private String code;//返回code
	private String url;//请求地址
	private Date date = new Date();
	private String message;  //响应消息
	private boolean result;//接口状态
	private Map<String,Object> page;  //响应消息
    private Object req;  //响应中数据


	public Map<String, Object> getPage() {
		return page;
	}

	public void setPage(Map<String, Object> page) {
		this.page = page;
	}

	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getDate() {
		return date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getReq() {
		return req;
	}

	/**
	 * 设置出参
	 * 默认key value
	 *
	 * @param value 出参值
	 */
	public void setReq(Object value) {
		if (value instanceof Page) {
			Page page = (Page) value;
			long totalSize = page.getTotal();
			int pageSize = page.getPageSize();
			if (pageSize == 0) pageSize = 1;
			long pages = totalSize / pageSize +
					(totalSize % pageSize == 0 ? 0 : 1);
			Map<String,Object> map=new HashMap<>();
			map.put("pageNo", page.getPageNum());
			map.put("pageSize", page.getPageSize());
			map.put("totalPage", pages);
			map.put("totalCount", totalSize);
			setPage(map);
			this.req=page.getResult();

			return;
		}else{
			this.req=value;
		}
	}





}
