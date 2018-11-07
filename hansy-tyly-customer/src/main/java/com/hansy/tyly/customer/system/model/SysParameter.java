package com.hansy.tyly.customer.system.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_parameter")
public class SysParameter {
    /**
     * 主键
     */
    @Id
    @Column(name = "PARA_ID")
    private String paraId;

    /**
     * 参数名称
     */
    @Column(name = "PARA_NAME")
    private String paraName;

    /**
     * 参数值
     */
    @Column(name = "PARA_VALUE")
    private String paraValue;

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
     * @return PARA_ID - 主键
     */
    public String getParaId() {
        return paraId;
    }

    /**
     * 设置主键
     *
     * @param paraId 主键
     */
    public void setParaId(String paraId) {
        this.paraId = paraId;
    }

    /**
     * 获取参数名称
     *
     * @return PARA_NAME - 参数名称
     */
    public String getParaName() {
        return paraName;
    }

    /**
     * 设置参数名称
     *
     * @param paraName 参数名称
     */
    public void setParaName(String paraName) {
        this.paraName = paraName;
    }

    /**
     * 获取参数值
     *
     * @return PARA_VALUE - 参数值
     */
    public String getParaValue() {
        return paraValue;
    }

    /**
     * 设置参数值
     *
     * @param paraValue 参数值
     */
    public void setParaValue(String paraValue) {
        this.paraValue = paraValue;
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