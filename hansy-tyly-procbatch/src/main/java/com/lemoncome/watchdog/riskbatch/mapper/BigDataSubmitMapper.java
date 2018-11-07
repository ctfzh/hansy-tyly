package com.lemoncome.watchdog.riskbatch.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lemoncome.watchdog.riskbatch.model.BdDataModel;
import com.lemoncome.watchdog.riskbatch.model.CustBill;
import com.lemoncome.watchdog.riskbatch.model.SmsMessage;
import com.lemoncome.watchdog.riskbatch.model.TScCustDcsRst;
import com.lemoncome.watchdog.riskbatch.model.TScIntLog;

/**
 * @CreateTime:2017年11月2日上午11:45:54
 * @Description:大数据A5提交相关查询、插入操作接口
 */
public interface BigDataSubmitMapper {
	public Map proctScBill(Map map);
	String queryParam(String paramId);
	
	List<CustBill> queryCustList(Map map);
	
	int insertCustBill(List<CustBill> list);
	
	int deleteCustBill(Map map);
	
	List<BdDataModel> queryCustBillList(Map map);
	
	int updateCustBill(Map<String, Object> inMap);
	
	int insertScIntLog(@Param("logList") List<TScIntLog> logList);
	
	List<BdDataModel> queryCustBillListForA7();
	
	int insertDscRstInfo(@Param("dscRstInfo") List<TScCustDcsRst> logList);
	
	List<SmsMessage> querySmsMessageList(String busiDate);
	
	int updateSmsMessageInfo(Map<String, Object> inMap);
}
