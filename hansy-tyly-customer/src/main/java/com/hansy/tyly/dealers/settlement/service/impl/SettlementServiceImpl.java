package com.hansy.tyly.dealers.settlement.service.impl;

import com.github.pagehelper.PageInfo;
import com.hansy.tyly.admin.constant.SysCodeConstant;
import com.hansy.tyly.common.utils.Respones;
import com.hansy.tyly.core.command.CryptoCmd;
import com.hansy.tyly.core.excepiton.ParameterException;
import com.hansy.tyly.core.helper.NPageHelper;
import com.hansy.tyly.dealers.manage.domain.DealersVO;
import com.hansy.tyly.dealers.settlement.dao.mapper.TFinaDistributorOrderMapper;
import com.hansy.tyly.dealers.settlement.dao.mapper.TFinaDistributorSettMapper;
import com.hansy.tyly.dealers.settlement.dao.model.TFinaDistributorOrder;
import com.hansy.tyly.dealers.settlement.service.SettlementService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Auther: 18383
 * @Date: 2018/8/16 18:22
 * @Description:
 */
@Service
public class SettlementServiceImpl implements SettlementService {

    @Autowired
    private TFinaDistributorSettMapper tFinaDistributorSettMapper;
    @Autowired
    private TFinaDistributorOrderMapper tFinaDistributorOrderMapper;

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
        Object startTime = params.get("start_time");
        Object endTime = params.get("end_time");
        Object distributorNo = params.get("distributor_no");
        if(distributorNo == null){
            throw new ParameterException("参数错误，经销商编号不能为空");
        }
        if((startTime == null && endTime != null) || (startTime != null && endTime == null)){
            throw new ParameterException("开始时间和结束时间必须成对出现");
        }

