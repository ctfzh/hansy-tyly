package com.lemoncome.watchdog.riskbatch.service;

import com.alibaba.fastjson.JSONObject;
import com.lemoncome.watchdog.Startup;
import com.lemoncome.watchdog.riskbatch.mapper.BigDataSubmitMapper;
import com.lemoncome.watchdog.riskbatch.model.BdDataModel;
import com.lemoncome.watchdog.riskbatch.model.BdResultModel;
import com.lemoncome.watchdog.riskbatch.model.CustBill;
import com.lemoncome.watchdog.riskbatch.model.TScIntLog;
import com.lemoncome.watchdog.riskbatch.util.ConstantsUtil;
import com.lemoncome.watchdog.riskbatch.util.DateUtil;
import com.lemoncome.watchdog.riskbatch.util.RandomUtil;
import com.lemoncome.watchdog.riskbatch.util.RemoteClientHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.util.StringUtil;

import java.util.*;

/**
 * @CreateTime:2017年11月2日上午10:36:46
 * @Description:大数据A5风控接口请求服务
 */

@Service
public class LemonA5SubmitService {
	
	@Autowired
	private BigDataSubmitMapper bdA5Mapper;

	private static final Logger logger = LoggerFactory.getLogger(LemonA5SubmitService.class);

	public void startService() {
		/*
		 * 所有操作均为全量读取、不考虑记录数过大的问题
		 * */
		//Step01:一次性读取所有需要提交A5的记录(由ETL生成)
		List<CustBill> custList = this.doA5SubmitStep01();
		
		if(custList != null && custList.size() > 0){
			//Step02:对记录生成对应OrderId，并入库
			this.doA5SubmitStep02(custList);
		}
		
		//Step03:重新全量读取待提交A5记录(含客户信息、地址、规则链等信息)，组装大数据A5数据并执行提交
		this.doA5SubmitStep03();
	}

	public List<CustBill> doA5SubmitStep01() {
		//Step01:一次性读取所有需要提交A5的记录(凌晨跑批，业务日期为"Startup.BUSI_DATE"若为空则取系统时间的前一天)
		String busiDate = (StringUtil.isEmpty(Startup.BUSI_DATE) == true ? DateUtil.date(DateUtil.day(-1)) : Startup.BUSI_DATE);
		List<CustBill> custList = bdA5Mapper.queryCustList(busiDate);
		return custList;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
	public int doA5SubmitStep02(List<CustBill> custList) {
		//清空CustBill表数据(历史数据)
		bdA5Mapper.deleteCustBill();
		Date currDate = new Date();
		// Step02:对记录生成对应OrderId，并入库
		for (CustBill custBill : custList) {
			custBill.setBillId(RandomUtil.UUID());
			custBill.setInsertTime(currDate);
		}
		return bdA5Mapper.insertCustBill(custList);
	}
	
	
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=120000,rollbackFor=Exception.class)
	public void doA5SubmitStep03() {
		// Step03:重新全量读取待提交A5记录(含客户对应产品、地址、规则链等信息)
		List<BdDataModel> bdDataList = bdA5Mapper.queryCustBillList();
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
			bodyMap.put("loanDataJson", JSONObject.toJSONString(bdData));
			bodyMap.put("ruleId", bdData.getRuleId());
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
				result = RemoteClientHelper.invokePostIgnoreException(requstUrl, headerMap, bodyMap);
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
	
}
