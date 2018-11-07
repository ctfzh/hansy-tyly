package com.hansy.tyly.merchants.goods.controller;

import com.hansy.tyly.common.enumClass.CodeEnum;
import com.hansy.tyly.common.enumClass.CodeNameEnum;
import com.hansy.tyly.common.utils.Context;
import com.hansy.tyly.common.utils.ParamsUtils;
import com.hansy.tyly.common.utils.Respones;
import com.hansy.tyly.dealers.goods.service.IDealersGoodsService;
import com.hansy.tyly.merchants.goods.dao.model.CartOfGoods;
import com.hansy.tyly.merchants.goods.dao.model.TBusShoppCart;
import com.hansy.tyly.merchants.goods.dao.model.GoodsBaseInfos;
import com.hansy.tyly.merchants.goods.dao.model.TGoodsType;
import com.hansy.tyly.merchants.goods.service.IMerchantsGoodsService;
import com.hansy.tyly.merchants.mer.service.MerchantsMangerService;
import com.hansy.tyly.merchants.orders.dao.mapper.TpubDistributorInfoMapper;
import com.hansy.tyly.merchants.orders.dao.model.TpubDistributorInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "商户商品管理")
@RestController
@RequestMapping("/merchants/goods")
public class MerchantsGoodsController {
    private  final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private IMerchantsGoodsService merchantsGoodsService;
    @Autowired
    private IDealersGoodsService dealersGoodsService;
    @Autowired
    private MerchantsMangerService mangerService;
    @Autowired
    private TpubDistributorInfoMapper distributorInfoMapper;


    @ApiOperation(value = "首页商品推荐接口", notes = "首页商品推荐接口")
    @ResponseBody
    @RequestMapping(value = "/getHotGoods", method = RequestMethod.POST)
    public Respones getHotGoods(@RequestParam(required = false) String merchantsNo,
                                @RequestParam(required = false) String disNo
                                ) {
        Respones respones=new Respones();
        try{
            List<GoodsBaseInfos> list= merchantsGoodsService.getHotGoods(merchantsNo,disNo);
            respones.setReq(list);
            respones.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            respones.setMessage(Context.GET_LIST_FALSE);
            respones.setResult(false);
        }
        return respones;
    }

    @ApiOperation(value = "获取商户绑定经销商列表", notes = "获取商户绑定经销商列表")
    @ResponseBody
    @RequestMapping(value = "/getDistributorList", method = RequestMethod.POST)
    public Respones getDistributorList(@RequestParam(required = false) String merNo) {
        Respones respones=new Respones();
        Map<String,Object> params=new HashMap<>();
        try{

            List<TpubDistributorInfo> list= merchantsGoodsService.getDistributorList(merNo);
            respones.setReq(list);
            respones.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            respones.setMessage(Context.GET_LIST_FALSE);
            respones.setResult(false);
        }
        return respones;
    }

    @ApiOperation(value = "商户商品列表", notes = "查看与其绑定的经销商商品列表")
    @ResponseBody
    @RequestMapping(value = "/getGoodsList", method = RequestMethod.POST)
    public Respones getGoodsList(@RequestParam(required = false,defaultValue = "10") Integer pageSize,
                                 @RequestParam(required = false,defaultValue = "1") Integer pageNo,
                                 @RequestParam(required = false) String orderBy,
                                 @RequestParam(required = false) String merNo,
                                 @RequestParam(required = false) String disNo,
                                 @RequestParam(required = false) String goodsName,
                                 @RequestParam(required = false) String typeNo
    ) {
        Respones respones=new Respones();
        Map<String,Object> params=new HashMap<>();
        try{
            params.put("pageSize",pageSize);
            params.put("pageNo",pageNo);
            params.put("orderBy",ParamsUtils.isNull(orderBy));
            params.put("typeNo", ParamsUtils.isNull(typeNo));
            params.put("merNo",ParamsUtils.isNull(merNo));
            params.put("disNo",ParamsUtils.isNull(disNo));
            params.put("goodsName",ParamsUtils.isNull(goodsName));

            List<GoodsBaseInfos> list= merchantsGoodsService.getGoodsList(params);
            respones.setReq(list);
            respones.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            respones.setMessage(Context.GET_LIST_FALSE);
            respones.setResult(false);
        }
        return respones;
    }

    @ApiOperation(value = "商户商品详情", notes = "查看与其绑定的经销商商品详情")
    @ResponseBody
    @RequestMapping(value = "/getGoodsInfo", method = RequestMethod.POST)
    public Respones getGoodsInfo(@RequestParam String goodsNo,
                                 @RequestParam(required = false) String merNo,
                                 @RequestParam(required = false) String disNo
                                 ) {
        Respones respones=new Respones();
        try{
            GoodsBaseInfos goodsInfo= merchantsGoodsService.getGoodsInfo(goodsNo,merNo,disNo);


            respones.setReq(goodsInfo);
            respones.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            respones.setMessage(Context.GET_INFO_FALSE);
            respones.setResult(false);
        }
        return respones;
    }


