package com.lemoncome.watchdog.riskbatch.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lemoncome.watchdog.riskbatch.mapper.BigDataSubmitMapper;
import com.lemoncome.watchdog.riskbatch.util.RemoteClientHelper;

@Service
public class LemonProdIndcQueryService {
	
	@Autowired
	private BigDataSubmitMapper bdA5Mapper;

	private static final Logger logger = LoggerFactory.getLogger(LemonProdIndcQueryService.class);

	public void startService() {
		String requstUrl = bdA5Mapper.queryParam("prodIndcQueryUrl");
		Map<String, String> bodyMap = new HashMap<String, String>();
		bodyMap.put("userid", "FfCMzBTaPTQodJBg7EIK2g==");
		try {
			logger.info("############### 大数据A7结果指标明细查询(S) ##################");
			String result = RemoteClientHelper.invokePost(requstUrl, bodyMap);
			logger.info("===");
			logger.info("=== result：" + JSONObject.toJSONString(result));
			logger.info("===");
			logger.info("############### 大数据A7结果指标明细查询(E) ##################");
			System.out.println(JSONObject.toJSON(result));
		} catch (Exception e) {
			logger.info("############### 大数据A7结果指标明细查询异常(S) ##################");
			logger.info("===");
			logger.info("===");
			logger.info("=== bodyMap：" + JSONObject.toJSONString(bodyMap));
			logger.info("===");
			logger.info("===");
			logger.info("=== 异常描述："+ JSONObject.toJSONString(e.getMessage()));
			logger.info("===");
			logger.info("############### 大数据A7结果指标明细查询异常(E) ##################");
		}
	}


}
