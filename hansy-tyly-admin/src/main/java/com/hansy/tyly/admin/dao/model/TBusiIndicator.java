package com.hansy.tyly.admin.dao.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_busi_indicator")
public class TBusiIndicator {
    /**
     * 主键
     */
    @Id
    @Column(name = "INDICATOR_ID")
    private String indicatorId;

    /**
     * 产品名称
     */
    @Column(name = "INDICATOR_NAME")
    private String indicatorName;

    /**
     * 产品类型
     */
    @Column(name = "INDICATOR_TYPE")
    private String indicatorType;

    /**
     * 产品类型
     */
    @Column(name = "INDC_TYPE")
    private String indcType;

    /**
     * 状态
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 上级指标ID
     */
    @Column(name = "PARENT_INDC_ID")
    private String parentIndcId;

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
     * @return INDICATOR_ID - 主键
     */
    public String getIndicatorId() {
        return indicatorId;
    }

    /**
     * 设置主键
     *
     * @param indicatorId 主键
     */
    public void setIndicatorId(String indicatorId) {
        this.indicatorId = indicatorId;
    }

    public String getIndcType() {
        return indcType;
    }

    public void setIndcType(String indcType) {
        this.indcType = indcType;
    }

    /**
     * 获取产品名称
     *
     * @return INDICATOR_NAME - 产品名称
     */
    public String getIndicatorName() {
        return indicatorName;
    }

    /**
     * 设置产品名称
     *
     * @param indicatorName 产品名称
     */
    public void setIndicatorName(String indicatorName) {
        this.indicatorName = indicatorName;
    }

    /**
     * 获取产品类型
     *
     * @return INDICATOR_TYPE - 产品类型
     */
    public String getIndicatorType() {
        return indicatorType;
    }

    /**
     * 设置产品类型
     *
     * @param indicatorType 产品类型
     */
    public void setIndicatorType(String indicatorType) {
        this.indicatorType = indicatorType;
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
     * 获取上级指标ID
     *
     * @return PARENT_INDC_ID - 上级指标ID
     */
    public String getParentIndcId() {
        return parentIndcId;
    }

    /**
     * 设置上级指标ID
     *
     * @param parentIndcId 上级指标ID
     */
    public void setParentIndcId(String parentIndcId) {
        this.parentIndcId = parentIndcId;
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