package com.hansy.tyly.admin.merchant.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.qos.logback.core.net.SyslogConstants;
import com.hansy.tyly.admin.dao.mapper.*;
import com.hansy.tyly.admin.dao.model.*;
import com.hansy.tyly.admin.dao.mapper.TPubFilesMapper;
import com.hansy.tyly.admin.dao.model.TPubFiles;
import com.hansy.tyly.admin.system.mapper.SysCodeInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hansy.tyly.admin.merchant.mapper.ManageMapper;
import com.hansy.tyly.admin.merchant.mapper.TPubGroupCustMapper;
import com.hansy.tyly.admin.merchant.mapper.TPubMerChangeMapper;
import com.hansy.tyly.admin.merchant.mapper.TPubMerInfoMapper;
import com.hansy.tyly.admin.merchant.service.MerchantManageService;
import com.hansy.tyly.admin.utils.Respones;
import com.hansy.tyly.admin.utils.UUIDUtils;
import com.hansy.tyly.admin.utils.constant.SysCodeConstant;
import com.hansy.tyly.core.command.CryptoCmd;
import com.hansy.tyly.core.excepiton.ParameterException;
import com.hansy.tyly.core.excepiton.ServiceException;
import com.hansy.tyly.core.helper.NPageHelper;

/**
 * @Auther: 18383
 * @Date: 2018/8/8 10:10
 * @Description:
 */
@Service
public class ManageServiceImpl implements MerchantManageService {

    @Autowired
    private TPubMerInfoMapper tPubMerInfoMapper;
    @Autowired
    private TPubMerChangeMapper tPubMerChangeMapper;
    @Autowired
    private ManageMapper manageMapper;
    @Autowired
    private TPubGroupMapper tPubGroupMapper;
    @Autowired
    private TPubGroupCustMapper tPubGroupCustMapper;
    @Autowired
    private TPubDistributorMerMapper tPubDistributorMerMapper;
    @Autowired
    private TPubDistributorInfoMapper distributorInfoMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private TUserBaseInfoMapper tUserBaseInfoMapper;
    @Autowired
    private TPubFilesMapper tPubFilesMapper;
    @Autowired
    private SysCodeInfoMapper sysCodeInfoMapper;
    @Autowired
    private TGoodsFilesMapper tGoodsFilesMapper;
    @Autowired
    private TGoodsDistributorMerMapper tGoodsDistributorMerMapper;

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public boolean auditMerchant(CryptoCmd cryptoCmd) throws Exception {
//        TPubMerInfo pubMerInfo;
//        try{
//            pubMerInfo = ((JSONObject) cryptoCmd.getParams().get("tPubMerInfo")).toJavaObject(TPubMerInfo.class);
//        }catch (Exception e){
//            throw new ParameterException("参数错误");
//        }
//        if(pubMerInfo == null){
//            throw new ParameterException("参数错误，审核商户不能为空");
//        }
//        String distributorNo= (String) cryptoCmd.getParams().get("distributorNo");
//        if(StringUtils.isEmpty(distributorNo)){
//            throw new ParameterException("参数错误，商户所属经销商不能为空");
//        }
//
//        pubMerInfo.setInsertDate(new Date());
//        pubMerInfo.setMerNo(UUIDUtils.getUuid());
//
//        // 向商户详情表插入数据
//        if(tPubMerInfoMapper.insert(pubMerInfo) < 0){
//            throw new ServiceException("更新信息失败，请检查网络");
//        }
//
//        // 向商户经销商表插入数据
//        TPubDistributorMer tPubDistributorMer = new TPubDistributorMer();
//        tPubDistributorMer.setTableKey(UUIDUtils.getUuid());
//        tPubDistributorMer.setMerNo(pubMerInfo.getMerNo());
//        tPubDistributorMer.setDistributorNo(distributorNo);
//        tPubDistributorMer.setInsertDate(new Date());
//        if(tPubDistributorMerMapper.insert(tPubDistributorMer) < 1){
//            throw new ServiceException("更新信息失败，请检查网络");
//        }


        Map<String, Object> params = cryptoCmd.getParams();
        Object merNo = params.get("merNo");
        if(merNo == null){
            throw new ParameterException("参数错误，商户编号不能为空");
        }
        Object distributorNo = params.get("distributorNo");
        if(distributorNo == null){
            throw new ParameterException("参数错误，经销商编号不能为空");
        }
        Boolean auditStatus = (Boolean) params.get("auditStatus");
        TPubMerInfo updateDemo = new TPubMerInfo();
        TUserBaseInfo tUserBaseInfo = new TUserBaseInfo();
        updateDemo.setMerNo(merNo.toString());
        if(auditStatus){
            // 审核通过
            updateDemo.setMerStatus(SysCodeConstant.DISTRIBUTOR_STATUS_NORMAL);
            tUserBaseInfo.setUserStatus(SysCodeConstant.DISTRIBUTOR_STATUS_NORMAL);
        }else {
            updateDemo.setMerStatus(SysCodeConstant.DISTRIBUTOR_STATUS_FAILD);
            tUserBaseInfo.setUserStatus(SysCodeConstant.DISTRIBUTOR_STATUS_FAILD);
        }
        // 更新商户表
        if(tPubMerInfoMapper.updateByPrimaryKeySelective(updateDemo) < 1){
            throw new ServiceException("更新商户信息失败,商户不存在");
        }
        // 更新基本信息表
        tUserBaseInfo.setUserNo(merNo.toString());
        tUserBaseInfoMapper.updateByPrimaryKeySelective(tUserBaseInfo);
//        if(tUserBaseInfoMapper.updateStatusByRelationNo(merNo.toString(),SysCodeConstant.STATUS_YES) < 1){
        if(tUserBaseInfoMapper.updateByPrimaryKeySelective(tUserBaseInfo) < 1){
            throw new ServiceException("更新基本信息失败");
        }

        TPubMerInfo pubMerInfo = tPubMerInfoMapper.selectOne(updateDemo);
        // 向商户变更信息表插入数据
        TPubMerChange tPubMerChange = new TPubMerChange();
        tPubMerChange.setCompanyType(pubMerInfo.getCompanyType());
        tPubMerChange.setDistributorNo(distributorNo.toString());
        tPubMerChange.setInsertDate(new Date());
        tPubMerChange.setLegalName(pubMerInfo.getLegalName());
        tPubMerChange.setMerAddre(pubMerInfo.getMerAddre());
        tPubMerChange.setMerContact(pubMerInfo.getMerContact());
        tPubMerChange.setMerName(pubMerInfo.getMerName());
        tPubMerChange.setMerNo(pubMerInfo.getMerNo());
        tPubMerChange.setMerRegNo(pubMerInfo.getMerRegNo());
        tPubMerChange.setMerStatus(pubMerInfo.getMerStatus());
        tPubMerChange.setTableKey(UUIDUtils.getUuid());
        tPubMerChange.setMerContactPhone(pubMerInfo.getMerContactPhone());
        if(tPubMerChangeMapper.insert(tPubMerChange) < 0){
            throw  new ServiceException("审核失败");
        }
        return true;
    }

