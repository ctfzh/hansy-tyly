package com.hansy.tyly.dealers.settlement.service;

import com.hansy.tyly.common.utils.Respones;
import com.hansy.tyly.core.command.CryptoCmd;
import com.hansy.tyly.dealers.manage.domain.DealersVO;

/**
 * @Auther: 18383
 * @Date: 2018/8/16 18:22
 * @Description:
 */
public interface SettlementService {
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

    Respones getAll(DealersVO vo) throws Exception;

    Respones searchList(DealersVO vo) throws Exception;

    Respones detailList(DealersVO vo) throws Exception;

    Respones searchDetail(DealersVO vo) throws Exception;
}
