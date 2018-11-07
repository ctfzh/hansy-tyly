package com.hansy.tyly.customer.system.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_org")
public class SysOrg {
    /**
     * 主键
     */
    @Id
    @Column(name = "ORG_ID")
    private String orgId;

    /**
     * 机构名称
     */
    @Column(name = "ORG_NAME")
    private String orgName;

    /**
     * 机构类型
     */
    @Column(name = "ORG_TYPE")
    private String orgType;

    /**
     * 状态
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 上级机构ID
     */
    @Column(name = "PARENT_ORG_ID")
    private String parentOrgId;

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
     * @return ORG_ID - 主键
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 设置主键
     *
     * @param orgId 主键
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * 获取机构名称
     *
     * @return ORG_NAME - 机构名称
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * 设置机构名称
     *
     * @param orgName 机构名称
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * 获取机构类型
     *
     * @return ORG_TYPE - 机构类型
     */
    public String getOrgType() {
        return orgType;
    }

    /**
     * 设置机构类型
     *
     * @param orgType 机构类型
     */
    public void setOrgType(String orgType) {
        this.orgType = orgType;
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
     * 获取上级机构ID
     *
     * @return PARENT_ORG_ID - 上级机构ID
     */
    public String getParentOrgId() {
        return parentOrgId;
    }

    /**
     * 设置上级机构ID
     *
     * @param parentOrgId 上级机构ID
     */
    public void setParentOrgId(String parentOrgId) {
        this.parentOrgId = parentOrgId;
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