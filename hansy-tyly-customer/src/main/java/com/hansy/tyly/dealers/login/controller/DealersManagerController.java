package com.hansy.tyly.dealers.login.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.hansy.tyly.common.utils.Context;
import com.hansy.tyly.common.utils.Respones;
import com.hansy.tyly.dealers.login.service.DealersmanagerService;
import com.hansy.tyly.merchants.orders.dao.model.TpubDistributorInfo;

@RestController
@RequestMapping("/dealers")
public class DealersManagerController {
	@Autowired
	private DealersmanagerService dealersmanagerService;

	/**
	 * 经销商个人信息录入接口
	 */
	@PostMapping("/dealersManagerInfo")
	public Respones MerchantsInfo(@RequestParam(value = "distributorNo", required = false) String distributorNo,
			@RequestParam(value = "distributorName", required = false) String distributorName,
			@RequestParam(value = "distributorContact", required = false) String distributorContact,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "legalName", required = false) String legalName,
			@RequestParam(value = "distributorRegNo", required = false) String distributorRegNo,
			@RequestParam(value = "companyType", required = false) String companyType,
			@RequestParam(value = "distributorAddre", required = false) String distributorAddre,
			@RequestParam(value = "url", required = false) String url,
			@RequestParam(value = "pid", required = false) String pid) {
		Respones respones = new Respones();
		Map<String, Object> map = new HashMap<>();
		map.put("distributorNo", distributorNo);
		map.put("distributorName", distributorName);
		map.put("distributorContact", distributorContact);
		map.put("phone", phone);
		map.put("legalName", legalName);
		map.put("distributorRegNo", distributorRegNo);
		map.put("companyType", companyType);
		map.put("distributorAddre", distributorAddre);
		String[] imageUrl = url.split(",");
		String[] pcId = pid.split(",");
		for (int i = 0; i < imageUrl.length; i++) {
			String urls = imageUrl[i];
			String picid = pcId[i];
			dealersmanagerService.updateInfo(urls, picid, distributorNo);
		}
		try {
			if (dealersmanagerService.updateMerChants(map) > 0) {
				respones.setMessage(Context.LOGIN_MESSAGE_SUCCESS);
				respones.setCode(Context.REGISTER_TRUE);
				respones.setUrl("http://localhost:8080/dealers/dealersManagerInfo");
				// 查询返回状态
				String status = dealersmanagerService.getMerstatus(map.get("distributorNo").toString());
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("Status", status);
				respones.setReq(jsonObject);
				respones.setResult(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			respones.setMessage(Context.REGISTER_FALSE);
			respones.setCode(Context.LOGIN_MESSAGE_FALSE);

		}

		return respones;
	}

	/*
	 * 经销商个人信息查询
	 */
	@PostMapping("/DealersPersonnalInfo")
	public Respones DealersPersonnalInfo(
			@RequestParam(value = "distributorNo", required = false) String distributorNo) {
		Respones respones = new Respones();
		TpubDistributorInfo tpubDistributorInfo = new TpubDistributorInfo();
		try {
			// 返回商户信息
			tpubDistributorInfo = dealersmanagerService.slelectPersonalInfo(distributorNo);
			respones.setMessage(Context.LOGIN_SELECT_SUCCESS);
			respones.setCode(Context.REGISTER_TRUE);
			respones.setUrl("http://localhost:8080/dealers/DealersPersonnalInfo");
			respones.setReq(tpubDistributorInfo);
			respones.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			respones.setMessage(Context.REGISTER_FALSE);
			respones.setCode(Context.LOGIN_MESSAGE_FALSE);
		}

		return respones;

	}

	/**
	 * 经销商个人中心查询
	 */
	@PostMapping("/DealersPersonnalCenter")
	public Respones getDealersPersonal(@RequestParam(value = "distributorNo", required = false) String distributorNo
			                   ) {
	  
		Respones respones = new Respones();
		JSONObject jsonObject = new JSONObject();
		// 查询收货地址 ，用户名
		 Map<String, Object> map = dealersmanagerService.getuserNameAndAddr(distributorNo);
		 Map<String, Object> mapping = dealersmanagerService.getuserNameAndAddres(distributorNo);
		 if(mapping == null){
			 map.put("recAddr", "");
			 jsonObject.put("personalInfo", map);
		 }else{
			  map.put("recAddr", mapping.get("recAddr"));
			 jsonObject.put("personalInfo", map);

		 }
		// 出售订单查询
	     Map<String, Object> mapSale = dealersmanagerService.getMapSaleInfo(distributorNo);
		jsonObject.put("saleOrder", mapSale);
		// 购买订单查询
		Map<String, Object> mapGet = dealersmanagerService.getBuyInfo(distributorNo);
		jsonObject.put("buyOrder", mapGet);
		Map<String, Object> news = new HashMap<>();
		Map<String, Object> mapNew = dealersmanagerService.getNews(distributorNo);
		if ("0".equals(String.valueOf(mapNew.get("count")))) {
			news.put("news", "1");//没消息
		} else {
			news.put("news", "0");//有消息
		}
		jsonObject.put("new", news);
		//库存预警
		Map<String,Object> mapyujin = dealersmanagerService.getYuJinXinXi(distributorNo);
		Map<String, Object> mapper = new HashMap<>();
		if(Integer.parseInt(mapyujin.get("count").toString())>0){
			mapper.put("warning", "1");
		} else{
			mapper.put("warning", "0");
		}
		jsonObject.put("yujin", mapper);
		jsonObject.put("distributorNo",  distributorNo);
		respones.setResult(true);
		respones.setMessage("查询成功");
		respones.setReq(jsonObject);
		return respones;
		
	}
	
}
