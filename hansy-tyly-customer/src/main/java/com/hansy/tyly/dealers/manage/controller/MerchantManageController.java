package com.hansy.tyly.dealers.manage.controller;

import com.hansy.tyly.admin.base.controller.BaseController;
import com.hansy.tyly.admin.constant.SysCodeConstant;
import com.hansy.tyly.common.utils.Respones;
import com.hansy.tyly.core.command.CryptoCmd;
import com.hansy.tyly.dealers.manage.domain.DealersVO;
import com.hansy.tyly.dealers.manage.service.MerchantManageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**concons
 * @Auther: yfqlzlx
 * @Date: 2018/8/6 16:35
 * @Description: 商户管理
 */
@RestController
@Api(description = "商户管理")
@RequestMapping(value = "/dealers/merchant")
public class MerchantManageController extends BaseController {

    @Autowired
    private MerchantManageService merchantManageService;
    @ApiOperation(value = "查看经销商的状态码值",notes = "{}")
    @PostMapping(value = "/status")
    public Respones getMerchantStatus(Respones respones) throws Exception {
        merchantManageService.getMerchantStatus(respones);
        return respones;
    }

    @ApiOperation(value = "查看企业的状态码值",notes = "{}")
    @PostMapping(value = "/companyType")
    public Respones getCompanyType(Respones respones) throws Exception {
        merchantManageService.getCompanyType(respones);
        return respones;
    }

    @ApiOperation(value = "获得所有的销售名和销售编号",notes = "{")
    @PostMapping(value = "/sale/list")
    public CryptoCmd getAllSales(CryptoCmd cryptoCmd) throws Exception {
        merchantManageService.getAllSales(cryptoCmd);
        return cryptoCmd;
    }

    @ApiOperation(value = "查询商户列表",notes = "{\"distributor_no\":\"4779d59cd87846e29c584d982f45058d\",\"pageNo\":1,\"pageSize\":10}")
    @PostMapping(value = "/list")
    public Respones getAllMerchants(DealersVO vo) throws Exception {
        return merchantManageService.getAllMerchants(vo);
    }

    @ApiOperation(value = "根据条件查询所有商户",notes = "{\"loginId\":\"4779d59cd87846e29c584d982f45058d\",\"mer_name\":\"战三\",\"mer_no\":\"123\",\"sale_name\":\"销售1\",\"mer_status\":\"1\",\"start_date\":\"2018-07-01 \",\"end_date\":\"2018-08-01 \"}    yyyy-MM-dd")
    @PostMapping(value = "/search")
    public Respones searchMerchants(DealersVO vo) throws Exception {
        return merchantManageService.searchMerchants(vo);
    }

    @ApiOperation(value = "查看商户详情",notes = "{\"mer_no\":\"1231231\",\"distributor_no\":\"123\"}")
    @PostMapping(value = "/detail")
    public Respones getMerchantDetail(DealersVO vo) throws Exception {
        return merchantManageService.getMerchantDetail(vo);
    }

    @ApiOperation(value = "商户与经销商协议商品查看接口",notes = "{\"mer_no\":\"1231231\",\"pageNo\":1,\"pageSize\":10}")
    @PostMapping(value = "/agreementGood/detail")
    public Respones getAgreementGoodsDetail(DealersVO vo) throws Exception {
        return merchantManageService.getAgreementGoodsDetail(vo);
    }

    @ApiOperation(value = "停止供货接口",notes = "{\"mer_no\":\"100220063860610001\",\"loginId\":\"1e16ada0c32746f3991558f622deaf6c\",\"isStop\":true} ----- isStop=》true:停止，false:开始")
    @PostMapping(value = "/supply/stop")
    public Respones stopSupply(DealersVO vo) throws Exception {
        return merchantManageService.stopSupply(vo);
    }

    @ApiOperation(value = "添加和或改商户的分组",notes = "{\"group_no\":\"344c01d05c0546659beee753c1d50c24\",\"loginId\":\"jingxiaoshang\"}   如果是新增，group_no不传或者传空值")
    @PostMapping(value = "/group/modify")
    public Respones modifyGroup(DealersVO vo) throws Exception {
        return merchantManageService.modifyGroup(vo);
    }

    @ApiOperation(value = "删除商户的分组",notes = "{\"group_no\":\"123123\"}")
    @PostMapping(value = "/group/delete")
    public Respones deleteGroup(DealersVO vo) throws Exception {
        return merchantManageService.deleteGroup(vo);
    }

    @ApiOperation(value = "添加商户到某个分组",notes = "{\"cust_nos\":[\"123123\",\"2312323\"],\"group_name\":\"分组1\",\"group_no\":\"123\"}")
    @PostMapping(value = "/group/push")
    public Respones pushGroup(DealersVO vo) throws Exception {
        return merchantManageService.pushGroup(vo);
    }

    @ApiOperation(value = "将商户从分组中移除",notes = "{\"cust_nos\":[\"123123\",\"2312323\"],\"group_no\":\"123\"}")
    @PostMapping(value = "/group/pop")
    public Respones popGroup(DealersVO vo) throws Exception {
        return merchantManageService.popGroup(vo);
    }

    @ApiOperation(value = "查看所有的分组和分组下商户详情", notes = "{\"loginId\":\"4779d59cd87846e29c584d982f45058d\"}")
    @PostMapping(value = "/group/detail")
    public Respones getAllMerchantsInGroup(String loginId) throws Exception {
        return merchantManageService.getAllMerchantsInGroup(loginId);
    }

