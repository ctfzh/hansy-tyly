package com.hansy.tyly.admin.sale.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hansy.tyly.admin.base.controller.BaseController;
import com.hansy.tyly.admin.dao.model.TUserNews;
import com.hansy.tyly.admin.sale.service.MessageService;
import com.hansy.tyly.admin.utils.Context;
import com.hansy.tyly.admin.utils.Respones;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "消息管理")
@RestController
@RequestMapping("/sales/News")
public class MessageController extends BaseController{
	@Autowired
	private MessageService messageService;
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	//查看消息列表
	@ApiOperation(value = "查看消息列表", notes = "查看消息列表")
	@ResponseBody
	@RequestMapping(value = "/queryNewsList", method = RequestMethod.POST)
	public Respones queryNewsList(@RequestParam(required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(required = false, defaultValue = "1") Integer pageNo,
			@RequestParam String userNo,
			@RequestParam(required = false, defaultValue = "0") String isRead
	) {
		Respones respones = new Respones();
		Map<String, Object> inMap = new HashMap<>();

		inMap.put("pageSize", pageSize);
		inMap.put("pageNo", pageNo);
		inMap.put("userNo", userNo);
		inMap.put("isRead", isRead);
		try {
			List<TUserNews> list = messageService.getNewsList(inMap,userNo);
			respones.setReq(list);
			respones.setCode("200");
			respones.setUrl("/queryNewsList");
			respones.getDate();
			respones.setMessage(Context.REGISTER_TRUE);
			respones.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			respones.setMessage(Context.GET_LIST_FALSE);
			respones.setResult(false);
		}
		return respones;
	}


	//查看消息列表
	@ApiOperation(value = "查看未读消息", notes = "查看未读消息")
	@ResponseBody
	@RequestMapping(value = "/queryNewsCount", method = RequestMethod.POST)
	public Respones queryNewsCount(
								  @RequestParam String userNo

	) {
		Respones respones = new Respones();
		Map<String, Object> inMap = new HashMap<>();


		inMap.put("userNo", userNo);
		inMap.put("isRead", Context.READ_FALSE);
		try {
			Integer total = messageService.getNewsCount(inMap);
			respones.setReq(total);
			respones.setCode("200");
			respones.setUrl("/queryNewsList");
			respones.getDate();
			respones.setMessage(Context.REGISTER_TRUE);
			respones.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			respones.setMessage(Context.GET_LIST_FALSE);
			respones.setResult(false);
		}
		return respones;
	}


			
	//查看订单详情
	@ApiOperation(value = "查看消息详情", notes = "经销商查看消息详情")
	@ResponseBody
	@RequestMapping(value = "/findNewsBytableKey", method = RequestMethod.POST)
	public Respones findNewsBytableKey(@RequestParam String tableKey) {
		Respones respones = new Respones();
		try {
			Map orderInfo = messageService.getNewsDetail(tableKey);
			respones.setReq(orderInfo);
			respones.setCode("200");
			respones.setUrl("/findNewsBytableKey");
			respones.getDate();
			respones.setMessage(Context.REGISTER_TRUE);
			respones.setResult(true);
		}catch(Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			respones.setMessage(Context.GET_INFO_FALSE);
			respones.setResult(false);
		}

		return respones;
	}
	
}
