package com.hansy.tyly.customer.system.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_role")
public class SysRole {
    /**
     * 主键
     */
    @Id
    @Column(name = "ROLE_ID")
    private String roleId;

    /**
     * 角色名称
     */
    @Column(name = "ROLE_NAME")
    private String roleName;

    /**
     * 角色类型
     */
    @Column(name = "ROLE_TYPE")
    private String roleType;

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
     * @return ROLE_ID - 主键
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * 设置主键
     *
     * @param roleId 主键
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取角色名称
     *
     * @return ROLE_NAME - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取角色类型
     *
     * @return ROLE_TYPE - 角色类型
     */
    public String getRoleType() {
        return roleType;
    }

    /**
     * 设置角色类型
     *
     * @param roleType 角色类型
     */
    public void setRoleType(String roleType) {
        this.roleType = roleType;
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