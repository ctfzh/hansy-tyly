package com.hansy.tyly.common.orders.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hansy.tyly.common.orders.service.LoginLogService;
import com.hansy.tyly.customer.system.mapper.SysLoginLogMapper;
import com.hansy.tyly.customer.system.model.SysLoginLog;
import com.hansy.tyly.customer.utils.RandomUtil;
@Service
public class LoginLogServiceImpl implements LoginLogService{

	@Autowired
	private SysLoginLogMapper sysLoginLogMapper;
	@Override
	@Transactional
	public void insertLoginLog(SysLoginLog loginLog) {
		loginLog.setInsertTime(new Date());
		loginLog.setLoginTime(new Date());
		loginLog.setInsertUserId("SYSTEM");
		loginLog.setSysUuid(RandomUtil.uuid());
		sysLoginLogMapper.insert(loginLog);
	}
	
}
