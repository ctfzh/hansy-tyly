package com.hansy.tyly.customer.system.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_busi_op_log")
public class TBusiOpLog {
    /**
     * 主键
     */
    @Id
    @Column(name = "SYS_UUID")
    private String sysUuid;

    /**
     * 业务类型
     */
    @Column(name = "BUSI_OP_TYPE")
    private String busiOpType;

    /**
     * 机构ID
     */
    @Column(name = "ORG_ID")
    private String orgId;

    /**
     * 用户ID
     */
    @Column(name = "USER_ID")
    private String userId;

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
     * 获取业务类型
     *
     * @return BUSI_OP_TYPE - 业务类型
     */
    public String getBusiOpType() {
        return busiOpType;
    }

    /**
     * 设置业务类型
     *
     * @param busiOpType 业务类型
     */
    public void setBusiOpType(String busiOpType) {
        this.busiOpType = busiOpType;
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
     * 获取用户ID
     *
     * @return USER_ID - 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
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