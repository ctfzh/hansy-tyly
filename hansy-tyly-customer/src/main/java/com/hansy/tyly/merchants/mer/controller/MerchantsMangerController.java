package com.hansy.tyly.merchants.mer.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.hansy.tyly.common.utils.Context;
import com.hansy.tyly.common.utils.Respones;
import com.hansy.tyly.common.utils.WechatMsgUtil;
import com.hansy.tyly.core.helper.NPageHelper;
import com.hansy.tyly.dealers.login.service.DealersmanagerService;
import com.hansy.tyly.merchants.mer.service.MerchantsMangerService;
import com.hansy.tyly.merchants.orders.dao.model.TpubMerInfo;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/merchants")
public class MerchantsMangerController {

	@Autowired
	private MerchantsMangerService merchantsMangerService;

	@Autowired
	private DealersmanagerService dealersmanagerService;
	
	@Autowired
	private WechatMsgUtil wechatMsgUtil;

	/**
	 * 商户绑定经销商管理
	 * 
	 * @param mId
	 *            商户编号
	 * @param dId
	 *            经销商编号
	 */
	@ApiOperation(value = "经销商与商户绑定", notes = "{mId:\"商户编号\",dId:\"经销商编号\"}")
	@PostMapping("/manager")
	public Respones register(@RequestParam(value = "mId", required = false) String mId,
			@RequestParam(value = "dId", required = false) String dId) throws ServletException, IOException {

		Respones respones = new Respones();
		try {
			// 跟新商户表信系
			Map<String, Object> map = merchantsMangerService.updateMerChantsInfo(mId, dId);
			// 已绑定
			if ("0000".equals(map.get("code"))) {
				respones.setCode("0000");
				respones.setMessage("已绑定");
				respones.setResult(false);
			} else {
				respones.setCode("1111");
				respones.setMessage("绑定成功");
				respones.setResult(true);
			}
		} catch (Exception e) {
			respones.setCode("0011");
			respones.setMessage("绑定失败");
			respones.setResult(false);
			e.printStackTrace();
		}
		respones.setUrl("/merchants/manager");
		return respones;
	}

