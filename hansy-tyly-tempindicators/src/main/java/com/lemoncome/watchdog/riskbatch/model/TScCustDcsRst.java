package com.lemoncome.watchdog.riskbatch.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_sc_cust_dcs_rst")
public class TScCustDcsRst{
	
	/**
     * 主键
     */
    @Id
    @Column(name = "BILL_ID")
    private String billId;

    /**
     * 决策结果
     */
    @Id
    @Column(name = "DCS_RST")
    private String dcsRst;
    
    /**
     * 分数
     */
    @Id
    @Column(name = "SCORE")
    private String score;
    
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
     * 获取决策结果
     *
     * @return DCS_RST - 决策结果
     */
    public String getDcsRst() {
        return dcsRst;
    }

    /**
     * 设置决策结果
     *
     * @param dcsRst 决策结果
     */
    public void setDcsRst(String dcsRst) {
        this.dcsRst = dcsRst;
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

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
}