package com.hansy.tyly.admin.merchant.controller;

import com.hansy.tyly.admin.dao.model.MerchantVO;
import com.hansy.tyly.admin.dao.model.TPubDistributorInfo;
import com.hansy.tyly.admin.dao.model.TPubMerInfo;
import com.hansy.tyly.admin.merchant.service.MerchantManageService;
import com.hansy.tyly.admin.utils.Respones;
import com.hansy.tyly.admin.utils.constant.SysCodeConstant;
import com.hansy.tyly.core.command.CryptoCmd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 18383
 * @Date: 2018/8/8 09:59
 * @Description:
 */
@Api(description = "商户管理")
@RestController
public class MerchantManageController {

    @Autowired
    private MerchantManageService merchantManageService;

    @ApiOperation(value = "查询经销商下的商户",notes = "{\"distributorNo\":\"123123\",\"pageNo\":1,\"pageSize\":10}")
    @PostMapping(value = "/api/dealer/list")
    public CryptoCmd getMerchantsByDealer (CryptoCmd cryptoCmd) throws Exception {
        merchantManageService.getMerchantsByDealer(cryptoCmd);
        return cryptoCmd;
    }

    @ApiOperation(value = "查询(可按条件)所有的用户状态为正常的商户,必须先登录平台",notes = "{\"merName\":\"商户1\",\"startDate\":\"2018-07-01 \",\"endDate\":\"2018-08-01 \",\"merNo\":\"123123\",\"saleName\":\"123123\",\"merStatus\":\"10025002\",\"pageNo\":1,\"pageSize\":10}   yyyy-MM-dd")
    @PostMapping(value = "/api/merchant/list")
    public CryptoCmd searchMerchants(CryptoCmd cryptoCmd) throws Exception {
        // mer_status: 10025002 正常
        // SALE_ROLE: sales 销售
        // SALE_ROLE: salesAdministrator 销售主管
        merchantManageService.searchMerchants(cryptoCmd);
        return cryptoCmd;
    }

    @ApiOperation(value = "获得所有的销售名和销售编号",notes = "{")
    @PostMapping(value = "/api/sale/list")
    public CryptoCmd getAllSales(CryptoCmd cryptoCmd) throws Exception {
        merchantManageService.getAllSales(cryptoCmd);
        return cryptoCmd;
    }

    @ApiOperation(value = "根据商户编号查询商户详细信息",notes = "{\"merNo\":\"3452cdb4b4604375b31ff53ec74625bc\"}")
    @PostMapping(value = "/api/merchant/detail")
    public CryptoCmd getMerchantDetail(CryptoCmd cryptoCmd){
        // TRANS_STATUS_SUCCESS: 10024005 订单交易成功
        // TRANS_STATUS_RECIEVE_NO: 10024003 待收货
        // TRANS_STATUS_SEND_NO: 10024003 待发货
       return merchantManageService.getMerchantDetail(cryptoCmd);
    }

    @ApiOperation(value = "商户信息审核接口-平台",notes = "{\"merNo\":\"123123\",\"distributorNo\":\"123123\",\"auditStatus\":true}-----merStatus表示审核结果，true为审核成功，false为审核失败")
    @PostMapping(value = "/api/merchant/audit")
    public CryptoCmd auditMerchantByPlatform(CryptoCmd cryptoCmd) throws Exception {
        // mer_status MERCHANT_STATUS_NORMAL: 10025002 商户状态正常
        // mer_status MERCHANT_STATUS_AUDIT_FAILD: 10025003 审核失败
        merchantManageService.auditMerchant(cryptoCmd);
        return cryptoCmd;
    }


    @ApiOperation(value = "商户信息修改接口",notes = "{\"merNo\":\"1231231\",\"merName\":\"商户1\",\"legalName\":\"法人代表1\",\"merRegNo\":\"12319900-9\",\"companyType\":\"10000200\",\"merAddre\":\"成都市\",\"merContact\":\"张三改\",\"merContactPhone\":\"18493214012\",\"staffNo\":\"123adse4sda098e01eusdao\"}")
    @PostMapping(value = "/api/merchant/update")
    public CryptoCmd updateMerchant(CryptoCmd cryptoCmd) throws Exception {
        merchantManageService.updateMerchant(cryptoCmd);
        return cryptoCmd;
    }

