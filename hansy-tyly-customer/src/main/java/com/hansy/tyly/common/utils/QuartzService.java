package com.hansy.tyly.common.utils;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hansy.tyly.admin.system.service.SysUserRoleMenuService;
import com.hansy.tyly.common.enumClass.CodeEnum;
import com.hansy.tyly.common.enumClass.CodeNameEnum;
import com.hansy.tyly.common.orders.dao.mapper.TBusOrderMapper;
import com.hansy.tyly.merchants.WeChat.WeChat;
import com.hansy.tyly.merchants.WeChat.WechatConfig;

/**
 * 订单自动收货定时任务
 */
@Component
public class QuartzService {
	@Autowired
	private TBusOrderMapper tbusOrderMapper;
	@Autowired
	public SysUserRoleMenuService sysUserRoleMenuService;
	
	//@Scheduled(cron = "*/5 * * * * ?") //测试每隔5秒执行一次
/*	@Scheduled(cron = "0 0 1 * * ?") //每天凌晨1点执行
	public void reveiceTimer() {
		String receivingDate = sysUserRoleMenuService.getCodeValueByName(CodeNameEnum.receiveDate.getName(), CodeEnum.receivingDate.getCode());
		tbusOrderMapper.getScheduleTimer(receivingDate);
		System.out.println("begin...");
	}*/
	
	@Resource
	private WeChat weChat;
	/*@Scheduled(fixedRate = 1000*60*60)//每小时执行一次
    private void test(){
	String  accessToken = weChat.getToken();
	WechatConfig.setToken(accessToken);
    System.out.println("-------------------------   xiafei   _________________________"+accessToken);
    } */
}
