package com.hansy.tyly.admin.settlement.controller;

import com.hansy.tyly.admin.settlement.service.DistributorSettlementService;
import com.hansy.tyly.core.command.CryptoCmd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 18383
 * @Date: 2018/9/3 10:16
 * @Description:
 */
@RestController
@RequestMapping(value = "/dealers/settlement")
@Api(description = "经销商结算")
public class DistributorSettlementController {

    @Autowired
    private DistributorSettlementService distributorSettlementService;

    @ApiOperation(value = "查询经销商下的所有结算订单", notes = "{\"distributor_no\":\"4779d59cd87846e29c584d982f45058d\",\"pageNo\":1,\"pageSize\":10}")
    @PostMapping(value = "/list")
    public CryptoCmd list(CryptoCmd cryptoCmd) throws Exception {
        return distributorSettlementService.getAll(cryptoCmd);
    }

    @ApiOperation(value = "根据条件查询经销商下的结算订单", notes = "{\"distributor_no\":\"4779d59cd87846e29c584d982f45058d\",\"sett_no\":\"123\",\"start_time\":\"2018-01-01\",\"end_time\":\"2018-12-31\",\"pageNo\":1,\"pageSize\":10}")
    @PostMapping(value = "/search/list")
    public CryptoCmd searchList(CryptoCmd cryptoCmd) throws Exception {
        return distributorSettlementService.searchList(cryptoCmd);
    }

    @ApiOperation(value = "查询经销商下的所有结算订单的详细信息", notes = "{\"distributor_no\":\"4779d59cd87846e29c584d982f45058d\",\"pageNo\":1,\"pageSize\":10}")
    @PostMapping(value = "/list/detail")
    public CryptoCmd detail(CryptoCmd cryptoCmd) throws Exception {
        return distributorSettlementService.detailList(cryptoCmd);
    }

    @ApiOperation(value = "根据条件查询经销商下的所有结算订单的详细信息", notes = "{\"distributor_no\":\"4779d59cd87846e29c584d982f45058d\",\"distributor_name\":\"经销商1\",\"merchant_name\":\"商户1\",\"start_time\":\"2018-01-01\",\"end_time\":\"2018-12-31\",\"pageNo\":1,\"pageSize\":10}")
    @PostMapping(value = "/search/detail")
    public CryptoCmd searchDetail(CryptoCmd cryptoCmd) throws Exception {
        return distributorSettlementService.searchDetail(cryptoCmd);
    }
}
