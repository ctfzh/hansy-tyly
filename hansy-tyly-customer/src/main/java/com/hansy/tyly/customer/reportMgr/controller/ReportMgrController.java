package com.hansy.tyly.customer.reportMgr.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.util.StringUtil;
import com.hansy.tyly.admin.base.bean.UserProfile;
import com.hansy.tyly.admin.base.controller.BaseController;
import com.hansy.tyly.admin.dao.model.SysRole;
import com.hansy.tyly.core.command.CryptoCmd;
import com.hansy.tyly.core.excepiton.ParameterException;
import com.hansy.tyly.customer.reportMgr.service.ReportMgrService;
import com.hansy.tyly.customer.utils.ConstantsUtil;
import com.hansy.tyly.customer.utils.DateUtil;

@Api(description = "W07-[风控报告]风控结果、风控报告查看")
@Controller
@RequestMapping("/api/reportMgr")
public class ReportMgrController extends BaseController{

    @Autowired
    private ReportMgrService reportMgrService;
    
    private static final Logger logger = LoggerFactory.getLogger(ReportMgrController.class);
    
	
    @ApiOperation(value = "01-[业务管理->客户管理->查看]查询风控产品结果明细", notes =  "{\"billId\":\"1111\"}")
	@ResponseBody
	@RequestMapping(value = "/queryProdIndcInfo", method = RequestMethod.POST)
    public CryptoCmd queryProdIndcInfo(CryptoCmd cmd) {
    	String billId = cmd.getInString("billId");
    	if(StringUtil.isEmpty(billId)){
    		throw new ParameterException("接口参数有误，风控流水号[billId]不能为空！");
    	}
		cmd.setOut(reportMgrService.queryIndcInfoByBillId(cmd.getParams()));
        return cmd;
    }
    
    @ApiOperation(value = "02-[查看报告->风控报告]风控报告列表", notes =  "{\"billId\":\"1111\"}")
	@ResponseBody
	@RequestMapping(value = "/queryRiskList", method = RequestMethod.POST)
    public CryptoCmd queryRiskList(CryptoCmd cmd) {
    	Map<String, Object> inMap = cmd.getParams();
    	String beginTime = cmd.getInString("beginTime");
    	String endTime = cmd.getInString("endTime");
    	//没有开始时间，默认查询2017-10-10以后的时间
    	if(StringUtil.isEmpty(beginTime)){
    		//beginTime = "2017-10-10 00:00:00";
    		beginTime =  "2017-10-10";
		}else{
			//beginTime += " 00:00:00";
		}
		
		if (StringUtil.isEmpty(endTime)) {
			//endTime = DateUtil.currentDate() + " 23:59:59";
			endTime = DateUtil.currentDate();
		}else{
			//endTime += " 23:59:59";
		}
		
		if(DateUtil.compareDate(beginTime, endTime) > 0){
			throw new ParameterException("查询参数有误，开始日期不能大于结束日期！");
		}
    	
    	inMap.put("beginTime", beginTime);
    	inMap.put("endTime", endTime);
    	UserProfile userProfile = this.getCurrentUserProfile();
    	inMap.put("orgId",userProfile.getOrgId());//仅仅查询当前机构
    	inMap.put("userId", this.getCurrentUserId());//默认查自己名下
		List<SysRole> sysRoles = userProfile.getSysRoles();
		for (SysRole sysRole : sysRoles) {
			if(ConstantsUtil.ORG_ADMIN_ROLE_CODE.equals(sysRole.getRoleId())){
				//具有机构管理员角色
				inMap.remove("userId");
			}
		}
		cmd.setOut(reportMgrService.queryRiskList(inMap));
        return cmd;
    }
    
    
    @ApiOperation(value = "03-[查看报告->风险统计]查询风险统计信息", notes =  "{\"beginTime\":\"2017-10-01 00:00:00\",\"endTime\":\"2017-10-01 59:59:59\"}")
	@ResponseBody
	@RequestMapping(value = "/queryRiskStatistics", method = RequestMethod.POST)
    public CryptoCmd queryRiskStatistics(CryptoCmd cmd) {
    	Map<String, Object> inMap = cmd.getParams();
    	String beginTime = cmd.getInString("beginTime");
    	String endTime = cmd.getInString("endTime");
    	//没有开始时间，默认查询7天以内
    	if(StringUtil.isEmpty(beginTime)){
    		//beginTime = DateUtil.date(DateUtil.day(-7))+ " 00:00:00";
    		beginTime =  DateUtil.date(DateUtil.day(-6));
		}else{
			//beginTime += " 00:00:00";
		}
		
		if (StringUtil.isEmpty(endTime)) {
			//endTime = DateUtil.currentDate() + " 23:59:59";
			endTime = DateUtil.currentDate();
		}else{
			//endTime += " 23:59:59";
		}
		
		if(DateUtil.compareDate(beginTime, endTime) > 0){
			throw new ParameterException("查询参数有误，开始日期不能大于结束日期！");
		}
    	
    	inMap.put("beginTime", beginTime);
    	inMap.put("endTime", endTime);
    	inMap.put("dcsRst", ConstantsUtil.DCS_RST_01);//只查结果为风险的记录
    	UserProfile userProfile = this.getCurrentUserProfile();
    	inMap.put("orgId",userProfile.getOrgId());//仅仅查询当前机构
    	inMap.put("userId", this.getCurrentUserId());//默认查自己名下
		List<SysRole> sysRoles = userProfile.getSysRoles();
		for (SysRole sysRole : sysRoles) {
			if(ConstantsUtil.ORG_ADMIN_ROLE_CODE.equals(sysRole.getRoleId())){
				//具有机构管理员角色
				inMap.remove("userId");
			}
		}
    	
		cmd.setOut(reportMgrService.queryRiskStatistics(inMap));
        return cmd;
    }
    
