package com.hansy.tyly.common.orders.pojo;

import com.hansy.tyly.common.orders.dao.model.TBusOrder;
import com.hansy.tyly.common.orders.dao.model.TBusOrderDetail;
import com.hansy.tyly.dealers.goods.dao.model.TGoodsFiles;

import java.util.List;

public class OrderMessage {
    private TBusOrder order;
    private List<TBusOrderDetail> detail;
    private List<TGoodsFiles> files;
    private String formId;
    private String page;

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


    public List<TGoodsFiles> getFiles() {
        return files;
    }

    public void setFiles(List<TGoodsFiles> files) {
        this.files = files;
    }

    public OrderMessage() {
    }

    public TBusOrder getOrder() {
        return order;
    }

    public void setOrder(TBusOrder order) {
        this.order = order;
    }

    public List<TBusOrderDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<TBusOrderDetail> detail) {
        this.detail = detail;
    }
}
