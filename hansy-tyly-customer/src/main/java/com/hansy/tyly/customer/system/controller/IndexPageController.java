package com.hansy.tyly.customer.system.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hansy.tyly.admin.base.controller.BaseController;
import com.hansy.tyly.core.command.CryptoCmd;
import com.hansy.tyly.customer.system.service.IndexService;

@Api(description = "W02-[首页信息展现]")
@RestController
@RequestMapping("/api/index")
public class IndexPageController extends BaseController{

    @Autowired
    private IndexService indexService;
    
    @ApiOperation(value = "01-获取机构(或经理)名下客户总体数据信息", notes = "获取已管理、待管理、风险总数统计数据，不需要任何参数")
    @ResponseBody
	@RequestMapping(value = "/getCustMngGeneralData", method = RequestMethod.POST)
    public CryptoCmd getCustMngGeneralData(CryptoCmd cmd) {
    	Map<String, String> outMap = new HashMap<String, String>();
    	outMap = indexService.queryCustMngGeneralData(this.getCurrentUserProfile());
		cmd.setOut("mngInfo", outMap);
        return cmd;
    }
	
    @ApiOperation(value = "02-一周内机构(或经理)名下管理债务人数量", notes = "获取已管理、风险总数统计数据，不需要任何参数")
    @ResponseBody
	@RequestMapping(value = "/getLastWeekMngData", method = RequestMethod.POST)
    public CryptoCmd getLastWeekMngData(CryptoCmd cmd) {
		cmd.setOut(indexService.queryLastWeekMngData(this.getCurrentUserProfile()));
        return cmd;
    }
    
    @ApiOperation(value = "03-风险概况", notes = "获取当前已管理客户风险概况统计数据，不需要任何参数")
    @ResponseBody
	@RequestMapping(value = "/getRiskGeneralData", method = RequestMethod.POST)
    public CryptoCmd getRiskGeneralData(CryptoCmd cmd) {
    	Map<String, String> outMap = new HashMap<String, String>();
    	outMap = indexService.queryRiskGeneralData(this.getCurrentUserProfile());
		cmd.setOut("riskInfo", outMap);
        return cmd;
    }
    
    @ApiOperation(value = "04-预警趋势变化", notes = "获取当前已管理客户风险概况统计数据，不需要任何参数")
    @ResponseBody
	@RequestMapping(value = "/getWarnTrendsData", method = RequestMethod.POST)
    public CryptoCmd getWarnTrendsData(CryptoCmd cmd) {
		cmd.setOut(indexService.queryWarnTrendsData(this.getCurrentUserProfile()));
        return cmd;
    }
    
}
