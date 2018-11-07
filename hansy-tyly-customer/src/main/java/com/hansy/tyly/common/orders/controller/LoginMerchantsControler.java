package com.hansy.tyly.common.orders.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.hansy.tyly.admin.dao.model.SysCodeInfo;
import com.hansy.tyly.admin.utils.AppPropertiesUtil;
import com.hansy.tyly.admin.utils.DateUtil;
import com.hansy.tyly.admin.utils.PasswordUtil;
import com.hansy.tyly.admin.utils.RandomUtil;
import com.hansy.tyly.common.orders.service.LoginCommomService;
import com.hansy.tyly.common.orders.service.LoginLogService;
import com.hansy.tyly.common.utils.AliyunOssUtil;
import com.hansy.tyly.common.utils.Context;
import com.hansy.tyly.common.utils.HttpHelper;
import com.hansy.tyly.common.utils.ImageChack;
import com.hansy.tyly.common.utils.NumberUtil;
import com.hansy.tyly.common.utils.Respones;
import com.hansy.tyly.customer.system.model.SysLoginLog;
import com.hansy.tyly.dealers.goods.service.LoginDealersService;
import com.hansy.tyly.dealers.login.service.DealersmanagerService;
import com.hansy.tyly.merchants.WeChat.WeChat;
import com.hansy.tyly.merchants.WeChat.WechatConfig;
import com.hansy.tyly.merchants.mer.service.MerchantsMangerService;
import com.hansy.tyly.merchants.orders.dao.model.TuserBaseInfo;
import com.hansy.tyly.merchants.orders.service.LoginMerchantsService;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author xiafei 商户注册，登陆
 */
@RestController
@RequestMapping("/merchants")
public class LoginMerchantsControler {
	@Autowired
	private DealersmanagerService dealersmanagerService;
	@Autowired
	private LoginMerchantsService loginMerchantsService;

	@Autowired
	private LoginDealersService loginDealersService;

	@Autowired
	private LoginCommomService loginCommomService;

	@Autowired
	private LoginLogService loginLogService;
	@Autowired
	private MerchantsMangerService merchantsMangerService;
	
	private static final String MERCHANTS_IMG = AppPropertiesUtil.getValue("picture.MERCHANTS_IMG");
	
	private static final String DEALERS_IMG = AppPropertiesUtil.getValue("picture.DEALERS_IMG");


