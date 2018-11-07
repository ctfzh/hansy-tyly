package com.hansy.tyly.merchants.WeChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hansy.tyly.admin.base.controller.BaseController;
import com.hansy.tyly.admin.utils.AppPropertiesUtil;
import com.hansy.tyly.common.utils.Context;
import com.hansy.tyly.merchants.mer.service.MerchantsMangerService;
import com.hansy.tyly.merchants.orders.dao.model.TuserBaseInfo;
import com.hansy.tyly.merchants.orders.service.LoginMerchantsService;

@Controller
public class WechatLogin extends BaseController {
	@Autowired
	private MerchantsMangerService merchantsMangerService;
	@Resource
	private WechatConfig wechatConfig;
	@Autowired
	private LoginMerchantsService loginMerchantsService;

	private static final String MERCHANTS_WX_URL = AppPropertiesUtil.getValue("wechat.MERCHANTS_WX_URL");

	private static final String MERCHANTS_APPID = AppPropertiesUtil.getValue("code.MERCHANTS_APPID");
	
	private static final String MERCHANTS_SECRET = AppPropertiesUtil.getValue("code.MERCHANTS_SECRET");
    
	private static final String USER_URL = AppPropertiesUtil.getValue("wechat.USER_URL");

	@RequestMapping(value = "/merchants/auth2/{unLoginURIKey}/{loginURIKey}", method = RequestMethod.GET)
	public void authorize(@PathVariable String unLoginURIKey,
			@PathVariable String loginURIKey, HttpServletRequest request,
			 HttpServletResponse response)
			throws IOException {
		String code = request.getParameter("code");// 获取授权code代码
		String requestUrl = MERCHANTS_WX_URL.replace("${appid}", MERCHANTS_APPID)
				           .replace("${secret}", MERCHANTS_SECRET)
				           .replace("${code}", code);
		String result = GET(requestUrl);
		// 定义一个json对象。
		JSONObject convertvalue = new JSONObject();
		// 将得到的字符串转换为json
		convertvalue = (JSONObject) JSON.parse(result);
		convertvalue.put("result", true);
		String openId = (String) convertvalue.get("openid");
		//获取用户头像入库
		  String token = WechatConfig.getToken();
		   if(StringUtils.isBlank("token")){
			   token = wechatConfig.getToken();
			   WechatConfig.setToken(token);
		   }
		   
	     System.out.println("====================");
		 System.out.println("token:"+token);
		 System.out.println("code:"+code);
		 System.out.println("requestUrl:"+requestUrl);
		 System.out.println("result:"+result);
		 System.out.println("openId:"+openId);
		 System.out.println("====================");
		 String userUrl = USER_URL.replace("ACCESS_TOKEN", token)
					.replace("OPENID", openId);
		 String resultInfo = GET(userUrl);
		// 定义一个json对象。
		JSONObject convertvalueINFO = new JSONObject();
			// 将得到的字符串转换为json
		convertvalueINFO = (JSONObject) JSON.parse(resultInfo);
		convertvalueINFO.put("result", true);
		String headimgurl = (String) convertvalueINFO.get("headimgurl");
		HttpSession sessi = request.getSession();
		sessi.setAttribute("openId", openId);
		  // 获取openID
		List<TuserBaseInfo> list = new ArrayList<>();
		try {
			list = merchantsMangerService.selectIstrue(openId);
			String userNo = null;
			String userStatus =null;
			for (TuserBaseInfo staus : list) {
				userNo = staus.getUserNo();
				userStatus = staus.getUserStatus();

			}
			if (list.isEmpty()) {
				response.sendRedirect("https://ysp.hansyinfo.com/index.html#/"+unLoginURIKey+"?userNo="+userNo);
			} else {
				//入库用户信息
		       System.out.println("==================headimgurl="+headimgurl+"=============================");
		       System.out.println("==================headimgurl="+headimgurl+"=============================");
		       System.out.println("==================headimgurl="+headimgurl+"=============================");
		       System.out.println("==================headimgurl="+headimgurl+"=============================");
				if(!StringUtils.isEmpty("headimgurl")){
					loginMerchantsService.updateUserImge(headimgurl,userNo);
				}
				if (userStatus.equals(Context.USER_STATUS_ENABLE)) {
					if (loginMerchantsService.getMerStus(userNo).get("addr").toString().equals("1")) {
						response.sendRedirect("https://ysp.hansyinfo.com/index.html#/authview?userNo="+userNo);
						// 用户已注册。未注册信息
					} else {
						if (loginMerchantsService.getMerStus(userNo).get("stus").toString().equals(Context.USER_STATUS_ENABLE_WW)) {
							//待审核
							response.sendRedirect("https://ysp.hansyinfo.com/index.html#/waitAuthview/3");
						}
						if (loginMerchantsService.getMerStus(userNo).get("stus").toString().equals(Context.USER_STATUS_ENABLE)) {
							// 用户已注册。审核通过
							response.sendRedirect("https://ysp.hansyinfo.com/index.html#/"+loginURIKey+"?userNo="+userNo);
						}
						if (loginMerchantsService.getMerStus(userNo).get("stus").toString().equals(Context.USER_STATUS_ENABLE_ff)) {
							response.sendRedirect("https://ysp.hansyinfo.com/index.html#/waitAuthview/5");
							 // 用户已注册。审核不通过
						}

					}
					
				}else{
					//账户被冻结
					response.sendRedirect("https://ysp.hansyinfo.com/index.html#/waitAuthview/6");
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

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
	public static void main(String[] args) {
		System.err.println(MERCHANTS_WX_URL);
	}
}