    @ApiOperation(value = "商户与经销商协议商品查看接口",notes = "{\"merNo\":\"123sdaef1231213123ssadd123sadadd\",\"pageNo\":1,\"pageSize\":10}")
    @PostMapping(value = "/api/merchant/agreementGood/detail")
    public CryptoCmd getAgreementGoodsDetail(CryptoCmd cryptoCmd){
        // GOODS_ON_STATUS_YES: 10022011 上架商品
        merchantManageService.getAgreementGoodsDetail(cryptoCmd);
        return cryptoCmd;
    }

    @ApiOperation(value = "新增商户和经销商关系，可以是商户新增经销商，也可以经销商新增商户(双向)",notes = "{\"merNos\":[\"3452cdb4b4604375b31ff53ec74625bc\",\"123sdaef1231213123ssadd123sadadd\"],\"distributorNos\":[\"523434d\"]}")
    @PostMapping(value = "/api/merchant/dealer/bind")
    public CryptoCmd modifyMerchants(CryptoCmd cryptoCmd) throws Exception {
        merchantManageService.merchantDealerBind(cryptoCmd);
        return cryptoCmd;
    }

    @ApiOperation(value = "冻结商户接口", notes = "{\"userNo\":\"1e16ada0c32746f3991558f622deaf6c\",\"isFreeze\":true}"+"-----userNo是用户编号，来自商户基本信息表的，isFreeze为true表示冻结，false为恢复正常")
    @PostMapping(value = "/api/merchant/freeze")
    public CryptoCmd freezeMerchants(CryptoCmd cryptoCmd) throws Exception {
        // 冻结： 00001002
        // 解除： 00001001
        merchantManageService.freezeMerchants(cryptoCmd);
        return cryptoCmd;
    }

    @ApiOperation(value = "查看商户的状态码值",notes = "{}")
    @PostMapping(value = "/api/merchant/status")
    public CryptoCmd getMerchantStatus(CryptoCmd cryptoCmd) throws Exception {
        merchantManageService.getMerchantStatus(cryptoCmd);
        return cryptoCmd;
    }

    @ApiOperation(value = "查看商户对应的经销商的信息",notes = "{\"merNo\";\"123123\",\"pageNo\":1,\"pageSize\":10}")
    @PostMapping(value = "/api/merchant/dealer/list")
    public CryptoCmd getDealersByMerchant(CryptoCmd cryptoCmd) throws Exception {
        merchantManageService.getDealersByMerchant(cryptoCmd);
        return cryptoCmd;
    }

    @ApiOperation(value = "解除商户与经销商的绑定（双向）",notes = "{\"merNo\":[\"123123\"],\"distributorNo\":[\"123123\"]}")
    @PostMapping(value = "/api/merchant/dealer/unbind")
    public CryptoCmd merchantAndDealerUnbind(CryptoCmd cryptoCmd) throws Exception {
        merchantManageService.merchantAndDealerUnbind(cryptoCmd);
        return cryptoCmd;
    }

    @ApiOperation(value = "解除商户与销售绑定",notes = "{\"merNo\";\"123123\"}")
    @PostMapping(value = "/api/merchant/sale/unbind")
    public CryptoCmd merchantAndSaleUnbind(CryptoCmd cryptoCmd) throws Exception {
        merchantManageService.merchantAndSaleUnbind(cryptoCmd);
        return cryptoCmd;
    }


    @ApiOperation(value = "（无条件）商户列表接口-销售",notes = "{\"loginId\":\"cheyan\",\"pageNo\":1,\"pageSize\":10}")
    @PostMapping(value = "/sales/merchant/list")
    public Respones getAllMerchantsBySales(MerchantVO vo){
        return merchantManageService.getAllMerchants(vo);
    }


    @ApiOperation(value = "查看某个商户的详细信息-销售",notes = "{\"mer_no\":\"3452cdb4b4604375b31ff53ec74625bc\",\"distributor_no\":\"123\"}")
    @PostMapping(value = "/sales/merchant/detail")
    public Respones getMerchantDetailBySale(MerchantVO vo){
        Map<String,Object> listMap = merchantManageService.getMerchantDetail(vo);
        return getRespones(true,null,listMap);
    }

    @ApiOperation(value = "（根据条件）查询所有的商户信息-销售",notes = "{\"loginId\":\"123\",\"mer_name\":\"战三\",\"start_date\":\"2018-07-01\",\"end_date\":\"2018-08-01\",\"pageNo\":1,\"pageSize\":10}    yyyy-MM-dd")
    @PostMapping(value = "/sales/merchant/search")
    public Respones searchMerchants(MerchantVO vo) throws Exception {
        return merchantManageService.searchMerchants(vo);
    }

