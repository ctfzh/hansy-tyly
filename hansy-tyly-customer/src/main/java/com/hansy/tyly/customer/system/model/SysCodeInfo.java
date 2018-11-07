package com.hansy.tyly.customer.system.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_code_info")
public class SysCodeInfo {
    /**
     * 编码信息值
     */
    @Id
    @Column(name = "CODE_INFO_VALUE")
    private String codeInfoValue;

    /**
     * 编码类型ID
     */
    @Column(name = "CODE_TYPE_ID")
    private String codeTypeId;

    /**
     * 编码信息名称
     */
    @Column(name = "CODE_INFO_NAME")
    private String codeInfoName;

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
     * 获取编码信息值
     *
     * @return CODE_INFO_VALUE - 编码信息值
     */
    public String getCodeInfoValue() {
        return codeInfoValue;
    }

    /**
     * 设置编码信息值
     *
     * @param codeInfoValue 编码信息值
     */
    public void setCodeInfoValue(String codeInfoValue) {
        this.codeInfoValue = codeInfoValue;
    }

    /**
     * 获取编码类型ID
     *
     * @return CODE_TYPE_ID - 编码类型ID
     */
    public String getCodeTypeId() {
        return codeTypeId;
    }

    /**
     * 设置编码类型ID
     *
     * @param codeTypeId 编码类型ID
     */
    public void setCodeTypeId(String codeTypeId) {
        this.codeTypeId = codeTypeId;
    }

    /**
     * 获取编码信息名称
     *
     * @return CODE_INFO_NAME - 编码信息名称
     */
    public String getCodeInfoName() {
        return codeInfoName;
    }

    /**
     * 设置编码信息名称
     *
     * @param codeInfoName 编码信息名称
     */
    public void setCodeInfoName(String codeInfoName) {
        this.codeInfoName = codeInfoName;
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