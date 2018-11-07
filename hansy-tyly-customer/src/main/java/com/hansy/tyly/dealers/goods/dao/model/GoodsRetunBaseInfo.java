package com.hansy.tyly.dealers.goods.dao.model;

import java.math.BigDecimal;
import java.util.List;

public class GoodsRetunBaseInfo {
    //商品基本信息
    private TGoodsBaseInfo tGoodsBaseInfo;
    //商品附件
    private List<TGoodsFiles> goodsFilesList;
    //经销商商品信息
    private TGoodsDistributor goodsDistributor;
    //经销商商品信息
    private BigDecimal xyAmt;
    //经销商商品信息
    private Integer xyKey;

    public Integer getXyKey() {
        return xyKey;
    }

    public void setXyKey(Integer xyKey) {
        this.xyKey = xyKey;
    }

    public BigDecimal getXyAmt() {
        return xyAmt;
    }

    public void setXyAmt(BigDecimal xyAmt) {
        this.xyAmt = xyAmt;
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