    @PostMapping("/register")
	public Respones register(@RequestParam(value = "openId", required = false) String openId,
			@RequestParam(value = "userName") String userName, @RequestParam(value = "pwd") String pwd,
			@RequestParam(value = "saleId") String saleId, @RequestParam(value = "id") String lxId)
			throws ServletException, IOException {
		Respones respones = new Respones();
		respones.setUrl("/merchants/register");
		Map<String, Object> map = new HashMap<>();
		JSONObject jsonObject = new JSONObject();
		if(lxId.equals("2")){
		 map.put("openId", openId);
		}else{
		 map.put("openId", "12345");	
		}
		map.put("userName", userName);
		// MD5加密
		map.put("pwd", PasswordUtil.md5(pwd));
		map.put("saleId", saleId);
		map.put("userNo", NumberUtil.createCode());
		map.put("date", DateUtil.dateTime(new Date()));
		 if(lxId.equals("1")){
		   map.put("lxId", "10003004"); 
		 }else{
			 map.put("lxId", "10003003");
		 }
		
		try {
			Map<String, Object> resultMap = loginCommomService.isRegisterOrTrue(userName);
			// 判断用户是否已注册
			if (resultMap == null) {
				//清空以前绑定的openId
				loginCommomService.updateUserInfo(openId);
				loginCommomService.insertUserInfo(map);
				if (lxId.equals("1")) {
					// 商户登陆,跟新商户信息表
					loginMerchantsService.insertMerchantsInfo(map);

				} else {
					// 经销商登陆，跟新经销商信息表
					loginDealersService.inserDealersInfo(map);
				}
				jsonObject.put("userId", map.get("userNo").toString());
				respones.setMessage("注册成功");
				respones.setReq(jsonObject);
				respones.setResult(Context.REGISTER_RESULT_TRUE);
			} else {
				jsonObject.put("userId", resultMap.get("userNo").toString());
				respones.setCode("2");// 已注册
				respones.setMessage("已注册，注册失败");
				respones.setReq(jsonObject);
				respones.setResult(Context.REGISTER_RESULT_FALSE);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			respones.setMessage("注册失败");
			respones.setResult(Context.REGISTER_RESULT_FALSE);
		}
		return respones;

	}
/*
	*//**
	 * 判断用户是否注册
	 *//*
	@PostMapping("/isRegister")
	public Respones isRegister(@RequestParam(value = "openId", required = false) String openId)
			throws ServletException, IOException {
		Respones respones = new Respones();
		try {
			Map<String, Object> resultMap = loginCommomService.isRegister(openId);
			if (resultMap.get("code").equals("1")) {
				respones.setCode("1");// 未注册
				respones.setResult(Context.REGISTER_RESULT_FALSE);
			} else {
				respones.setCode("2");// 以注册
				respones.setResult(Context.REGISTER_RESULT_TRUE);

			}
		} catch (Exception e) {
			e.printStackTrace();
			respones.setResult(Context.REGISTER_RESULT_TRUE);
		}
		return respones;

	}*/

	
	/**
	 * 第一种登录 jsonobject
	 */
	@PostMapping("/loginopenid")
	public Respones loginInfo(@RequestParam("code") String code, @RequestParam("lb") String lb) {
		Respones respones = new Respones();
		String openId = WeChat.getOpenid(code,lb);
		Map<String, Object> map = new HashMap<>();
		JSONObject jsonObject = new JSONObject();
		List<TuserBaseInfo> list = new ArrayList<>();
		String merNo = null;
		String userStatus = null;
		try {
			// 验证用户openId登录
			list = merchantsMangerService.selectIstrue(openId);
			if (list.isEmpty()) {
				// 用户未注册
				map.put("status", "1");

			} else {
				for (TuserBaseInfo staus : list) {
					merNo = staus.getUserNo();
					userStatus = staus.getUserStatus();
				}
				if (userStatus.equals(Context.USER_STATUS_ENABLE)) {
					if (lb.equals("1")) {
						/*
						 * 商户
						 */
						if (loginMerchantsService.getMerStus(merNo).get("addr").toString().equals("1")) {
							map.put("status", "2");// 用户已注册。未注册信息
						} else {
							if (loginMerchantsService.getMerStus(merNo).get("stus").toString().equals(Context.USER_STATUS_ENABLE_WW)) {
								map.put("status","3"); // 用户已注册。未审核
							}
							if (loginMerchantsService.getMerStus(merNo).get("stus").toString().equals(Context.USER_STATUS_ENABLE)) {
								map.put("status", "4"); // 用户已注册。审核通过
							}
							if (loginMerchantsService.getMerStus(merNo).get("stus").toString().equals(Context.USER_STATUS_ENABLE_ff)) {
								map.put("status", "5"); // 用户已注册。审核不通过
							}

						}
					} else {
						/*
						 * 经销商
						 */
						if (loginDealersService.getMerStus(merNo).get("addr").toString().equals("1")) {
							map.put("status", "2");// 用户已注册。未注册信息
						} else {
							if (loginDealersService.getMerStus(merNo).get("stus").toString().equals(Context.USER_STATUS_ENABLE_WW)) {
								map.put("status", "3"); // 用户已注册。未审核
							}
							if (loginDealersService.getMerStus(merNo).get("stus").toString().equals(Context.USER_STATUS_ENABLE)) {
								map.put("status", "4"); // 用户已注册。已审核
							}
							if (loginDealersService.getMerStus(merNo).get("stus").toString().equals(Context.USER_STATUS_ENABLE_ff)) {
								map.put("status", "5"); // 用户已注册。审核不通过
							}

						}

					}
				} else {
					map.put("status", "6");// 已冻结账号
				}

			}
			jsonObject.put("stus", map.get("status"));
			jsonObject.put("merno", merNo);
			jsonObject.put("openid", openId);
			respones.setCode(Context.REGISTER_TRUE);
			respones.setMessage(Context.LOGIN_SELECT_SUCCESS);
			respones.setReq(jsonObject);
			respones.setResult(true);
			respones.setUrl("localhost:8080/merchants/loginopenid");

		} catch (Exception e) {
			e.printStackTrace();
			respones.setMessage(Context.LOGIN_SELECT_FASLE);
			respones.setResult(false);
			respones.setCode(Context.REGISTER_FALSE);
		}
		return respones;

	}

	/**
	 * 登录接口<br>
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            用户密码
	 * @param openId
	 *            weixin
	 * @param loginType(1：商户登录，2:经销商登录)
	 *            第二种登录
	 */
	@ApiOperation(value = "账号登录", notes = "{username:\"用户名\",password:\"密码\",openId:\"微信openID\",loginType:\"登录类型\"}")
	@PostMapping("/login")
	public Respones login(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "loginType") String loginType, 
			@RequestParam(value = "openId") String openId,
			HttpServletRequest request
			) {
		Respones respones = new Respones();
		String IP = "";
		// 获取登录的IP地址
		try {
			IP = HttpHelper.getIpAddr(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			SysLoginLog loginLog = new SysLoginLog();
			loginLog.setIp(IP);
			if(loginType.equals("1")){
				loginType="10003004";
			}else{
				loginType="10003003";
			}
			if(StringUtils.isBlank(openId)){
				HttpSession sessi = request.getSession();
				openId = sessi.getAttribute("openId").toString();
			}
			
			Map<String, Object> info = loginCommomService.login(username, password, openId, loginType);
			String code = info.get("code").toString();
			String status = info.get("staus").toString();
			// 登录成功
			if (Context.REGISTER_TRUE.equals(status)) {
				respones.setResult(true);
				// 设置登录日志状态
				loginLog.setUserId(info.get("userNo").toString());
				loginLog.setStatus(Context.LOGIN_RESULT_SUCCESS);
				loginLogService.insertLoginLog(loginLog);
				JSONObject object = new JSONObject();
				object.put("userNo", info.get("userNo").toString());
				object.put("stus", code);
				respones.setReq(object);
			}
			// 登录失败
			else {
				respones.setResult(false);
				Object userNo = info.get("userNo");
				if (userNo != null) {
					JSONObject object = new JSONObject();
					object.put("userNo", userNo.toString());
					respones.setReq(object);
					loginLog.setUserId(userNo.toString());
					loginLog.setStatus(Context.LOGIN_RESULT_FAIL);
					loginLogService.insertLoginLog(loginLog);
				}
			}
			/**
			 * code对应的状态
			 * 0000：登录成功
			 * 1:未注册 
			 * 2:基本信息未完善 
			 * 3：信息待审核 
			 * 4:密码错误 
			 * 5：审核未通过
			 * 6:账号被冻结 
			 * 7:登录类型用户错误
			 * 8:用户名或密码为空 
			 * 9999:登录失败
			 */
			respones.setCode(info.get("code").toString());
			respones.setMessage(info.get("msg").toString());
			respones.setUrl("/dealers/login");
		} catch (Exception e) {
			respones.setMessage("登录失败");
			respones.setResult(false);
			respones.setCode(Context.REGISTER_FALSE);
			e.printStackTrace();
		}

		return respones;
	}

	/**
	 * 修改密码
	 * 
	 * @param userNo
	 *            用户编号
	 * @param oldPwd
	 *            旧密码
	 * @param newPwd
	 *            新密码
	 */
	@ApiOperation(value="修改密码",notes="{userNo:\"用户编号\",oldPwed:\"旧密码\",newPwd:\"新密码\"}")
	@PostMapping("/alertPwd")
	public Respones alertPwd(@RequestParam(value = "userNo") String userNo,
			@RequestParam(value = "oldPwd") String oldPwd, @RequestParam(value = "newPwd") String newPwd) {
		Respones respones = new Respones();
		try {
			Map<String, Object> map = loginCommomService.alertPassword(userNo, oldPwd, newPwd);
			// 修改用户不存在
			if (map.get("code").equals("0000")) {
				respones.setMessage("修改用户不存在");
				respones.setResult(false);
			}
			// 旧密码验证不正确
			else if (map.get("code").equals("1111")) {
				respones.setMessage("旧密码验证不正确");
				respones.setResult(false);
			} else {
				respones.setMessage("修改成功");
				respones.setResult(true);
			}
		} catch (Exception e) {
			respones.setResult(false);
			respones.setMessage("修改失败");
			e.printStackTrace();
		}
		respones.setUrl("/merchants/alertPwd");
		return respones;
	}

	/**
	 * 首页上传证件
	 */
	@RequestMapping("/picture")
	public Respones uploadPicture(HttpServletRequest request, HttpServletResponse response, MultipartFile file)
			throws Exception {
		String code = request.getParameter("lb");
		Respones respones = new Respones();
		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = req.getFile("file");
		InputStream ins = multipartFile.getInputStream();
		  JSONObject jsonObject = new JSONObject();
		  String fileId =  RandomUtil.uuid();
		  String imgName = fileId + ".jpg";
		   String fileName = null;
		try {
			   if(code.equals("1")){
				   fileName = MERCHANTS_IMG + imgName;
			   }else{
				   fileName = DEALERS_IMG + imgName;
			}
			String url = AliyunOssUtil.uploadImg(fileName, ins);
			dealersmanagerService.insertImgeName(imgName, fileId);
			jsonObject.put("url", url);
			jsonObject.put("pid", fileId);
			respones.setMessage("上传成功");
			respones.setResult(true);
			respones.setReq(jsonObject);
		} catch (Exception e) {
			respones.setResult(false);
			respones.setMessage("上传失败");
		}

		return respones;

	}

	/**
	 * 企业类型
	 */
	@PostMapping("/getEnterpriseType")
	public Respones getEnterpriseType() {
		Respones respones = new Respones();
		List<SysCodeInfo> list = new ArrayList<>();
		try {
			list = loginCommomService.getEnterpriseType();
		} catch (Exception e) {
			e.printStackTrace();
			respones.setMessage(Context.LOGIN_SELECT_FASLE);
			respones.setResult(false);
			respones.setCode(Context.REGISTER_FALSE);
		}
		respones.setReq(list);
		respones.setResult(Context.REGISTER_RESULT_TRUE);
		respones.setCode(Context.REGISTER_TRUE);

		return respones;

	}
	
	@RequestMapping("/pictureMerchants")
	public Respones uploadPictureMerchants(@RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		InputStream content = file.getInputStream();
		  Respones respones = new Respones();
		  JSONObject jsonObject = new JSONObject();
		  String fileId =  RandomUtil.uuid();
		  String imgName = fileId + ".jpg";
		   String fileName = null;
		try {
		    fileName = MERCHANTS_IMG + imgName;
			String url = AliyunOssUtil.uploadImg(fileName, content);
			dealersmanagerService.insertImgeName(imgName, fileId);
			jsonObject.put("url", url);
			jsonObject.put("pid", fileId);
			respones.setMessage("上传成功");
			respones.setResult(true);
			respones.setReq(jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
			respones.setResult(false);
			respones.setMessage("上传失败");
		}

		return respones;

	}
	//经销商。销售电话查询
	@PostMapping("/getSalePhone")
	public Respones getTelePhone( @RequestParam(value ="dealerId", required = false) String dealerId ,
			                      @RequestParam(value ="merId" ,required = false) String merId){
		       Respones respones = new Respones();
		       JSONObject jsonObject = new  JSONObject();
		      if(StringUtils.isBlank(dealerId)){
		    	//查询销售电话   商户
		    	  List<Map<String, Object>> list= new ArrayList<>();
		    	  list = loginCommomService.getmeriPHONE(merId);  
		    	   if(list==null){
		    		   jsonObject.put("merSaPh", "");  
		    	   }
		    	   else{
		    		   jsonObject.put("merSaPh", list);
		    	   }
		    	    respones.setResult(true);
			    	respones.setMessage("查询成功");
			    	respones.setReq(jsonObject);
		      }else{
		    	//查询销售电话   经销商 
		    	  List<Map<String, Object>> list= new ArrayList<>();
		    	  list= loginCommomService.getdealeriPHONE(dealerId);
		    	  if(list==null){
		    		  jsonObject.put("dealerSaPh", "");
		    	  }else{
		    		  jsonObject.put("dealerSaPh", list);  
		    	  }
		    	  respones.setResult(true);
		    	  respones.setMessage("查询成功");
		    	  respones.setReq(jsonObject);
		      }
		return respones;
	}
	/**
	 * 经销商平台登录
	 */
	@PostMapping("/loginPlatform")
	public Respones loginPlatform(
			HttpServletRequest request){
		    String username = request.getParameter("username");
		    String pwd = request.getParameter("pwd");
		    String inputCode = request.getParameter("inputCode");
		    Respones respones = new Respones();
		    String IP = "";
			SysLoginLog loginLog = new SysLoginLog();

			// 获取登录的IP地址
			try {
				IP = HttpHelper.getIpAddr(request);
				loginLog.setIp(IP);
			} catch (Exception e) {
				e.printStackTrace();
			}
		    HttpSession session = request.getSession();
            String code = String.valueOf(session.getAttribute("inpucode"));
			Map<String, Object> info = loginCommomService.loginPlatform(username,pwd,inputCode,code);
			String codeinfo = info.get("code").toString();
			String status = info.get("staus").toString();
			// 登录成功
			if (Context.REGISTER_TRUE.equals(status)) {
				respones.setResult(true);
				// 设置登录日志状态
				loginLog.setUserId(info.get("userNo").toString());
				loginLog.setStatus(Context.LOGIN_RESULT_SUCCESS);
				loginLogService.insertLoginLog(loginLog);
				info.put("userName",username);
				respones.setReq(info);
			}
			// 登录失败
			else {
				    respones.setResult(false);
					respones.setReq(info);
					loginLog.setStatus(Context.LOGIN_RESULT_FAIL);
			}
			
			respones.setCode(info.get("code").toString());
			respones.setMessage(info.get("msg").toString());
			respones.setUrl("/dealers/login");
		return respones;
	}
	
	/*
	 * 获取验证码接口
	 */
	@RequestMapping("/getCode")
	public Respones loginPlatform(HttpServletRequest request, HttpServletResponse response){
		Respones  respones = new Respones();
		ImageChack imge = new ImageChack();
		     try {
		    	 imge.doGet(request, response);
		    	 respones.setResult(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return respones;
	}
}
