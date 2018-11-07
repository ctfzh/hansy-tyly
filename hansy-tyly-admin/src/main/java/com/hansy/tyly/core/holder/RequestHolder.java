package com.hansy.tyly.core.holder;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RequestHolder {
	
	public static HttpServletRequest getHttpServletRequest() {
		 return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	
	public static String getCurrentRequestIpAddress(){
	
		HttpServletRequest request = getHttpServletRequest();
		
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("PRoxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0 || LOCALHOST_IP_ADRESS_1.equals(ip)) {
			ip = LOCALHOST_IP_ADRESS_2;
		}
		
		return ip;
	}

	public static HttpSession getHttpSession() {
		return getHttpServletRequest().getSession();
	}
	
	
	private static final String LOCALHOST_IP_ADRESS_1 = "0:0:0:0:0:0:0:1";
	private static final String LOCALHOST_IP_ADRESS_2 = "127.0.0.1";
}
