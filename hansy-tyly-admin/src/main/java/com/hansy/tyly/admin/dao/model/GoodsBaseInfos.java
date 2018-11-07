package com.hansy.tyly.admin.dao.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class GoodsBaseInfos implements Serializable {
    private TGoodsBaseInfo goodsBaseInfo;
    private List<TGoodsFiles> goodsFilesList;

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
