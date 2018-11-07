package com.hansy.tyly.riskbatch.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.util.StringUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hansy.tyly.Startup;
import com.hansy.tyly.riskbatch.mapper.BigDataSubmitMapper;
import com.hansy.tyly.riskbatch.model.BdDataModel;
import com.hansy.tyly.riskbatch.model.BdResultModel;
import com.hansy.tyly.riskbatch.model.CustBill;
import com.hansy.tyly.riskbatch.model.TScIntLog;
import com.hansy.tyly.riskbatch.util.ConstantsUtil;
import com.hansy.tyly.riskbatch.util.DateUtil;
import com.hansy.tyly.riskbatch.util.RandomUtil;
import com.hansy.tyly.riskbatch.util.RemoteClientHelper;

/**
 * @CreateTime:2017年11月2日上午10:36:46
 * @Description:大数据A5风控接口请求服务
 */

@Service
public class LemonA5SubmitService {
	
	@Autowired
	private BigDataSubmitMapper bdA5Mapper;

	private static final Logger logger = LoggerFactory.getLogger(LemonA5SubmitService.class);

	public void startService(Map paramMap) {
		logger.info("风控A5接口提交开始 请求参数:"+JSON.toJSONString(paramMap));
		/*
		 * 所有操作均为全量读取、不考虑记录数过大的问题
		 * */
		//Step01:一次性读取所有需要提交A5的记录(由ETL生成)
		List<CustBill> custList = this.doA5SubmitStep01(paramMap);
		
		if(custList != null && custList.size() > 0){
			//Step02:对记录生成对应OrderId，并入库
			this.doA5SubmitStep02(custList,paramMap);
		}
		
		//Step03:重新全量读取待提交A5记录(含客户信息、地址、规则链等信息)，组装大数据A5数据并执行提交
		this.doA5SubmitStep03(paramMap);
	}