    @ApiOperation(value = "03-[查看报告->风险统计->风险列表]查询风险列表", notes =  "{\"queryDate\":\"2017-10-01\"}")
	@ResponseBody
	@RequestMapping(value = "/queryRiskDetailList", method = RequestMethod.POST)
	public CryptoCmd queryRiskDetailList(CryptoCmd cmd) {
		Map<String, Object> inMap = cmd.getParams();
		String queryDate = cmd.getInString("queryDate");
		if (StringUtil.isEmpty(queryDate)) {
			throw new ParameterException("接口参数有误，请输入查询具体日期！");
		}
		inMap.put("queryDate", queryDate);
		inMap.put("dcsRst", ConstantsUtil.DCS_RST_01);//只查结果为风险的记录
    	UserProfile userProfile = this.getCurrentUserProfile();
    	inMap.put("orgId",userProfile.getOrgId());//仅仅查询当前机构
    	inMap.put("userId", this.getCurrentUserId());//默认查自己名下
		List<SysRole> sysRoles = userProfile.getSysRoles();
		for (SysRole sysRole : sysRoles) {
			if(ConstantsUtil.ORG_ADMIN_ROLE_CODE.equals(sysRole.getRoleId())){
				//具有机构管理员角色
				inMap.remove("userId");
			}
		}
		cmd.setOut(reportMgrService.queryRiskDetailList(inMap));
		return cmd;
	}
    
    @ApiOperation(value = "04-查看报告->风控报告->查看", notes = "{\"busiDate\":\"2017-10-01\"}")
	@RequestMapping(value = "/riskReportDetail/{busiDate}", method = RequestMethod.GET)
    public String riskReportDetail(Model model, @PathVariable("busiDate") String busiDate) {
    	if(com.hansy.tyly.customer.utils.StringUtil.isEmpty(busiDate)){
    		throw new ParameterException("接口参数有误，业务日期[busiDate]为必传项！");
    	}
    	Map<String, Object> inMap = new HashMap<String, Object>();
    	inMap.put("busiDate", busiDate);
    	UserProfile userProfile = this.getCurrentUserProfile();
    	inMap.put("orgId",userProfile.getOrgId());//仅仅查询当前机构
    	inMap.put("userId", this.getCurrentUserId());//默认查自己名下
		List<SysRole> sysRoles = userProfile.getSysRoles();
		for (SysRole sysRole : sysRoles) {
			if(ConstantsUtil.ORG_ADMIN_ROLE_CODE.equals(sysRole.getRoleId())){
				//具有机构管理员角色
				inMap.remove("userId");
			}
		}
	    
		model.addAttribute("detailData", JSONObject.toJSONString(reportMgrService.queryReportDeatil(inMap),SerializerFeature.WriteMapNullValue));
        return "riskReport";
    }
    
}
