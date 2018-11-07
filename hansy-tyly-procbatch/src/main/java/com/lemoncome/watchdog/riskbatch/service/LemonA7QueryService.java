package com.lemoncome.watchdog.riskbatch.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lemoncome.watchdog.riskbatch.mapper.BigDataSubmitMapper;
import com.lemoncome.watchdog.riskbatch.model.*;
import com.lemoncome.watchdog.riskbatch.util.ConstantsUtil;
import com.lemoncome.watchdog.riskbatch.util.RandomUtil;
import com.lemoncome.watchdog.riskbatch.util.RemoteClientHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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

	

	public void doA7QueryStep01() {
		// Step03:重新全量读取待提交A5记录(含客户对应产品、地址、规则链等信息)
		List<BdDataModel> bdDataList = bdA7Mapper.queryCustBillListForA7();

		List<List<BdDataModel>> split = split(bdDataList, 10);
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
					dealA7(bdDataModels);
					return "";
				}
			});
			list.add(submit);
		}
		e.shutdown();

//		List<BdDataModel> bdDataListSf = new ArrayList<>();
//		List<BdDataModel> bdDataListmf = new ArrayList<>();
//		for (BdDataModel bdDataModel : bdDataList) {
//			if("YES".equals(bdDataModel.getIsFree())){
//				bdDataListmf.add(bdDataModel);
//			}else{
//				bdDataListSf.add(bdDataModel);
//			}
//		}
//		if(bdDataListmf.size() > 0){
//			this.doA7QueryStep02(bdDataListmf);
//		}
//		if(bdDataListSf.size() > 0){
//			this.doA7QueryStep02(bdDataListSf);
//		}
	}
	public void dealA7(List<BdDataModel> bdDataList){
		List<BdDataModel> bdDataListSf = new ArrayList<>();
		List<BdDataModel> bdDataListmf = new ArrayList<>();
		for (BdDataModel bdDataModel : bdDataList) {
			if("YES".equals(bdDataModel.getIsFree())){
				bdDataListmf.add(bdDataModel);
			}else{
				bdDataListSf.add(bdDataModel);
			}
		}
		if(bdDataListmf.size() > 0){
			this.doA7QueryStep02(bdDataListmf);
		}
		if(bdDataListSf.size() > 0){
			this.doA7QueryStep02(bdDataListSf);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=120000,rollbackFor=Exception.class)
	public void doA7QueryStep02(List<BdDataModel> bdDataList) {
		String requstUrl = bdA7Mapper.queryParam("getFKScoreResultUrl");//"http://123.57.7.55:8080/getFKScoreResult";
		int totalNum = bdDataList.size();//总条目数
		TScIntLog intLog = null;
		List<TScIntLog> logList = new ArrayList<TScIntLog>();//操作日志集合
		int tempCount = 0;
		Date currDate = new Date();
		Map<String, Object> inMap = new HashMap<String, Object>();//CustBill更新参数集合
		List<TScCustDcsRst> dscRstList = new ArrayList<TScCustDcsRst>();//决策结果集合
		StringBuilder ordersSb = new StringBuilder();
		Map<String, String> headerMap = new HashMap<String, String>();
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
			headerMap.put("appKey", "YES".equals(bdData.getIsFree()) ? appKeyMf : appKeySf);
			bodyMap.put("appKey", "YES".equals(bdData.getIsFree()) ? appKeyMf : appKeySf);
			try {
				//执行A7请求(A7接口一次最大可接受15条记录、最后一次循环可能不足15条)
				if(tempCount % 15 == 0 || (totalNum == tempCount)){
					bodyMap.put("orderIds", ordersSb.toString().trim());
					logger.info("A7查询请求参数:"+JSON.toJSONString(bodyMap)+JSON.toJSONString(headerMap));
					result = RemoteClientHelper.invokePostIgnoreException(requstUrl, headerMap, bodyMap);
					logger.info("A7查询请求参数:"+JSON.toJSONString(bodyMap)+JSON.toJSONString(headerMap)+"结果:"+result);
					ordersSb = new StringBuilder();
					currDate = new Date();
					logger.info("####### A00-result: "+result);
					BdResultModel resultModel = JSONObject.parseObject(result, BdResultModel.class);
					if(null==resultModel.getData()){
						throw new RuntimeException("A7查询没有结果");
					}
					logger.info("####### A01-resultModel: "+JSONObject.toJSONString(resultModel));
					if(resultModel.isSuccess()){
						//可能只有部分出了结果(小于等于15)
						List<A7DataNode> list = JSONObject.parseArray(JSONObject.toJSONString(resultModel.getData()), A7DataNode.class);
						logger.info("####### A02-List<A7DataNode>: "+JSONObject.toJSONString(list));
						String[] riskName;
						for (A7DataNode dataNode : list) {
							logger.info("####### B00-A7DataNode: "+JSONObject.toJSONString(dataNode));
							//构造接口请求记录日志
							intLog = new TScIntLog();
							intLog.setSysUuid(RandomUtil.UUID());
							intLog.setBillId(dataNode.getOrderId());
							intLog.setIntType(ConstantsUtil.INT_TYPE_A7);
							intLog.setInsertTime(currDate);
							intLog.setLogState(ConstantsUtil.LOG_SUCC_CODE);
							riskName = dataNode.getRiskName();
							if(riskName != null && riskName.length > 0){
								intLog.setErrorReason(riskName[0]);
							}
							logList.add(intLog);
							//仅查询成功才更新CUST_BILL的SCHEDULE_STATUS
							inMap.put("billId", dataNode.getOrderId());
							inMap.put("exeStatus", ConstantsUtil.SCHEDULE_STATUS_A7);
							//构造决策结果记录 0：建议人工审核，1：建议通过，2：建议拒绝
							TScCustDcsRst dcsRst  = new TScCustDcsRst();
							dcsRst.setBillId(dataNode.getOrderId());
							dcsRst.setInsertTime(currDate);
							logger.info("####### B01-A7DataNode.getScoreMap: "+JSONObject.toJSONString(dataNode.getScoreMap()));
							String josnStr = JSONObject.toJSONString(dataNode.getScoreMap());
							JSONObject object = JSONObject.parseObject(josnStr);
							logger.info("####### B02-JSONObject: "+JSONObject.toJSONString(object));
							Set<Map.Entry<String, Object>> entries = object.entrySet();
							for (Map.Entry<String, Object> entry : entries) {
								dcsRst.setScore(entry.getValue()+"");
								logger.info("####### B03-JSONObject: "+entry.getValue()+"");
							}
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
							bdA7Mapper.updateCustBill(inMap);
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
		String ss = "{\"errorCode\":200,\"errorMsg\":\"请求成功\",\"data\":[{\"checkResult\":\"1\",\"scoreMap\":{\"lemon07588df4ccba1cd668fe3e7ac827074f\":-500.0},\"creditScore\":\"0.0\",\"appKey\":\"lemon\",\"orderId\":\"21B6B877029C45CB8A1B7C0B096206E9\",\"riskName\":[]},{\"checkResult\":\"1\",\"scoreMap\":{\"lemon07588df4ccba1cd668fe3e7ac827074f\":500.0},\"creditScore\":\"0.0\",\"appKey\":\"lemon\",\"orderId\":\"6E09CC1DF39543CDBE5DED1E38DDF223\",\"riskName\":[\"评分较优，建议通过。\"]}]}";
		
		BdResultModel resultModel = JSONObject.parseObject(ss, BdResultModel.class);
		
		System.out.println(resultModel.isSuccess());
		
		System.out.println(JSONObject.toJSON(resultModel));
		
		System.out.println("===============================");
		
		System.out.println(JSONObject.toJSONString(resultModel.getData()));
		
		System.out.println("===============================");
		
		List<A7DataNode> list = JSONObject.parseArray(JSONObject.toJSONString(resultModel.getData()), A7DataNode.class);

		A7DataNode a7DataNode = list.get(0);

		String a7Str  = "{\"appKey\":\"kanjiagoumf\",\"checkResult\":\"0\",\"creditScore\":\"-99999.0\",\"orderId\":\"2A2408CD062E4F06B303CDF1D5170928\",\"riskName\":[],\"scoreMap\":{\"lemon4b5ef5df9bdeaf76d453618a51dd124a\":-20.0}}";

		A7DataNode a7DataNode1 = JSONObject.parseObject(a7Str, A7DataNode.class);

		System.out.println("ABC: "+JSONObject.toJSONString(a7DataNode1.getScoreMap()));
//		String josnStr = JSONObject.toJSONString({"lemon07588df4ccba1cd668fe3e7ac827074f":500.0});
		String josnStr = JSONObject.toJSONString(a7DataNode1.getScoreMap());


		JSONObject object = JSONObject.parseObject(josnStr);

		Set<Map.Entry<String, Object>> entries = object.entrySet();
		for (Map.Entry<String, Object> entry : entries) {
			System.out.println("OOOOOO "+entry.getKey());
			System.out.println("OOOOOO "+entry.getValue());
		}

		String  iiii = object.getString("21B6B877029C45CB8A1B7C0B096206E9");

		System.out.println("********* "+iiii);

		System.out.println(JSONObject.toJSON(list));
		
		System.out.println((31-15)/15);
		
		System.out.println((16-15)/15);
		
		System.out.println((16-16)/15);
		
		List<String> orderList = new ArrayList<String>();
		
		
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
