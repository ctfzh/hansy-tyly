package com.hansy.tyly.common.orders.dao.model;



public class RefundListMessage {
    private String merName;
    private TBusOrder order;
    private TBusRefund refund;

    public RefundListMessage() {
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName;
    }

    public TBusOrder getOrder() {
        return order;
    }

    public void setOrder(TBusOrder order) {
        this.order = order;
    }

    public TBusRefund getRefund() {
        return refund;
    }

    public void setRefund(TBusRefund refund) {
        this.refund = refund;
    }
}