	/**
	 * 商户信息注册接口
	 */
	@RequestMapping("/managerInfo")
	public Respones MerchantsInfo(@RequestParam(value = "merNo", required = false) String merNo,
			@RequestParam(value = "merName", required = false) String merName,
			@RequestParam(value = "merContact", required = false) String merContact,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "legalName", required = false) String legalName,
			@RequestParam(value = "merRegNo", required = false) String merRegNo,
			@RequestParam(value = "companyType", required = false) String companyType,
			@RequestParam(value = "merAddre", required = false) String merAddre,
			@RequestParam(value = "url", required = false) String url,
			@RequestParam(value = "pid", required = false) String pid) {
		Respones respones = new Respones();
		Map<String, Object> map = new HashMap<>();
		map.put("merNo", merNo);
		map.put("merName", merName);
		map.put("merContact", merContact);
		map.put("phone", phone);
		map.put("legalName", legalName);
		map.put("merRegNo", merRegNo);
		map.put("companyType", companyType);
		map.put("merAddre", merAddre);
		String[] imageUrl = url.split(",");
		String[] pcId = pid.split(",");
		for (int i = 0; i < imageUrl.length; i++) {
			String urls = imageUrl[i];
			String picid = pcId[i];
			dealersmanagerService.updateInfo(urls, picid, merNo);
		}
		try {
			if (merchantsMangerService.updateMerChants(map) > 0) {
				respones.setMessage(Context.LOGIN_MESSAGE_SUCCESS);
				respones.setCode(Context.REGISTER_TRUE);
				respones.setUrl("http://localhost:8080/merchants/managerInfo");
				// 查询返回状态
				String status = merchantsMangerService.getMerstatus(map.get("merNo").toString());
				if (status == "3") {
					status = "5";
				}
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

	/**
	 * 商户信息接口
	 */
	@PostMapping("/managerPersonalInfo")
	public Respones MerchantsPersonnalInfo(@RequestParam(value = "merNo", required = false) String merNo) {
		Respones respones = new Respones();
		TpubMerInfo tpubMerInfo = new TpubMerInfo();
		try {
			// 返回商户信息
			tpubMerInfo = merchantsMangerService.slelectPersonalInfo(merNo);
			respones.setMessage(Context.LOGIN_SELECT_SUCCESS);
			respones.setCode(Context.REGISTER_TRUE);
			respones.setUrl("http://localhost:6020/merchants/managerPersonalInfo");
			respones.setReq(tpubMerInfo);
			respones.setResult(true);
		} catch (Exception e) {
			e.printStackTrace();
			respones.setMessage(Context.REGISTER_FALSE);
			respones.setCode(Context.LOGIN_MESSAGE_FALSE);
		}

		return respones;

	}

	/**
	 * 商户个人中心查询
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(value ="/merchantsPersonalInfo")
	public Respones merchantsPersonalInfo(@RequestParam(value = "merId") String merId,
			@RequestParam(value = "beginDate") String beginDate, @RequestParam(value = "endDate") String endDate,
			@RequestParam(required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(required = false, defaultValue = "1") Integer pageNo) throws ParseException {
		Map<String, Object> params = new HashMap<>();
		params.put("pageSize", pageSize);
		params.put("pageNo", pageNo);
		   Respones	  respones = new Respones();
		JSONObject jsonObject = new JSONObject();
		// 查询收货地址 ，用户名
		Map<String, Object> map = dealersmanagerService.getuserNameAndAddr(merId);
		Map<String, Object> mapping = dealersmanagerService.getuserNameAndAddres(merId);
		Map<String, Object> mapper = dealersmanagerService.getUserImge(merId);	
		if(mapper==null){
			map.put("merUrl", "");
			if(mapping==null){
				map.put("recAddr", "");
			}else{
				map.put("recAddr", mapping.get("recAddr"));
			}
			jsonObject.put("personalInfo", map);
		}
		if(mapper!=null){
			map.put("merUrl", mapper.get("merUrl"));
			if(mapping==null){
				map.put("recAddr", "");
			}else{
				map.put("recAddr", mapping.get("recAddr"));
			}
			jsonObject.put("personalInfo", map);
		}
		// 消息查询
		Map<String, Object> news = new HashMap<>();
		Map<String, Object> mapNew = dealersmanagerService.getNews(merId);
		if ("0".equals(String.valueOf(mapNew.get("count"))) ) {
			news.put("news", "1");//没消息
		} else {
			news.put("news", "0");//有消息
		}
		jsonObject.put("new", news);
		// 我的订单查询
		Map<String, Object> mapGet = dealersmanagerService.getBuyInfoinfo(merId);
		jsonObject.put("buyOrder", mapGet);
		// 历史消费统计2014-5-6
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String bigindatee = beginDate + " 00:00:00";
		String enddatee = endDate + " 23:59:59";
		Date bigindate = sdf.parse(bigindatee);
		Date enddate = sdf.parse(enddatee);
		Map<String, Object> consumption = dealersmanagerService.getConsumption(merId, bigindate, enddate);
		Map<String, Object> consumptions = new HashMap<>();
		if (consumption == null) {
			consumptions.put("amt", "0");
			jsonObject.put("hisConsumption", consumptions);
		} else {
			jsonObject.put("hisConsumption", consumption);
		}
		// 统计消费明细
		params.put("merId", merId);
		params.put("bigindate", bigindate);
		params.put("enddate", enddate);
		NPageHelper.startPage(params);
		List<Map<String, Object>> list = dealersmanagerService.getConsumptionList(params);
		if (list == null) {
			jsonObject.put("consumptionList", "");
		} else {
			 for(Map<String, Object> datein:list){
				 if(!StringUtils.isEmpty(datein.get("updateDate").toString())){
					 String aString = datein.get("updateDate").toString().substring(0, 19);
					 datein.put("updateDate", aString);
				 }
			 }
			 respones.setReq(list);
			jsonObject.put("consumptionList",list);
		}
		jsonObject.put("merno", merId);
		respones.setReq(jsonObject);
		respones.setResult(true);
		respones.setMessage("查询成功");
		return respones;
	}
	
	
	
	 public static String timeStamp2Date(String seconds,String format) {  
         if(seconds == null || seconds.isEmpty() || seconds.equals("null")){  
             return "";  
         }  
         if(format == null || format.isEmpty()){
              format = "yyyy-MM-dd HH:mm:ss";
         }   
          SimpleDateFormat sdf = new SimpleDateFormat(format);  
        return sdf.format(new Date(Long.valueOf(seconds+"000")));  
    }  
	 // 查询商户经销商
	 @PostMapping("/MerchantsDealersInfo")
		public Respones MerchantsDealersInfo(@RequestParam(value = "merNo", required = false) String merNo){
		         Respones respones = new Respones();
		         JSONObject jsonObject = new JSONObject();
		         try {
					List<Map<String, Object>> list = new ArrayList<>();
					     list=merchantsMangerService.MerchantsDealersInfo(merNo);
					     if(list == null){
					    	 jsonObject.put("dealers", "") ;
					     }else{
					    	jsonObject.put("dealers", list);
					     }
					     respones.setMessage("查询成功");
					     respones.setResult(true);
					     respones.setReq(jsonObject);
				} catch (Exception e) {
					e.printStackTrace();
					 respones.setMessage("查询失败");
				     respones.setResult(false);
				}
			return respones;
	 }
	 
	 }