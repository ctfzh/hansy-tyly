package com.hansy.tyly.customer.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.hansy.tyly.customer.system.mapper.SysParameterMapper;

/**
 * @Author:YuanYan
 * @CreateTime:2017年11月13日下午1:52:21
 * @Description:阿里Dysmsapi短信发送工具类
 */
public class ALiDysmsHelper {
	//初始化ascClient需要的几个参数
	public static final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
	public static final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
	public static final HashMap<String, String> smsCodes;
	private SysParameterMapper sysParaMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(ALiDysmsHelper.class);
	
	static{
		//设置超时时间-可自行调整
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		//短信发送异常错误码
		smsCodes = new HashMap<String, String>();
		smsCodes.put("isp.RAM_PERMISSION_DENY", "RAM权限DENY");
		smsCodes.put("isv.OUT_OF_SERVICE", "业务停机");
		smsCodes.put("isv.PRODUCT_UN_SUBSCRIPT", "未开通云通信产品的阿里云客户");
		smsCodes.put("isv.PRODUCT_UNSUBSCRIBE", "产品未开通");
		smsCodes.put("isv.ACCOUNT_NOT_EXISTS", "账户不存在");
		smsCodes.put("isv.ACCOUNT_ABNORMAL", "账户异常");
		smsCodes.put("isv.SMS_TEMPLATE_ILLEGAL", "短信模板不合法");
		smsCodes.put("isv.SMS_SIGNATURE_ILLEGAL", "短信签名不合法");
		smsCodes.put("isv.INVALID_PARAMETERS", "参数异常");
		smsCodes.put("isp.SYSTEM_ERROR", "系统错误");
		smsCodes.put("isv.MOBILE_NUMBER_ILLEGAL", "非法手机号");
		smsCodes.put("isv.MOBILE_COUNT_OVER_LIMIT", "手机号码数量超过限制");
		smsCodes.put("isv.TEMPLATE_MISSING_PARAMETERS", "模板缺少变量");
		smsCodes.put("isv.BUSINESS_LIMIT_CONTROL", "业务限流");
		smsCodes.put("isv.INVALID_JSON_PARAM", "JSON参数不合法，只接受字符串值");
		smsCodes.put("isv.BLACK_KEY_CONTROL_LIMIT", "黑名单管控");
		smsCodes.put("isv.PARAM_LENGTH_LIMIT", "参数超出长度限制");
		smsCodes.put("isv.PARAM_NOT_SUPPORT_URL", "不支持URL");
		smsCodes.put("isv.AMOUNT_NOT_ENOUGH", "账户余额不足");
	}
	
	public ALiDysmsHelper() {
	}
	
	public ALiDysmsHelper(SysParameterMapper mapper) {
		this.sysParaMapper = mapper;
	}
	
	/**
	 * @Author:YuanYan
	 * @CreateAt:2017年11月13日下午3:23:33
	 * @Params:短信模板ID，待发送手机号(多个以","间隔)，模板中变量对应的值
	 * @Return:
	 * @Description:阿里短信发送
	 */
	public boolean smsSend(String smsTemCode,String phoneNos,Map<String, String> smsValues){
		String accessKeyId  = sysParaMapper.getParamByParaId("aliYunAccessKeyId");
		String accessKeySecret  = sysParaMapper.getParamByParaId("aliYunAccessKeySecret");
		String smsSignName  = sysParaMapper.getParamByParaId("smsSignName");
		//初始化ascClient,暂时不支持多region（请勿修改）
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,accessKeySecret);
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);
			// 组装请求对象
			SendSmsRequest aliRequst = new SendSmsRequest();
			// 使用post提交
			aliRequst.setMethod(MethodType.POST);
			// 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
			aliRequst.setPhoneNumbers(phoneNos);
			// 必填:短信签名-可在短信控制台中找到
			aliRequst.setSignName(smsSignName);
			// 必填:短信模板-可在短信控制台中找到
			aliRequst.setTemplateCode(smsTemCode);
			// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
			// 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
			aliRequst.setTemplateParam(JSONObject.toJSONString(smsValues));
			logger.info("===================阿里云短信发送执行(S)====================");
			logger.info("##");
			logger.info("## 发送信息：短信模板 | 手机号 | 短信Map内容 "+smsTemCode+" , "+phoneNos+" , "+JSONObject.toJSONString(smsValues));
			logger.info("##");
			//请求失败这里会抛ClientException异常
			SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(aliRequst);
			logger.info("## 返回信息："+JSONObject.toJSONString(sendSmsResponse));
			logger.info("##");
			logger.info("===================阿里云短信发送执行(E)====================");
			if (sendSmsResponse.getCode() == null ||  !"OK".equals(sendSmsResponse.getCode())) {
				//发送失败
				return false;
			}
		} catch (ClientException e) {
			logger.info("===================阿里云短信发送异常(S)====================");
			logger.info("##");
			logger.info("##"+JSONObject.toJSONString(e.getMessage()));
			logger.info("##");
			logger.info("===================阿里云短信发送异常(E)====================");
			return false;
		}
		return true;
	}

}