    @ApiOperation(value = "查询所有组名", notes = "{\"loginId\":\"4779d59cd87846e29c584d982f45058d\"}")
    @PostMapping(value = "/group/list")
    public Respones getAllGroupName(String loginId) throws Exception {
        return merchantManageService.getAllGroupName(loginId);
    }

    @ApiOperation(value = "移动商户到新的分组", notes = "{\"mer_no\":\"123\",\"group_no\":\"123\"}")
    @PostMapping(value = "/merchant/move")
    public Respones moveMerchant(String mer_no,String group_no) throws Exception {
        return merchantManageService.moveMerchant(mer_no,group_no);
    }


//    @ApiOperation(value = "查看经销商下所有未分组的商户", notes = "{\"loginId\":\"4779d59cd87846e29c584d982f45058d\"}")
//    @PostMapping(value = "/group/no")
//    public Respones getNoGroupedManchants(String loginId) throws Exception {
//        return merchantManageService.getNoGroupedManchants(loginId);
//    }

//    @ApiOperation(value = "商户列表接口",notes = "{\"distributor_no\":\"4779d59cd87846e29c584d982f45058d\",\"pageNo\":1,\"pageSize\":10}")
//    @PostMapping(value = "/list")
//    public CryptoCmd getAllMerchants(CryptoCmd cryptoCmd) throws Exception {
//        List<Map<String,Object>> listMap = merchantManageService.getAllMerchants(cryptoCmd);
//        cryptoCmd.setOut(listMap);
//        return cryptoCmd;
//    }

//    @ApiOperation(value = "商户查询接口",notes = "{\"distributor_no\":\"4779d59cd87846e29c584d982f45058d\",\"mer_name\":\"战三\",\"mer_status\":\"1\",\"start_date\":\"2018-07-01 00:11:31\",\"end_date\":\"2018-08-01 10:11:31\",\"pageNo\":1,\"pageSize\":10}")
//    @PostMapping(value = "/search")
//    public CryptoCmd searchMerchants(CryptoCmd cryptoCmd) throws Exception {
//        List<Map<String,Object>> listMap = merchantManageService.searchMerchants(cryptoCmd);
//        cryptoCmd.setOut(listMap);
//        return cryptoCmd;
//    }

//    @ApiOperation(value = "商户详情接口",notes = "{\"mer_no\":\"1231231\"}")
//    @PostMapping(value = "/detail")
//    public CryptoCmd getMerchantDetail(CryptoCmd cryptoCmd){
//        List<Map<String,Object>> listMap = merchantManageService.getMerchantDetail(cryptoCmd);
//        cryptoCmd.setOut(listMap);
//        return cryptoCmd;
//    }

//    @ApiOperation(value = "商户与经销商协议商品查看接口",notes = "{\"mer_no\":\"1231231\",\"pageNo\":1,\"pageSize\":10}")
//    @PostMapping(value = "/agreementGood/detail")
//    public CryptoCmd getAgreementGoodsDetail(CryptoCmd cryptoCmd){
//        List<Map<String,Object>> listMap = merchantManageService.getAgreementGoodsDetail(cryptoCmd);
//        cryptoCmd.setOut(listMap);
//        return cryptoCmd;
//    }


//    @ApiOperation(value = "停止供货接口",notes = "{\"goods_no\":\"100220063860610001\",\"distributor_no\":\"1e16ada0c32746f3991558f622deaf6c\",\"isStop\":true} ----- isStop=》true:停止，false:开始")
//    @PostMapping(value = "/supply/stop")
//    public CryptoCmd stopSupply(CryptoCmd cryptoCmd) throws Exception {
//        merchantManageService.stopSupply(cryptoCmd);
//        return cryptoCmd;
//    }

//    @ApiOperation(value = "添加修改分组接口,添加和修改商户的分组",notes = "{\"group_no\":\"344c01d05c0546659beee753c1d50c24\",\"group_name\":\"新分组\"}   如果是新增，group_no不传或者传空值")
//    @PostMapping(value = "/group/modify")
//    public CryptoCmd modifyGroup(CryptoCmd cryptoCmd) throws Exception {
//        boolean result = merchantManageService.modifyGroup(cryptoCmd);
//        return cryptoCmd;
//    }

//    @ApiOperation(value = "删除分组接口,删除商户的分组",notes = "{\"group_no\":\"123123\"}")
//    @PostMapping(value = "/group/delete")
//    public CryptoCmd deleteGroup(CryptoCmd cryptoCmd) throws Exception {
//        boolean result = merchantManageService.deleteGroup(cryptoCmd);
//        return cryptoCmd;
//    }

//    @ApiOperation(value = "商户分组接口,添加商户到某个分组",notes = "{\"cust_nos\":[\"123123\",\"2312323\"],\"group_name\":\"分组1\",\"group_no\":\"123\"}")
//    @PostMapping(value = "/group/push")
//    public CryptoCmd pushGroup(CryptoCmd cryptoCmd) throws Exception {
//        boolean result = merchantManageService.pushGroup(cryptoCmd);
//        return cryptoCmd;
//    }

//    @ApiOperation(value = "移除商户分组接口",notes = "{\"cust_nos\":[\"123123\",\"2312323\"],\"group_no\":\"123\"}")
//    @PostMapping(value = "/group/pop")
//    public CryptoCmd popGroup(CryptoCmd cryptoCmd) throws Exception {
//        boolean result = merchantManageService.popGroup(cryptoCmd);
//        return cryptoCmd;
//    }


    /**
     * 封装返回数据
     */
//    private CryptoCmd getCryptoCmd(CryptoCmd cryptoCmd,boolean result){
//        Map<String,Object> map = new HashMap<>();
//        map.put("result",result);
//        cryptoCmd.setOut(map);
//        return cryptoCmd;
//    }

}
