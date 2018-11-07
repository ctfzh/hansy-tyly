package com.hansy.tyly.dealers.manage.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.hansy.tyly.admin.constant.SysCodeConstant;
import com.hansy.tyly.admin.dao.model.SysCodeInfo;
import com.hansy.tyly.admin.system.mapper.SysCodeInfoMapper;
import com.hansy.tyly.common.orders.dao.mapper.TUserNewsMapper;
import com.hansy.tyly.common.orders.dao.model.TUserNews;
import com.hansy.tyly.common.utils.DateUtils;
import com.hansy.tyly.common.utils.Respones;
import com.hansy.tyly.common.utils.UUIDUtils;
import com.hansy.tyly.core.command.CryptoCmd;
import com.hansy.tyly.core.excepiton.ParameterException;
import com.hansy.tyly.core.excepiton.ServiceException;
import com.hansy.tyly.core.helper.NPageHelper;
import com.hansy.tyly.customer.utils.ConstantsUtil;
import com.hansy.tyly.dealers.goods.dao.mapper.TGoodsBaseInfoMapper;
import com.hansy.tyly.dealers.settlement.dao.mapper.TGoodsFilesMapper;
import com.hansy.tyly.dealers.goods.dao.model.TGoodsBaseInfo;
import com.hansy.tyly.dealers.manage.domain.DealersVO;
import com.hansy.tyly.dealers.manage.domain.TPubGroup;
import com.hansy.tyly.dealers.manage.domain.TPubGroupCust;
import com.hansy.tyly.dealers.manage.mapper.ManageMapper;
import com.hansy.tyly.dealers.manage.mapper.TPubGroupCustMapper;
import com.hansy.tyly.dealers.manage.mapper.TPubGroupMapper;
import com.hansy.tyly.dealers.manage.service.MerchantManageService;
import com.hansy.tyly.dealers.settlement.dao.model.TGoodsFiles;
import com.hansy.tyly.merchants.orders.dao.mapper.TpubDistributorInfoMapper;
import com.hansy.tyly.merchants.orders.dao.mapper.TuserBaseInfoMapper;
import com.hansy.tyly.merchants.orders.dao.model.TuserBaseInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Auther: 18383
 * @Date: 2018/8/6 16:43
 * @Description:
 */
@Service
public class MerchantManageServiceImpl implements MerchantManageService {

