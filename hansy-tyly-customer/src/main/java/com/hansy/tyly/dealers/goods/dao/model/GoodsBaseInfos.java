package com.hansy.tyly.dealers.goods.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

//添加经销商商品  接收数据类
public class GoodsBaseInfos implements Serializable {
    //商品基本信息
    private TGoodsBaseInfo tGoodsBaseInfo;
    //商品附件
    private List<TGoodsFiles> goodsFilesList;
    //经销商商品信息
    private TGoodsDistributor goodsDistributor;




    public GoodsBaseInfos() {
    }

    public TGoodsBaseInfo gettGoodsBaseInfo() {
        return tGoodsBaseInfo;
    }

    public void settGoodsBaseInfo(TGoodsBaseInfo tGoodsBaseInfo) {
        this.tGoodsBaseInfo = tGoodsBaseInfo;
    }

    public List<TGoodsFiles> getGoodsFilesList() {
        return goodsFilesList;
    }

    public void setGoodsFilesList(List<TGoodsFiles> goodsFilesList) {
        this.goodsFilesList = goodsFilesList;
    }

    public TGoodsDistributor getGoodsDistributor() {
        return goodsDistributor;
    }

    public void setGoodsDistributor(TGoodsDistributor goodsDistributor) {
        this.goodsDistributor = goodsDistributor;
    }

    @Override
    public String toString() {
        return "GoodsBaseInfos{" +
                "tGoodsBaseInfo=" + tGoodsBaseInfo +
                ", goodsFilesList=" + goodsFilesList +
                ", goodsDistributor=" + goodsDistributor +
                '}';
    }
}
