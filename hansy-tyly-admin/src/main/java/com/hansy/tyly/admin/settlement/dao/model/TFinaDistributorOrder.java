package com.hansy.tyly.admin.settlement.dao.model;

import javax.persistence.*;

@Table(name = "t_fina_distributor_order")
public class TFinaDistributorOrder {
    /**
     * 订单编号
     */
    @Id
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 结算编号
     */
    @Column(name = "sett_no")
    private String settNo;

    /**
     * 获取订单编号
     *
     * @return order_no - 订单编号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单编号
     *
     * @param orderNo 订单编号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取结算编号
     *
     * @return sett_no - 结算编号
     */
    public String getSettNo() {
        return settNo;
    }

    /**
     * 设置结算编号
     *
     * @param settNo 结算编号
     */
    public void setSettNo(String settNo) {
        this.settNo = settNo;
    }
}