package com.hansy.tyly.admin.bill.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hansy.tyly.admin.base.bean.UserProfile;
import com.hansy.tyly.admin.bill.mapper.TBusiBillHisMapper;
import com.hansy.tyly.admin.bill.mapper.TBusiBillMapper;
import com.hansy.tyly.admin.bill.service.OrgBillService;
import com.hansy.tyly.admin.cust.mapper.TBusiOrgMapper;
import com.hansy.tyly.admin.dao.model.TBusiBill;
import com.hansy.tyly.admin.dao.model.TBusiBillHis;
import com.hansy.tyly.admin.dao.model.TBusiOrg;
import com.hansy.tyly.admin.utils.DateUtil;
import com.hansy.tyly.admin.utils.RandomUtil;
import com.hansy.tyly.admin.utils.ValidUtil;
import com.hansy.tyly.admin.utils.constant.SysCodeConstant;
import com.hansy.tyly.admin.utils.constant.SysCodeMapUtil;
import com.hansy.tyly.core.excepiton.ParameterException;
import com.hansy.tyly.core.helper.NPageHelper;

@Service
@Transactional
public class OrgBillServiceImpl implements OrgBillService {

    @Autowired
    private TBusiOrgMapper tBusiOrgMapper;
    @Autowired
    private TBusiBillMapper tBusiBillMapper;
    @Autowired
    private TBusiBillHisMapper tBusiBillHisMapper;

    @Override
    public List<Map<String, Object>> queryOrgBillByCondition(Map<String, Object> map) {
        NPageHelper.startPage(map);
        return tBusiBillMapper.getListByCondit(map);
    }

    @Override
    public String alterBillStatus(String billId, UserProfile userProfile) {
        if(!ValidUtil.valid(billId)) throw new ParameterException("billId为空");
        //TBusiOrg tBusiOrg = tBusiOrgMapper.selectByPrimaryKey(billId);
        /*if (tBusiOrg.getStatus().equals(SysCodeConstant.STATUS_YES)) {
            tBusiOrg.setStatus(SysCodeConstant.STATUS_NO);
        } else {
            tBusiOrg.setStatus(SysCodeConstant.STATUS_YES);
        }
        tBusiOrg.setUpdateTime(new Date());
        tBusiOrgMapper.updateByPrimaryKeySelective(tBusiOrg);*/
        TBusiBill tBusiBill =tBusiBillMapper.selectByPrimaryKey(billId);
        if (tBusiBill.getStatus().equals(SysCodeConstant.STATUS_YES)) {
            tBusiBill.setStatus(SysCodeConstant.STATUS_NO);
        } else {
            tBusiBill.setStatus(SysCodeConstant.STATUS_YES);
        }
        tBusiBill.setUpdateTime(new Date());
        tBusiBillMapper.updateByPrimaryKeySelective(tBusiBill);
        TBusiBillHis tBusiBillHis=new TBusiBillHis();
        BeanUtils.copyProperties(tBusiBill,tBusiBillHis);
        tBusiBillHisMapper.updateByPrimaryKeySelective(tBusiBillHis);
        return null;
    }

    @Override
    public String orgRecharge(String orgId, BigDecimal amt, String billType, UserProfile userProfile) {
        if (!ValidUtil.valid(orgId)) {
            throw new ParameterException("orgId不能为空");
        }
        if (!ValidUtil.valid(billType)) {
            throw new ParameterException("billType不能为空");
        }
        if(amt.compareTo(BigDecimal.ZERO)==0 || amt==null) throw new ParameterException("充值金额为空");
        if(amt.toString().length()>9){
            throw new ParameterException("充值金额太多");
        }
        TBusiOrg tBusiOrg1 = tBusiOrgMapper.selectByPrimaryKey(orgId);
        //判断赠送金额 是否为0   不为0  就添加一条赠送记录

        //写一条充值金额
        TBusiBill tBusiBilln = new TBusiBill();
        tBusiBilln.setBillAmt(amt);
        tBusiBilln.setBillId(RandomUtil.uuid());
        tBusiBilln.setBillType(billType);
        tBusiBilln.setBusiDate(DateUtil.dateTime(new Date()));
        tBusiBilln.setInsertTime(new Date());
        tBusiBilln.setInsertUserId(userProfile.getUserId());
        tBusiBilln.setOrgId(orgId);
        tBusiBilln.setStatus(SysCodeConstant.STATUS_YES);
        tBusiBillMapper.insert(tBusiBilln);
        //写入历史表
        TBusiBillHis tBusiBillHis=new TBusiBillHis();
        BeanUtils.copyProperties(tBusiBilln,tBusiBillHis);
        tBusiBillHisMapper.insert(tBusiBillHis);
        //改变机构余额

        BigDecimal bal = tBusiOrg1.getBal();
        if(bal==null) bal=new BigDecimal("0.00");
        bal = bal.add(amt);
        TBusiOrg tBusiOrg = new TBusiOrg();
        tBusiOrg.setOrgId(orgId);
        tBusiOrg.setBal(bal);
        tBusiOrg.setUpdateTime(new Date());
        tBusiOrgMapper.updateByPrimaryKeySelective(tBusiOrg);
        return null;
    }

    @Override
    public List<Map<String, Object>> queryBillDtl(Map<String, Object> map) {
        NPageHelper.startPage(map);
        List<Map<String, Object>> mapList = tBusiBillMapper.queryBillDtl(map);
        return mapList;
    }

    @Override
    public List<Map<String, Object>> dowloadExl(Map<String, Object> map) {
        if (map.containsKey("pageSize")) {
            map.remove("pageSize");
        }
        if (map.containsKey("pageNo")) {
            map.remove("pageNo");
        }
        if (map.containsKey("pages")) {
            map.remove("pages");
        }
        if (map.containsKey("totalSize")) {
            map.remove("totalSize");
        }
        List<Map<String, Object>> mapList = tBusiBillMapper.queryBillDtl(map);
        List<Map<String, Object>> retList = new ArrayList<>();
        for(int i=0;i<mapList.size();i++){
            Map<String, Object> stringObjectMap = mapList.get(i);
            String freType = SysCodeMapUtil.getNameByCode(stringObjectMap.get("freType").toString());
            String status = SysCodeMapUtil.getNameByCode(stringObjectMap.get("status").toString());
            stringObjectMap.put("freType",freType);
            stringObjectMap.put("status",status);
            retList.add(stringObjectMap);
        }
        return retList;
    }
}
