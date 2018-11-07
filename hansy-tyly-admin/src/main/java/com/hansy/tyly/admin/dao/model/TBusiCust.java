package com.hansy.tyly.admin.dao.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "t_busi_cust")
public class TBusiCust {
    /**
     * 主键
     */
    @Id
    @Column(name = "CUST_ID")
    private String custId;

    /**
     * 客户姓名
     */
    @Column(name = "CUST_NAME")
    private String custName;

    /**
     * 客户身份证号
     */
    @Column(name = "CUST_CERT_NO")
    private String custCertNo;

    /**
     * 客户手机号
     */
    @Column(name = "CUST_TEL")
    private String custTel;

    /**
     * 机构ID
     */
    @Column(name = "ORG_ID")
    private String orgId;

    /**
     * 客户经理ID
     */
    @Column(name = "USER_ID")
    private String userId;

    /**
     * 贷款金额
     */
    @Column(name = "LOAN_AMT")
    private BigDecimal loanAmt;

    /**
     * 状态
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 管理状态
     */
    @Column(name = "MNG_STATUS")
    private String mngStatus;

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
     * @return CUST_ID - 主键
     */
    public String getCustId() {
        return custId;
    }

    /**
     * 设置主键
     *
     * @param custId 主键
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    /**
     * 获取客户姓名
     *
     * @return CUST_NAME - 客户姓名
     */
    public String getCustName() {
        return custName;
    }

    /**
     * 设置客户姓名
     *
     * @param custName 客户姓名
     */
    public void setCustName(String custName) {
        this.custName = custName;
    }

    /**
     * 获取客户身份证号
     *
     * @return CUST_CERT_NO - 客户身份证号
     */
    public String getCustCertNo() {
        return custCertNo;
    }

    /**
     * 设置客户身份证号
     *
     * @param custCertNo 客户身份证号
     */
    public void setCustCertNo(String custCertNo) {
        this.custCertNo = custCertNo;
    }

    /**
     * 获取客户手机号
     *
     * @return CUST_TEL - 客户手机号
     */
    public String getCustTel() {
        return custTel;
    }

    /**
     * 设置客户手机号
     *
     * @param custTel 客户手机号
     */
    public void setCustTel(String custTel) {
        this.custTel = custTel;
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
     * 获取客户经理ID
     *
     * @return USER_ID - 客户经理ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置客户经理ID
     *
     * @param userId 客户经理ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取贷款金额
     *
     * @return LOAN_AMT - 贷款金额
     */
    public BigDecimal getLoanAmt() {
        return loanAmt;
    }

    /**
     * 设置贷款金额
     *
     * @param loanAmt 贷款金额
     */
    public void setLoanAmt(BigDecimal loanAmt) {
        this.loanAmt = loanAmt;
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
     * 获取管理状态
     *
     * @return MNG_STATUS - 管理状态
     */
    public String getMngStatus() {
        return mngStatus;
    }

    /**
     * 设置管理状态
     *
     * @param mngStatus 管理状态
     */
    public void setMngStatus(String mngStatus) {
        this.mngStatus = mngStatus;
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