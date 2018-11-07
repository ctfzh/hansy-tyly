package com.hansy.tyly.common.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.hansy.tyly.common.orders.service.OrderService;

public class CommTimer {

	/**
	 * 指定时间为00：00：00
	 * @return
	 */
	public static Date getTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		Date time = calendar.getTime();
		return time;
	}

}
