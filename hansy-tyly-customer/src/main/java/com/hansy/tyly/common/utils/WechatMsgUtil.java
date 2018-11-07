package com.hansy.tyly.common.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;





import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.hansy.tyly.admin.system.service.SysUserRoleMenuService;
import com.hansy.tyly.admin.utils.AppPropertiesUtil;
import com.hansy.tyly.common.orders.pojo.OrderNewDemo;
import com.hansy.tyly.merchants.WeChat.WeChat;
import com.hansy.tyly.merchants.WeChat.WechatConfig;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 微信模板消息推送
 * @author Administrator
 *
 */
@Service
public class WechatMsgUtil {
	@Resource
	private WeChat weChat;
	@Resource
	private SysUserRoleMenuService sysUserRoleMenuService;
	
	private static final String MESSAGE_URL = AppPropertiesUtil.getValue("wechat.MESSAGE_URL");
    
	private static final String MESSAGE_URLjJXS = AppPropertiesUtil.getValue("wechat.MESSAGE_URLjJXS");
    //经销商
	private static final String Template_id = AppPropertiesUtil.getValue("code.Template_id");
     //商户
	private static final String Template_id_sh = AppPropertiesUtil.getValue("code.Template_id_sh");
	
	private static final String turn_off_mer = AppPropertiesUtil.getValue("boolean.turn_off_mer");
	
	private static final String turn_off_dealer = AppPropertiesUtil.getValue("boolean.turn_off_dealer");

	/**
	 * 组装请求数据 
	 * 获取token
	 */
	public String getPostInfo(OrderNewDemo orderNewDemo){
		if(turn_off_mer.equals("false")){
			
			return null;
		}else{
		 String accessToken  = WechatConfig.getToken();
		  if(StringUtils.isBlank(accessToken)){
			    accessToken = weChat.getToken();
				WechatConfig.setToken(accessToken);
		  }
		TempData body1=new TempData();
		body1.setValue(orderNewDemo.getOrder().getOrderNo());
		body1.setColor("#173177");
		TempData body2=new TempData();
		String amt = orderNewDemo.getOrder().getTotalAmt().toString()+"元";
		body2.setValue(amt);
		body2.setColor("#173177");
		TempData body3=new TempData();
		body3.setValue(orderNewDemo.getOrder().getContactAddre());
		body3.setColor("#173177");
		TempData body4=new TempData();
		String code =orderNewDemo.getOrder().getTransStatus();
		String status = sysUserRoleMenuService.getCodeNameByValue(code);
		body4.setValue(status);
		body4.setColor("#173177");
		TempData body5=new TempData();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		body5.setValue(sdf.format(orderNewDemo.getOrder().getTransDate()));
		body5.setColor("#173177");
		TempData body6=new TempData();
		body6.setValue("本人");
		body5.setColor("#173177");
        Map<String, TempData> data=new HashMap<String, TempData>();
        data.put("keyword1", body1);
        data.put("keyword2", body2);
        data.put("keyword3", body3);
        data.put("keyword4", body4);
        data.put("keyword5", body5);
        data.put("keyword6", body6);
	    BaseTempMsgBO baseTempMsgBO = new BaseTempMsgBO();
	    baseTempMsgBO.setData(data);
	    baseTempMsgBO.setTemplate_id(Template_id_sh);
	    String openid = orderNewDemo.getUserBaseInfo().getWxNo();
	    baseTempMsgBO.setTouser(openid);
	    baseTempMsgBO.setUrl("");
	    String jsonString = JSONObject.toJSONString(baseTempMsgBO);
        System.out.println(jsonString);
		String result = this.doPostJson(MESSAGE_URL+accessToken, jsonString);
		return result;
		}
	}
   /*
    * 经销商
    */
	public String getPostInfo1(OrderNewDemo orderNewDemo){
		if(turn_off_dealer.equals("false")){
			
			return null;
		}else{
		 String accessToken  = WechatConfig.getJxsToken();
		  if(StringUtils.isBlank(accessToken)){
			    accessToken = weChat.getTokenXC();
				WechatConfig.setToken(accessToken);
		  }
	    TempData body=new TempData();
		body.setValue(orderNewDemo.getOrder().getOrderNo());
		body.setColor("#173177");
		TempData body1=new TempData();
		String amt = orderNewDemo.getOrder().getTotalAmt().toString()+"元";
	    body1.setValue(amt);
	    body1.setColor("#173177");
		TempData body2=new TempData();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		body2.setValue(sdf.format(orderNewDemo.getOrder().getTransDate()));
		body2.setColor("#173177");
		TempData body3=new TempData();
		String code =orderNewDemo.getOrder().getTransStatus();
		String status = sysUserRoleMenuService.getCodeNameByValue(code);
		body3.setValue(status);
		body3.setColor("#173177");
		TempData body4=new TempData();
		if(orderNewDemo.getSaleOrBuy().equals("0")){
			//购买
		body4.setValue("购买订单");
		body4.setColor("#173177");	
		}else {
			body4.setValue("出售订单");
			body4.setColor("#173177");	
		}
        Map<String, TempData> data=new HashMap<String, TempData>();
        data.put("keyword1", body);
        data.put("keyword2", body1);
        data.put("keyword3", body2);
        data.put("keyword4", body3);
        data.put("keyword5", body4);
	    BaseTempMsgXC baseTempMsgBO = new BaseTempMsgXC();
	    baseTempMsgBO.setData(data);
	    baseTempMsgBO.setTemplate_id(Template_id);
	    baseTempMsgBO.setTouser(orderNewDemo.getUserBaseInfo().getWxNo());
	    baseTempMsgBO.setPage(orderNewDemo.getPage());
	    baseTempMsgBO.setForm_id(orderNewDemo.getFormId());
	    String jsonString = JSONObject.toJSONString(baseTempMsgBO);
        System.out.println(jsonString);
		String result = this.doPostJson(MESSAGE_URLjJXS+accessToken, jsonString);
		System.out.println("============result"+result+"============================");
		return result;}
	}
	/**
	 * 发送请求
	 * @param url
	 * @param jsonStr
	 * @return
	 */
	  public  String doPostJson(String url,String jsonStr) {
	        // 创建Httpclient对象  
			CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(null).setDefaultRequestConfig(null).build();
	        String resultString = "";  
	        try {
	            HttpPost httpPost = new HttpPost(url);
	            if(StringUtils.isNotEmpty(jsonStr)){
					StringEntity entity = new StringEntity(jsonStr,"utf-8");
					httpPost.setEntity(entity);
					entity.setContentType("application/json");
				}
	            httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
	            // 执行http请求  
	            CloseableHttpResponse response = httpClient.execute(httpPost);
	            try {
	            	if(response.getEntity()!=null && response.getStatusLine().getStatusCode() == 200 ){
	            		resultString = EntityUtils.toString(response.getEntity(),Consts.UTF_8);
	            		EntityUtils.consume(response.getEntity());
	            	}
				} finally {
					response.close();
				}
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	        	try {
					httpClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	        return resultString;  
	  }

}