    @ApiOperation(value = "商户信息审核接口-销售",notes = "{\"auditStatus\":true,\"mer_no\":\"1233211\",\"distributor_no\",\"123\"}-----auditStatus表示审核结果，true为审核成功")
    @PostMapping(value = "/sales/merchant/audit")
    public Respones auditMerchantBySale(MerchantVO vo) throws Exception {
        boolean result = merchantManageService.auditMerchant(vo);
        return getRespones(result,null,null);
    }

    @ApiOperation(value = "添加或者修改商户分组-销售",notes = "{\"group_no\":\"344c01d05c0546659beee753c1d50c24\",\"group_name\":\"新分组\",\"loginId\",\"cheyan\"}-----如果是新增，group_no不传或者传空值")
    @PostMapping(value = "/sales/group/modify")
    public Respones modifyGroup( MerchantVO vo) throws Exception {
        boolean result = merchantManageService.modifyGroup(vo);
        return getRespones(result,null,null);
    }

    @ApiOperation(value = "删除商户的分组-销售",notes = "{\"group_no\":\"123123\"}")
    @PostMapping(value = "/sales/group/delete")
    public Respones deleteGroup(MerchantVO vo) throws Exception {
        boolean result = merchantManageService.deleteGroup(vo);
        return getRespones(result,null,null);
    }

    @ApiOperation(value = "添加一个或多个商户到某个分组-销售",notes = "{\"cust_nos\":[\"123123\",\"2312323\"],\"group_no\":\"123\"}")
    @PostMapping(value = "/sales/group/push")
    public Respones pushGroup(MerchantVO vo) throws Exception {
        boolean result = merchantManageService.pushGroup(vo);
        return getRespones(result,null,null);
    }

    @ApiOperation(value = "将一个或多个商户移出某个商户分组-销售",notes = "{\"cust_nos\":[\"123123\",\"2312323\"],\"group_no\":\"123\"}")
    @PostMapping(value = "/sales/group/pop")
    public Respones popGroup(MerchantVO vo) throws Exception {
        boolean result = merchantManageService.popGroup(vo);
        return getRespones(result,null,null);
    }

    @ApiOperation(value = "分组详情查看，返回所有的分组和分组详情", notes = "{\"loginId\":\"cheyan\"}")
    @PostMapping(value = "/sales/group/detail")
    public Respones getAllMerchantsInGroup(MerchantVO vo) throws Exception {
        return merchantManageService.getAllMerchantsInGroup(vo);
    }

    @ApiOperation(value = "商户与经销商协议商品查看接口",notes = "{\"merNo\":\"123sdaef1231213123ssadd123sadadd\",\"pageNo\":1,\"pageSize\":10}")
    @PostMapping(value = "/sales/merchant/agreementGood/detail")
    public Respones getAgreementGoodsDetailBySales(MerchantVO vo) throws Exception {
       return  merchantManageService.getAgreementGoodsDetail(vo);
    }




    /**
     * 获得通用response返回
     */
    private static final Respones getRespones(Boolean flag,String msg,Object data){
        Respones respones = new Respones();
        respones.setResult(flag);
        if(flag){
            respones.setCode(SysCodeConstant.RESPONSE_STATUS_OK);
            respones.setReq(data);
            return respones;
        }
        respones.setCode(SysCodeConstant.RESPONSE_STATUS_ERROR);
        return respones;
    }

    @ApiOperation(value = "根据销售loginId和商户编号，查看与商户绑定，且是销售发展的经销商信息",notes = "{\"mer_no\":\"123sdaef1231213123ssadd123sadadd\",\"loginId\":\"saleId\",\"distributor_name\",\"name1\"}")
    @PostMapping(value = "/sales/dealer/list")
    public Respones getDealerBySaleAndMer(MerchantVO vo) throws Exception {
        return  merchantManageService.getDealerBySaleAndMer(vo);
    }



    @ApiOperation(value = "获取商户绑定经销商列表", notes = "获取商户绑定经销商列表")
    @ResponseBody
    @RequestMapping(value = "/getDistributorList", method = RequestMethod.POST)
    public Respones getDistributorList(@RequestParam(required = false) String merNo) {
        Respones respones=new Respones();
        Map<String,Object> params=new HashMap<>();
        try{

            List<TPubDistributorInfo> list= merchantManageService.getDistributorList(merNo);
            respones.setReq(list);
            respones.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            respones.setMessage(e.getMessage());
            respones.setResult(false);
        }
        return respones;
    }



}
