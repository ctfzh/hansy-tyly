package com.hansy.tyly.riskbatch.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.hansy.tyly.riskbatch.mapper.BigDataSubmitMapper;
import com.hansy.tyly.riskbatch.model.A7DataNode;
import com.hansy.tyly.riskbatch.model.BdDataModel;
import com.hansy.tyly.riskbatch.model.BdResultModel;
import com.hansy.tyly.riskbatch.model.TScCustDcsRst;
import com.hansy.tyly.riskbatch.model.TScIntLog;
import com.hansy.tyly.riskbatch.util.ConstantsUtil;
import com.hansy.tyly.riskbatch.util.RandomUtil;
import com.hansy.tyly.riskbatch.util.RemoteClientHelper;

/**
 * @CreateTime:2017年11月2日上午19:36:46
 * @Description:大数据A7风控结果请求服务
 */

@Service
public class LemonA7QueryService {
	
	@Autowired
	private BigDataSubmitMapper bdA7Mapper;

	private static final Logger logger = LoggerFactory.getLogger(LemonA7QueryService.class);

	public void startService() {
		/*
		 * 所有操作均为全量读取、不考虑记录数过大的问题
		 * */
		//Step01:重新全量读取待提交A7记录(含billId等信息)，组装大数据A7数据并执行提交
		this.doA7QueryStep01();
	}

	
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=120000,rollbackFor=Exception.class)
	public void doA7QueryStep01() {
		// Step03:重新全量读取待提交A5记录(含客户对应产品、地址、规则链等信息)
		String requstUrl = bdA7Mapper.queryParam("getFKScoreResultUrl");//"http://123.57.7.55:8080/getFKScoreResult";
		List<BdDataModel> bdDataList = bdA7Mapper.queryCustBillListForA7();
		int totalNum = bdDataList.size();//总条目数
		TScIntLog intLog = null;
		List<TScIntLog> logList = new ArrayList<TScIntLog>();//操作日志集合
		int tempCount = 0;
		Date currDate = new Date();
		Map<String, Object> inMap = new HashMap<String, Object>();//CustBill更新参数集合
		List<TScCustDcsRst> dscRstList = new ArrayList<TScCustDcsRst>();//决策结果集合
		StringBuilder ordersSb = new StringBuilder();
		Map<String, String> bodyMap = new HashMap<String, String>();//A7请求参数集合
		//bodyMap.put("appKey", bdA7Mapper.queryParam("appKey"));
		String appKeyMf = bdA7Mapper.queryParam("appKeyMf");
		String appKeySf = bdA7Mapper.queryParam("appKeySf");
		String result = "";//A7请求结果JSON
		List<String> orderList = new ArrayList<String>();
		for (BdDataModel bdData : bdDataList) {
			tempCount ++;
			//requstUrl = bdData.getRequstUrl();
			ordersSb.append(bdData.getOrderId());
			orderList.add(bdData.getOrderId());
			bodyMap.put("appKey", "YES".equals(bdData.getIsFree()) ? appKeyMf : appKeySf);
			try {
				//执行A7请求(A7接口一次最大可接受15条记录、最后一次循环可能不足15条)
				if(tempCount % 15 == 0 || (totalNum == tempCount)){
					bodyMap.put("orderIds", ordersSb.toString().trim());
			        result = RemoteClientHelper.invokePostIgnoreException(requstUrl, null, bodyMap);
			        ordersSb = new StringBuilder();
			        currDate = new Date();
			        BdResultModel resultModel = JSONObject.parseObject(result, BdResultModel.class);
			        if(resultModel.isSuccess()){
			        	//可能只有部分出了结果(小于等于15)
			        	List<A7DataNode> list = JSONObject.parseArray(JSONObject.toJSONString(resultModel.getData()), A7DataNode.class);
			        	for (A7DataNode dataNode : list) {
			        		//构造接口请求记录日志
			    			intLog = new TScIntLog();
			    			intLog.setSysUuid(RandomUtil.UUID());
			    			intLog.setBillId(dataNode.getOrderId());
			    			intLog.setIntType(ConstantsUtil.INT_TYPE_A7);
			    			intLog.setInsertTime(currDate);
			    			intLog.setLogState(ConstantsUtil.LOG_SUCC_CODE);
			    			intLog.setErrorReason(dataNode.getRiskName() == null ? null : dataNode.getRiskName()[0]);
			    			logList.add(intLog);
			        		//仅查询成功才更新CUST_BILL的SCHEDULE_STATUS
			        		inMap.put("billId", dataNode.getOrderId());
			        		inMap.put("exeStatus", ConstantsUtil.SCHEDULE_STATUS_A7);
							bdA7Mapper.updateCustBill(inMap);
							//构造决策结果记录 0：建议人工审核，1：建议通过，2：建议拒绝
							TScCustDcsRst dcsRst  = new TScCustDcsRst();
							dcsRst.setBillId(dataNode.getOrderId());
							dcsRst.setInsertTime(currDate);
							dcsRst.setScore(dataNode.getCreditScore());
							switch (dataNode.getCheckResult()) {
							case "0":
								dcsRst.setDcsRst(ConstantsUtil.DCS_RST_02);//警告
								break;
							case "1":
								dcsRst.setDcsRst(ConstantsUtil.DCS_RST_03);//安全
								break;
							case "2":
								dcsRst.setDcsRst(ConstantsUtil.DCS_RST_01);//风险
								break;
							default:
								dcsRst.setDcsRst(ConstantsUtil.DCS_RST_04);//暂无
								logger.info("############### 大数据A7结果有误(S) ##################");
								logger.info("===");
								logger.info("=== OrderId: "+dataNode.getOrderId());
								logger.info("===");
								logger.info("=== 不存在结果为【" + dataNode.getCheckResult()+"】的结果定义！");
								logger.info("===");
								logger.info("############### 大数据A7结果有误(E) ##################");
								break;
							}
							dscRstList.add(dcsRst);
							//移除已经出结果记录
							orderList.remove(dataNode.getOrderId());
						}
			        	//尚未出结果ordrId
			        	for (String orderId : orderList) {
			        		//构造接口请求记录日志
			    			intLog = new TScIntLog();
			    			intLog.setSysUuid(RandomUtil.UUID());
			    			intLog.setBillId(orderId);
			    			intLog.setIntType(ConstantsUtil.INT_TYPE_A7);
			    			intLog.setInsertTime(currDate);
			    			intLog.setLogState(ConstantsUtil.LOG_FAIL_CODE);
			    			logList.add(intLog);
						}
			        	
			        }else{
				        //A7请求有误(整体一条结果都不会有)
				        for (String orderId : orderList) {
				        	//构造接口请求记录日志
			    			intLog = new TScIntLog();
			    			intLog.setSysUuid(RandomUtil.UUID());
			    			intLog.setBillId(orderId);
			    			intLog.setIntType(ConstantsUtil.INT_TYPE_A7);
			    			intLog.setInsertTime(currDate);
			    			intLog.setLogState(ConstantsUtil.LOG_FAIL_CODE);
			    			logList.add(intLog);
						}
			        }
			        //批量插入请求日志
			        if(logList != null && logList.size() > 0){
			        	bdA7Mapper.insertScIntLog(logList);
			        }
					logList.clear();
					if(dscRstList != null && dscRstList.size() > 0){
						//批量插入决策结果
						bdA7Mapper.insertDscRstInfo(dscRstList);
						dscRstList.clear();
					}
				}else{
					ordersSb.append(",");
				}
			} catch (Exception e) {
				logger.info("############### 大数据A7请求或结果转换异常(S) ##################");
				logger.info("===");
				logger.info("=== bodyMap：" + JSONObject.toJSONString(bodyMap));
				logger.info("===");
				logger.info("=== A7-Result：" + JSONObject.toJSONString(result));
				logger.info("===");
				logger.info("=== 异常描述："+ JSONObject.toJSONString(e.getMessage()));
				logger.info("===");
				logger.info("############### 大数据A7请求或结果转换异常(S) ##################");
			}
		}
			
	}
	
	
	public static void main(String[] args) {
		//A7还回结果JSON字符串
		String ss = "{\"errorCode\":200,\"errorMsg\":\"请求成功\",\"data\":[{\"checkResult\":\"1\",\"scoreMap\":{\"lemon07588df4ccba1cd668fe3e7ac827074f\":500.0},\"creditScore\":\"0.0\",\"appKey\":\"lemon\",\"orderId\":\"21B6B877029C45CB8A1B7C0B096206E9\",\"riskName\":[\"评分较优，建议通过。\"]},{\"checkResult\":\"1\",\"scoreMap\":{\"lemon07588df4ccba1cd668fe3e7ac827074f\":500.0},\"creditScore\":\"0.0\",\"appKey\":\"lemon\",\"orderId\":\"6E09CC1DF39543CDBE5DED1E38DDF223\",\"riskName\":[\"评分较优，建议通过。\"]}]}";
		
		BdResultModel resultModel = JSONObject.parseObject(ss, BdResultModel.class);
		
		System.out.println(resultModel.isSuccess());
		
		System.out.println(JSONObject.toJSON(resultModel));
		
		System.out.println("===============================");
		
		System.out.println(JSONObject.toJSONString(resultModel.getData()));
		
		System.out.println("===============================");
		
		List<A7DataNode> list = JSONObject.parseArray(JSONObject.toJSONString(resultModel.getData()), A7DataNode.class);
		
		System.out.println(JSONObject.toJSON(list));
		
		System.out.println((31-15)/15);
		
		System.out.println((16-15)/15);
		
		System.out.println((16-16)/15);
		
		List<String> orderList = new ArrayList<String>();
		
		
	}
	
	

}
