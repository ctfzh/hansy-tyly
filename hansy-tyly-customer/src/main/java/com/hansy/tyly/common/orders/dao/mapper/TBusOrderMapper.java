package com.hansy.tyly.common.orders.dao.mapper;

import com.hansy.tyly.common.orders.dao.model.TBusOrder;

import java.util.List;
import java.util.Map;

import com.hansy.tyly.common.orders.pojo.OrderInfo;

import tk.mybatis.mapper.common.Mapper;

public interface TBusOrderMapper extends Mapper<TBusOrder> {
    //查看订单列表
    List<Map> queryOrderList(Map<String, Object> inMap);
    //定时任务
	void getScheduleTimer(String receivingDate);

    int updateStatusByPrimaryKey(Map<String,Object> order);


}