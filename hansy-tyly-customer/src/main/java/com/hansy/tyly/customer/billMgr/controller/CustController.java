package com.hansy.tyly.customer.billMgr.controller;

import io.swagger.annotations.Api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hansy.tyly.admin.utils.AppPropertiesUtil;

@Api(description = "cust测试")
@RestController
@RequestMapping("/cust")
public class CustController {
	
	private String test = AppPropertiesUtil.getInstance().get("wx.url");

	@RequestMapping(value = "/getcust")
    public String queryOrgBillAmt(String a) {
		return test;
	}
}
