package com.hansy.tyly.admin.merchant.service;

import com.hansy.tyly.admin.dao.model.MerchantVO;
import com.hansy.tyly.admin.dao.model.TPubDistributorInfo;
import com.hansy.tyly.admin.dao.model.TPubMerInfo;
import com.hansy.tyly.admin.utils.Respones;
import com.hansy.tyly.core.command.CryptoCmd;
import com.hansy.tyly.core.excepiton.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 18383
 * @Date: 2018/8/8 10:10
 * @Description:
 */
public interface MerchantManageService {
    /**
     * 审核商户，通过以及不通过-平台
     */
    boolean auditMerchant(CryptoCmd cryptoCmd) throws  Exception;
    /**
     * 权限区分销售只显示发展商户，上级可看所有。
     */
    List<Map<String,Object>> getAllMerchants(CryptoCmd cryptoCmd) throws ServiceException;

    /**
     * 查看商户详情信息
     */
    CryptoCmd getMerchantDetail(CryptoCmd cryptoCmd) throws ServiceException;

    /**
     * 选择商户查看与经销商协议商品信息。
     */
    List<Map<String, Object>> getAgreementGoodsDetail(CryptoCmd cryptoCmd) throws ServiceException;

    /**
     * 可单个也可批量变更商户绑定是经销商
     */
    boolean merchantDealerBind(CryptoCmd cryptoCmd) throws Exception;

    /**
     * 修改商户状态，不可登陆系统
     */
    boolean freezeMerchants(CryptoCmd cryptoCmd) throws Exception;

    /**
     * 条件查询商户
     */
    List<Map<String, Object>> searchMerchants(CryptoCmd cryptoCmd) throws Exception;

    /**
     * 添加或修改商户的分组-经销商
     */
    boolean modifyGroup(CryptoCmd cryptoCmd) throws  Exception;

    /**
     * 删除商户的分组-经销商
     */
    boolean deleteGroup(CryptoCmd cryptoCmd) throws Exception;

    /**
     * 添加一个或多个商户到某个分组-经销商
     */
    boolean pushGroup(CryptoCmd cryptoCmd) throws  Exception;

    /**
     * 移除某个分组一个或多个商户-经销商
     */
    boolean popGroup(CryptoCmd cryptoCmd) throws  Exception;
    /**
     * 修改商户信息
     */
    boolean updateMerchant(CryptoCmd cryptoCmd) throws  Exception;

    /**
     * 用户审核-销售
     */
    boolean auditMerchant(MerchantVO vo) throws  Exception;

    /**
     * 修改改或添加分组-销售
     */
    boolean modifyGroup(MerchantVO vo) throws  Exception;
    /**
     * 删除商户的分组-销售
     */
    boolean deleteGroup(MerchantVO vo) throws  Exception;
    /**
     * 添加一个或多个商户到某个分组-销售
     */
    boolean pushGroup(MerchantVO vo) throws  Exception;
    /**
     * 移除某个分组一个或多个商户-销售
     */
    boolean popGroup(MerchantVO vo) throws  Exception;

    /**
     * 获得销售的所有分组和分组下所有的商户信息
     */
    Respones getAllMerchantsInGroup(MerchantVO vo) throws Exception;

    Respones getAllMerchants(MerchantVO vo);

    Map<String,Object> getMerchantDetail(MerchantVO vo);

    Respones searchMerchants(MerchantVO vo) throws Exception;

    Respones getAgreementGoodsDetail(MerchantVO vo) throws Exception;

    /**
     * 获得所有销售
     */
    List<Map<String,Object>> getAllSales(CryptoCmd cryptoCmd) throws Exception;

    List<TPubDistributorInfo> getDistributorList(String merNo) throws Exception;

    void getMerchantsByDealer(CryptoCmd cryptoCmd) throws Exception;

    void getMerchantStatus(CryptoCmd cryptoCmd) throws Exception;

    void getDealersByMerchant(CryptoCmd cryptoCmd) throws Exception;

    void merchantAndDealerUnbind(CryptoCmd cryptoCmd) throws Exception;

    void merchantAndSaleUnbind(CryptoCmd cryptoCmd) throws Exception;

    void modifyMerchantsRelation(CryptoCmd cryptoCmd) throws Exception;

    Respones getDealerBySaleAndMer(MerchantVO vo) throws Exception;
}