    @Autowired
    private ManageMapper manageMapper;
    @Autowired
    private TPubGroupMapper tPubGroupMapper;
    @Autowired
    private TPubGroupCustMapper tPubGroupCustMapper;
    @Autowired
    private TGoodsBaseInfoMapper tGoodsBaseInfoMapper;
    @Autowired
    private TpubDistributorInfoMapper tpubDistributorInfoMapper;
    @Autowired
    private TuserBaseInfoMapper tuserBaseInfoMapper;
    @Autowired
    private SysCodeInfoMapper sysCodeInfoMapper;
    @Autowired
    private TUserNewsMapper tUserNewsMapper;
    @Autowired
    private TGoodsFilesMapper tGoodsFilesMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean stopSupply(CryptoCmd cryptoCmd) throws Exception {
        Map<String, Object> params = cryptoCmd.getParams();
        Object goods_no = params.get("goods_no");
        Object distributor_no = params.get("distributor_no");
        if(goods_no == null || distributor_no == null){
            throw  new ParameterException("参数错误,商户和供应商均不能为空");
        }
        params.put("update_date",new Date());
        Boolean isStop = (Boolean) params.get("isStop");
        if(isStop){
            params.put("supply_status",SysCodeConstant.STATUS_NO);
        }else {
            params.put("supply_status",SysCodeConstant.STATUS_YES);
        }

        // 更改经销商与商户供货信息表
        if(manageMapper.stopSupply(params) < 1){
            throw new ServiceException("添加消息通知失败");
        }
        // 获得停止供货的商品的商品名
        TGoodsBaseInfo tGoodsBaseInfo = new TGoodsBaseInfo();
        tGoodsBaseInfo.setGoodsNo(goods_no.toString());
//        // 获得经销商的名字
//        TpubDistributorInfo tpubDistributorInfo = new TpubDistributorInfo();
//        tpubDistributorInfo.setDistributorNo(SecurityUtils.getSubject().getPrincipal().toString());
//        String distributorName = tpubDistributorInfoMapper.selectOne(tpubDistributorInfo).getDistributorName();
        // 组装商户消息记录
        List<TUserNews> newsList = new ArrayList<>();
        List<String> userNos = manageMapper.selectUserNoByDistributorNo(params);
        String goodsName = tGoodsBaseInfoMapper.selectOne(tGoodsBaseInfo).getGoodsName();
        for(String userNo:userNos){
            TUserNews tUserNews = new TUserNews();
            tUserNews.setUserNo(userNo);
            if(isStop){
                tUserNews.setNewsContent("经销商于"+ DateUtils.getDate() + "停止了"+goodsName+"的供货");
            }else {
                tUserNews.setNewsContent("经销商于"+ DateUtils.getDate() + "开始了"+goodsName+"的供货");
            }
            tUserNews.setNewsDate(new Date());
            tUserNews.setTableKey(UUIDUtils.getUuid());
            tUserNews.setUserType(SysCodeConstant.NEWS_TYPE_MERCHANTS);
            tUserNews.setNewsType(SysCodeConstant.NEWS_TYPE_MERCHANTS);
            tUserNews.setIsRead(SysCodeConstant.IS_READ_NO);
            newsList.add(tUserNews);
        }
        // 插入到通知消息记录表
//        if(newsList.size() > 0) {
//            if (manageMapper.pushStopSupplyMessage(newsList) < 1) {
//                throw new ServiceException("添加消息通知失败");
//            }
//        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public boolean modifyGroup(CryptoCmd cryptoCmd) throws Exception {
        Map<String, Object> params = cryptoCmd.getParams();
        TPubGroup tPubGroup = new TPubGroup();
        tPubGroup.setCreateType(ConstantsUtil.DEALERS_IDENTITY);
        Object group_no = params.get("group_no");
        if(group_no == null || StringUtils.isEmpty(group_no.toString().trim())){
            // 新增
            tPubGroup.setGroupNo(UUIDUtils.getUuid());
            tPubGroup.setCreateDate(new Date());
            tPubGroup.setGroupStatus(ConstantsUtil.STATUS_NORMAL);
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
            if(tPubGroupMapper.updateByPrimaryKey(tPubGroup) < 0){
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
        tPubGroup.setGroupStatus(ConstantsUtil.STATUS_NO);
        // 删除从表
        /*
        TPubGroupCust tPubGroupCust = new TPubGroupCust();
        tPubGroupCust.setGroupNo(group_no.toString());
        if(tPubGroupCustMapper.delete(tPubGroupCust) < 0){
            throw new ServiceException("删除失败");
        }
        */
        // 更新主表
        if(tPubGroupMapper.updateByPrimaryKeySelective(tPubGroup) < 1){
            throw new ServiceException("删除失败");
        }

        return true;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public boolean pushGroup(CryptoCmd cryptoCmd) throws Exception {
        List<TPubGroupCust> tPubGroupCusts = new ArrayList<>();
        List<String> custNos = ((JSONArray) cryptoCmd.getParams().get("cust_nos")).toJavaList(String.class);
        if(custNos == null || custNos.size() < 0){
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
        if(custNos == null || custNos.size() < 1){
            throw new ParameterException("参数错误，商户不能为空");
        }
        if(cryptoCmd.getParams().get("group_no") == null){
            throw new ParameterException("参数错误，分组编号不能为空");
        }
        if(tPubGroupCustMapper.deleteBatch(custNos,cryptoCmd.getParams().get("group_no").toString()) < 0){
            throw new ServiceException("删除失败");
        }
        return true;
    }

    @Override
    public Respones getAllMerchants(DealersVO vo) throws Exception {
        String distributorNo = vo.getDistributor_no();
        Integer pageNo = vo.getPageNo();
        Integer pageSize = vo.getPageSize();
        if(StringUtils.isEmpty(distributorNo)){
            throw new ParameterException("参数错误，经销商编号不能为空");
        }
        Map<String,Object> param = new HashMap<>();
        param.put("distributor_no",distributorNo);
        param.put("pageNo",pageNo);
        param.put("pageSize",pageSize);
        param.put("useStatus", SysCodeConstant.MERCHANT_STATUS_NORMAL);
        NPageHelper.startPage(param);
        List<Map<String, Object>> list = manageMapper.getAllMerchants(param);
        return getRespones(list);
    }

    @Override
    public Respones searchMerchants(DealersVO vo) throws Exception {
        Map<String,Object> param = new HashMap<>();
        String merName = vo.getMer_name();
        String merStatus = vo.getMer_status();
        String loginId = vo.getLoginId();
        if(StringUtils.isEmpty(loginId)){
            throw new ParameterException("参数错误，loginId不能为空");
        }
        // 查询经销商编号
        TuserBaseInfo queryDemo = new TuserBaseInfo();
        queryDemo.setUserNo(loginId);
        TuserBaseInfo selectOne = tuserBaseInfoMapper.selectOne(queryDemo);
        if(selectOne == null){
            throw new ParameterException("参数错误,经销商不存在");
        }

        String distributorNo = selectOne.getUserNo();
        Date startDate = vo.getStartTime();
        Date endDate = vo.getEndTime();
        Integer pageNo = vo.getPageNo();
        Integer pageSize = vo.getPageSize();
        String saleName = vo.getSale_name();
        String merNo = vo.getMer_no();
        if(!StringUtils.isEmpty(merName)){
            param.put("mer_name",merName.trim());
        }
        if(!StringUtils.isEmpty(merStatus)){
            param.put("mer_status",merStatus.trim());
        }
        if(StringUtils.isEmpty(distributorNo)){
            throw new ServiceException("未知用户,只有经销商能查询列表");
        }
        if(!StringUtils.isEmpty(saleName)){
            param.put("sale_name",saleName);
        }
        if(!StringUtils.isEmpty(merNo)){
            param.put("mer_no",merNo);
        }
        param.put("pageNo",pageNo);
        param.put("pageSize",pageSize);
        if(startDate != null){
            param.put("start_date",startDate);
        }
        if(endDate != null){
            param.put("end_date",endDate);
        }
        param.put("distributor_no",distributorNo.trim());
        NPageHelper.startPage(param);
        List<Map<String, Object>> list = manageMapper.searchMerchantsByNo(param);
        return getRespones(list);
    }

    @Override
    public Respones getMerchantDetail(DealersVO vo) throws Exception {
        Map<String,Object> param = new HashMap<>();
        String merNo = vo.getMer_no();
        String distributorNo = vo.getDistributor_no();
        if(StringUtils.isEmpty(merNo)){
            throw new ServiceException("错误参数，商户编号不能为空");
        }
        if(StringUtils.isEmpty(distributorNo)){
            throw new ServiceException("错误参数，经销商编号不能为空");
        }
        param.put("mer_no",merNo);
        param.put("distributor_no",distributorNo);
        // 查询商户基本信息
        List<Map<String, Object>> merchantDetail = manageMapper.getMerchantDetail(param);
        if(merchantDetail == null || merchantDetail.size()<1){
            throw new ServiceException("未知商户");
        }
        // 需要统计的订单状态
        List<String> orderStatus = Arrays.asList(SysCodeConstant.TRANS_STATUS_SUCCESS,SysCodeConstant.TRANS_STATUS_RECIEVE_NO,SysCodeConstant.TRANS_STATUS_SEND_NO);
        // 查询商户消费总数
        Double totalAmount = manageMapper.selectTotalAmount(merNo,orderStatus,distributorNo);
        merchantDetail.get(0).put("totalAmount",String.format("%.2f",totalAmount==null?0:totalAmount));
        Map<String, Object> stringObjectMap = merchantDetail.get(0);
        Date date = (Date) stringObjectMap.get("updateDate");
        if(date != null){
            stringObjectMap.put("insertDate",formatDate(date));
        }
        String companyType = (String) stringObjectMap.get("companyType");
        SysCodeInfo sysCodeInfo = new SysCodeInfo();
        sysCodeInfo.setCodeInfoValue(companyType);
        SysCodeInfo selectCode = sysCodeInfoMapper.selectOne(sysCodeInfo);
        if(selectCode != null){
            stringObjectMap.put("companyType",selectCode.getCodeInfoName());
        }else{
            stringObjectMap.put("companyType","未知");
        }
        return getRespones(stringObjectMap);
    }

    @Override
    public Respones getAgreementGoodsDetail(DealersVO vo) throws Exception {
        Map<String,Object> param = new HashMap<>();
        String merNo = vo.getMer_no();
        Integer pageNo = vo.getPageNo();
        Integer pageSize = vo.getPageSize();
        if(StringUtils.isEmpty(merNo)){
            throw new ServiceException("错误参数，商户id不能为空");
        }
        param.put("pageNo",pageNo);
        param.put("pageSize",pageSize);
        if(pageNo == 0){
            param.put("pageNo",1);
        }
        if(pageSize == 0){
            param.put("pageSize",10);
        }
        param.put("merNo",merNo);
        param.put("goodsStatus", SysCodeConstant.GOODS_ON_STATUS_YES);
        NPageHelper.startPage(param);
        List<Map<String, Object>> list = manageMapper.getAgreementGoodsDetail(param);
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
        return getRespones(list);
    }

    @Override
    public Respones stopSupply(DealersVO vo) throws Exception {
//        Map<String,Object> param = new HashMap<>();
//
//        String goodsNo = vo.getGoods_no();
//        String distributorNo = vo.getDistributor_no();
//
//        if(StringUtils.isEmpty(goodsNo)|| StringUtils.isEmpty(distributorNo)){
//            throw  new ParameterException("参数错误,商户和供应商均不能为空");
//        }
//        param.put("update_date",new Date());
//        param.put("mer_no",goodsNo);
//        param.put("distributor_no",distributorNo);
//        Boolean isStop = vo.isStop();
//        if(isStop){
//            param.put("supply_status",SysCodeConstant.STATUS_NO);
//        }else {
//            param.put("supply_status",SysCodeConstant.STATUS_YES);
//        }

        // 更改经销商与商户供货信息表
//        if(manageMapper.stopSupply(param) < 1){
//            throw new ServiceException("添加消息通知失败");
//        }
        // 获得停止供货的商品的商品名
//        TGoodsBaseInfo tGoodsBaseInfo = new TGoodsBaseInfo();
//        tGoodsBaseInfo.setGoodsNo(goodsNo);
//        // 获得经销商的名字
//        TpubDistributorInfo tpubDistributorInfo = new TpubDistributorInfo();
//        tpubDistributorInfo.setDistributorNo(SecurityUtils.getSubject().getPrincipal().toString());
//        String distributorName = tpubDistributorInfoMapper.selectOne(tpubDistributorInfo).getDistributorName();
        // 组装商户消息记录
//        List<TUserNews> newsList = new ArrayList<>();
////        List<String> userNos = manageMapper.selectUserNoByDistributorNo(param);
////        String goodsName = tGoodsBaseInfoMapper.selectOne(tGoodsBaseInfo).getGoodsName();
//        for(String userNo:userNos){
//            TUserNews tUserNews = new TUserNews();
//            tUserNews.setUserNo(userNo);
//            if(isStop){
//                tUserNews.setNewsContent("经销商于"+ DateUtils.getDate() + "停止了"+"的供货");
//            }else {
//                tUserNews.setNewsContent("经销商于"+ DateUtils.getDate() + "开始了"+"的供货");
//            }
//            tUserNews.setNewsDate(new Date());
//            tUserNews.setTableKey(UUIDUtils.getUuid());
//            tUserNews.setUserType(SysCodeConstant.NEWS_TYPE_MERCHANTS);
//            tUserNews.setNewsType(SysCodeConstant.NEWS_TYPE_MERCHANTS);
//            tUserNews.setIsRead(SysCodeConstant.IS_READ_NO);
//            newsList.add(tUserNews);
//        }
//        // 插入到通知消息记录表
//        if(newsList.size() > 0) {
//            if (manageMapper.pushStopSupplyMessage(newsList) < 1) {
//                throw new ServiceException("添加消息通知失败");
//            }
//        }
        String merNo = vo.getMer_no();
        String loginId = vo.getLoginId();
        if(StringUtils.isEmpty(merNo) || StringUtils.isEmpty(loginId)){
            throw new ParameterException("参数错误,商户编号和经销商loginId均不能为空");
        }
        TuserBaseInfo tuserBaseInfo = new TuserBaseInfo();
        tuserBaseInfo.setUserNo(loginId);
        TuserBaseInfo selectOne = tuserBaseInfoMapper.selectOne(tuserBaseInfo);
        if(selectOne == null){
            throw new ParameterException("参数错误，用户不存在");
        }

//        String relationNo = selectOne.getRelationNo();
        String relationNo = selectOne.getUserNo();

        TUserNews tUserNews = new TUserNews();

        tUserNews.setNewsContent("经销商"+relationNo+"于"+DateUtils.getDate()+"停止对商户"+merNo+"的供货");
        tUserNews.setNewsDate(new Date());
        tUserNews.setTableKey(UUIDUtils.getUuid());
        tUserNews.setUserType(SysCodeConstant.NEWS_TYPE_MERCHANTS);
        tUserNews.setNewsType(SysCodeConstant.NEWS_TYPE_MERCHANTS);
        tUserNews.setIsRead(SysCodeConstant.IS_READ_NO);
        tUserNews.setUserNo(merNo);
        if(tUserNewsMapper.insert(tUserNews) < 1){
            throw new ServiceException("更改失败");
        }
        return getRespones(null);
    }

    @Override
    public Respones modifyGroup(DealersVO vo) throws Exception {
        String loginId = vo.getLoginId();
        if(StringUtils.isEmpty(loginId)){
            throw new ParameterException("参数错误，loginId不能为空");
        }

        String groupNo = vo.getGroup_no();
        String groupName = vo.getGroup_name();

        TPubGroup tPubGroup = new TPubGroup();
        tPubGroup.setCreateType(ConstantsUtil.DEALERS_IDENTITY);

        // 获得经销商编号
        TuserBaseInfo queryOne = new TuserBaseInfo();
        queryOne.setUserNo(loginId);
        TuserBaseInfo baseInfo = tuserBaseInfoMapper.selectOne(queryOne);
        if(baseInfo == null){
            throw new ParameterException("参数错误，用户不存在");
        }
        String relationNo = baseInfo.getUserNo();

        if(StringUtils.isEmpty(groupNo)){
            // 新增
            // 查询组名是否存在
            TPubGroup queryName = new TPubGroup();
            queryName.setCreateNo(relationNo);
            queryName.setGroupName(groupName.trim());
            queryName.setGroupStatus(SysCodeConstant.STATUS_YES);
            TPubGroup tPubGroup1 = tPubGroupMapper.selectOne(queryName);
            if(tPubGroup1!=null){
                Respones respones = new Respones();
                respones.setResult(false);
                respones.setMessage("新增失败，分组已存在");
                respones.setCode("500");
                return respones;
            }

            tPubGroup.setGroupType(SysCodeConstant.CUST_TYPE_MERCHANT);
            tPubGroup.setGroupNo(UUIDUtils.getUuid());
            tPubGroup.setCreateDate(new Date());
            tPubGroup.setGroupStatus(ConstantsUtil.STATUS_NORMAL);
            tPubGroup.setGroupName(groupName.trim());

            if(StringUtils.isEmpty(loginId)){
                throw new ParameterException("参数错误，loginId不能为空");
            }
            TuserBaseInfo tuserBaseInfo = new TuserBaseInfo();
            tuserBaseInfo.setUserNo(loginId.trim());
            TuserBaseInfo selectOne = tuserBaseInfoMapper.selectOne(tuserBaseInfo);
            if(selectOne == null){
                throw new ParameterException("参数错误,loginId错误");
            }
            tPubGroup.setCreateNo(selectOne.getUserNo());
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
            if(tPubGroupMapper.updateByPrimaryKey(tPubGroup) < 0){
                throw  new ServiceException("修改用户组失败");
            }
        }
        return getRespones(null);
    }

    @Override
    public Respones deleteGroup(DealersVO vo) throws Exception {
        String groupNo = vo.getGroup_no();

        if(StringUtils.isEmpty(groupNo)){
            throw new ParameterException("参数错误,分组编号不能为空");
        }
        TPubGroup tPubGroup = new TPubGroup();
        tPubGroup.setGroupNo(groupNo);
        tPubGroup.setGroupStatus(ConstantsUtil.STATUS_NO);
        // 删除从表

        TPubGroupCust tPubGroupCust = new TPubGroupCust();
        tPubGroupCust.setGroupNo(groupNo);
        if(tPubGroupCustMapper.delete(tPubGroupCust) < 0){
            throw new ServiceException("删除失败");
        }

        // 更新主表
        if(tPubGroupMapper.updateByPrimaryKeySelective(tPubGroup) < 1){
            throw new ServiceException("删除失败");
        }
        return getRespones(null);
    }

    @Override
    public Respones pushGroup(DealersVO vo) throws Exception {
        List<TPubGroupCust> tPubGroupCusts = new ArrayList<>();
        List<String> custNos = vo.getCust_nos();
        String groupNo = vo.getGroup_no();
        if(custNos == null || custNos.size() < 1){
            throw new ParameterException("参数错误，商户不能为空");
        }
        if(StringUtils.isEmpty(groupNo)){
            throw new ParameterException("参数错误，分组编号不能为空");
        }
        TPubGroup tPubGroup = new TPubGroup();
        tPubGroup.setGroupNo(groupNo);
        TPubGroup selectOne = tPubGroupMapper.selectOne(tPubGroup);
        if(selectOne == null){
            throw new ParameterException("分组不存在");
        }
        String groupName = selectOne.getGroupName();
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
        return getRespones(null);
    }

    @Override
    public Respones popGroup(DealersVO vo) throws Exception {
        List<String> custNos = vo.getCust_nos();
        String groupNo = vo.getGroup_no();

        if(custNos == null || custNos.size() < 1){
            throw new ParameterException("参数错误，商户不能为空");
        }
        if(StringUtils.isEmpty(groupNo)){
            throw new ParameterException("参数错误，分组编号不能为空");
        }
        if(tPubGroupCustMapper.deleteBatch(custNos,groupNo) < 0){
            throw new ServiceException("删除失败");
        }
        return getRespones(null);
    }

    @Override
    public Respones getAllMerchantsInGroup(String loginId) throws Exception {
        if(StringUtils.isEmpty(loginId)){
            throw new ParameterException("参数错误，用户id不能为空");
        }
        List<Map<String, Object>> result = new ArrayList<>();
        List<String> groupNos = new ArrayList<>();
        Map<String,Object> params = new HashMap<>();
        params.put("loginId",loginId);
        params.put("userStatus",SysCodeConstant.MERCHANT_STATUS_NORMAL);
        params.put("groupType",SysCodeConstant.CUST_TYPE_MERCHANT);
        params.put("groupStatus",SysCodeConstant.STATUS_YES);
        // 获得已分组商户信息
        List<Map<String,Object>> groupsInfo =  manageMapper.getGroupsByLoginId(params);
        for(Map<String,Object> item: groupsInfo){
            List<Map<String,Object>> list = tPubGroupCustMapper.getGroupedMerchants(item.get("groupNo").toString());
            Map<String,Object> map = new HashMap<>();
            map.put("groupNo",item.get("groupNo"));
            map.put("groupName",item.get("groupName"));
            map.put("groupSize",list.size());
            for(Map<String,Object> listItem :list){
                Date date = (Date) listItem.get("updateDate");
                if(date != null){
                    listItem.put("updateDate",formatDate(date));
                }
            }
            map.put("list",list);
            result.add(map);
        }
        // 获得分组
        groupsInfo.forEach(node->{
            groupNos.add(node.get("groupNo").toString());
        });

        if(groupNos.size() < 1){
            params.put("groupNos",null);
        }else {
            params.put("groupNos",groupNos);
        }

        // 获得所有未分组的人员信息
        List<Map<String,Object>> noGroupedManchats = manageMapper.getNoGroupedManchats(params);
        Map<String,Object> map = new HashMap<>();
        map.put("groupNo",0);
        map.put("groupName","未分组");
        map.put("groupSize",0);
        if(noGroupedManchats != null && noGroupedManchats.size() > 0 && noGroupedManchats.get(0) != null){
            for(Map<String,Object> listItem :noGroupedManchats){
                Date date = (Date) listItem.get("updateDate");
                if(date != null) {
                    listItem.put("updateDate", formatDate(date));
                }
                map.put("groupSize",noGroupedManchats.size());
                map.put("list",noGroupedManchats);
            }
        }else {
            map.put("list",new ArrayList<>());
        }

        result.add(map);
        Respones respones = new Respones();
        return addPageInfo(result,respones,false);
    }

    @Override
    public Respones getNoGroupedManchants(String loginId) throws Exception {
        if(StringUtils.isEmpty(loginId)){
            throw new ParameterException("参数错误，loginId不能为空");
        }
        Map<String,Object> param = new HashMap<>();
        param.put("loginId",loginId);

        return null;
    }

    @Override
    public Respones getAllGroupName(String loginId) throws Exception {
        TuserBaseInfo tuserBaseInfo = new TuserBaseInfo();
        tuserBaseInfo.setUserNo(loginId);
        TuserBaseInfo baseInfo = tuserBaseInfoMapper.selectOne(tuserBaseInfo);
        if(baseInfo == null){
            throw  new ParameterException("参数错误，用户不存在");
        }
        TPubGroup tPubGroup = new TPubGroup();
        tPubGroup.setCreateNo(baseInfo.getUserNo());
        tPubGroup.setGroupStatus(SysCodeConstant.STATUS_YES);
        List<TPubGroup> select = tPubGroupMapper.select(tPubGroup);
        return getRespones(select);
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public Respones moveMerchant(String mer_no, String group_no) throws Exception {
        if(StringUtils.isEmpty(mer_no) || StringUtils.isEmpty(group_no)){
            throw new ParameterException("参数错误，用户编号和分组编号均不能为空");
        }

        DealersVO vo = new DealersVO();
        vo.setCust_nos(Arrays.asList(mer_no));
        vo.setGroup_no(group_no);
        try {
            TPubGroupCust tPubGroupCust = new TPubGroupCust();
            tPubGroupCust.setCustNo(mer_no);
            tPubGroupCustMapper.delete(tPubGroupCust);
            pushGroup(vo);
        }catch (Exception e){
            throw new ServiceException("移动商户失败");
        }
        return getRespones(null);
    }

    @Override
    public void getMerchantStatus(Respones respones) throws Exception {
        SysCodeInfo sysCodeInfo = new SysCodeInfo();
        sysCodeInfo.setCodeTypeId(SysCodeConstant.DEALER_STATUS);
        List<SysCodeInfo> selectAll = sysCodeInfoMapper.select(sysCodeInfo);
        List<Map<String,Object>> result = new ArrayList<>();
        selectAll.forEach(node->{
            Map<String,Object> map = new HashMap<>();
            map.put("codeName",node.getCodeInfoName());
            map.put("codeValue",node.getCodeInfoValue());
            result.add(map);
        });
        respones.setResult(true);
        respones.setReq(result);
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
    public void getCompanyType(Respones respones) throws Exception {
        SysCodeInfo sysCodeInfo = new SysCodeInfo();
        sysCodeInfo.setCodeTypeId(SysCodeConstant.COMPANY_TYPE);
        List<SysCodeInfo> select = sysCodeInfoMapper.select(sysCodeInfo);
        List<Map<String,Object>> result = new ArrayList<>();
        select.forEach(node->{
            Map<String,Object> map = new HashMap<>();
            map.put("codeName",node.getCodeInfoName());
            map.put("codeValue",node.getCodeInfoValue());
            result.add(map);
        });
        respones.setReq(result);
        respones.setResult(true);
    }


    @Override
    public List<Map<String,Object>> getAllMerchants(CryptoCmd cryptoCmd) throws Exception {
        Object distributor_no = cryptoCmd.getParams().get("distributor_no");
        if(distributor_no == null){
            throw new ParameterException("参数错误，经销商编号不能为空");
        }
        Map<String, Object> params = cryptoCmd.getParams();
        params.put("useStatus", SysCodeConstant.MERCHANT_STATUS_NORMAL);
//        Object loginId = SecurityUtils.getSubject().getPrincipal();
//        if(SecurityUtils.getSubject().hasRole(SysCodeConstant.SALE_ROLE)){
//            // 销售
//            params.put("loginId",loginId.toString());
//            NPageHelper.startPage(params);
//            return manageMapper.getAllMerchantsByDealer(params);
//        }
//        else if(SecurityUtils.getSubject().hasRole(SysCodeConstant.SALES_ADMINISTRATOR)){
//            // 销售主管
//            List<String> salesUserId = manageMapper.selectAllSales(loginId.toString());
//            params.put("userIds",salesUserId);
//            NPageHelper.startPage(params);
//            return manageMapper.getMerchantsBySaleAdmin(params);
//        }
//        else {
            NPageHelper.startPage(params);
            List<Map<String, Object>> list = manageMapper.getAllMerchants(params);
            addPageInfo(list,cryptoCmd);
            return list;
//        }
    }

    @Override
    public List<Map<String, Object>> getMerchantDetail(CryptoCmd cryptoCmd) throws ServiceException {
        Map<String, Object> params = cryptoCmd.getParams();
        if(params.get("mer_no") == null){
            throw new ServiceException("错误参数，商户id不能为空");
        }
        // 查询商户基本信息
        List<Map<String, Object>> merchantDetail = manageMapper.getMerchantDetail(params);
        if(merchantDetail == null || merchantDetail.size()<1){
            throw new ServiceException("未知商户");
        }
        // 需要统计的订单状态
        List<String> orderStatus = Arrays.asList(SysCodeConstant.TRANS_STATUS_SUCCESS,SysCodeConstant.TRANS_STATUS_RECIEVE_NO,SysCodeConstant.TRANS_STATUS_SEND_NO);
        // 查询商户消费总数
//        Double totalAmount = manageMapper.selectTotalAmount(params.get("mer_no").toString(),orderStatus);
//        merchantDetail.get(0).put("totalAmount",totalAmount==null?0:totalAmount);
        return merchantDetail;
    }

    @Override
    public List<Map<String, Object>> getAgreementGoodsDetail(CryptoCmd cryptoCmd) throws ServiceException {
        Map<String, Object> params = cryptoCmd.getParams();
        if(params.get("mer_no") == null){
            throw new ServiceException("错误参数，商户id不能为空");
        }
        params.put("goodsStatus", SysCodeConstant.GOODS_ON_STATUS_YES);
        NPageHelper.startPage(params);
        List<Map<String, Object>> list = manageMapper.getAgreementGoodsDetail(params);
        addPageInfo(list,cryptoCmd);
        return list;
    }

    @Override
    public List<Map<String, Object>> searchMerchants(CryptoCmd cryptoCmd) throws Exception {
        Map<String, Object> params = cryptoCmd.getParams();
        if(params.get("mer_name") != null){
            params.put("mer_name",params.get("mer_name").toString().trim());
        }
        if(params.get("mer_status") != null){
            params.put("mer_status",params.get("mer_status").toString().trim());
        }
        if(cryptoCmd.getParams().get("distributor_no") == null){
            throw new ServiceException("未知用户,只有经销商能查询列表");
        }
        params.put("distributor_no",cryptoCmd.getParams().get("distributor_no").toString());
        NPageHelper.startPage(params);
        List<Map<String, Object>> list = manageMapper.searchMerchantsByNo(params);
        addPageInfo(list,cryptoCmd);
        return list;
    }


    /**
     * 给返回结果添加分页信息
     */
    private static final void addPageInfo(List<Map<String,Object>> list,CryptoCmd cryptoCmd){
        PageInfo pageinfo = new PageInfo(list);
        cryptoCmd.setOut("pageNo",pageinfo.getPageNum());
        cryptoCmd.setOut("pageSize",pageinfo.getPageSize());
        cryptoCmd.setOut("totalSize",pageinfo.getTotal());
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
        respones.setCode("200");
        return respones;
    }
    private static final Respones addPageInfo(List<Map<String,Object>> list,Respones respones,boolean flag){
        Map<String,Object> out = new HashMap<>();
        out.put("list",list);
        respones.setReq(out);
        respones.setResult(true);
        respones.setCode("200");
        return respones;
    }
    private static Respones getRespones(Object obj){
        Respones respones = new Respones();
        respones.setCode("200");
        respones.setReq(obj);
        respones.setResult(true);
        return respones;
    }
    private static String formatDate(Date date){
        return date == null?"":new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
}
