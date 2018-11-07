package com.hansy.tyly.merchants.WeChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hansy.tyly.admin.utils.AppPropertiesUtil;
import com.hansy.tyly.common.utils.Respones;
import com.hansy.tyly.merchants.mer.service.MerchantsMangerService;


@RestController
@RequestMapping("/merchants")
public class WeChat {

	@Resource
	public MerchantsMangerService merchantsMangerService;
	
	private static final String TOKEN = AppPropertiesUtil.getValue("code.TOKEN");
	
	private static final String WX_URL = AppPropertiesUtil.getValue("wechat.WX_URL");
	
	private static final String DEALER_APPID = AppPropertiesUtil.getValue("code.DEALER_APPID");
	
	private static final String DEALER_SECRET = AppPropertiesUtil.getValue("code.DEALER_SECRET");
	
	private static final String GETPAGEA_URL = AppPropertiesUtil.getValue("wechat.GETPAGEA_URL");

	private static final String MERCHANTS_APPID = AppPropertiesUtil.getValue("code.MERCHANTS_APPID");
	
	private static final String MERCHANTS_SECRET = AppPropertiesUtil.getValue("code.MERCHANTS_SECRET");
    
	private static final String GETTOKEN_URL = AppPropertiesUtil.getValue("wechat.GETTOKEN_URL");



	/**
	 *微信端 获取session_key .open_id
	 * @return 
	 * @return 
	 */

	@RequestMapping("/weChat/code")
	public static String getOpenid(@RequestParam(value="code",required=false) String getcode,
			                       @RequestParam(value="lb",required=false) String lb) {//接收用户传过来的code，required=false表明如果这个参数没有传过来也可以。
		  String code=getcode;
		  String APPID ="";
		  String SECRET ="";
		  String requestUrl = "";
		 if(lb.equals("2")){
			 //经销商
			   requestUrl = WX_URL.replace("APPID", DEALER_APPID).//填写自己的appid
			   replace("SECRET", DEALER_SECRET).replace("JSCODE", code).//填写自己的appsecret，
			   replace("authorization_code", "authorization_code");
		 }
	  
	  
	  //调用get方法发起get请求，并把返回值赋值给returnvalue
	    String  returnvalue=GET(requestUrl);
	    System.out.println(requestUrl);//打印发起请求的url
	    System.out.println(returnvalue);//打印调用GET方法返回值
	    //定义一个json对象。 
	    JSONObject convertvalue=new JSONObject();
	    //将得到的字符串转换为json
	    convertvalue=(JSONObject) JSON.parse(returnvalue);
	    convertvalue.put("result", true);
	    String openid = (String)convertvalue.get("openid");
	  System.out.println("return openid is ："+(String)convertvalue.get("openid")); //打印得到的openid
	  System.out.println("return sessionkey is ："+(String)convertvalue.get("session_key"));//打印得到的sessionkey，
	  //把openid和sessionkey分别赋值给openid和sessionkey
	//  String openid=(String) convertvalue.get("openid");
	//  String sessionkey=(String) convertvalue.get("session_key");//定义两个变量存储得到的openid和session_key.
	  return openid;//返回openid
	}




	@RequestMapping("/weChat/getToken")
	public String getToken(String signature,String timestamp,String nonce,String echostr) throws NoSuchAlgorithmException, IOException{
	    // 将token、timestamp、nonce三个参数进行字典序排序 
		System.out.println("signature:"+signature);
		System.out.println("timestamp:"+timestamp);
		System.out.println("nonce:"+nonce);
		System.out.println("echostr:"+echostr);
		System.out.println("TOKEN:"+TOKEN);
	    String[] params = new String[] { TOKEN, timestamp, nonce };
	    Arrays.sort(params);
	    // 将三个参数字符串拼接成一个字符串进行sha1加密
	    String clearText = params[0] + params[1] + params[2];
	    String algorithm = "SHA-1";
	    String sign = new String(  
	    		Hex.encodeHex(MessageDigest.getInstance(algorithm).digest((clearText).getBytes()), true));  
	    // 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信  
	    if (signature.equals(sign)) {  
	    	return echostr;
	    }  
	    return echostr;
	}


