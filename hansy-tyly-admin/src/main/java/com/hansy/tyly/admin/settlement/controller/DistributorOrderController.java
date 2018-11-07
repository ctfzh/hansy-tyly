package com.hansy.tyly.admin.settlement.controller;

import com.github.pagehelper.PageInfo;
import com.hansy.tyly.admin.dao.model.TBusOrder;
import com.hansy.tyly.admin.settlement.dao.model.TFinaDistributorSett;
import com.hansy.tyly.admin.settlement.service.DistributorOrderService;
import com.hansy.tyly.core.command.CryptoCmd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(description = "平台经销商结算")
@RestController
@RequestMapping("/api/dealers/settlement/")
public class DistributorOrderController {

    @Autowired
    private DistributorOrderService distributorOrderService;

    @ApiOperation(value = "经销商待结算订单查询", notes = "搜索条件（可选）  \n{distributorName: \"\", orderNo: \"\", startTime: \"\", endTime: \"\"}")
    @PostMapping("/list")
    public CryptoCmd list(CryptoCmd cryptoCmd){
        Map<String, Object> params = cryptoCmd.getParams();
        List<TBusOrder> orders = distributorOrderService.listOrder(params);
        cryptoCmd.setOut(orders);
        cryptoCmd.setSuccess(true);
        return cryptoCmd;
    }

    @ApiOperation(value = "经销商订单结算", notes = "{orderNos: [\"\", \"\"]}")
    @PostMapping("/settle")
    public CryptoCmd settle(CryptoCmd cryptoCmd){
        List<String> orderNos = cryptoCmd.getInArrayString("orderNos");

        boolean flag = distributorOrderService.settle(orderNos);

        cryptoCmd.setSuccess(flag);
        return cryptoCmd;
    }

    @ApiOperation(value = "经销商结算信息列表", notes = "搜索条件（可选）  \n{ settNo: \"\", startTime: \"\", endTime: \"\"}")
    @PostMapping("/listSettlement")
    public CryptoCmd listSettlement(CryptoCmd cryptoCmd){
        Map<String, Object> params = cryptoCmd.getParams();
        List<TFinaDistributorSett> distributorSetts = distributorOrderService.listSettlements(params);
        cryptoCmd.setSuccess(true);
        cryptoCmd.setOut(distributorSetts);
        return cryptoCmd;
    }

    @ApiOperation(value = "经销商结算订单列表", notes = "{\"settNo\": \"6134428164800\"}")
    @PostMapping("/listSettlementOrder")
    public CryptoCmd listSettlementOrder(CryptoCmd cryptoCmd){
        Map<String, Object> params = cryptoCmd.getParams();
        List<TBusOrder> orders = distributorOrderService.listSettlementOrders(params);
        cryptoCmd.setSuccess(true);
        cryptoCmd.setOut(orders);
        return cryptoCmd;
    }
}
