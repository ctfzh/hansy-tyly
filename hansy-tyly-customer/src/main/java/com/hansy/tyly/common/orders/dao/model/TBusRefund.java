package com.hansy.tyly.common.orders.dao.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_bus_refund")
public class TBusRefund {
    /**
     * 申请编号
     */
    @Id
    @Column(name = "refund_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer refundNo;

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
     * 商品个数
     */
    @Column(name = "goods_num")
    private Integer goodsNum;

    /**
     * 退款金额
     */
    @Column(name = "refund_amt")
    private BigDecimal refundAmt;

    /**
     * 退款原因
     */
    @Column(name = "refund_reason")
    private String refundReason;

    /**
     * 申请时间
     */
    @Column(name = "apply_date")
    private Date applyDate;

    /**
     * 审批状态
     */
    @Column(name = "appr_status")
    private String apprStatus;

    /**
     * 审批时间
     */
    @Column(name = "appr_date")
    private Date apprDate;

    /**
     * 获取申请编号
     *
     * @return refund_no - 申请编号
     */
    public Integer getRefundNo() {
        return refundNo;
    }

    /**
     * 设置申请编号
     *
     * @param refundNo 申请编号
     */
    public void setRefundNo(Integer refundNo) {
        this.refundNo = refundNo;
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
     * 获取商品个数
     *
     * @return goods_num - 商品个数
     */
    public Integer getGoodsNum() {
        return goodsNum;
    }

    /**
     * 设置商品个数
     *
     * @param goodsNum 商品个数
     */
    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    /**
     * 获取退款金额
     *
     * @return refund_amt - 退款金额
     */
    public BigDecimal getRefundAmt() {
        return refundAmt;
    }

    /**
     * 设置退款金额
     *
     * @param refundAmt 退款金额
     */
    public void setRefundAmt(BigDecimal refundAmt) {
        this.refundAmt = refundAmt;
    }

    /**
     * 获取退款原因
     *
     * @return refund_reason - 退款原因
     */
    public String getRefundReason() {
        return refundReason;
    }

    /**
     * 设置退款原因
     *
     * @param refundReason 退款原因
     */
    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    /**
     * 获取申请时间
     *
     * @return apply_date - 申请时间
     */
    public Date getApplyDate() {
        return applyDate;
    }

    /**
     * 设置申请时间
     *
     * @param applyDate 申请时间
     */
    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    /**
     * 获取审批状态
     *
     * @return appr_status - 审批状态
     */
    public String getApprStatus() {
        return apprStatus;
    }

    /**
     * 设置审批状态
     *
     * @param apprStatus 审批状态
     */
    public void setApprStatus(String apprStatus) {
        this.apprStatus = apprStatus;
    }

    /**
     * 获取审批时间
     *
     * @return appr_date - 审批时间
     */
    public Date getApprDate() {
        return apprDate;
    }

    /**
     * 设置审批时间
     *
     * @param apprDate 审批时间
     */
    public void setApprDate(Date apprDate) {
        this.apprDate = apprDate;
    }
}