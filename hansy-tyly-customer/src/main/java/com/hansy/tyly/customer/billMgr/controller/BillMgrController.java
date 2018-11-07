package com.hansy.tyly.customer.billMgr.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Calendar;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hansy.tyly.admin.base.controller.BaseController;
import com.hansy.tyly.core.command.CryptoCmd;
import com.hansy.tyly.core.excepiton.ParameterException;
import com.hansy.tyly.customer.billMgr.service.BillMgrService;
import com.hansy.tyly.customer.utils.ConstantsUtil;
import com.hansy.tyly.customer.utils.DateUtil;
import com.hansy.tyly.customer.utils.StringUtil;

@Api(description = "W06-[机构账单]账单查询")
@RestController
@RequestMapping("/api/billMgr")
public class BillMgrController extends BaseController{

    @Autowired
    private BillMgrService billMgrService;
    
    private static final Logger logger = LoggerFactory.getLogger(BillMgrController.class);
    
	
    @ApiOperation(value = "01-[账单查询]机构账单金额查询", notes = "{\"beginTime\":\"2017-10-01 00:00:00\",\"endTime\":\"2017-10-01 59:59:59\",\"dateType\":\"Mouth\"}")
	@ResponseBody
	@RequestMapping(value = "/queryOrgBillAmt", method = RequestMethod.POST)
    public CryptoCmd queryOrgBillAmt(CryptoCmd cmd) {
    	Map<String, Object> inMap = cmd.getParams();
    	String beginTime = "";//cmd.getInString("beginTime");
    	String endTime = "";//cmd.getInString("endTime");
    	String dateType = cmd.getInString("dateType");//月、三月、一年
    	//没有开始时间，默认查询2017-10-10以后的时间
    	if(StringUtil.isEmpty(beginTime)){
    		//beginTime =  "2017-10-10 00:00:00";
    		//beginTime =  "2017-10-10";
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, 0);
            c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
            beginTime = DateUtil.date(c.getTime());
		}else{
			//beginTime += " 00:00:00";
		}
		
		if (StringUtil.isEmpty(endTime)) {
			//endTime = DateUtil.currentDate() + " 23:59:59";
			endTime = DateUtil.currentDate();
		}else{
			//endTime += " 23:59:59";
		}
		
		/*if(DateUtil.compareDate(beginTime, endTime) > 0){
			throw new ParameterException("查询参数有误，开始日期不能大于结束日期！");
		}
    	
		dateType = (StringUtil.isEmpty(dateType) == true  ? "" : dateType);
		switch (dateType) {
		case ConstantsUtil.BILL_DATE_TYPE_01:
			endTime = DateUtil.currentDate();
			beginTime = DateUtil.date(DateUtil.day(-30));
			break;
		case ConstantsUtil.BILL_DATE_TYPE_02:
			endTime = DateUtil.currentDate();
			beginTime = DateUtil.date(DateUtil.day(-90));
			break;
		case ConstantsUtil.BILL_DATE_TYPE_03:
			endTime = DateUtil.currentDate();
			beginTime = DateUtil.date(DateUtil.day(-365));
			break;
		default:
			break;
		}*/
		
    	inMap.put("beginTime", beginTime);
    	inMap.put("endTime", endTime);
    	inMap.put("orgId", this.getCurrentUserOrgId());
    	//查询机构 余额、当前支出总额
    	cmd.setOut("amtInfo", billMgrService.queryBillInfo(inMap));
        return cmd;
    }
    
    /**
     * 01-02接口不能合并，PageHelper特性会使02接口无法分页
     * */
    
    @ApiOperation(value = "02-[账单查询]机构账单明细列表查询", notes = "{\"beginTime\":\"2017-10-01 00:00:00\",\"endTime\":\"2017-10-01 59:59:59\",\"dateType\":\"Mouth\"}")
	@ResponseBody
	@RequestMapping(value = "/queryOrgBillList", method = RequestMethod.POST)
	public CryptoCmd queryOrgBillList(CryptoCmd cmd) {
		Map<String, Object> inMap = cmd.getParams();
		String beginTime = cmd.getInString("beginTime");
		String endTime = cmd.getInString("endTime");
		String dateType = cmd.getInString("dateType");//月、三月、一年
    	if(StringUtil.isEmpty(beginTime)){
    		//beginTime =  "2017-10-10 00:00:00";
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
		
		dateType = (StringUtil.isEmpty(dateType) == true ? "" : dateType);
		switch (dateType) {
		case ConstantsUtil.BILL_DATE_TYPE_01:
			endTime = DateUtil.currentDate();
			beginTime = DateUtil.date(DateUtil.day(-30));
			break;
		case ConstantsUtil.BILL_DATE_TYPE_02:
			endTime = DateUtil.currentDate();
			beginTime = DateUtil.date(DateUtil.day(-90));
			break;
		case ConstantsUtil.BILL_DATE_TYPE_03:
			endTime = DateUtil.currentDate();
			beginTime = DateUtil.date(DateUtil.day(-365));
			break;
		default:
			break;
		}

		inMap.put("beginTime", beginTime);
		inMap.put("endTime", endTime);
		inMap.put("orgId", this.getCurrentUserOrgId());
		// 查询账单明细列表
		cmd.setOut(billMgrService.queryBillList(inMap));
		return cmd;
	}
}
