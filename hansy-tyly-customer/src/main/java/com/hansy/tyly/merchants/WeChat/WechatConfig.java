package com.hansy.tyly.merchants.WeChat;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@Controller
public class WechatConfig {
	/**
	 * ACESS_TOKEN
	 */
	private static String token;
	
	/**
	 * ACESS_TOKEN
	 */
	private static String jxsToken;
	
	/*
	 * 存放二维码
	 */
	private static String VerificationCode;
	public static String getJxsToken() {
		return jxsToken;
	}

	public static void setJxsToken(String jxsToken) {
		WechatConfig.jxsToken = jxsToken;
	}

	/*
	 * 记录调用token次数
	 */
     private static int count;
	
	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		WechatConfig.count = count;
	}

	public static String getVerificationCode() {
		return VerificationCode;
	}

	public static void setVerificationCode(String verificationCode) {
		VerificationCode = verificationCode;
	}

	public static String getToken(){
		return token;
	}

	public static void setToken(String token) {
		WechatConfig.token = token;
	}


}
