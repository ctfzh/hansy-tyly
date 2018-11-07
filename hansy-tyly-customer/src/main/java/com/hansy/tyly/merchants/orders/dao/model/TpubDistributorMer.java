package com.hansy.tyly.merchants.orders.dao.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_pub_distributor_mer")
public class TpubDistributorMer {
    /**
     * 主键
     */
    @Id
    @Column(name = "table_key")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String tableKey;

    /**
     * 经销商编号
     */
    @Column(name = "distributor_no")
    private String distributorNo;

    /**
     * 商户编号
     */
    @Column(name = "mer_no")
    private String merNo;

    /**
     * 新增时间
     */
    @Column(name = "insert_date")
    private Date insertDate;

    /**
     * 修改时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 获取主键
     *
     * @return table_key - 主键
     */
    public String getTableKey() {
        return tableKey;
    }

    /**
     * 设置主键
     *
     * @param tableKey 主键
     */
    public void setTableKey(String tableKey) {
        this.tableKey = tableKey;
    }

    /**
     * 获取经销商编号
     *
     * @return distributor_no - 经销商编号
     */
    public String getDistributorNo() {
        return distributorNo;
    }

    /**
     * 设置经销商编号
     *
     * @param distributorNo 经销商编号
     */
    public void setDistributorNo(String distributorNo) {
        this.distributorNo = distributorNo;
    }

    /**
     * 获取商户编号
     *
     * @return mer_no - 商户编号
     */
    public String getMerNo() {
        return merNo;
    }

    /**
     * 设置商户编号
     *
     * @param merNo 商户编号
     */
    public void setMerNo(String merNo) {
        this.merNo = merNo;
    }

    /**
     * 获取新增时间
     *
     * @return insert_date - 新增时间
     */
    public Date getInsertDate() {
        return insertDate;
    }

    /**
     * 设置新增时间
     *
     * @param insertDate 新增时间
     */
    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    /**
     * 获取修改时间
     *
     * @return update_date - 修改时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置修改时间
     *
     * @param updateDate 修改时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}