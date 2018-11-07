package com.hansy.tyly.dealers.manage.service;

import com.hansy.tyly.common.utils.Respones;
import com.hansy.tyly.core.command.CryptoCmd;
import com.hansy.tyly.core.excepiton.ServiceException;
import com.hansy.tyly.dealers.manage.domain.DealersVO;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 18383
 * @Date: 2018/8/6 16:42
 * @Description:
 */
public interface MerchantManageService {
    /**
     * 条件查询商户
     */
    List<Map<String, Object>> searchMerchants(CryptoCmd cryptoCmd) throws Exception;
    /**
     * 权限区分销售只显示发展商户，上级可看所有。
     */
    List<Map<String,Object>> getAllMerchants(CryptoCmd cryptoCmd) throws Exception;

    /**
     * 查看商户详情信息
     */
    List<Map<String, Object>> getMerchantDetail(CryptoCmd cryptoCmd) throws ServiceException;

    /**
     * 选择商户查看与经销商协议商品信息。
     */
    List<Map<String, Object>> getAgreementGoodsDetail(CryptoCmd cryptoCmd) throws ServiceException;

    /**
     * 通知平台做处理。推送消息和插入消息日志表
     */
    boolean stopSupply(CryptoCmd cryptoCmd) throws Exception;

    /**
     * 添加或修改商户的分组
     */
    boolean modifyGroup(CryptoCmd cryptoCmd) throws  Exception;

    /**
     * 删除商户的分组
     */
    boolean deleteGroup(CryptoCmd cryptoCmd) throws Exception;

    /**
     * 添加一个或多个商户到某个分组
     */
    boolean pushGroup(CryptoCmd cryptoCmd) throws  Exception;

    /**
     * 移除某个分组一个或多个商户
     */
    boolean popGroup(CryptoCmd cryptoCmd) throws  Exception;

    // weixin
    Respones getAllMerchants(DealersVO vo) throws  Exception;

    //weixin
    Respones searchMerchants(DealersVO vo) throws  Exception;
    //weixin
    Respones getMerchantDetail(DealersVO vo) throws  Exception;

    Respones getAgreementGoodsDetail(DealersVO vo) throws  Exception;

    Respones stopSupply(DealersVO vo) throws  Exception;

    Respones modifyGroup(DealersVO vo) throws  Exception;

    Respones deleteGroup(DealersVO vo) throws  Exception;

    Respones pushGroup(DealersVO vo) throws  Exception;

    Respones popGroup(DealersVO vo) throws  Exception;

    Respones getAllMerchantsInGroup(String loginId) throws  Exception;

    Respones getNoGroupedManchants(String loginId) throws  Exception;

    Respones getAllGroupName(String loginId) throws  Exception;

    Respones moveMerchant(String mer_no, String group_no) throws  Exception;

    void getMerchantStatus(Respones respones) throws  Exception;

    List<Map<String, Object>> getAllSales(CryptoCmd cryptoCmd) throws  Exception;

    void getCompanyType(Respones respones) throws  Exception;
}
