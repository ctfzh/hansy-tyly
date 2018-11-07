package com.hansy.tyly.customer.custmgr.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_busi_cust_prod")
public class TBusiCustProd {
    /**
     * 主键
     */
    @Id
    @Column(name = "SYS_UUID")
    private String sysUuid;

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
     * 调用频次类型
     */
    @Column(name = "FRE_TYPE")
    private String freType;

    /**
     * 调用频次
     */
    @Column(name = "FRE_NUM")
    private Integer freNum;

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
     * 获取调用频次类型
     *
     * @return FRE_TYPE - 调用频次类型
     */
    public String getFreType() {
        return freType;
    }

    /**
     * 设置调用频次类型
     *
     * @param freType 调用频次类型
     */
    public void setFreType(String freType) {
        this.freType = freType;
    }

    /**
     * 获取调用频次
     *
     * @return FRE_NUM - 调用频次
     */
    public Integer getFreNum() {
        return freNum;
    }

    /**
     * 设置调用频次
     *
     * @param freNum 调用频次
     */
    public void setFreNum(Integer freNum) {
        this.freNum = freNum;
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