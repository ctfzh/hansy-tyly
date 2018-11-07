package com.hansy.tyly.admin.settlement.service;

import com.hansy.tyly.core.command.CryptoCmd;

/**
 * @Auther: 18383
 * @Date: 2018/9/3 10:17
 * @Description:
 */
public interface DistributorSettlementService {
    /**
     * 查询经销商下所有的结算信息
     */
    CryptoCmd getAll(CryptoCmd cryptoCmd) throws Exception;

    /**
     * 根据条件查询经销商的所有结算信息
     */
    CryptoCmd searchList(CryptoCmd cryptoCmd) throws Exception;

    /**
     * 查询经销商下的所有结算的详细信息
     */
    CryptoCmd detailList(CryptoCmd cryptoCmd) throws Exception;

    /**
     * 根据条件查询经销商下结算的详细信息
     */
    CryptoCmd searchDetail(CryptoCmd cryptoCmd) throws Exception;
}
