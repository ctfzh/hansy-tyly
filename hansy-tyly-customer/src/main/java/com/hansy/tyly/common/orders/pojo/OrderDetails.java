package com.hansy.tyly.common.orders.pojo;

import com.hansy.tyly.common.orders.dao.model.TBusOrderDetail;
import com.hansy.tyly.common.orders.dao.model.TBusRefund;
import com.hansy.tyly.dealers.goods.dao.model.TGoodsBaseInfo;
import com.hansy.tyly.dealers.goods.dao.model.TGoodsFiles;

import java.util.List;

public class OrderDetails {
    // 订单详情
    private TBusOrderDetail detail;
    private TGoodsBaseInfo baseInfo;

    private TGoodsFiles files;

    public TBusOrderDetail getDetail() {
        return detail;
    }

    public void setDetail(TBusOrderDetail detail) {
        this.detail = detail;
    }

    public TGoodsBaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(TGoodsBaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public TGoodsFiles getFiles() {
        return files;
    }

    public void setFiles(TGoodsFiles files) {
        this.files = files;
    }
}
