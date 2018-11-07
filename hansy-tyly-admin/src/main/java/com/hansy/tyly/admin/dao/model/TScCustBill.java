package com.hansy.tyly.admin.dao.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_sc_cust_bill")
public class TScCustBill {
    /**
     * 主键
     */
    @Id
    @Column(name = "BILL_ID")
    private String billId;

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
     * 调度状态
     */
    @Column(name = "SCHEDULE_STATUS")
    private String scheduleStatus;

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
     * 获取调度状态
     *
     * @return SCHEDULE_STATUS - 调度状态
     */
    public String getScheduleStatus() {
        return scheduleStatus;
    }

    /**
     * 设置调度状态
     *
     * @param scheduleStatus 调度状态
     */
    public void setScheduleStatus(String scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
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
}