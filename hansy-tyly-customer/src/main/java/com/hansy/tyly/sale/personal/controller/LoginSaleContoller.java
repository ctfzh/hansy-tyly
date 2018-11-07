package com.hansy.tyly.sale.personal.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hansy.tyly.common.utils.Context;
import com.hansy.tyly.common.utils.Respones;
import com.hansy.tyly.customer.system.model.SysUser;
import com.hansy.tyly.sale.personal.service.LoginSaleService;

import java.util.Map;

@RestController
@RequestMapping("/sale")
public class LoginSaleContoller {
	@Autowired
	private LoginSaleService loginSaleService;

	/**
	 * 销售个人信息查询
	 */
	@PostMapping("/sysusermanager")
	public Respones salerInfo(@RequestParam(value = "userId", required = false) String userId) {
           Respones respones = new Respones();
          SysUser sysUser = new SysUser();
          try {
  			// 返回商户信息
        	sysUser = loginSaleService.getSysuserInfo(userId);
  			respones.setMessage(Context.LOGIN_SELECT_SUCCESS);
  			respones.setCode(Context.REGISTER_TRUE);
  			respones.setUrl("http://localhost:8080/sale/sysusermanager");
  			respones.setReq(sysUser);
  			respones.setResult(true);
  		} catch (Exception e) {
  			e.printStackTrace();
  			respones.setMessage(Context.REGISTER_FALSE);
  			respones.setCode(Context.LOGIN_MESSAGE_FALSE);
  		}

  		return respones;


	}

	@ApiOperation(value = "销售获取报表之拜访汇总", notes = "销售获取报表")
	@ResponseBody
	@RequestMapping(value = "/getReport", method = RequestMethod.POST)
	public Respones getReportOfVisit(@RequestParam String saleNo,
							  @RequestParam(required = false) Integer num,
							  @RequestParam String dateType,
							  @RequestParam(required = false) String date) {
		Respones cmd=new Respones();
		try{
			Map<String,Object> list=loginSaleService.getReportOfVisit(saleNo,num,dateType,date);
			cmd.setReq(list);
			cmd.setResult(true);
		}catch (Exception e){
			e.printStackTrace();
			cmd.setMessage(Context.GET_LIST_FALSE);
			cmd.setResult(false);
		}
		return cmd;
	}
	@ApiOperation(value = "销售获取报表之业绩信息", notes = "销售获取报表")
	@ResponseBody
	@RequestMapping(value = "/getReportOfAchievement", method = RequestMethod.POST)
	public Respones getReportOfAchievement(@RequestParam String saleNo,
							  @RequestParam(required = false) Integer num,
							  @RequestParam String dateType,
							  @RequestParam(required = false) String date) {
		Respones cmd=new Respones();
		try{

			Map<String,Object> list=loginSaleService.getReportOfAchievement(saleNo,num,dateType,date);
			cmd.setReq(list);
			cmd.setResult(true);
		}catch (Exception e){
			e.printStackTrace();
			cmd.setMessage(Context.GET_LIST_FALSE);
			cmd.setResult(false);
		}
		return cmd;
	}
	@ApiOperation(value = "销售获取报表之部门销售总数据之销售客户数", notes = "销售获取报表")
	@ResponseBody
	@RequestMapping(value = "/getReportOfUserCount", method = RequestMethod.POST)
	public Respones getReportOfUserCount(@RequestParam(required = false) Integer num,
							  @RequestParam String dateType,
							  @RequestParam(required = false) String date) {
		Respones cmd=new Respones();
		try{
			Map<String,Object> list=loginSaleService.getReportOfUserCount(num,dateType,date);
			cmd.setReq(list);
			cmd.setResult(true);
		}catch (Exception e){
			e.printStackTrace();
			cmd.setMessage(Context.GET_LIST_FALSE);
			cmd.setResult(false);
		}
		return cmd;
	}

	@ApiOperation(value = "销售获取报表之部门销售总数据之销售客户消费金额", notes = "销售获取报表")
	@ResponseBody
	@RequestMapping(value = "/getReportOfConsume", method = RequestMethod.POST)
	public Respones getReportOfConsume(
							  @RequestParam(required = false) Integer num,
							  @RequestParam String dateType,
							  @RequestParam(required = false) String date) {
		Respones cmd=new Respones();
		try{
			Map<String,Object> list=loginSaleService.getReportOfConsume(num,dateType,date);
			cmd.setReq(list);
			cmd.setResult(true);
		}catch (Exception e){
			e.printStackTrace();
			cmd.setMessage(Context.GET_LIST_FALSE);
			cmd.setResult(false);
		}
		return cmd;
	}

}
