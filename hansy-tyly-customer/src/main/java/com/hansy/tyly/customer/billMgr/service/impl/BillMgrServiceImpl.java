package com.hansy.tyly.customer.billMgr.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hansy.tyly.customer.billMgr.mapper.TBusiBillDtlMapper;
import com.hansy.tyly.customer.billMgr.mapper.TBusiBillHisMapper;
import com.hansy.tyly.customer.billMgr.mapper.TBusiBillMapper;
import com.hansy.tyly.customer.billMgr.model.TBusiBill;
import com.hansy.tyly.customer.billMgr.service.BillMgrService;
import com.hansy.tyly.customer.custmgr.mapper.TBusiOrgMapper;
import com.hansy.tyly.customer.custmgr.model.TBusiOrg;
import com.hansy.tyly.customer.utils.StringUtil;

@Service
public class BillMgrServiceImpl implements BillMgrService {
	
	@Autowired
	private TBusiBillMapper billMapper;
	@Autowired
	private TBusiBillDtlMapper billDtlMapper;
	@Autowired
	private TBusiBillHisMapper billHisMapper;
	@Autowired
	private TBusiOrgMapper busiOrgMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(BillMgrServiceImpl.class);

	@Override
	public Map<String, Object> queryBillInfo(Map<String, Object> inMap) {
		Map<String, Object> outMap = new HashMap<String, Object>();
		TBusiOrg busiOrg = new TBusiOrg();
		busiOrg.setOrgId(inMap.get("orgId").toString());
		//查询机构余额
		busiOrg = busiOrgMapper.selectOne(busiOrg);
		if(busiOrg.getBal() == null ){
			busiOrg.setBal(new BigDecimal(0));
		}
		outMap.put("balAmt", StringUtil.amtFormat(busiOrg.getBal().toString(), 2));
		//统计机构总开销(支出)
		TBusiBill costAmt = billHisMapper.queryCostAmt(inMap);
		if(costAmt == null){
			costAmt = new TBusiBill();
			costAmt.setBillAmt(new BigDecimal("0.00"));
		}
		outMap.put("costAmt",StringUtil.amtFormat(costAmt.getBillAmt().toString(),2));
		return outMap;
	}

	@Override
	public List<Map<String, String>> queryBillList(Map<String, Object> inMap) {
		List<Map<String, String>> billList = billHisMapper.queryBillList(inMap);
		return billList == null ? new ArrayList<Map<String,String>>():billList;
	}
}
