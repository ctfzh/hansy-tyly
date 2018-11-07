package com.hansy.tyly.admin.dao.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_sc_int_log")
public class TScIntLog {
    /**
     * 主键
     */
    @Id
    @Column(name = "SYS_UUID")
    private String sysUuid;

    /**
     * 主键
     */
    @Column(name = "BILL_ID")
    private String billId;

    /**
     * 接口类型
     */
    @Column(name = "INT_TYPE")
    private String intType;

    /**
     * 执行状态
     */
    @Column(name = "LOG_STATE")
    private String logState;

    /**
     * 失败原因
     */
    @Column(name = "ERROR_REASON")
    private String errorReason;

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

    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }

    /**
     * 获取主键
     *
     * @return SYS_UUID - 主键
     */
    public String getSysUuid() {
        return sysUuid;
    }

    /**
     * 设置主键
     *
     * @param sysUuid 主键
     */
    public void setSysUuid(String sysUuid) {
        this.sysUuid = sysUuid;
    }

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
     * 获取接口类型
     *
     * @return INT_TYPE - 接口类型
     */
    public String getIntType() {
        return intType;
    }

    /**
     * 设置接口类型
     *
     * @param intType 接口类型
     */
    public void setIntType(String intType) {
        this.intType = intType;
    }

    public String getLogState() {
        return logState;
    }

    public void setLogState(String logState) {
        this.logState = logState;
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