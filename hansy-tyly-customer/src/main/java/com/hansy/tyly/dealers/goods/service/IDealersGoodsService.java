package com.hansy.tyly.dealers.goods.service;

import com.github.pagehelper.Page;
import com.hansy.tyly.core.command.CryptoCmd;
import com.hansy.tyly.dealers.goods.dao.model.*;

import java.util.List;
import java.util.Map;

public interface IDealersGoodsService {

    String getCodeValueByName(String str,String code) throws Exception;
    public void addGoods(GoodsBaseInfos goodsBaseInfos) throws Exception;

    public void editGoods(GoodsBaseInfos goodsBaseInfos) throws Exception;

    public List<GoodsBaseInfos> getGoodsList(Map<String, Object> params)throws Exception;

    public void editGoodsDistributorMer(TGoodsDisMerList distributorMer) throws Exception;

    public void deleteGoods(String goodsNo,String distributorNo) throws Exception;

    public GoodsBaseInfos getGoodsBaseInfo(String goodsNo) throws Exception;

    public void addCart(TBusShoppCart shoppCart) throws Exception;

    List<CartOfGoods> getCartOfGoods(Map<String, Object> params)throws Exception;

    List<GoodsBaseInfos> getPlatformGoodsList(Map<String, Object> params)throws Exception;

    GoodsBaseInfos getPlatformGoodsInfo(String goodsNo)throws Exception;

    void deleteCart(String cartKey) throws Exception;

    void editGoodsPrice(GoodsPriceDisToMers goodsPriceDisToMers)throws Exception;

    List<TGoodsType> getAllGoodsType(Map<String, Object> params)throws Exception;

    List<GoodsBaseInfos> getGoodsStocks(String disNo)throws Exception;

    List<Map> getGoodsDM(String disNo,String goodsNo) throws Exception;

    void editGoodsDM(GoodsDistirbutors distirbutorList)throws Exception;

    List<Map> getNotGoodsDM(String disNo, String goodsNo) throws Exception;


    Map<String,Object> getReport(String disNo, Integer type,String dateType, String date)throws Exception;
}
