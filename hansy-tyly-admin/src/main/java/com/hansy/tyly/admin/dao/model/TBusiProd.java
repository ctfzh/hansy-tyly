package com.hansy.tyly.admin.dao.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "t_busi_prod")
public class TBusiProd {
    /**
     * 主键
     */
    @Id
    @Column(name = "PROD_ID")
    private String prodId;

    /**
     * 产品名称
     */
    @NotBlank
    @Column(name = "PROD_NAME")
    private String prodName;

    /**
     * 产品类型
     */
    @Column(name = "PROD_TYPE")
    private String prodType;

    /**
     * 产品简介
     */
    @Column(name = "PROD_REMARK")
    private String prodRemark;

    /**
     * 消费类型
     */
    @Column(name = "COST_TYPE")
    private String costType;

    /**
     * 消费金额
     */
    @NotBlank
    @Column(name = "COST_AMT")
    private BigDecimal costAmt;

    /**
     * 管理等级
     */
    @Column(name = "MNG_TYPE")
    private String mngType;

    /**
     * 规则链
     */
    @NotBlank
    @Column(name = "RULE_ID")
    private String ruleId;

    @Column(name= "SCORE_ID")
    private String scoreId;

    /**
     * 接口地址
     */
    @Column(name = "INTER_ADDR1")
    private String interAddr1;

    /**
     * 接口地址
     */
    @Column(name = "INTER_ADDR2")
    private String interAddr2;

    /**
     * 接口地址
     */
    @Column(name = "INTER_ADDR3")
    private String interAddr3;

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
     * @return PROD_ID - 主键
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置主键
     *
     * @param prodId 主键
     */
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getScoreId() {
        return scoreId;
    }

    public void setScoreId(String scoreId) {
        this.scoreId = scoreId;
    }

    /**
     * 获取产品名称
     *
     * @return PROD_NAME - 产品名称
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置产品名称
     *
     * @param prodName 产品名称
     */
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    /**
     * 获取产品类型
     *
     * @return PROD_TYPE - 产品类型
     */
    public String getProdType() {
        return prodType;
    }

    /**
     * 设置产品类型
     *
     * @param prodType 产品类型
     */
    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    /**
     * 获取产品简介
     *
     * @return PROD_REMARK - 产品简介
     */
    public String getProdRemark() {
        return prodRemark;
    }

    /**
     * 设置产品简介
     *
     * @param prodRemark 产品简介
     */
    public void setProdRemark(String prodRemark) {
        this.prodRemark = prodRemark;
    }

    /**
     * 获取消费类型
     *
     * @return COST_TYPE - 消费类型
     */
    public String getCostType() {
        return costType;
    }

    /**
     * 设置消费类型
     *
     * @param costType 消费类型
     */
    public void setCostType(String costType) {
        this.costType = costType;
    }

    /**
     * 获取消费金额
     *
     * @return COST_AMT - 消费金额
     */
    public BigDecimal getCostAmt() {
        return costAmt;
    }

    /**
     * 设置消费金额
     *
     * @param costAmt 消费金额
     */
    public void setCostAmt(BigDecimal costAmt) {
        this.costAmt = costAmt;
    }

    /**
     * 获取管理等级
     *
     * @return MNG_TYPE - 管理等级
     */
    public String getMngType() {
        return mngType;
    }

    /**
     * 设置管理等级
     *
     * @param mngType 管理等级
     */
    public void setMngType(String mngType) {
        this.mngType = mngType;
    }

    /**
     * 获取规则链
     *
     * @return RULE_ID - 规则链
     */
    public String getRuleId() {
        return ruleId;
    }

    /**
     * 设置规则链
     *
     * @param ruleId 规则链
     */
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    /**
     * 获取接口地址
     *
     * @return INTER_ADDR1 - 接口地址
     */
    public String getInterAddr1() {
        return interAddr1;
    }

    /**
     * 设置接口地址
     *
     * @param interAddr1 接口地址
     */
    public void setInterAddr1(String interAddr1) {
        this.interAddr1 = interAddr1;
    }

    /**
     * 获取接口地址
     *
     * @return INTER_ADDR2 - 接口地址
     */
    public String getInterAddr2() {
        return interAddr2;
    }

    /**
     * 设置接口地址
     *
     * @param interAddr2 接口地址
     */
    public void setInterAddr2(String interAddr2) {
        this.interAddr2 = interAddr2;
    }

    /**
     * 获取接口地址
     *
     * @return INTER_ADDR3 - 接口地址
     */
    public String getInterAddr3() {
        return interAddr3;
    }

    /**
     * 设置接口地址
     *
     * @param interAddr3 接口地址
     */
    public void setInterAddr3(String interAddr3) {
        this.interAddr3 = interAddr3;
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