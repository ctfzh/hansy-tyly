package com.hansy.tyly.admin.sale.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hansy.tyly.admin.utils.Context;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hansy.tyly.admin.base.bean.UserProfile;
import com.hansy.tyly.admin.base.controller.BaseController;
import com.hansy.tyly.admin.dao.model.SysUser;
import com.hansy.tyly.admin.sale.service.LoginSaleService;
import com.hansy.tyly.admin.system.service.SysUserRoleMenuService;
import com.hansy.tyly.admin.utils.PasswordUtil;
import com.hansy.tyly.core.command.CryptoCmd;
@RestController
@RequestMapping("/sales")
public class LoginSaleController extends BaseController {
	@Autowired
	private LoginSaleService loginSaleService;
	
	 @Autowired
	 private SysUserRoleMenuService sysUserRoleMenuService;
/**
 * 销售通过openId登录
 */@PostMapping("/LoginSaleOpenId")
	 public CryptoCmd LoginSale(@RequestParam(value = "code", required = false) String code,HttpServletRequest request){
	        String openId = getOpenid(code);
		    JSONObject jsonObject = new JSONObject();
		    CryptoCmd cryptoCmd = new CryptoCmd();
		    jsonObject.put("openId", openId);
		    List<SysUser> list = new ArrayList<>();
		    String loginId = "";
		    String pwd = "";
	        String ip="";
		  try {
			list= loginSaleService.getSaleUserInfo(openId);
			if(null == list || list.size() ==0){
				cryptoCmd.setSuccess(false);
				cryptoCmd.setMessage("登录失败");
				cryptoCmd.setOut(jsonObject);
			}else {
				for(SysUser sysUser : list){
					jsonObject.put("userNo", sysUser.getUserId());
					loginId = sysUser.getLoginId();
					pwd= sysUser.getUserPwd();
				}
				 //md5解密
				jsonObject.put("openId", openId);

	            ip=getIpAddr(request);
	            sysUserRoleMenuService.login(loginId, pwd, ip);
				cryptoCmd.setMessage("登录成功");
				//是否登录状态
		        Boolean loginStatus = super.isLoginStatus();
		        if (loginStatus) {
		            UserProfile currentUserProfile = super.getCurrentUserProfile();
		            cryptoCmd.setOut("currentUserProfile", currentUserProfile);
		        }
				cryptoCmd.setOut(jsonObject);
				cryptoCmd.setSuccess(true);
			}
		} catch (Exception e) {
		  e.printStackTrace();
		  cryptoCmd.setSuccess(false);
		  cryptoCmd.setMessage("登录失败");
		 cryptoCmd.setOut(jsonObject);
		}
		return cryptoCmd;
	 }
	 /**
	  * 通过验证密码登录
	  * @param getcode
	  * @return
	  */
    @PostMapping("/LoginSale")
    public  CryptoCmd loginPwd(@RequestParam("loginId") String loginId,
    		                   @RequestParam("pwd") String pwd,
    		                   @RequestParam("openId") String openId,
    		                 HttpServletRequest request){
          String ip="";
		  CryptoCmd cryptoCmd = new CryptoCmd();

         try {
              ip=getIpAddr(request);
         } catch (Exception e) {
             e.printStackTrace();
         }
         	    sysUserRoleMenuService.login(loginId, pwd, ip);
         	 //是否登录状态
		        Boolean loginStatus = super.isLoginStatus();
		        if (loginStatus) {
		            UserProfile currentUserProfile = super.getCurrentUserProfile();
		            cryptoCmd.setOut("currentUserProfile", currentUserProfile);
		        }
               //跟新用户OPEN ID
                sysUserRoleMenuService.updateSaleId(loginId, openId);
                
         return cryptoCmd;
    	
    }
	 
