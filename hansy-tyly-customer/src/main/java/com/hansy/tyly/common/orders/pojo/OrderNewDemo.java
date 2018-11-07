package com.hansy.tyly.common.orders.pojo;


import com.hansy.tyly.common.orders.dao.model.TBusOrder;
import com.hansy.tyly.merchants.orders.dao.model.TuserBaseInfo;
import com.hansy.tyly.dealers.goods.dao.model.TGoodsBaseInfo;

import java.util.List;

public class OrderNewDemo {
    private TuserBaseInfo userBaseInfo;
    private TBusOrder order;
    private List<TGoodsBaseInfo> goods;
    private String saleOrBuy;
    private String formId;
    private String page;

    public String getSaleOrBuy() {
        return saleOrBuy;
    }

    public void setSaleOrBuy(String saleOrBuy) {
        this.saleOrBuy = saleOrBuy;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public TuserBaseInfo getUserBaseInfo() {
        return userBaseInfo;
    }

    public void setUserBaseInfo(TuserBaseInfo userBaseInfo) {
        this.userBaseInfo = userBaseInfo;
    }

    public TBusOrder getOrder() {
        return order;
    }

    public void setOrder(TBusOrder order) {
        this.order = order;
    }

    public List<TGoodsBaseInfo> getGoods() {
        return goods;
    }

    public void setGoods(List<TGoodsBaseInfo> goods) {
        this.goods = goods;
    }
}