    @ApiOperation(value = "商户添加购物车", notes = "商户添加购物车")
    @ResponseBody
    @RequestMapping(value = "/addCart", method = RequestMethod.POST)
    public Respones addCart(@RequestBody TBusShoppCart shoppCart) {
        Respones respones=new Respones();
        try{
            merchantsGoodsService.addCart(shoppCart);
            respones.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            respones.setMessage("商户添加购物车失败");
            respones.setResult(false);
        }
        return respones;
    }

    @ApiOperation(value = "商户添加到购物车的商品列表", notes = "商户添加到购物车的商品列表")
    @ResponseBody
    @RequestMapping(value = "/getCartOfGoods", method = RequestMethod.POST)
    public Respones getCartOfGoods(@RequestParam(required = false,defaultValue = "10") Integer pageSize,
                                   @RequestParam(required = false,defaultValue = "1") Integer pageNo,
                                   @RequestParam(required = false) String orderBy,
                                   @RequestParam(required = false)  String disNo,
                                   @RequestParam String merNo
                                   ){
        Respones respones=new Respones();
        Map<String,Object> params=new HashMap<>();
        try{
            params.put("pageSize",pageSize);
            params.put("pageNo",pageNo);
            params.put("orderBy",ParamsUtils.isNull(orderBy));
            params.put("custNo",ParamsUtils.isNull(merNo));
            params.put("disNo",ParamsUtils.isNull(disNo));
            List<CartOfGoods> list=merchantsGoodsService.getCartOfGoods(params);
            respones.setReq(list);
            respones.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            respones.setMessage("查询商户添加到购物车的商品列表失败");
            respones.setResult(false);
        }
        return respones;
    }






    @ApiOperation(value = "商户购物车中商品个数", notes = "商户购物车中商品个数")
    @ResponseBody
    @RequestMapping(value = "/getCartOfGoodsCount", method = RequestMethod.POST)
    public Respones getCartOfGoodsCount(
                                   @RequestParam String disNo,
                                   @RequestParam String merNo
    ){
        Respones respones=new Respones();
        Map<String,Object> params=new HashMap<>();
        try{
            params.put("custNo",ParamsUtils.isNull(merNo));
            params.put("disNo",ParamsUtils.isNull(disNo));
            Integer m=merchantsGoodsService.getCartOfGoodsCount(params);
            respones.setReq(m);
            respones.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            respones.setMessage("查询商户添加到购物车的商品列表失败");
            respones.setResult(false);
        }
        return respones;
    }



    @ApiOperation(value = "商户删除购物车", notes = "商户删除购物车")
    @ResponseBody
    @RequestMapping(value = "/deleteCart", method = RequestMethod.POST)
    public Respones deleteCart(@RequestParam String cartKey) {
        Respones respones=new Respones();
        try{
            merchantsGoodsService.deleteCart(cartKey);
            respones.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            respones.setMessage(Context.DELETE_FALSE);
            respones.setResult(false);
        }
        return respones;
    }

    @ApiOperation(value = "商户获取所有商品分类", notes = "商户获取所有商品分类")
    @ResponseBody
    @RequestMapping(value = "/getAllGoodsType", method = RequestMethod.POST)
    public Respones getAllGoodsType(
            @RequestParam String disNo,
            @RequestParam String merNo
    ) {
        Respones cmd=new Respones();
        Map<String,Object> params=new HashMap<>();
        try{
            params.put("disNo",ParamsUtils.isNull(disNo)
            );
            params.put("merNo",ParamsUtils.isNull(merNo)
            );
            List<TGoodsType> list=merchantsGoodsService.getAllGoodsType(params);
            cmd.setReq(list);
            cmd.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            cmd.setMessage(Context.GET_LIST_FALSE);
            cmd.setResult(false);
        }
        return cmd;
    }

    @ApiOperation(value = "商户获取绑定经销商列表", notes = "商户获取绑定经销商列表")
    @ResponseBody
    @RequestMapping(value = "/getDisListByMer", method = RequestMethod.POST)
    public Respones getDisListByMer(@RequestParam("merNo") String merNo) {
        Respones cmd=new Respones();
        Map<String,Object> params=new HashMap<>();
        try{

            List<Map<String,Object>> list=merchantsGoodsService.getDisListByMer(merNo);
            cmd.setReq(list);
            cmd.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            cmd.setMessage(Context.GET_LIST_FALSE);
            cmd.setResult(false);
        }
        return cmd;
    }



}
