package com.hansy.tyly.admin.goods.service;


import com.hansy.tyly.admin.dao.model.GoodsBaseInfos;
import com.hansy.tyly.admin.dao.model.TGoodsBaseInfo;
import com.hansy.tyly.admin.dao.model.TGoodsType;

import java.util.List;
import java.util.Map;

public interface IAdminGoodsService{

    void addGoods(GoodsBaseInfos goodsBaseInfos)throws Exception;

    void editGoods(GoodsBaseInfos goodsBaseInfos)throws Exception;

    List<GoodsBaseInfos> getGoodsList(Map<String, Object> params)throws Exception;

    void onSaleOrOffShelvesGoods(List<TGoodsBaseInfo> baseInfo)throws  Exception;

    void deleteGoods(String goodsNo)throws Exception;

    GoodsBaseInfos getGoodsBaseInfo(String goodsNo)throws Exception;

    void addGoodsType(TGoodsType goodsType)throws Exception;

    void editGoodsType(TGoodsType goodsType)throws Exception;

    List<TGoodsType> getAllGoodsType(Map<String, Object> params)throws Exception;
}