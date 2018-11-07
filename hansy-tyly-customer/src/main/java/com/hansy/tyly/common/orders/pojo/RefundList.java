package com.hansy.tyly.common.orders.pojo;

import com.hansy.tyly.common.orders.dao.model.TBusRefund;

import java.util.List;

public class RefundList {
    private List<TBusRefund> refundList;
    private String formId;
    private String page;
    private String returnAll;

    public String getReturnAll() {
        return returnAll;
    }

    public void setReturnAll(String returnAll) {
        this.returnAll = returnAll;
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


    public List<TBusRefund> getRefundList() {
        return refundList;
    }

    public void setRefundList(List<TBusRefund> refundList) {
        this.refundList = refundList;
    }
}