   //发起get请求的方法。
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
/**
 * 签名验证
 * @throws NoSuchAlgorithmException 
 */
@RequestMapping("/weChat/sign")
public Respones getAccessToken(HttpServletRequest requesturl) throws NoSuchAlgorithmException{
	  Respones respones = new Respones();
	  String url = requesturl.getParameter("url");
	  System.out.println(url);
	 String requestUrl = GETPAGEA_URL.replace("APPID",MERCHANTS_APPID).replace("SECRET", MERCHANTS_SECRET);
	 String  returnvalue=GET(requestUrl);
	 JSONObject convertvalue=new JSONObject();
	 convertvalue=(JSONObject) JSON.parse(returnvalue);
	 convertvalue.put("result", true);
	 String accessToken  = WechatConfig.getToken();
	  if(StringUtils.isBlank(accessToken)){
		    accessToken = getToken();
			WechatConfig.setToken(accessToken);
	  }
	 String ticket = JsapiTicket(accessToken);
	 String noncestr = UUID.randomUUID().toString();//随机字符串
	 String timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳
	 String str = "jsapi_ticket="+ticket+"&noncestr="+noncestr+"&timestamp="+timestamp+"&url="+url;
	 MessageDigest crypt = MessageDigest.getInstance("SHA-1");
     crypt.reset();
     try {
		crypt.update(str.getBytes("UTF-8"));
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    String  signature = byteToHex(crypt.digest());
    Map<String, Object> map = new HashMap<>();
    map.put("timestamp",timestamp);
    map.put("accessToken",accessToken);
    map.put("ticket",ticket);
    map.put("noncestr",noncestr);
    map.put("signature",signature);
    System.out.println("====================");
    System.out.println(timestamp);
    System.out.println(accessToken);
    System.out.println(ticket);
    System.out.println(noncestr);
    System.out.println(signature);
    System.out.println("====================");
    respones.setResult(true);
    respones.setReq(map);
	 //获取tiket
	return respones;
	
}

public  String JsapiTicket(String accessToken) {
	 String requestUrl = GETTOKEN_URL.replace("ACCESS_TOKEN", accessToken);
	 String returnvalue=GET(requestUrl);
	 System.out.println("====================");
     System.out.println(accessToken);
     System.out.println(returnvalue);
     System.out.println("====================");
	 JSONObject returnvalueJson =(JSONObject) JSON.parse(returnvalue);
	 String errcode = String.valueOf(returnvalueJson.get("errcode"));
	 if(!"0".equals(errcode)){
		String token = getToken();
		return JsapiTicket(token);//递归
	 }else{
		WechatConfig.setToken(accessToken);
	 }
	 JSONObject convertvalue=new JSONObject();
	 convertvalue=(JSONObject) JSON.parse(returnvalue);
	 String ticket = (String)convertvalue.get("ticket");
  	 return ticket;
   
}

 private static String byteToHex(final byte[] hash) {
    Formatter formatter = new Formatter();
    for (byte b : hash) {
        formatter.format("%02x", b);
    }
    String result = formatter.toString();
    formatter.close();
    return result;
}
 /**
  * 公众号
  * @return
  */
  public  String getToken(){
	    String Url = GETPAGEA_URL.replace("APPID",MERCHANTS_APPID).replace("SECRET", MERCHANTS_SECRET);
		String value=WeChat.GET(Url);
		System.out.println(value);
		JSONObject convertvalue=new JSONObject();
		 convertvalue=(JSONObject) JSON.parse(value);
		  String token = (String)convertvalue.get("access_token");
		  Map<String, Object> map = new HashMap<>();
		     try {
		    	  map=merchantsMangerService.selectToken("0");
				  System.out.println(map);
				  int count = Integer.valueOf(map.get("count").toString());
				  count = count+1;
				  merchantsMangerService.updateToken(count,"0");
				  merchantsMangerService.insertToken(token,count,"0");
				  System.out.println("==========count======"+count+"==========date========"+new Date());
			} catch (Exception e) {
				e.printStackTrace();
			}
		 
		 
	      return token;
  }
  /**
   * 经销商小程序
   * @return
   */
  public  String getTokenXC(){
	    String Url = GETPAGEA_URL.replace("APPID",DEALER_APPID).replace("SECRET", DEALER_SECRET);
		String value=WeChat.GET(Url);
		System.out.println(value);
		JSONObject convertvalue=new JSONObject();
		 convertvalue=(JSONObject) JSON.parse(value);
		  String token = (String)convertvalue.get("access_token");
		  Map<String, Object> map = new HashMap<>();
		     try {
		    	  map=merchantsMangerService.selectToken("1");
				  System.out.println(map);
				  int count = Integer.valueOf(map.get("count").toString());
				  count = count+1;
				  merchantsMangerService.updateToken(count,"1");
				  merchantsMangerService.insertToken(token,count,"1");
				  System.out.println("==========count======"+count+"==========date========"+new Date());
			} catch (Exception e) {
				e.printStackTrace();
			}
		 
	      return token;
}
	  
}

