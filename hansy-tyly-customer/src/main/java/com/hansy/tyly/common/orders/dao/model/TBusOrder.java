package com.hansy.tyly.common.orders.dao.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_bus_order")
public class TBusOrder {
    /**
     * 订单编号
     */
    @Id
    @Column(name = "order_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String orderNo;

    /**
     * 购买总金额
     */
    @Column(name = "total_amt")
    private BigDecimal totalAmt;

    /**
     * 交易状态
     */
    @Column(name = "trans_status")
    private String transStatus;

    /**
     * 交易时间
     */
    @Column(name = "trans_date")
    private Date transDate;

    /**
     * 修改时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 订单类型(平台经销商)
     */
    @Column(name = "order_type")
    private String orderType;

    /**
     * 收货人
     */
    @Column(name = "contact_name")
    private String contactName;

    /**
     * 收货地址
     */
    @Column(name = "contact_addre")
    private String contactAddre;

    /**
     * 收货人电话
     */
    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "zip_code")
    private String zipCode;

    /**
     * 支付方式
     */
    @Column(name = "pay_type")
    private String payType;

    /**
     * 购买方
     */
    @Column(name = "buy_person")
    private String buyPerson;

    /**
     * 出售方
     */
    @Column(name = "sell_person")
    private String sellPerson;

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
     * 获取购买总金额
     *
     * @return total_amt - 购买总金额
     */
    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    /**
     * 设置购买总金额
     *
     * @param totalAmt 购买总金额
     */
    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    /**
     * 获取交易状态
     *
     * @return trans_status - 交易状态
     */
    public String getTransStatus() {
        return transStatus;
    }

    /**
     * 设置交易状态
     *
     * @param transStatus 交易状态
     */
    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }

    /**
     * 获取交易时间
     *
     * @return trans_date - 交易时间
     */
    public Date getTransDate() {
        return transDate;
    }

    /**
     * 设置交易时间
     *
     * @param transDate 交易时间
     */
    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    /**
     * 获取修改时间
     *
     * @return update_date - 修改时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置修改时间
     *
     * @param updateDate 修改时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取订单类型(平台经销商)
     *
     * @return order_type - 订单类型(平台经销商)
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * 设置订单类型(平台经销商)
     *
     * @param orderType 订单类型(平台经销商)
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * 获取收货人
     *
     * @return contact_name - 收货人
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * 设置收货人
     *
     * @param contactName 收货人
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * 获取收货地址
     *
     * @return contact_addre - 收货地址
     */
    public String getContactAddre() {
        return contactAddre;
    }

    /**
     * 设置收货地址
     *
     * @param contactAddre 收货地址
     */
    public void setContactAddre(String contactAddre) {
        this.contactAddre = contactAddre;
    }

    /**
     * 获取收货人电话
     *
     * @return contact_phone - 收货人电话
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * 设置收货人电话
     *
     * @param contactPhone 收货人电话
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * @return zip_code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * 获取支付方式
     *
     * @return pay_type - 支付方式
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置支付方式
     *
     * @param payType 支付方式
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * 获取购买方
     *
     * @return buy_person - 购买方
     */
    public String getBuyPerson() {
        return buyPerson;
    }

    /**
     * 设置购买方
     *
     * @param buyPerson 购买方
     */
    public void setBuyPerson(String buyPerson) {
        this.buyPerson = buyPerson;
    }

    /**
     * 获取出售方
     *
     * @return sell_person - 出售方
     */
    public String getSellPerson() {
        return sellPerson;
    }

    /**
     * 设置出售方
     *
     * @param sellPerson 出售方
     */
    public void setSellPerson(String sellPerson) {
        this.sellPerson = sellPerson;
    }
}