    @Override
    public List<Map<String,Object>> getAllMerchants(CryptoCmd cryptoCmd) throws ServiceException {
        Object loginId = SecurityUtils.getSubject().getPrincipal();
//        List<String> roles = getRoles(cryptoCmd.getParams());
//        Object loginId = cryptoCmd.getParams().get("loginId");
//        if(loginId == null){
//            throw new ServiceException("用户未登录");
//        }
        Map<String, Object> params = cryptoCmd.getParams();
        params.put("useStatus", SysCodeConstant.MERCHANT_STATUS_NORMAL);
//        if(roles.contains(SysCodeConstant.SALE_ROLE)){
        if(SecurityUtils.getSubject().hasRole(SysCodeConstant.SALE_ROLE)){
            // 销售
            params.put("loginId",loginId.toString());
            NPageHelper.startPage(params);
            List<Map<String, Object>> list = manageMapper.getAllMerchantsByDealer(params);
            addPageInfo(list,cryptoCmd);
            return list;
        }
//        else if(roles.contains(SysCodeConstant.SALES_ADMINISTRATOR)){
        else if(SecurityUtils.getSubject().hasRole(SysCodeConstant.SALES_ADMINISTRATOR)){
            // 销售主管
            List<String> salesUserId = manageMapper.selectAllSales(loginId.toString());
            params.put("userIds",salesUserId);
            NPageHelper.startPage(params);
            List<Map<String, Object>> list = manageMapper.getMerchantsBySaleAdmin(params);
            addPageInfo(list,cryptoCmd);
            return list;
        }
        else {
            // 上级
            NPageHelper.startPage(params);
            List<Map<String, Object>> list = manageMapper.getAllMerchants(params);
            addPageInfo(list,cryptoCmd);
            return list;
        }
    }

    @Override
    public CryptoCmd getMerchantDetail(CryptoCmd cryptoCmd) throws ServiceException {
        Map<String, Object> params = cryptoCmd.getParams();
        if(params.get("merNo") == null){
            throw new ServiceException("错误参数，商户id不能为空");
        }
        // 查询商户基本信息
        Map<String,Object> merchantDetail = manageMapper.getMerchantDetail(params);
        if(merchantDetail == null || merchantDetail.size()<1){
            throw new ServiceException("未知商户");
        }
        // 需要统计的订单状态：[未发货，未收货，交易成功]
        List<String> orderStatus = Arrays.asList(SysCodeConstant.TRANS_STATUS_SUCCESS,SysCodeConstant.TRANS_STATUS_RECIEVE_NO,SysCodeConstant.TRANS_STATUS_SEND_NO);
        // 查询商户消费总数
        Double totalAmount = manageMapper.selectTotalAmount(params.get("merNo").toString(),orderStatus);
        merchantDetail.put("totalAmount",String.format("%.2f",totalAmount==null?0:totalAmount));
        cryptoCmd.setOut(merchantDetail);
        return cryptoCmd;
    }

    @Override
    public Map<String,Object> getMerchantDetail(MerchantVO vo) throws ServiceException {
        String merNo = vo.getMer_no();
        String distributorNo = vo.getDistributor_no();
        if(StringUtils.isEmpty(merNo)){
            throw new ServiceException("错误参数，商户编号不能为空");
        }
        Map<String,Object> param = new HashMap<>();
        param.put("merNo",merNo);
        param.put("useStatus",SysCodeConstant.MERCHANT_STATUS_NORMAL);
        // 查询商户基本信息
        Map<String, Object> merchantDetail = manageMapper.getMerchantDetail(param);
        if(merchantDetail == null || merchantDetail.size()<1){
            throw new ServiceException("未知商户");
        }
        // 需要统计的订单状态
        List<String> orderStatus = Arrays.asList(SysCodeConstant.TRANS_STATUS_SUCCESS,SysCodeConstant.TRANS_STATUS_RECIEVE_NO,SysCodeConstant.TRANS_STATUS_SEND_NO);
        // 查询商户消费总数
        Double totalAmount = manageMapper.selectTotalAmount(merNo,orderStatus);
        merchantDetail.put("totalAmount",String.format("%.2f",totalAmount==null?0:totalAmount));

        // 获得注册时上次的图片地址
        TPubFiles tPubFiles = new TPubFiles();
        tPubFiles.setCustNo(merNo);
        tPubFiles.setFileStatus(SysCodeConstant.MERCHANT_STATUS_NORMAL);
        List<TPubFiles> select = tPubFilesMapper.select(tPubFiles);
        List<String> filePaths = new ArrayList<>();
        select.forEach(node ->{
            filePaths.add(node.getFilePath());
        });
        merchantDetail.put("filePaths",filePaths);

        // 码值转换
        Object companyType = merchantDetail.get("companyType");
        if(companyType == null){
            merchantDetail.put("companyType","未知类型");
        }
        else if(SysCodeConstant.MERCHANT_TYPE_SOHO.equals(companyType.toString())){
            merchantDetail.put("companyType","个体工商户");
        }else{
            merchantDetail.put("companyType","门店");
        }

        // 获得商户供货状态
        if(!StringUtils.isEmpty(distributorNo)){
            TGoodsDistributorMer queryStatus = new TGoodsDistributorMer();
            queryStatus.setDistributorNo(distributorNo);
            queryStatus.setMerNo(merNo);
            queryStatus.setOnStatus(SysCodeConstant.GOODS_ON_STATUS_YES);
            List<TGoodsDistributorMer> queryList = tGoodsDistributorMerMapper.select(queryStatus);
            if(queryList != null && queryList.size() > 0){
                merchantDetail.put("supplyStatus","正常供货");
            }else{
                merchantDetail.put("supplyStatus","停止供货");
            }
        }
//        Object supplyStatus = merchantDetail.get("supplyStatus");
//        if(supplyStatus == null){
//            merchantDetail.put("supplyStatus","未知状态");
//            return merchantDetail;
//        }
//        if(SysCodeConstant.STATUS_YES.equals(supplyStatus.toString())){
//            merchantDetail.put("supplyStatus","正常供货");
//        }else{
//            merchantDetail.put("supplyStatus","停止供货");
//        }
        return merchantDetail;
    }

