package com.hansy.tyly.dealers.settlement.controller;

import com.hansy.tyly.common.utils.Respones;
import com.hansy.tyly.core.command.CryptoCmd;
import com.hansy.tyly.dealers.manage.domain.DealersVO;
import com.hansy.tyly.dealers.settlement.service.SettlementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: yfqlzlx
 * @Date: 2018/8/16 18:10
 * @Description:
 */
@RestController
@RequestMapping(value = "/dealers/settlement")
@Api(description = "经销商结算")
public class SettlementController {

    @Autowired
    private SettlementService settlementService;

    @ApiOperation(value = "查询经销商下的所有结算订单", notes = "{\"distributor_no\":\"4779d59cd87846e29c584d982f45058d\",\"pageNo\":1,\"pageSize\":10}")
    @PostMapping(value = "/list")
    @Deprecated
    public Respones list(DealersVO vo) throws Exception {
        return settlementService.getAll(vo);
    }

    @ApiOperation(value = "根据条件查询经销商下的结算订单", notes = "{\"distributor_no\":\"4779d59cd87846e29c584d982f45058d\",\"sett_no\":\"123\",\"startTime\":\"2018-01-01 0:0:0\",\"endTime\":\"2018-12-31 0:0:0\",\"pageNo\":1,\"pageSize\":10}")
    @PostMapping(value = "/search/list")
    public Respones searchList(DealersVO vo) throws Exception {
        return settlementService.searchList(vo);
    }

    @ApiOperation(value = "查询经销商下某个结算订单的详细信息", notes = "{\"distributor_no\":\"4779d59cd87846e29c584d982f45058d\",\"sett_no\":\"123123\"}")
    @PostMapping(value = "/list/detail")
    @Deprecated
    public Respones detail(DealersVO vo) throws Exception {
        return settlementService.detailList(vo);
    }

    @ApiOperation(value = "根据条件查询经销商下的所有结算订单的详细信息", notes = "{\"distributor_no\":\"4779d59cd87846e29c584d982f45058d\",\"sett_no\":\"123\",\"distributor_name\":\"经销商1\",\"merchant_name\":\"商户1\",\"order_status\":\"10024006\",\"startTime\":\"2018-01-01 0:0:0\",\"endTime\":\"2018-12-31 0:0:0\",\"pageNo\":1,\"pageSize\":10}")
    @PostMapping(value = "/search/detail")
    public Respones searchDetail(DealersVO vo) throws Exception {
        return settlementService.searchDetail(vo);
    }
}
