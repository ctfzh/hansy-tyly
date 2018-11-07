package com.hansy.tyly.customer.reportMgr.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_sc_cust_indc_rst")
public class TScCustIndcRst {
    /**
     * 主键
     */
    @Id
    @Column(name = "SYS_UUID")
    private String sysUuid;

    /**
     * 主键
     */
    @Column(name = "BILL_ID")
    private String billId;

    /**
     * 指标ID
     */
    @Column(name = "INDC_ID")
    private String indcId;

    /**
     * 指标值
     */
    @Column(name = "INDC_VALUE")
    private String indcValue;

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

    public String getIndcId() {
		return indcId;
	}

	public void setIndcId(String indcId) {
		this.indcId = indcId;
	}

	public String getIndcValue() {
		return indcValue;
	}

	public void setIndcValue(String indcValue) {
		this.indcValue = indcValue;
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
}