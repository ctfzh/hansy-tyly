package com.hansy.tyly.admin.settlement.service.impl;

import com.github.pagehelper.PageInfo;
import com.hansy.tyly.admin.settlement.dao.mapper.TFinaDistributorSettMapper;
import com.hansy.tyly.admin.settlement.service.DistributorSettlementService;
import com.hansy.tyly.admin.utils.constant.SysCodeConstant;
import com.hansy.tyly.core.command.CryptoCmd;
import com.hansy.tyly.core.excepiton.ParameterException;
import com.hansy.tyly.core.helper.NPageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 18383
 * @Date: 2018/9/3 10:17
 * @Description:
 */
@Service
public class DistributorSettlementServiceImpl implements DistributorSettlementService {
    @Autowired
    private TFinaDistributorSettMapper tFinaDistributorSettMapper;

    @Override
    public CryptoCmd getAll(CryptoCmd cryptoCmd) throws Exception {
        Map<String, Object> params = cryptoCmd.getParams();
        Object distributorNo = params.get("distributor_no");
        if(distributorNo == null){
            throw new ParameterException("参数错误，经销商编号不能为空");
        }

        params.put("transStatus", SysCodeConstant.TRANS_STATUS_SETTLEMENT);
        NPageHelper.startPage(params);
        List<Map<String,Object>> mapList =  tFinaDistributorSettMapper.getAll(params);
        return addPageInfo(mapList);
    }

    @Override
    public CryptoCmd searchList(CryptoCmd cryptoCmd) throws Exception {
        Map<String, Object> params = cryptoCmd.getParams();
        Object distributorNo = params.get("distributor_no");
        if(distributorNo == null){
            throw new ParameterException("参数错误，经销商编号不能为空");
        }
        params.put("transStatus", SysCodeConstant.TRANS_STATUS_SETTLEMENT);
        NPageHelper.startPage(params);
        List<Map<String,Object>> mapList =  tFinaDistributorSettMapper.searchAll(params);
        return addPageInfo(mapList);
    }

    @Override
    public CryptoCmd detailList(CryptoCmd cryptoCmd) throws Exception {
        Map<String, Object> params = cryptoCmd.getParams();
        Object distributorNo = params.get("distributor_no");
        if(distributorNo == null){
            throw new ParameterException("参数错误，经销商编号不能为空");
        }

        params.put("transStatus", SysCodeConstant.TRANS_STATUS_SETTLEMENT);
        NPageHelper.startPage(params);
        List<Map<String,Object>> mapList =  tFinaDistributorSettMapper.getAllDetail(params);
        return addPageInfo(mapList);
    }

    @Override
    public CryptoCmd searchDetail(CryptoCmd cryptoCmd) throws Exception {
        Map<String, Object> params = cryptoCmd.getParams();
        Object distributorNo = params.get("distributor_no");
        if(distributorNo == null){
            throw new ParameterException("参数错误，经销商编号不能为空");
        }
        params.put("transStatus", SysCodeConstant.TRANS_STATUS_SETTLEMENT);
        NPageHelper.startPage(params);
        List<Map<String,Object>> mapList =  tFinaDistributorSettMapper.searchDetail(params);
        return addPageInfo(mapList);
    }


    /**
     * 给返回结果添加分页信息
     */
    private static CryptoCmd addPageInfo(List<Map<String,Object>> list){
        CryptoCmd cryptoCmd = new CryptoCmd();
        PageInfo pageinfo = new PageInfo(list);
        cryptoCmd.setOut("pageNo",pageinfo.getPageNum());
        cryptoCmd.setOut("pageSize",pageinfo.getPageSize());
        cryptoCmd.setOut("totalSize",pageinfo.getTotal());
        cryptoCmd.setOut("list",list);
        return cryptoCmd;
    }
}