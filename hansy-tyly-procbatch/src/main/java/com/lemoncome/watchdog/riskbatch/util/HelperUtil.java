package com.lemoncome.watchdog.riskbatch.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class HelperUtil {
	/**
	 * TODO 请求大数据接口
	 * @param custCertInfoRespJson
	 * @param phone
	 * @throws Exception
	 */
	public static String doRequestBigDataValidate(String url,
			Map<String, String> paramMap) throws Exception {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("appKey", "lemon");
		String result = RemoteClientHelper.invokePostIgnoreException(url,headers, paramMap);
		System.out.println("#############################################");
		System.out.println("==");
		System.out.println("=="+JSONObject.toJSONString(result));
		System.out.println("==");
		System.out.println("#############################################");
		return result;
	}

}
