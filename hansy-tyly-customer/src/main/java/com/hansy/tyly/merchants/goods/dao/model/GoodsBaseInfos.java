package com.hansy.tyly.merchants.goods.dao.model;

import java.io.Serializable;
import java.util.List;

public class GoodsBaseInfos extends BaseModel implements Serializable  {
    //商品基本信息
    private TGoodsBaseInfo goodsBaseInfo;
    //商品附件
    private List<TGoodsFiles> goodsFilesList;
    //商品经销商
    private TGoodsDistributor distributor;
    private TGoodsDistributorMer distributorMer;

    public TGoodsDistributorMer getDistributorMer() {
        return distributorMer;
    }

    public void setDistributorMer(TGoodsDistributorMer distributorMer) {
        this.distributorMer = distributorMer;
    }

    public TGoodsDistributor getDistributor() {
        return distributor;
    }

    public void setDistributor(TGoodsDistributor distributor) {
        this.distributor = distributor;
    }

    public GoodsBaseInfos() {
    }

    public TGoodsBaseInfo getGoodsBaseInfo() {
        return goodsBaseInfo;
    }

    public void setGoodsBaseInfo(TGoodsBaseInfo goodsBaseInfo) {
        this.goodsBaseInfo = goodsBaseInfo;
    }

    public List<TGoodsFiles> getGoodsFilesList() {
        return goodsFilesList;
    }

    public void setGoodsFilesList(List<TGoodsFiles> goodsFilesList) {
        this.goodsFilesList = goodsFilesList;
    }
}