    @Override
    public Respones searchMerchants(MerchantVO vo) throws Exception {
        String loginId = vo.getLoginId();
        if(StringUtils.isEmpty(loginId)){
            throw new ParameterException("参数错误，loginId不能为空");
        }
        Respones respones = new Respones();
        Map<String,Object> param = new HashMap<>();
        String merName = vo.getMer_name();
        if(!StringUtils.isEmpty(merName)){
            param.put("merName",merName.trim());
        }
        List<String> roles = getRoles(vo);
        param.put("distributorNo",vo.getDistributor_no());
        param.put("useStatus",SysCodeConstant.STATUS_YES);
        param.put("merStatus",Arrays.asList(SysCodeConstant.MERCHANT_STATUS_NORMAL,SysCodeConstant.MERCHANT_STATUS_FREEZE));
        if(roles.contains(SysCodeConstant.SALE_ROLE)) {
            // 销售
            param.put("loginId", loginId.trim());
            List<Map<String, Object>> list = manageMapper.searchMerchantsBySaleLoginId(param);
            addPageInfo(list, respones);
            return respones;
        }else if(roles.contains(SysCodeConstant.SALES_ADMINISTRATOR)){
            // 销售主管
            // 如果销售主管下没有销售，直接返回空
            List<String> salesUserId = manageMapper.selectAllSales(loginId.trim());
            if(salesUserId == null || salesUserId.size() <1){
                addPageInfo(new ArrayList<>(),respones);
                return respones;
            }
            param.put("userIds",salesUserId);
            List<Map<String, Object>> list = manageMapper.searchMerchantsBySaleAdmin(param);
            System.out.println(list);
            addPageInfo(list,respones);
            return respones;
        }
        List<Map<String, Object>> list = manageMapper.searchMerchants(param);
        addPageInfo(list,respones);
        return respones;
    }

    @Override
    public Respones getAgreementGoodsDetail(MerchantVO vo) throws Exception {
        String merNo = vo.getMer_no();

        Map<String, Object> params = new HashMap<>();
        if(StringUtils.isEmpty(merNo)){
            throw new ServiceException("错误参数，商户id不能为空");
        }
        params.put("merNo",merNo);
        params.put("goodsStatus",SysCodeConstant.GOODS_ON_STATUS_YES);
        NPageHelper.startPage(params);
        List<Map<String, Object>> list = manageMapper.getAgreementGoodsDetail(params);
        if(list != null && list.size() > 0 && list.get(0) == null){
            list = new ArrayList<>();
        }
        list.forEach(node->{
            // 获得销售量
            int totalCount = manageMapper.selectTotalCount(node.get("goodsNo"));
            node.put("totalCount",totalCount);
            // 获得图片
            TGoodsFiles tGoodsFiles = new TGoodsFiles();
            tGoodsFiles.setGoodsNo(node.get("goodsNo").toString());
            List<TGoodsFiles> goodsFiles = tGoodsFilesMapper.select(tGoodsFiles);
            List<String> filePath = new ArrayList<>();
            goodsFiles.forEach(file->{
                if(file != null){
                    filePath.add(file.getFilePath());
                }
            });
            node.put("filePath",filePath);
        });
        return addPageInfo(list,new Respones());
    }

    @Override
    public List<Map<String, Object>> getAllSales(CryptoCmd cryptoCmd) throws Exception {
        Map<String,Object> param = new HashMap<>();
        param.put("userStatus",SysCodeConstant.STATUS_YES);
        param.put("sales",SysCodeConstant.SALE_ROLE);
        List<Map<String, Object>> resultList = manageMapper.getAllSales(param);
        if(resultList == null || resultList.size() == 0 || resultList.get(0) == null){
            resultList = new ArrayList<>();
        }
        cryptoCmd.setOut(resultList);
        cryptoCmd.setSuccess(true);
        return resultList;
    }

    @Override
    public List<TPubDistributorInfo> getDistributorList(String merNo) throws Exception {
        TPubDistributorMerExample example = new TPubDistributorMerExample();
        TPubDistributorMerExample.Criteria criteria=example.createCriteria();
        criteria.andMerNoEqualTo(merNo);
        List<TPubDistributorMer> list=tPubDistributorMerMapper.selectByExample(example);
        List<String> disNo=new ArrayList<>();
        list.forEach(node ->disNo.add(node.getDistributorNo()));
        TPubDistributorInfoExample distributorInfoExample=new TPubDistributorInfoExample();
        TPubDistributorInfoExample.Criteria c=distributorInfoExample.createCriteria();
        c.andDistributorNoIn(disNo);
        return distributorInfoMapper.selectByExample(distributorInfoExample);
    }

    @Override
    public void getMerchantsByDealer(CryptoCmd cryptoCmd) throws Exception {
        Map<String, Object> params = cryptoCmd.getParams();
        Object distributorNo = params.get("distributorNo");
        if(distributorNo == null){
            throw new ParameterException("参数错误，经销商编号不能为空");
        }
        params.put("merStatus",SysCodeConstant.MERCHANT_STATUS_NORMAL);
        NPageHelper.startPage(params);
        List<Map<String, Object>> list = manageMapper.getMerchantsByDealer(params);
        if(list != null && list.size() > 0 && list.get(0) == null){
            list = new ArrayList<>();
        }
        addPageInfo(list,cryptoCmd);
    }

    @Override
    public void getMerchantStatus(CryptoCmd cryptoCmd) throws Exception {
        SysCodeInfo sysCodeInfo = new SysCodeInfo();
        sysCodeInfo.setCodeTypeId(SysCodeConstant.MERCHANT_CODE_NAME);
        List<SysCodeInfo> selectAll = sysCodeInfoMapper.select(sysCodeInfo);
        List<Map<String,Object>> result = new ArrayList<>();
        selectAll.forEach(node->{
            Map<String,Object> map = new HashMap<>();
            map.put("codeName",node.getCodeInfoName());
            map.put("codeValue",node.getCodeInfoValue());
            result.add(map);
        });
        cryptoCmd.setOut(result);
    }

