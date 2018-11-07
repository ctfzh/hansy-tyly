package com.hansy.tyly.customer.reportMgr.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hansy.tyly.customer.reportMgr.mapper.ReportDetailMapper;
import com.hansy.tyly.customer.reportMgr.mapper.TScCustIndcRstMapper;
import com.hansy.tyly.customer.reportMgr.service.ReportMgrService;

@Service
public class ReportMgrServiceImpl implements ReportMgrService {
	
	@Autowired
	private TScCustIndcRstMapper custIndcRstMapper;
	@Autowired
	private ReportDetailMapper reportDetailMapper;

	@Override
	public List<Map<String, String>> queryIndcInfoByBillId(Map<String, Object> inMap) {
		return custIndcRstMapper.queryIndcInfoByBillId(inMap);
	}

	@Override
	public List<Map<String, String>> queryRiskStatistics(
			Map<String, Object> inMap) {
		return custIndcRstMapper.queryRiskStatistics(inMap);
	}

	@Override
	public List<Map<String, String>> queryRiskDetailList(
			Map<String, Object> inMap) {
		return custIndcRstMapper.queryRiskDetailList(inMap);
	}

	@Override
	public List<Map<String, String>> queryRiskList(Map<String, Object> inMap) {
		return custIndcRstMapper.queryRiskList(inMap);
	}

	@Override
	public Map<String, Object> queryReportDeatil(Map<String, Object> inMap) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		outMap.put("riskData",reportDetailMapper.queryRiskGeneralData(inMap));
		outMap.put("topCustData",reportDetailMapper.queryTop10RiskCustInfo(inMap));
		outMap.put("multLoanData",reportDetailMapper.queryMultLoanHitInfo(inMap));
		outMap.put("blackHitData",reportDetailMapper.queryBlackHitInfo(inMap));
		return outMap;
	}

    @Override
    public List<Map<String, String>> queryCustRiskReportList(Map<String, Object> inMap) {
        return custIndcRstMapper.queryCustRiskReportList(inMap);
    }


}
