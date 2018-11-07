package com.hansy.tyly.customer.billMgr.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_busi_bill_his")
public class TBusiBillHis {
    /**
     * 主键
     */
    @Id
    @Column(name = "BILL_ID")
    private String billId;

    /**
     * 机构ID
     */
    @Column(name = "ORG_ID")
    private String orgId;

    /**
     * 客户ID
     */
    @Column(name = "CUST_ID")
    private String custId;

    /**
     * 产品ID
     */
    @Column(name = "PROD_ID")
    private String prodId;

    /**
     * 账单类型
     */
    @Column(name = "BILL_TYPE")
    private String billType;

    /**
     * 账单日期
     */
    @Column(name = "BUSI_DATE")
    private String busiDate;

    /**
     * 借贷类型
     */
    @Column(name = "DC_TYPE")
    private String dcType;

    /**
     * 账单金额
     */
    @Column(name = "BILL_AMT")
    private BigDecimal billAmt;

    /**
     * 决策结果
     */
    @Column(name = "DCS_RST")
    private String dcsRst;

    /**
     * 状态
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 是否计费
     */
    @Column(name = "ISCHARGE")
    private String ischarge;

    /**
     * 插入时间
     */
    @Column(name = "INSERT_TIME")
    private Date insertTime;

    /**
     * 插入用户ID
     */
    @Column(name = "INSERT_USER_ID")
    private String insertUserId;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /**
     * 获取主键
     *
     * @return BILL_ID - 主键
     */
    public String getBillId() {
        return billId;
    }

    /**
     * 设置主键
     *
     * @param billId 主键
     */
    public void setBillId(String billId) {
        this.billId = billId;
    }

    /**
     * 获取机构ID
     *
     * @return ORG_ID - 机构ID
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 设置机构ID
     *
     * @param orgId 机构ID
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * 获取客户ID
     *
     * @return CUST_ID - 客户ID
     */
    public String getCustId() {
        return custId;
    }

    /**
     * 设置客户ID
     *
     * @param custId 客户ID
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    /**
     * 获取产品ID
     *
     * @return PROD_ID - 产品ID
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置产品ID
     *
     * @param prodId 产品ID
     */
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    /**
     * 获取账单类型
     *
     * @return BILL_TYPE - 账单类型
     */
    public String getBillType() {
        return billType;
    }

    /**
     * 设置账单类型
     *
     * @param billType 账单类型
     */
    public void setBillType(String billType) {
        this.billType = billType;
    }

    /**
     * 获取账单日期
     *
     * @return BUSI_DATE - 账单日期
     */
    public String getBusiDate() {
        return busiDate;
    }

    /**
     * 设置账单日期
     *
     * @param busiDate 账单日期
     */
    public void setBusiDate(String busiDate) {
        this.busiDate = busiDate;
    }

    /**
     * 获取借贷类型
     *
     * @return DC_TYPE - 借贷类型
     */
    public String getDcType() {
        return dcType;
    }

    /**
     * 设置借贷类型
     *
     * @param dcType 借贷类型
     */
    public void setDcType(String dcType) {
        this.dcType = dcType;
    }

    /**
     * 获取账单金额
     *
     * @return BILL_AMT - 账单金额
     */
    public BigDecimal getBillAmt() {
        return billAmt;
    }

    /**
     * 设置账单金额
     *
     * @param billAmt 账单金额
     */
    public void setBillAmt(BigDecimal billAmt) {
        this.billAmt = billAmt;
    }

    /**
     * 获取决策结果
     *
     * @return DCS_RST - 决策结果
     */
    public String getDcsRst() {
        return dcsRst;
    }

    /**
     * 设置决策结果
     *
     * @param dcsRst 决策结果
     */
    public void setDcsRst(String dcsRst) {
        this.dcsRst = dcsRst;
    }

    /**
     * 获取状态
     *
     * @return STATUS - 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取是否计费
     *
     * @return ISCHARGE - 是否计费
     */
    public String getIscharge() {
        return ischarge;
    }

    /**
     * 设置是否计费
     *
     * @param ischarge 是否计费
     */
    public void setIscharge(String ischarge) {
        this.ischarge = ischarge;
    }

    /**
     * 获取插入时间
     *
     * @return INSERT_TIME - 插入时间
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     * 设置插入时间
     *
     * @param insertTime 插入时间
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * 获取插入用户ID
     *
     * @return INSERT_USER_ID - 插入用户ID
     */
    public String getInsertUserId() {
        return insertUserId;
    }

    /**
     * 设置插入用户ID
     *
     * @param insertUserId 插入用户ID
     */
    public void setInsertUserId(String insertUserId) {
        this.insertUserId = insertUserId;
    }

    /**
     * 获取更新时间
     *
     * @return UPDATE_TIME - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}