	 public static String getOpenid(@RequestParam(value="code",required=false) String getcode) {//接收用户传过来的code，required=false表明如果这个参数没有传过来也可以。
	   String code=getcode;
	   String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
	   String requestUrl = WX_URL.replace("APPID", "wxe7ec44b6e129913f").//填写自己的appid
	   replace("SECRET", "b6cbc6a1b3a4da99662e64e384c81a09").replace("JSCODE", code).//填写自己的appsecret，
	   replace("authorization_code", "authorization_code");
	    String  returnvalue=GET(requestUrl);
	    System.out.println(requestUrl);//打印发起请求的url
	    System.out.println(returnvalue);//打印调用GET方法返回值
	    JSONObject convertvalue=new JSONObject();
	    convertvalue=(JSONObject) JSON.parse(returnvalue);
	    convertvalue.put("result", true);
	    String openid = (String)convertvalue.get("openid");
	  System.out.println("return openid is ："+(String)convertvalue.get("openid")); //打印得到的openid
	  System.out.println("return sessionkey is ："+(String)convertvalue.get("session_key"));//打印得到的sessionkey，
	  return openid;
	}
	public static String GET(String url) {
		String result = "";
		BufferedReader in = null;
		InputStream is = null;
		InputStreamReader isr = null;
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.connect();
			Map<String, List<String>> map = conn.getHeaderFields();
			is = conn.getInputStream();
			isr = new InputStreamReader(is);
			in = new BufferedReader(isr);
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			// 异常记录
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (is != null) {
					is.close();
				}
				if (isr != null) {
					isr.close();
				}
			} catch (Exception e2) {
				// 异常记录
			}
		}
		return result;
	}
	
	 public static String getIpAddr(HttpServletRequest request) throws Exception{
	        String ip = request.getHeader("X-Real-IP");
	        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
	            return ip;
	        }
	        ip = request.getHeader("X-Forwarded-For");
	        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
	        // 多次反向代理后会有多个IP值，第一个为真实IP。
	            int index = ip.indexOf(',');
	            if (index != -1) {
	                return ip.substring(0, index);
	            } else {
	                return ip;
	            }
	        } else {
	            return request.getRemoteAddr();
	        }
	    }
	 
	 public CryptoCmd getLoginStatus(CryptoCmd cryptoCmd) {
	        Boolean loginStatus = super.isLoginStatus();
	        if (loginStatus) {
	            UserProfile currentUserProfile = super.getCurrentUserProfile();
	            cryptoCmd.setOut("currentUserProfile", currentUserProfile);
	        }
	        cryptoCmd.setOut("loginStatus", loginStatus);
	        return cryptoCmd;
	    }

	@ApiOperation(value = "销售获取报表之拜访汇总", notes = "销售获取报表")
	@ResponseBody
	@RequestMapping(value = "/getReport", method = RequestMethod.POST)
	public CryptoCmd getReportOfVisit(@RequestParam String saleNo,
									 @RequestParam(required = false) Integer num,
									 @RequestParam String dateType,
									 @RequestParam(required = false) String date) {
		CryptoCmd cmd=new CryptoCmd();
		try{
			Map<String,Object> list=loginSaleService.getReportOfVisit(saleNo,num,dateType,date);
			cmd.setOut(list);
			cmd.setSuccess(true);
		}catch (Exception e){
			e.printStackTrace();
			cmd.setMessage(Context.GET_LIST_FALSE);
			cmd.setSuccess(false);
		}
		return cmd;
	}
	@ApiOperation(value = "销售获取报表之业绩信息", notes = "销售获取报表")
	@ResponseBody
	@RequestMapping(value = "/getReportOfAchievement", method = RequestMethod.POST)
	public CryptoCmd getReportOfAchievement(@RequestParam String saleNo,
										   @RequestParam(required = false) Integer num,
										   @RequestParam String dateType,
										   @RequestParam(required = false) String date) {
		CryptoCmd cmd=new CryptoCmd();
		try{

			Map<String,Object> list=loginSaleService.getReportOfAchievement(saleNo,num,dateType,date);
			cmd.setOut(list);
			cmd.setSuccess(true);
		}catch (Exception e){
			e.printStackTrace();
			cmd.setMessage(Context.GET_LIST_FALSE);
			cmd.setSuccess(false);
		}
		return cmd;
	}
	@ApiOperation(value = "销售获取报表之部门销售总数据之销售客户数", notes = "销售获取报表")
	@ResponseBody
	@RequestMapping(value = "/getReportOfUserCount", method = RequestMethod.POST)
	public CryptoCmd getReportOfUserCount(
			                             @RequestParam String saleNo,
			                             @RequestParam(required = false) Integer num,
										 @RequestParam String dateType,
										 @RequestParam(required = false) String date) {
		CryptoCmd cmd=new CryptoCmd();
		try{
			Map<String,Object> list=loginSaleService.getReportOfUserCount(saleNo,num,dateType,date);
			cmd.setOut(list);
			cmd.setSuccess(true);
		}catch (Exception e){
			e.printStackTrace();
			cmd.setMessage(Context.GET_LIST_FALSE);
			cmd.setSuccess(false);
		}
		return cmd;
	}

	@ApiOperation(value = "销售获取报表之部门销售总数据之销售客户消费金额", notes = "销售获取报表")
	@ResponseBody
	@RequestMapping(value = "/getReportOfConsume", method = RequestMethod.POST)
	public CryptoCmd getReportOfConsume(
			@RequestParam String saleNo,
			@RequestParam(required = false) Integer num,
			@RequestParam String dateType,
			@RequestParam(required = false) String date) {
		CryptoCmd cmd=new CryptoCmd();
		try{
			Map<String,Object> list=loginSaleService.getReportOfConsume(saleNo,num,dateType,date);
			cmd.setOut(list);
			cmd.setSuccess(true);
		}catch (Exception e){
			e.printStackTrace();
			cmd.setMessage(Context.GET_LIST_FALSE);
			cmd.setSuccess(false);
		}
		return cmd;
	}


}