    @Override
    public void getDealersByMerchant(CryptoCmd cryptoCmd) throws Exception {
        Map<String, Object> params = cryptoCmd.getParams();
        Object merNo = params.get("merNo");
        if(merNo == null){
            throw new ParameterException("参数错误，销售编号不能为空");
        }
        params.put("status",SysCodeConstant.DISTRIBUTOR_STATUS_NORMAL);
        NPageHelper.startPage(params);
        List<Map<String,Object>> result =  manageMapper.getDealersByMerchant(params);
        if(result != null && result.size() > 0 && result.get(0) == null){
            result = new ArrayList<>();
        }
        addPageInfo(result,cryptoCmd);
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void merchantAndDealerUnbind(CryptoCmd cryptoCmd) throws Exception {
        Map<String, Object> params = cryptoCmd.getParams();
        String mer_no = (String) params.get("merNo");
        String distributor_no = (String) params.get("distributorNo");
        List<String> merNo = JSON.parseArray(mer_no).toJavaObject(List.class);
        List<String> distributorNo = JSON.parseArray(distributor_no).toJavaObject(List.class);

//        List<String> merNo = (List<String>) params.get("merNo");
//        List<String> distributorNo = (List<String>) params.get("distributorNo");
        if(merNo == null || merNo.size() < 1){
            throw new ParameterException("参数错误，商户编号不能为空");
        }
        if(distributorNo == null || distributorNo.size() < 1){
            throw new ParameterException("参数错误，经销商编号不能为空");
        }

        TPubDistributorMer tPubDistributorMer = new TPubDistributorMer();
        tPubDistributorMer.setMerNo(merNo.toString());
        tPubDistributorMer.setDistributorNo(distributorNo.toString());
        // 删除商户和经销商关系
        if(tPubDistributorMerMapper.merchantAndDealerUnbind(merNo, distributorNo) < 1){
            throw new ServiceException("解除绑定失败,关系不存在");
        }
        // 删除商户与经销商的协议商品价
        try{
            manageMapper.deleteMerchantAndDealerGoodsAmt(merNo,distributorNo);
        }catch (Exception e){
            throw new ServiceException("删除商户与经销商的协议商品价失败");
        }
    }

    @Override
    public void merchantAndSaleUnbind(CryptoCmd cryptoCmd) throws Exception {
        Map<String, Object> params = cryptoCmd.getParams();
        Object merNo = params.get("merNo");
        if(merNo == null){
            throw new ParameterException("参数错误，商户编号不能为空");
        }
        TPubMerInfo tPubMerInfo = new TPubMerInfo();
        tPubMerInfo.setMerNo(merNo.toString());
        tPubMerInfo.setStaffNo("");
        tPubMerInfoMapper.updateByPrimaryKeySelective(tPubMerInfo);
        cryptoCmd.setSuccess(true);
    }

    @Override
    public void modifyMerchantsRelation(CryptoCmd cryptoCmd) throws Exception {
        Map<String, Object> params = cryptoCmd.getParams();
        List<String> distributorNos = (List<String>) params.get("distributorNos");
        Object merNo = params.get("merNo");
        if(merNo == null){
            throw new ParameterException("参数错误，商户编号不能为空");
        }
        if(distributorNos == null || distributorNos.size() < 1){
            throw new ParameterException("参数错误，经销商编号不能为空");
        }
    }

    @Override
    public Respones getDealerBySaleAndMer(MerchantVO vo) throws Exception {
        List<Map<String,Object>> resultList;
        String loginId = vo.getLoginId();
        String merNo = vo.getMer_no();
        if(StringUtils.isEmpty(loginId)){
            throw new ParameterException("参数错误，销售loginId不能为空");
        }
        if(StringUtils.isEmpty(merNo)){
            throw new ParameterException("参数错误，商户编号不能为空");
        }

        // 组装参数
        Map<String,Object> param = new HashMap<>();
        param.put("distributorName",vo.getDistributor_name());
        param.put("merNo",merNo);
        param.put("loginId",loginId);
        param.put("merStatus", SysCodeConstant.MERCHANT_STATUS_NORMAL);
        param.put("distributorStatus",SysCodeConstant.DISTRIBUTOR_STATUS_NORMAL);

        // 获取权限
        List<String> roles = getRoles(vo);
        if(roles == null || roles.size() < 1){
            throw new ParameterException("参数错误，用户无任何权限");
        }
        if(roles.contains(SysCodeConstant.SALE_ROLE)){
            // 销售
            resultList =  manageMapper.getDealerBySaleAndMer(param);
        }else if(roles.contains(SysCodeConstant.SALES_ADMINISTRATOR)){
            // 销售主管
            // 获得所有销售id
            List<String> saleIds = manageMapper.selectAllSales(loginId);
            param.put("saleIds",saleIds);
            resultList = manageMapper.getDealerBySaleAdminAndMer(param);
        }else{
            // 上级
            resultList = manageMapper.getDealerByAdminAndMer(param);
        }

        if(resultList != null && resultList.size()>0 && resultList.get(0) == null){
            resultList = new ArrayList<>();
        }
        Respones respones = new Respones();
        respones.setReq(resultList);
        respones.setResult(true);
        respones.setCode("200");
        return respones;
    }

    @Override
    public List<Map<String, Object>> getAgreementGoodsDetail(CryptoCmd cryptoCmd) throws ServiceException {
        Map<String, Object> params = cryptoCmd.getParams();
        if(params.get("merNo") == null){
            throw new ServiceException("错误参数，商户id不能为空");
        }
        params.put("goodsStatus",SysCodeConstant.GOODS_ON_STATUS_YES);
        NPageHelper.startPage(params);
        List<Map<String, Object>> list = manageMapper.getAgreementGoodsDetail(params);
        if(list !=null && list.size()>0 && list.get(0) == null){
            list = new ArrayList<>();
        }
        list.forEach(node->{
            // 获得销售量
            int totalCount = manageMapper.selectTotalCount(node.get("goodsNo"));
            node.put("totalCount",totalCount);
            // 获得图片
            TGoodsFiles tGoodsFiles = new TGoodsFiles();
            tGoodsFiles.setGoodsNo(node.get("goodsNo").toString());
            List<TGoodsFiles> goodsFiles = tGoodsFilesMapper.select(tGoodsFiles);
            List<String> filePath = new ArrayList<>();
            goodsFiles.forEach(file->{
                if(file != null){
                    filePath.add(file.getFilePath());
                }
            });
            node.put("filePath",filePath);
        });
        addPageInfo(list,cryptoCmd);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean merchantDealerBind(CryptoCmd cryptoCmd) throws Exception {
        List<String> merNos;
        List<String> distributorNos;
        //List<String> merNo = JSON.parseArray(mer_no).toJavaObject(List.class);
        try{
//            merNos = ((JSONArray) cryptoCmd.getParams().get("merNos")).toJavaList(String.class);
            merNos = JSON.parseArray(cryptoCmd.getParams().get("merNos").toString()).toJavaObject(List.class);
            distributorNos = JSON.parseArray(cryptoCmd.getParams().get("distributorNos").toString()).toJavaObject(List.class);
//            distributorNos = ((JSONArray) cryptoCmd.getParams().get("distributorNos")).toJavaList(String.class);
        }catch (Exception e){
            throw new ParameterException("参数转换错误");
        }
        if(merNos == null || merNos.size() <1){
            throw new ParameterException("错误参数，商户不能为空");
        }
        if(distributorNos == null || distributorNos.size() <1){
            throw new ParameterException("错误参数，经销商不能为空");
        }
        // 修改商户与经销商关系表(t_pub_distributor_mer)
//        int flag = manageMapper.modifyMerchants(merNos,distributorNos, new Date());
//        if(flag <0){
//            throw new ServiceException("修改失败");
//        }
        merNos.forEach(merNo->{
            distributorNos.forEach(disNo->{
                TPubDistributorMer queryDemo = new TPubDistributorMer();
                queryDemo.setDistributorNo(disNo);
                queryDemo.setMerNo(merNo);
                List<TPubDistributorMer> select = tPubDistributorMerMapper.select(queryDemo);
                if(select != null && select.size() > 0){
                    // 更新
                    queryDemo.setUpdateDate(new Date());
                    queryDemo.setTableKey(select.get(0).getTableKey());
                    tPubDistributorMerMapper.updateByPrimaryKey(queryDemo);
                }else{
                    // 新增
                    queryDemo.setTableKey(UUIDUtils.getUuid());
                    queryDemo.setInsertDate(new Date());
                    tPubDistributorMerMapper.insert(queryDemo);
                }
            });
        });
        return true;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public boolean freezeMerchants(CryptoCmd cryptoCmd) throws Exception {
        Object user_no;
        try {
            user_no = cryptoCmd.getParams().get("userNo");
        }catch (Exception e){
            throw new ParameterException("参数错误");
        }
        if(user_no == null){
            throw new ParameterException("错误参数，商户不能为空");
        }
        String isFreeze = (String) cryptoCmd.getParams().get("isFreeze");
        // 通过userNo获得mer_no
        TUserBaseInfo tUserBaseInfo = new TUserBaseInfo();
        tUserBaseInfo.setUserNo(user_no.toString());
        TUserBaseInfo selectOne = tUserBaseInfoMapper.selectOne(tUserBaseInfo);
        if(selectOne == null){
            throw new ParameterException("商户不存在");
        }

        TPubMerInfo info = new TPubMerInfo();
        info.setMerNo(selectOne.getUserNo());
        Integer flag;
        if(isFreeze.equals("true")){
            flag = manageMapper.freezeMerchant(user_no.toString(), SysCodeConstant.MERCHANT_STATUS_FREEZE);
            info.setMerStatus(SysCodeConstant.MERCHANT_STATUS_FREEZE);
        }else {
            flag = manageMapper.freezeMerchant(user_no.toString(), SysCodeConstant.MERCHANT_STATUS_NORMAL);
            info.setMerStatus(SysCodeConstant.MERCHANT_STATUS_NORMAL);
        }
        if(tPubMerInfoMapper.updateByPrimaryKeySelective(info) < 1){
            throw new ServiceException("操作失败");
        }
        if(flag <=0){
            throw new ServiceException("商户不存在");
        }
        return true;
    }

    @Override
    public List<Map<String, Object>> searchMerchants(CryptoCmd cryptoCmd) throws Exception {
        Map<String, Object> params = cryptoCmd.getParams();
        if(params.get("merName") != null){
            params.put("merName",params.get("merName").toString().trim());
        }
        Object loginId = SecurityUtils.getSubject().getPrincipal();
        if(loginId == null){
            throw new ParameterException("用户未登录");
        }
        if(SecurityUtils.getSubject().hasRole(SysCodeConstant.SALE_ROLE)){
            // 销售
            params.put("loginId",loginId.toString());
            List<Map<String, Object>> list = manageMapper.searchMerchantsByLoginId(params);
            addPageInfo(list,cryptoCmd);
            return list;
        }else if(SecurityUtils.getSubject().hasRole(SysCodeConstant.SALES_ADMINISTRATOR)){
            // 销售主管
            List<String> salesUserId = manageMapper.selectAllSales(loginId.toString());
            if(salesUserId == null || salesUserId.size() == 0) {
                // 无销售
                addPageInfo(new ArrayList<>(), cryptoCmd);
                return null;
            }
            params.put("userIds",salesUserId);
            if(params.get("merStatus") != null){
                params.put("merStatus",Arrays.asList(params.get("merStatus").toString()));
            }
            NPageHelper.startPage(params);
            List<Map<String, Object>> list = manageMapper.searchMerchantsBySaleAdmin(params);
            addPageInfo(list,cryptoCmd);
            return list;
        }
        List<Map<String, Object>> list = manageMapper.searchMerchants(params);
        addPageInfo(list,cryptoCmd);
        return list;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public boolean modifyGroup(CryptoCmd cryptoCmd) throws Exception {
        Map<String, Object> params = cryptoCmd.getParams();
        TPubGroup tPubGroup = new TPubGroup();
        tPubGroup.setCreateType(SysCodeConstant.DEALERS_IDENTITY);
//      List<String> roles = getRoles(cryptoCmd.getParams());
        Object loginId = SecurityUtils.getSubject().getPrincipal();
        if(loginId == null){
            throw new ServiceException("用户未登录");
        }
        tPubGroup.setCreateNo(loginId.toString());
        Object group_no = params.get("group_no");
        if(group_no == null || StringUtils.isEmpty(group_no.toString().trim())){
            // 新增
            tPubGroup.setGroupNo(UUIDUtils.getUuid());
            tPubGroup.setCreateDate(new Date());
            tPubGroup.setGroupStatus(SysCodeConstant.STATUS_YES);
            tPubGroup.setGroupName(cryptoCmd.getParams().get("group_name").toString().trim());
            if(tPubGroupMapper.insert(tPubGroup) <= 0){
                throw  new ServiceException("新增用户组失败");
            }
        }else{
            // 修改
            TPubGroup qoeryEntity = new TPubGroup();
            qoeryEntity.setGroupNo(group_no.toString());
            TPubGroup tPubGroup1 = tPubGroupMapper.selectOne(qoeryEntity);
            if(tPubGroup1 == null){
                throw new ServiceException("修改失败，用户组不存在");
            }
            // 判断分组名是否修改，如果修改，则修改销售商户经销商分组内容表（t_pub_group_cust）
            if(!tPubGroup1.getGroupName().equals(cryptoCmd.getParams().get("group_name").toString())){
                tPubGroupCustMapper.updateGroupName(group_no.toString(),cryptoCmd.getParams().get("group_name").toString());
            }
            tPubGroup.setGroupName(cryptoCmd.getParams().get("group_name").toString());
            tPubGroup.setGroupNo(cryptoCmd.getParams().get("group_no").toString());
            if(tPubGroupMapper.updateByPrimaryKeySelective(tPubGroup) < 0){
                throw  new ServiceException("修改用户组失败");
            }

        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public boolean deleteGroup(CryptoCmd cryptoCmd) throws Exception {
        Object group_no = cryptoCmd.getParams().get("group_no");
        if(group_no == null){
            throw new ParameterException("参数错误,分组编号不能为空");
        }
        TPubGroup tPubGroup = new TPubGroup();
        tPubGroup.setGroupNo(group_no.toString());
        tPubGroup.setGroupStatus(SysCodeConstant.STATUS_NO);
        // 删除从表
        TPubGroupCust tPubGroupCust = new TPubGroupCust();
        tPubGroupCust.setGroupNo(group_no.toString());
        if(tPubGroupCustMapper.delete(tPubGroupCust) < 0){
            throw new ServiceException("删除失败");
        }
        // 更新主表
        if(tPubGroupMapper.updateByPrimaryKeySelective(tPubGroup) <= 0){
            throw new ServiceException("用户组不存在");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public boolean pushGroup(CryptoCmd cryptoCmd) throws Exception {
        List<TPubGroupCust> tPubGroupCusts = new ArrayList<>();
        List<String> custNos = ((JSONArray) cryptoCmd.getParams().get("cust_nos")).toJavaList(String.class);
        if(custNos == null || custNos.size() <= 0){
            throw new ParameterException("参数错误，商户不能为空");
        }
        if(cryptoCmd.getParams().get("group_no") == null || cryptoCmd.getParams().get("group_name") == null){
            throw new ParameterException("参数错误，分组编号和分组名均不能为空");
        }
        String groupNo = cryptoCmd.getParams().get("group_no").toString();
        String groupName = cryptoCmd.getParams().get("group_name").toString();
        for (String custNo:custNos){
            TPubGroupCust queryEntity = new TPubGroupCust();
            queryEntity.setCustNo(custNo);
            queryEntity.setGroupNo(groupNo);
            if(tPubGroupCustMapper.select(queryEntity).size() == 0){
                TPubGroupCust tPubGroupCust = new TPubGroupCust();
                tPubGroupCust.setInsertDate(new Date());
                tPubGroupCust.setCustNo(custNo);
                tPubGroupCust.setTableKey(UUIDUtils.getUuid());
                tPubGroupCust.setGroupName(groupName);
                tPubGroupCust.setGroupNo(groupNo);
                tPubGroupCusts.add(tPubGroupCust);
            }
        }
        // 批量插入
        if(tPubGroupCusts.size() != 0) {
            if (tPubGroupCustMapper.insertBatch(tPubGroupCusts) < 0) {
                throw new ServiceException("添加商户到商户分组失败");
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public boolean popGroup(CryptoCmd cryptoCmd) throws  Exception{
        List<String> custNos = ((JSONArray) cryptoCmd.getParams().get("cust_nos")).toJavaList(String.class);
        if(custNos == null || custNos.size() <= 0){
            throw new ParameterException("参数错误，商户不能为空");
        }
        if(cryptoCmd.getParams().get("group_no") == null){
            throw new ParameterException("参数错误，分组编号不能为空");
        }
//        if(tPubGroupCustMapper.deleteBatch(custNos,cryptoCmd.getParams().get("group_no").toString()) < 0){
//            throw new ServiceException("删除失败");
//        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public boolean updateMerchant(CryptoCmd cryptoCmd) throws  Exception {
        if(cryptoCmd.getParams() == null){
            throw new ParameterException("商户不能为空");
        }
        TPubMerInfo tPubMerInfo;
        try {
            tPubMerInfo = JSONObject.parseObject(JSON.toJSONString(cryptoCmd.getParams()), TPubMerInfo.class);
        }catch (Exception e){
            throw new ParameterException("参数转换错误，请检查参数");
        }
        tPubMerInfo.setUpdateDate(new Date());
        if(tPubMerInfoMapper.updateByPrimaryKeySelective(tPubMerInfo) < 1){
            throw new ServiceException("更新失败,商户不存在");
        }

        // 向商户变更信息表插入数据
        TPubMerChange tPubMerChange = new TPubMerChange();
        tPubMerChange.setCompanyType(tPubMerInfo.getCompanyType());
        tPubMerChange.setInsertDate(new Date());
        tPubMerChange.setLegalName(tPubMerInfo.getLegalName());
        tPubMerChange.setMerAddre(tPubMerInfo.getMerAddre());
        tPubMerChange.setMerContact(tPubMerInfo.getMerContact());
        tPubMerChange.setMerName(tPubMerInfo.getMerName());
        tPubMerChange.setMerNo(tPubMerInfo.getMerNo());
        tPubMerChange.setMerRegNo(tPubMerInfo.getMerRegNo());
        tPubMerChange.setMerStatus(tPubMerInfo.getMerStatus());
        tPubMerChange.setTableKey(UUIDUtils.getUuid());
        tPubMerChange.setMerContactPhone(tPubMerInfo.getMerContactPhone());
        // 获得经销商编号
        TPubDistributorMer tPubDistributorMer = new TPubDistributorMer();
        tPubDistributorMer.setMerNo(tPubMerInfo.getMerNo());
        List<TPubDistributorMer> selectAll = tPubDistributorMerMapper.select(tPubDistributorMer);
        if(selectAll != null && selectAll.size() > 0){
            tPubMerChange.setDistributorNo(selectAll.get(0).getDistributorNo());
        }
        if(tPubMerChangeMapper.insert(tPubMerChange) < 1){
            throw new ServiceException("更新失败");
        }
        return true;
    }

    @Override
    public boolean auditMerchant(MerchantVO vo) throws Exception {
//        if(pubMerInfo == null){
//            throw new ParameterException("参数错误，审核商户不能为空");
//        }
//        if(StringUtils.isEmpty(distributorNo)){
//            throw new ParameterException("参数错误，商户所属经销商不能为空");
//        }
//        pubMerInfo.setInsertDate(new Date());
//        pubMerInfo.setMerNo(UUIDUtils.getUuid());
//        if(tPubMerInfoMapper.insert(pubMerInfo) < 0){
//            throw new ServiceException("审核失败");
//        }

        String merNo = vo.getMer_no();
        if(StringUtils.isEmpty(merNo)){
            throw new ParameterException("参数错误，商户编号不能为空");
        }
        String distributorNo = vo.getDistributor_no();
        if(StringUtils.isEmpty(distributorNo)){
            throw new ParameterException("参数错误，经销商编号不能为空");
        }
        Boolean auditStatus = vo.getAuditStatus();
        TPubMerInfo updateDemo = new TPubMerInfo();
        TUserBaseInfo tUserBaseInfo = new TUserBaseInfo();
        updateDemo.setMerNo(merNo);
        if(auditStatus){
            // 审核通过
            updateDemo.setMerStatus(SysCodeConstant.MERCHANT_STATUS_NORMAL);
            tUserBaseInfo.setUserStatus(SysCodeConstant.MERCHANT_STATUS_NORMAL);
        }else {
            updateDemo.setMerStatus(SysCodeConstant.MERCHANT_STATUS_AUDIT_FAILD);
            tUserBaseInfo.setUserStatus(SysCodeConstant.MERCHANT_STATUS_AUDIT_FAILD);
        }
        // 更新商户表
        if(tPubMerInfoMapper.updateByPrimaryKeySelective(updateDemo) < 1){
            throw new ServiceException("更新商户信息失败,商户不存在");
        }
        // 更新基本信息表

        tUserBaseInfo.setUserNo(merNo);

//        if(tUserBaseInfoMapper.updateStatusByRelationNo(merNo,SysCodeConstant.STATUS_YES) < 1){
        if(tUserBaseInfoMapper.updateByPrimaryKeySelective(tUserBaseInfo) < 1){
            throw new ServiceException("更新基本信息失败");
        }
        TPubMerInfo pubMerInfo = tPubMerInfoMapper.selectOne(updateDemo);
        // 向商户变更信息表插入数据
        TPubMerChange tPubMerChange = new TPubMerChange();
        tPubMerChange.setCompanyType(pubMerInfo.getCompanyType());
        tPubMerChange.setDistributorNo(distributorNo);
        tPubMerChange.setInsertDate(new Date());
        tPubMerChange.setLegalName(pubMerInfo.getLegalName());
        tPubMerChange.setMerAddre(pubMerInfo.getMerAddre());
        tPubMerChange.setMerContact(pubMerInfo.getMerContact());
        tPubMerChange.setMerName(pubMerInfo.getMerName());
        tPubMerChange.setMerNo(pubMerInfo.getMerNo());
        tPubMerChange.setMerRegNo(pubMerInfo.getMerRegNo());
        tPubMerChange.setMerStatus(pubMerInfo.getMerStatus());
        tPubMerChange.setTableKey(UUIDUtils.getUuid());
        tPubMerChange.setMerContactPhone(pubMerInfo.getMerContactPhone());
        if(tPubMerChangeMapper.insert(tPubMerChange) < 0){
            throw  new ServiceException("审核失败");
        }
        return true;
    }

    @Override
    public boolean modifyGroup(MerchantVO vo) throws Exception {
        String groupName = vo.getGroup_name();
        String groupNo = vo.getGroup_no();
        if(StringUtils.isEmpty(groupName)){
            throw new ParameterException("参数错误，分组名不能为空");
        }
        if(StringUtils.isEmpty(vo.getLoginId())){
            throw new ParameterException("参数错误，loginId不能为空");
        }

        // 获得销售编号
        SysUser sysUser = new SysUser();
        sysUser.setLoginId(vo.getLoginId());
        SysUser user = sysUserMapper.selectOne(sysUser);
        if(user == null){
            throw new ParameterException("用户账户错误");
        }
        TPubGroup tPubGroup = new TPubGroup();
        tPubGroup.setCreateType(SysCodeConstant.SALE_IDENTITY);
        tPubGroup.setGroupType(SysCodeConstant.CUST_TYPE_MERCHANT);
        tPubGroup.setCreateNo(user.getUserId());

        String merNo = user.getUserId();
        if(StringUtils.isEmpty(groupNo)){
            // 新增
            // 查询组名是否存在
            TPubGroup queryName = new TPubGroup();
            queryName.setCreateNo(merNo);
            queryName.setGroupName(groupName.trim());
            queryName.setGroupStatus(SysCodeConstant.STATUS_YES);
            TPubGroup tPubGroup1 = tPubGroupMapper.selectOne(queryName);
            if(tPubGroup1!=null){
                throw new ParameterException("参数错误,分组名已存在");
            }

            tPubGroup.setGroupNo(UUIDUtils.getUuid());
            tPubGroup.setCreateDate(new Date());
            tPubGroup.setGroupType(SysCodeConstant.CUST_TYPE_MERCHANT);
            tPubGroup.setGroupStatus(SysCodeConstant.STATUS_YES);
            tPubGroup.setGroupName(groupName);
            if(tPubGroupMapper.insert(tPubGroup) <= 0){
                throw  new ServiceException("新增用户组失败");
            }
        }else{
            // 修改
            TPubGroup qoeryEntity = new TPubGroup();
            qoeryEntity.setGroupNo(groupNo);
            TPubGroup tPubGroup1 = tPubGroupMapper.selectOne(qoeryEntity);
            if(tPubGroup1 == null){
                throw new ServiceException("修改失败，用户组不存在");
            }
            // 判断分组名是否修改，如果修改，则修改销售商户经销商分组内容表（t_pub_group_cust）
            if(!tPubGroup1.getGroupName().equals(groupName)){
                tPubGroupCustMapper.updateGroupName(groupNo,groupName);
            }
            tPubGroup.setGroupName(groupName);
            tPubGroup.setGroupNo(groupNo);
            if(tPubGroupMapper.updateByPrimaryKeySelective(tPubGroup) < 0){
                throw  new ServiceException("修改用户组失败");
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public boolean deleteGroup(MerchantVO vo) throws Exception {
        String groupNo = vo.getGroup_no();
        if(StringUtils.isEmpty(groupNo)){
            throw new ParameterException("参数错误,分组编号不能为空");
        }
        TPubGroup tPubGroup = new TPubGroup();
        tPubGroup.setGroupNo(groupNo);
        tPubGroup.setGroupStatus(SysCodeConstant.STATUS_NO);
        // 删除从表

        TPubGroupCust tPubGroupCust = new TPubGroupCust();
        tPubGroupCust.setGroupNo(groupNo);
        if(tPubGroupCustMapper.delete(tPubGroupCust) < 0){
            throw new ServiceException("删除失败");
        }

        // 更新主表
        if(tPubGroupMapper.updateByPrimaryKeySelective(tPubGroup) <= 0){
            throw new ServiceException("用户组不存在");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public boolean pushGroup(MerchantVO vo) throws Exception {
        List<TPubGroupCust> tPubGroupCusts = new ArrayList<>();
        List<String> custNos = vo.getCust_nos();
        if(custNos == null || custNos.size() <= 0){
            throw new ParameterException("参数错误，商户不能为空");
        }
        String groupNo = vo.getGroup_no();
        if(StringUtils.isEmpty(groupNo)){
            throw new ParameterException("参数错误，分组编号不能为空");
        }
        for (String custNo:custNos){
            // 查询是商户是否已存在该分组
            TPubGroupCust queryEntity = new TPubGroupCust();
            queryEntity.setCustNo(custNo);
            queryEntity.setGroupNo(groupNo);
            if(tPubGroupCustMapper.select(queryEntity).size() == 0){
                // 查询分组名
                TPubGroup tPubGroup = new TPubGroup();
                tPubGroup.setGroupNo(groupNo);

                // 组装插入信息
                TPubGroupCust tPubGroupCust = new TPubGroupCust();
                tPubGroupCust.setInsertDate(new Date());
                tPubGroupCust.setCustNo(custNo);
                tPubGroupCust.setTableKey(UUIDUtils.getUuid());
                tPubGroupCust.setGroupName(tPubGroupMapper.selectOne(tPubGroup).getGroupName());
                tPubGroupCust.setGroupNo(groupNo);

                // 获得商户名
                TPubMerInfo tPubMerInfo = new TPubMerInfo();
                tPubMerInfo.setMerNo(custNo);
                tPubMerInfo.setMerStatus(SysCodeConstant.MERCHANT_STATUS_NORMAL);
                TPubMerInfo selectMerInfo = tPubMerInfoMapper.selectOne(tPubMerInfo);
                if(selectMerInfo == null){
                    throw new ServiceException("参数错误，用户"+custNo+"不存在");
                }
                tPubGroupCust.setCustName(selectMerInfo.getMerName());
                tPubGroupCusts.add(tPubGroupCust);
            }
        }
        // 批量插入
        if(tPubGroupCusts.size() != 0) {
            if (tPubGroupCustMapper.insertBatch(tPubGroupCusts) < 0) {
                throw new ServiceException("添加商户到商户分组失败");
            }
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public boolean popGroup(MerchantVO vo) throws Exception {
        List<String> custNos = vo.getCust_nos();
        String groupNo = vo.getGroup_no();
        if(custNos == null || custNos.size() <= 0){
            throw new ParameterException("参数错误，商户不能为空");
        }
        if(StringUtils.isEmpty(groupNo)){
            throw new ParameterException("参数错误，分组编号不能为空");
        }
        if(tPubGroupCustMapper.deleteBatch(custNos,groupNo) < 0){
            throw new ServiceException("删除失败");
        }
        return true;
    }

    @Override
    public Respones getAllMerchantsInGroup(MerchantVO vo) throws Exception {
        String loginId = vo.getLoginId();
        if(StringUtils.isEmpty(loginId)){
            throw new ParameterException("参数错误，用户id不能为空");
        }
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String,Object> params = new HashMap<>();
        params.put("loginId",loginId);
        params.put("userStatus",SysCodeConstant.STATUS_YES);
        params.put("groupType",SysCodeConstant.CUST_TYPE_MERCHANT);
        NPageHelper.startPage(params);
        List<Map<String,Object>> groupsInfo =  manageMapper.getGroupsByLoginId(params);
        for(Map<String,Object> item: groupsInfo){
            TPubGroupCust tPubGroupCust = new TPubGroupCust();
            tPubGroupCust.setGroupNo(item.get("groupNo").toString());
            List<TPubGroupCust> list = tPubGroupCustMapper.select(tPubGroupCust);
            Map<String,Object> map = new HashMap<>();
            map.put("groupNo",item.get("groupNo"));
            map.put("groupName",item.get("groupName"));
            map.put("list",list);
            result.add(map);
        }
        Respones respones = new Respones();
        return addPageInfo(result,respones);
    }

    @Override
    public Respones getAllMerchants(MerchantVO vo) {
        List<String> roles = getRoles(vo);
        if(roles == null || roles.size() < 1){
            throw new ParameterException("用户没有任何权限，请检查参数");
        }
        String loginId = vo.getLoginId();
        if(StringUtils.isEmpty(loginId)){
            throw new ParameterException("参数错误，loginId不能为空");
        }
        Map<String,Object> params = new HashMap<>();
        params.put("pageNo",vo.getPageNo());
        params.put("pageSize",vo.getPageSize());
        params.put("distributorNo",vo.getDistributor_no());
        params.put("useStatus", SysCodeConstant.MERCHANT_STATUS_NORMAL);
        Respones respones = new Respones();
        if(roles.contains(SysCodeConstant.SALE_ROLE)){
            // 销售
            params.put("loginId",loginId);
            NPageHelper.startPage(params);
            List<Map<String, Object>> list = manageMapper.getAllMerchantsByDealer(params);
            return addPageInfo(list,respones);
        }
        else if(roles.contains(SysCodeConstant.SALES_ADMINISTRATOR)){
            // 销售主管
            List<String> salesUserId = manageMapper.selectAllSales(loginId);
            if(salesUserId == null || salesUserId.size() < 1){
                addPageInfo(new ArrayList<>(),respones);
            }
            params.put("userIds",salesUserId);
            NPageHelper.startPage(params);
            List<Map<String, Object>> list = manageMapper.getMerchantsBySaleAdmin(params);
            return addPageInfo(list,respones);
        }
        else {
            // 上级
            NPageHelper.startPage(params);
            List<Map<String, Object>> list = manageMapper.getAllMerchants(params);
            return addPageInfo(list,respones);
        }
    }

    /**
     * 给返回结果添加分页信息
     */
    private static final void addPageInfo(List<Map<String,Object>> list,CryptoCmd cryptoCmd){
        PageInfo<List<Map<String,Object>>> pageinfo = new PageInfo(list);
        cryptoCmd.setOut("pageNo",pageinfo.getPageNum());
        cryptoCmd.setOut("pageSize",pageinfo.getPageSize());
        cryptoCmd.setOut("totalSize",pageinfo.getTotal());
        cryptoCmd.setOut(list);
        if(list.size() == 1 && list.get(0) == null){
            cryptoCmd.setOut("totalSize",0);
            cryptoCmd.setOut("pageSize",0);
        }
    }

    /**
     * 给返回结果添加分页信息
     */
    private static final Respones addPageInfo(List<Map<String,Object>> list,Respones respones){
        PageInfo pageinfo = new PageInfo(list);
        Map<String,Object> out = new HashMap<>();
        out.put("pageNo",pageinfo.getPageNum());
        out.put("pageSize",pageinfo.getPageSize());
        out.put("totalSize",pageinfo.getTotal());
        out.put("list",list);
        respones.setReq(out);
        respones.setResult(true);
        respones.setCode(SysCodeConstant.RESPONSE_STATUS_OK);
        if(list.size() == 1 && list.get(0) == null){
            out.put("pageSize",0);
            out.put("totalSize",0);
        }
        return respones;
    }

    /**
     * 根据loginId查询所有权限id
     */
    private final List<String> getRoles(Map<String,Object> params){
        Object loginId = params.get("loginId");
        if(loginId == null){
            throw new ParameterException("参数错误，loginId不能为空");
        }
        List<String> roles = manageMapper.getRolesByLoginId(loginId.toString(), SysCodeConstant.STATUS_YES);
        return roles;
    }
    /**
     * 根据loginId查询所有权限id
     */
    private final List<String> getRoles(MerchantVO vo){
        String loginId = vo.getLoginId();
        if(StringUtils.isEmpty(loginId)){
            throw new ParameterException("参数错误，loginId不能为空");
        }
        List<String> roles = manageMapper.getRolesByLoginId(loginId, SysCodeConstant.STATUS_YES);
        return roles;
    }
}
