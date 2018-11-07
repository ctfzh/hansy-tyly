package com.hansy.tyly.admin.bill.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hansy.tyly.admin.base.bean.UserProfile;

public interface OrgBillService {
    /*条件查询*/
    List<Map<String,Object>> queryOrgBillByCondition(Map<String,Object> map);
    /*禁用和启用*/
    String alterBillStatus(String billId, UserProfile userProfile);
    /*充值*/
    String orgRecharge(String orgId, BigDecimal amt,String extAmt,UserProfile userProfile);
    /*消费明细*/
    List<Map<String,Object>>queryBillDtl(Map<String,Object> map);
    /*导出明细*/
    List<Map<String, Object>> dowloadExl(Map<String, Object> map);

}
