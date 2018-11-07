package com.hansy.tyly.admin.orders.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.reflect.TypeToken;
import com.hansy.tyly.admin.dao.model.OrderInfos;
import com.hansy.tyly.admin.dao.model.TBusOrder;
import com.hansy.tyly.admin.orders.service.IAdminOrderService;
import com.hansy.tyly.admin.utils.Context;
import com.hansy.tyly.admin.utils.MapUtils;
import com.hansy.tyly.core.command.CryptoCmd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "平台订单管理")
@RestController
@RequestMapping("/api/orders")
public class AdminOrderController {
    @Autowired
    private IAdminOrderService orderService;
    private  final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());


    @ApiOperation(value = "获取订单列表", notes = "获取订单列表")
    @ResponseBody
    @RequestMapping(value = "/getOrdersList", method = RequestMethod.POST)
    public CryptoCmd getOrdersList(
            CryptoCmd cryptoCmd

    ) {
        Map<String, Object> inMap = new HashMap<>();
        try{
            inMap=cryptoCmd.getParams();
            List<OrderInfos> list= orderService.getOrdersList(inMap);
            cryptoCmd.setSuccess(true);

            cryptoCmd.setOut(list);


            if(null!= list && list.size()>0){

                Map map=new HashMap();



                map.put("list",orderService.doOrderList(list));
                //map.put("page",map.get("page"));
                cryptoCmd.setOut(map);
            }

        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            cryptoCmd.setMessage(Context.GET_LIST_FALSE);
            cryptoCmd.setSuccess(false);
        }
        return cryptoCmd;
    }

    @ApiOperation(value = "获取订单详情", notes = "获取订单列表")
    @ResponseBody
    @RequestMapping(value = "/getOrderDetailInfo", method = RequestMethod.POST)
    public CryptoCmd getOrderDetailInfo(
/*
            @RequestParam(required = false) String orderNo*/
            CryptoCmd cmd
    ) {
        try{
            String orderNo=cmd.getInString("orderNo");
            Map<String,Object> list= orderService.getOrderDetailInfo(orderNo);
            cmd.setSuccess(true);
            cmd.setOut(list);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            cmd.setMessage(Context.GET_LIST_FALSE);
            cmd.setSuccess(false);
        }
        return cmd;
    }


    @ApiOperation(value = "01-[订单管理]商户确认订单")
    @RequestMapping(value = "/editOrder", method = RequestMethod.POST)
    public CryptoCmd editOrder( CryptoCmd cmd) {
        // 调用服务生成订单
        CryptoCmd respones = new CryptoCmd();
        try {
            TBusOrder   orderInfo=cmd.getInObject("order",TBusOrder.class);
            orderService.editOrder(orderInfo);
            respones.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            respones.setSuccess(false);
        }
        return respones;
    }



    @ApiOperation(value = "平台处理订单", notes = "平台处理订单")
    @ResponseBody
    @RequestMapping(value = "/processOrders", method = RequestMethod.POST)
    public CryptoCmd processOrders(
            /*@RequestParam(required = false) String orderNo,
            @RequestParam(required = false) String changeStatus*/
            CryptoCmd cmd
    ) {

        try{
            String orderNo=cmd.getInString("orderNo");
            String changeStatus=cmd.getInString("changeStatus");
            orderService.processOrders(orderNo,changeStatus);
            cmd.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            cmd.setMessage(e.getMessage());
            cmd.setSuccess(false);
        }
        return cmd;
    }

    @ApiOperation(value = "平台获取退款申请", notes = "平台审批退款")
    @ResponseBody
    @RequestMapping(value = "/getRefundOrders", method = RequestMethod.POST)
    public CryptoCmd getRefundOrders(
           /* @RequestParam(required = false,defaultValue = "10") Integer pageSize,
            @RequestParam(required = false,defaultValue = "1") Integer pageNo,
            @RequestParam(required = false) String orderBy*/
            CryptoCmd cmd
    ) {


        try{
            Map<String,Object> params=cmd.getParams();
            List<OrderInfos> list=orderService.getRefundOrders(params);
            cmd.setOut(list);
            cmd.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            cmd.setMessage(Context.GET_LIST_FALSE);
            cmd.setSuccess(false);
        }
        return cmd;
    }

    @ApiOperation(value = "平台查看退款申请", notes = "平台查看退款申请")
    @ResponseBody
    @RequestMapping(value = "/getRefundOrderInfo", method = RequestMethod.POST)
    public CryptoCmd getRefundOrderInfo(
            /*@RequestParam Integer tableKey*/
            CryptoCmd cmd
    ) {

        try{
            Integer tableKey=cmd.getInInteger("tableKey");
            List<OrderInfos> refundOrder=orderService.getRefundOrderInfo(tableKey);
            cmd.setOut(refundOrder);
            cmd.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            cmd.setMessage(Context.GET_LIST_FALSE);
            cmd.setSuccess(false);
        }
        return cmd;
    }

    @ApiOperation(value = "平台审批退款申请", notes = "平台审批退款申请")
    @ResponseBody
    @RequestMapping(value = "/approveRefund", method = RequestMethod.POST)
    public CryptoCmd approveRefund(
          /*  @RequestParam Integer tableKey,
            @RequestParam String apprStatus*/
            CryptoCmd cmd
    ) {
        try{
            Integer tableKey=cmd.getInInteger("tableKey");
            String apprStatus=cmd.getInString("apprStatus");
            orderService.approveRefund(tableKey,apprStatus);
            cmd.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            cmd.setMessage(e.getMessage());
            cmd.setSuccess(false);
        }
        return cmd;
    }
}