        params.put("transStatus", SysCodeConstant.TRANS_STATUS_SETTLEMENT);
        NPageHelper.startPage(params);
        List<Map<String,Object>> mapList =  tFinaDistributorSettMapper.searchDetail(params);
        return addPageInfo(mapList);
    }

    @Override
    public Respones getAll(DealersVO vo) throws Exception {
        String distributorNo = vo.getDistributor_no();
        Map<String, Object> params = new HashMap<>();
        if(StringUtils.isEmpty(distributorNo)){
            throw new ParameterException("参数错误，经销商编号不能为空");
        }
        params = initPaheInfo(params,vo);
        params.put("distributor_no",distributorNo);
        params.put("transStatus", SysCodeConstant.TRANS_STATUS_SETTLEMENT);
        NPageHelper.startPage(params);
        List<Map<String,Object>> mapList =  tFinaDistributorSettMapper.getAll(params);
        return getResponse(mapList);
    }

    @Override
    public Respones searchList(DealersVO vo) throws Exception {
        Map<String, Object> params = new HashMap<>();
        String distributorNo = vo.getDistributor_no();
        if(StringUtils.isEmpty(distributorNo)){
            throw new ParameterException("参数错误，经销商编号不能为空");
        }
        params = initPaheInfo(params,vo);
        String settNo = vo.getSett_no();
        if(!StringUtils.isEmpty(settNo)){
            params.put("sett_no",settNo);
        }
        params.put("start_date",vo.getStartTime());
        params.put("end_date",vo.getEndTime());
        params.put("distributor_no",distributorNo);
        params.put("transStatus", SysCodeConstant.TRANS_STATUS_SETTLEMENT);
        NPageHelper.startPage(params);
        List<Map<String,Object>> mapList =  tFinaDistributorSettMapper.searchAll(params);
        return getResponse(mapList);
    }

    @Override
    public Respones detailList(DealersVO vo) throws Exception {
        Map<String, Object> params = new HashMap<>();
        String settNo = vo.getSett_no();
        if(StringUtils.isEmpty(settNo)){
            throw new ParameterException("参数错误，结算编号不能为空");
        }
//        params = initPaheInfo(params,vo);
//        params.put("settNo",settNo);
//        params.put("transStatus", SysCodeConstant.TRANS_STATUS_SETTLEMENT);
//        NPageHelper.startPage(params);
//        List<Map<String,Object>> mapList =  tFinaDistributorSettMapper.getAllDetail(params);
        // 查询所有编号
        TFinaDistributorOrder query = new TFinaDistributorOrder();
        query.setSettNo(settNo);
        List<TFinaDistributorOrder> select = tFinaDistributorOrderMapper.select(query);
        List<String> orderNos = new ArrayList<>();
        select.forEach(node->orderNos.add(node.getOrderNo()));
        params.put("settNo",settNo);
        params.put("transStatus", SysCodeConstant.TRANS_STATUS_SETTLEMENT);
        List<Map<String,Object>> mapList = tFinaDistributorSettMapper.getAllDetail(params);
        return getResponse(mapList);
    }

    @Override
    public Respones searchDetail(DealersVO vo) throws Exception {
//        Map<String, Object> params = new HashMap<>();
//        String distributorNo = vo.getDistributor_no();
//        if(StringUtils.isEmpty(distributorNo)){
//            throw new ParameterException("参数错误，经销商编号不能为空");
//        }
//        params = initPaheInfo(params,vo);
//        params.put("order_status",vo.getOrder_status());
//        params.put("start_date",vo.getStart_date());
//        params.put("end_date",vo.getEnd_date());
//        params.put("transStatus", SysCodeConstant.TRANS_STATUS_SETTLEMENT);
//        params.put("merchant_name",vo.getMer_name());
//        params.put("distributor_no",distributorNo);
//        NPageHelper.startPage(params);
//        List<Map<String,Object>> mapList =  tFinaDistributorSettMapper.searchDetail(params);
//        return getResponse(mapList);
        Map<String, Object> params = new HashMap<>();
        String settNo = vo.getSett_no();
        if(StringUtils.isEmpty(settNo)){
            throw new ParameterException("参数错误，结算编号不能为空");
        }
        // 查询所有编号
        TFinaDistributorOrder query = new TFinaDistributorOrder();
        query.setSettNo(settNo);
        List<TFinaDistributorOrder> select = tFinaDistributorOrderMapper.select(query);
        List<String> orderNos = new ArrayList<>();
        select.forEach(node->orderNos.add(node.getOrderNo()));
        params.put("orderNos",orderNos);
        params.put("transStatus", SysCodeConstant.TRANS_STATUS_SETTLEMENT);
        if(orderNos.size() < 1){
            return getResponse(new ArrayList<>());
        }
        List<Map<String,Object>> mapList = tFinaDistributorSettMapper.searchDetail(params);
        return getResponse(mapList);
    }


    public static  final  Respones getResponse(List<Map<String,Object>> list){
        if(list != null && list.size() > 0 && list.get(0) == null){
            list = new ArrayList<>();
        }
        list.forEach(node->{
            Object oSettTotalAmt = node.get("settTotalAmt");
            double settTotalAmt = oSettTotalAmt==null?0:Double.parseDouble(oSettTotalAmt.toString());
            node.put("settTotalAmt",String.format("%.2f",settTotalAmt));
//            Object oTotalAmt = node.get("totalAmt");
//            double totalAmt = oTotalAmt==null?0:(double) oTotalAmt;
//            node.put("totalAmt",String.format("%.2f",totalAmt));
        });

        PageInfo pageInfo = new PageInfo(list);
        Respones respones = new Respones();
        respones.setResult(true);
        respones.setCode("200");
        Map<String,Object> result = new HashMap<>();
        result.put("pageNo",pageInfo.getPageNum());
        result.put("pageSize",pageInfo.getPageSize());
        result.put("totalCount",pageInfo.getTotal());
        respones.setPage(result);
        respones.setReq(list);
        return respones;
    }


    private static final Map<String,Object> initPaheInfo(Map<String,Object> param,DealersVO vo){
        int pageNo = vo.getPageNo();
        int pageSize = vo.getPageSize();
        if(pageNo < 1){
            pageNo = 1;
        }
        if(pageSize < 1 || pageSize > 100){
            pageSize = 10;
        }
        param.put("pageNo",pageNo);
        param.put("pageSize",pageSize);
        return param;
    }


    /**
     * 给返回结果添加分页信息
     */
    private static CryptoCmd  addPageInfo(List<Map<String,Object>> list){
        CryptoCmd cryptoCmd = new CryptoCmd();
        PageInfo pageinfo = new PageInfo(list);
        cryptoCmd.setOut("pageNo",pageinfo.getPageNum());
        cryptoCmd.setOut("pageSize",pageinfo.getPageSize());
        cryptoCmd.setOut("totalSize",pageinfo.getTotal());
        cryptoCmd.setOut("list",list);
        return cryptoCmd;
    }
}
