package com.hansy.tyly.common.orders.dao.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_bus_order_detail")
public class TBusOrderDetail {
    /**
     * 主键
     */
    @Id
    @Column(name = "table_key")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tableKey;

    /**
     * 订单编号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 商品编号
     */
    @Column(name = "goods_no")
    private String goodsNo;

    /**
     * 购买数量
     */
    @Column(name = "goods_num")
    private Integer goodsNum;

    /**
     * 购买时间
     */
    @Column(name = "trans_date")
    private Date transDate;

    /**
     * 商品单价金额
     */
    @Column(name = "goods_amt")
    private BigDecimal goodsAmt;

    /**
     * 获取主键
     *
     * @return table_key - 主键
     */
    public Integer getTableKey() {
        return tableKey;
    }

    /**
     * 设置主键
     *
     * @param tableKey 主键
     */
    public void setTableKey(Integer tableKey) {
        this.tableKey = tableKey;
    }

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
     * 获取商品编号
     *
     * @return goods_no - 商品编号
     */
    public String getGoodsNo() {
        return goodsNo;
    }

    /**
     * 设置商品编号
     *
     * @param goodsNo 商品编号
     */
    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    /**
     * 获取购买数量
     *
     * @return goods_num - 购买数量
     */
    public Integer getGoodsNum() {
        return goodsNum;
    }

    /**
     * 设置购买数量
     *
     * @param goodsNum 购买数量
     */
    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    /**
     * 获取购买时间
     *
     * @return trans_date - 购买时间
     */
    public Date getTransDate() {
        return transDate;
    }

    /**
     * 设置购买时间
     *
     * @param transDate 购买时间
     */
    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    /**
     * 获取商品单价金额
     *
     * @return goods_amt - 商品单价金额
     */
    public BigDecimal getGoodsAmt() {
        return goodsAmt;
    }

    /**
     * 设置商品单价金额
     *
     * @param goodsAmt 商品单价金额
     */
    public void setGoodsAmt(BigDecimal goodsAmt) {
        this.goodsAmt = goodsAmt;
    }
}