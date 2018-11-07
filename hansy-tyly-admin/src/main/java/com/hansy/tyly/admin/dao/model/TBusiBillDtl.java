package com.hansy.tyly.admin.dao.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_busi_bill_dtl")
public class TBusiBillDtl {
    /**
     * 主键
     */
    @Id
    @Column(name = "BILL_DTL_ID")
    private String billDtlId;

    /**
     * 账单ID
     */
    @Column(name = "BILL_ID")
    private String billId;

    /**
     * 指标ID
     */
    @Column(name = "INDC_ID")
    private String indcId;

    /**
     * 指标值
     */
    @Column(name = "INDC_VALUE")
    private String indcValue;

    /**
     * 状态
     */
    @Column(name = "STATUS")
    private String status;

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
     * @return BILL_DTL_ID - 主键
     */
    public String getBillDtlId() {
        return billDtlId;
    }

    /**
     * 设置主键
     *
     * @param billDtlId 主键
     */
    public void setBillDtlId(String billDtlId) {
        this.billDtlId = billDtlId;
    }

    /**
     * 获取账单ID
     *
     * @return BILL_ID - 账单ID
     */
    public String getBillId() {
        return billId;
    }

    /**
     * 设置账单ID
     *
     * @param billId 账单ID
     */
    public void setBillId(String billId) {
        this.billId = billId;
    }

    /**
     * 获取指标ID
     *
     * @return INDC_ID - 指标ID
     */
    public String getIndcId() {
        return indcId;
    }

    /**
     * 设置指标ID
     *
     * @param indcId 指标ID
     */
    public void setIndcId(String indcId) {
        this.indcId = indcId;
    }

    /**
     * 获取指标值
     *
     * @return INDC_VALUE - 指标值
     */
    public String getIndcValue() {
        return indcValue;
    }

    /**
     * 设置指标值
     *
     * @param indcValue 指标值
     */
    public void setIndcValue(String indcValue) {
        this.indcValue = indcValue;
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