package com.hansy.tyly.merchants.goods.service;

import com.hansy.tyly.merchants.goods.dao.model.CartOfGoods;
import com.hansy.tyly.merchants.goods.dao.model.GoodsBaseInfos;
import com.hansy.tyly.merchants.goods.dao.model.TBusShoppCart;
import com.hansy.tyly.merchants.goods.dao.model.TGoodsType;
import com.hansy.tyly.merchants.orders.dao.model.TpubDistributorInfo;

import java.util.List;
import java.util.Map;

public interface IMerchantsGoodsService{

    List<GoodsBaseInfos> getGoodsList(Map<String, Object> params)throws Exception;

    List<GoodsBaseInfos> getHotGoods(String merchantsNo,String disNo)throws Exception;

    GoodsBaseInfos getGoodsInfo(String goodsNo,String merNo,String disNo)throws Exception;

    void addCart(TBusShoppCart shoppCart) throws Exception;

    List<CartOfGoods> getCartOfGoods(Map<String, Object> params)throws Exception;

    List<TpubDistributorInfo> getDistributorList(String merNo) throws Exception;

    void deleteCart(String cartKey)throws  Exception;

    List<TGoodsType> getAllGoodsType(Map<String, Object> params)throws Exception;

    Integer getCartOfGoodsCount(Map<String, Object> params)throws Exception;

    List<Map<String,Object>> getDisListByMer(String merNo)throws Exception;
}