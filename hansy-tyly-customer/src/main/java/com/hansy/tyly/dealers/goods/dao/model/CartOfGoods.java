package com.hansy.tyly.dealers.goods.dao.model;

import java.util.List;

public class CartOfGoods {
    private TGoodsBaseInfo goodsBaseInfo;
    private TBusShoppCart shoppCart;
    private List<TGoodsFiles> goodsFilesList;

    public CartOfGoods() {
    }

    public TBusShoppCart getShoppCart() {
        return shoppCart;
    }

    public void setShoppCart(TBusShoppCart shoppCart) {
        this.shoppCart = shoppCart;
    }

    public List<TGoodsFiles> getGoodsFilesList() {
        return goodsFilesList;
    }

    public void setGoodsFilesList(List<TGoodsFiles> goodsFilesList) {
        this.goodsFilesList = goodsFilesList;
    }

    public TGoodsBaseInfo getGoodsBaseInfo() {
        return goodsBaseInfo;
    }

    public void setGoodsBaseInfo(TGoodsBaseInfo goodsBaseInfo) {
        this.goodsBaseInfo = goodsBaseInfo;
    }
}
