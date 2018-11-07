package com.hansy.tyly.admin.sale.service;

import java.util.List;
import java.util.Map;

public interface AuditService {
	/**
	 * 获取销售待审核的经销商和商户
	 * @param saleNo 销售编号
	 */
	List<Map<String, Object>> getAudits(Map<String, Object> params);
	/**
	 * 商户审核
	 */
	int auditMerchant(String userNo,String status);
	/**
	 * 经销商审核
	 */
	int auditDealer(String userNo,String status);
	/**
	 * 设置销售二维码
	 */
	String setTwoDimensionCode(String salesId);
	/**
	 * 设置销售二维码
	 */
	String getTwoDimensionCode(String salesId);
	/**
	 * 获取经销商二维码
	 */
	String getDealerTwoDimensionCode(String dealerID);
	/**
	 * 刷新销售邀请商户注册二维码
	 */
	String refreshTwoDimensionCode(String salesId);
	/**
	 * 刷新经销商绑定二维码
	 */
	String refreshDealerBindCode(String userNo);
}
