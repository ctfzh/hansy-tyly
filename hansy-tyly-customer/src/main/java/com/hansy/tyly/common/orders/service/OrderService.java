package com.hansy.tyly.common.orders.service;

import java.util.List;
import java.util.Map;

import com.hansy.tyly.common.orders.dao.model.RefundListMessage;
import com.hansy.tyly.common.orders.dao.model.TBusOrder;
import com.hansy.tyly.common.orders.dao.model.TBusRefund;
import com.hansy.tyly.common.orders.pojo.OrderInfo;
import com.hansy.tyly.common.orders.pojo.OrderMessage;
import com.hansy.tyly.common.orders.pojo.RefundList;
import com.hansy.tyly.dealers.login.Dao.model.TUserRecAddr;
import org.springframework.web.bind.annotation.RequestParam;

public interface OrderService {

	//提交订单
	String createOrder(OrderMessage orderInfo,String formId,
					    String page) throws Exception;
	
	//订单列表
	List<OrderInfo> getOrderList(Map<String, Object> inMap)throws Exception;

	String getCodeValueByName(String str, String code)throws Exception;

	//订单详情
	Map<String,Object> getOrderDetail(String orderNo) throws Exception;

	//经销商退款申请
	void merchantRefund(RefundList refund,String formId,
						String page) throws Exception;

	void approveRefund(String orderNo, String apprStatus,String disNo,String formId,
					   String page)throws Exception;


    List<RefundListMessage> getRefundOrders(Map<String, Object> params)throws Exception;

    void processOrders(String orderNo, String changeStatus, String disNo,String formId,String page)throws Exception;

    OrderInfo getRefundOrderInfo(Integer tableKey)throws Exception;

	List<TUserRecAddr> getAddrInfo(String userNo,String isDefault) throws Exception;

	void editAddrInfo(TUserRecAddr addr) throws Exception;

	void editOrder(OrderMessage orderInfo , String formId,
				   String page)throws Exception;

    void deleteAddrInfo(String tableKey) throws Exception;

    TUserRecAddr getOneAddrInfo(String tableKey) throws Exception;

    void processOrdersForPlatform(String orderNo, String changeStatus, String disNo,String formId,String page)throws Exception;

	void processOrdersForDelear(String orderNo, String changeStatus, String mewNo)throws Exception;

	List<Map<String,Object>> doOrderList(List<OrderInfo> list) throws Exception;

	void insertOrderNews(String orderNo,String userNo,String userType,String content, String title)throws Exception;
}
