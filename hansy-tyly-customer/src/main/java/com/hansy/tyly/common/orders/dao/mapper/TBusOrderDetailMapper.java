package com.hansy.tyly.common.orders.dao.mapper;


import org.apache.ibatis.annotations.Param;

import com.hansy.tyly.common.orders.dao.model.TBusOrderDetail;
import com.hansy.tyly.common.orders.pojo.OrderInfo;

import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

public interface TBusOrderDetailMapper extends Mapper<TBusOrderDetail> {

	//查看订单详情
	Map findOrderByorderNo(@Param("orderNo") String orderNo);

}