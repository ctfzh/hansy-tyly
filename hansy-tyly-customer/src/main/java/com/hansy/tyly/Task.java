package com.hansy.tyly;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.hansy.tyly.merchants.WeChat.WeChat;
import com.hansy.tyly.merchants.WeChat.WechatConfig;

@Component
public class Task {
	@Resource
	private WeChat weChat;
	
	/*@Scheduled(fixedRate = 1000*60*60)*///每小时执行一次
	    private void test(){
		String  accessToken = weChat.getToken();
		String  Token = weChat.getTokenXC();
		WechatConfig.setJxsToken(Token);
		WechatConfig.setToken(accessToken);
	    System.out.println("-------------------------   xiafei   _________________________"+accessToken);
	    } 

	  
}