	public List<CustBill> doA5SubmitStep01(Map paramMap) {
		//Step01:一次性读取所有需要提交A5的记录(凌晨跑批，业务日期为"Startup.BUSI_DATE"若为空则取系统时间的前一天)
		String busiDate = (StringUtil.isEmpty(Startup.BUSI_DATE) == true ? DateUtil.date(DateUtil.day(-1)) : Startup.BUSI_DATE);
		paramMap.put("busiDate",busiDate);
		List<CustBill> custList = bdA5Mapper.queryCustList(paramMap);
		return custList;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
	public int doA5SubmitStep02(List<CustBill> custList,Map paramMap) {
		//清空CustBill表数据(历史数据)
		bdA5Mapper.deleteCustBill(paramMap);
		Date currDate = new Date();
		// Step02:对记录生成对应OrderId，并入库
		for (CustBill custBill : custList) {
			custBill.setBillId(RandomUtil.UUID());
			custBill.setInsertTime(currDate);
		}
		return bdA5Mapper.insertCustBill(custList);
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=120000,rollbackFor=Exception.class)
	public void doA5SubmitStep03(Map paramMap) {
		// Step03:重新全量读取待提交A5记录(含客户对应产品、地址、规则链等信息)
		List<BdDataModel> bdDataList = bdA5Mapper.queryCustBillList(paramMap);

		//拆分集合
		List<List<BdDataModel>> split = split(bdDataList, 10);
		//拆分集合的子集合条数
        int size=split.size();
        //创建子集合条数的线程数(最大为10)
		ExecutorService e = Executors.newFixedThreadPool(size);
		List<Future> list=new ArrayList<>();
        for (int i=0;i<size;i++){
        	int k=i;
			Future<Object> submit = e.submit(new Callable<Object>() {
				@Override
				public Object call() throws Exception {
					List<BdDataModel> bdDataModels = split.get(k);
					System.out.println("当前线程名称:" + Thread.currentThread().getName());
					dealA5(bdDataList);
					return "";
				}
			});
			list.add(submit);
		}
        e.shutdown();

//		String requstUrl = bdA5Mapper.queryParam("submitApplicationDataUrl");
//		TScIntLog intLog = null;
//		List<TScIntLog> logList = new ArrayList<TScIntLog>();
//		int tempCount = 0;
//		Date currDate = new Date();
//		Map<String, Object> inMap = new HashMap<String, Object>();
//		String result = "";
//		//String appKey = bdA5Mapper.queryParam("appKey");
//		String appKeyMf = bdA5Mapper.queryParam("appKeyMf");
//		String appKeySf = bdA5Mapper.queryParam("appKeySf");
//		for (BdDataModel bdData : bdDataList) {
//			tempCount ++;
//			//bdData.setRuleId("lemon4d804ed839f06db4f603c043211fb56e");
//			//requstUrl = bdData.getRequstUrl();
//			Map<String, String> bodyMap = new HashMap<String, String>();
//			bodyMap.put("ruleId", bdData.getRuleId());
//			bodyMap.put("scoreId", bdData.getScoreId());
//			bodyMap.put("appKey", "YES".equals(bdData.getIsFree()) ? appKeyMf : appKeySf);
//			bdData.setRuleId(null);
//			bdData.setScoreId(null);
//			bdData.setAppKey(("YES".equals(bdData.getIsFree()) ? appKeyMf : appKeySf));
//			bodyMap.put("loanDataJson", JSONObject.toJSONString(bdData));
//			Map<String, String> headerMap = new HashMap<String, String>();
//			headerMap.put("appKey", "YES".equals(bdData.getIsFree()) ? appKeyMf : appKeySf);
//			//构造接口请求记录日志
//			intLog = new TScIntLog();
//			intLog.setSysUuid(RandomUtil.UUID());
//			intLog.setBillId(bdData.getOrderId());
//			intLog.setIntType(ConstantsUtil.INT_TYPE_A5);
//			intLog.setInsertTime(currDate);
//			intLog.setLogState(ConstantsUtil.LOG_FAIL_CODE);
//			inMap.put("billId", bdData.getOrderId());
//			try {
//				//执行A5请求(不支持批量)
//				result = RemoteClientHelper.invokePostIgnoreException(requstUrl, headerMap, bodyMap);
//				BdResultModel resultModel = JSONObject.parseObject(result, BdResultModel.class);
//				//仅执行成功才更新CUST_BILL的SCHEDULE_STATUS
//				if(resultModel.isSuccess()){
//					intLog.setLogState(ConstantsUtil.LOG_SUCC_CODE);
//					inMap.put("exeStatus", ConstantsUtil.SCHEDULE_STATUS_A5);
//					bdA5Mapper.updateCustBill(inMap);
//				}else{
//					intLog.setErrorReason(resultModel.getErrorMsg());
//				}
//			} catch (Exception e) {
//				logger.info("############### 大数据A5请求或结果转换异常(S) ##################");
//				logger.info("===");
//				logger.info("=== headerMap："+ JSONObject.toJSONString(headerMap));
//				logger.info("===");
//				logger.info("=== bodyMap：" + JSONObject.toJSONString(bodyMap));
//				logger.info("===");
//				logger.info("=== A5-Result：" + JSONObject.toJSONString(result));
//				logger.info("===");
//				logger.info("=== 异常描述："+ JSONObject.toJSONString(e.getMessage()));
//				logger.info("===");
//				logger.info("############### 大数据A5请求或结果转换异常(S) ##################");
//				throw new RuntimeException("A5请求异常");
//			}
//			logList.add(intLog);
//			if(tempCount % 50 == 0){
//				currDate = new Date();
//				bdA5Mapper.insertScIntLog(logList);
//				logList.clear();
//			}
//		}
//		//不足50条的记录集合
//		if(logList !=null && logList.size()>0){
//			bdA5Mapper.insertScIntLog(logList);
//		}
	}

    public void dealA5(List<BdDataModel> bdDataList){
		String requstUrl = bdA5Mapper.queryParam("submitApplicationDataUrl");
		TScIntLog intLog = null;
		List<TScIntLog> logList = new ArrayList<TScIntLog>();
		int tempCount = 0;
		Date currDate = new Date();
		Map<String, Object> inMap = new HashMap<String, Object>();
		String result = "";
		//String appKey = bdA5Mapper.queryParam("appKey");
		String appKeyMf = bdA5Mapper.queryParam("appKeyMf");
		String appKeySf = bdA5Mapper.queryParam("appKeySf");
		for (BdDataModel bdData : bdDataList) {
			tempCount ++;
			//bdData.setRuleId("lemon4d804ed839f06db4f603c043211fb56e");
			//requstUrl = bdData.getRequstUrl();
			Map<String, String> bodyMap = new HashMap<String, String>();
			bodyMap.put("ruleId", bdData.getRuleId());
			bodyMap.put("scoreId", bdData.getScoreId());
			bodyMap.put("appKey", "YES".equals(bdData.getIsFree()) ? appKeyMf : appKeySf);
			bdData.setRuleId(null);
			bdData.setScoreId(null);
			bdData.setAppKey(("YES".equals(bdData.getIsFree()) ? appKeyMf : appKeySf));
			bodyMap.put("loanDataJson", JSONObject.toJSONString(bdData));
			Map<String, String> headerMap = new HashMap<String, String>();
			headerMap.put("appKey", "YES".equals(bdData.getIsFree()) ? appKeyMf : appKeySf);
			//构造接口请求记录日志
			intLog = new TScIntLog();
			intLog.setSysUuid(RandomUtil.UUID());
			intLog.setBillId(bdData.getOrderId());
			intLog.setIntType(ConstantsUtil.INT_TYPE_A5);
			intLog.setInsertTime(currDate);
			intLog.setLogState(ConstantsUtil.LOG_FAIL_CODE);
			inMap.put("billId", bdData.getOrderId());
			try {
				//执行A5请求(不支持批量)
				logger.info("A5请求参数:"+JSON.toJSONString(bodyMap)+JSON.toJSONString(headerMap));
				result = RemoteClientHelper.invokePostIgnoreException(requstUrl, headerMap, bodyMap);
				logger.info("A5请求参数:"+JSON.toJSONString(bodyMap)+JSON.toJSONString(headerMap)+"的请求结果:"+result);
				BdResultModel resultModel = JSONObject.parseObject(result, BdResultModel.class);
				//仅执行成功才更新CUST_BILL的SCHEDULE_STATUS
				if(resultModel.isSuccess()){
					intLog.setLogState(ConstantsUtil.LOG_SUCC_CODE);
					inMap.put("exeStatus", ConstantsUtil.SCHEDULE_STATUS_A5);
					bdA5Mapper.updateCustBill(inMap);
				}else{
					intLog.setErrorReason(resultModel.getErrorMsg());
				}
			} catch (Exception e) {
				logger.info("############### 大数据A5请求或结果转换异常(S) ##################");
				logger.info("===");
				logger.info("=== headerMap："+ JSONObject.toJSONString(headerMap));
				logger.info("===");
				logger.info("=== bodyMap：" + JSONObject.toJSONString(bodyMap));
				logger.info("===");
				logger.info("=== A5-Result：" + JSONObject.toJSONString(result));
				logger.info("===");
				logger.info("=== 异常描述："+ JSONObject.toJSONString(e.getMessage()));
				logger.info("===");
				logger.info("############### 大数据A5请求或结果转换异常(S) ##################");
				throw new RuntimeException("A5请求异常");
			}
			logList.add(intLog);
			if(tempCount % 50 == 0){
				currDate = new Date();
				bdA5Mapper.insertScIntLog(logList);
				logList.clear();
			}
		}
		//不足50条的记录集合
		if(logList !=null && logList.size()>0){
			bdA5Mapper.insertScIntLog(logList);
		}
	}

	public static <T> List<List<T>> split(List<T> resList, int count) {

		if (resList == null || count < 1)
			return null;
		List<List<T>> ret = new ArrayList<List<T>>();
		int size = resList.size();
		if (size <= count) { //数据量不足count指定的大小
			ret.add(resList);
		} else {
			int pre = size / count;
			int last = size % count;
			//前面pre个集合，每个大小都是count个元素
			for (int i = 0; i < pre; i++) {
				List<T> itemList = new ArrayList<T>();
				for (int j = 0; j < count; j++) {
					itemList.add(resList.get(i * count + j));
				}
				ret.add(itemList);
			}
			//last的进行处理
			if (last > 0) {
				List<T> itemList = new ArrayList<T>();
				for (int i = 0; i < last; i++) {
					itemList.add(resList.get(pre * count + i));
				}
				ret.add(itemList);
			}
		}
		return ret;
	}
}
