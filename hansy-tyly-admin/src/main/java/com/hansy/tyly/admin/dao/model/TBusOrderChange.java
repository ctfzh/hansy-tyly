package com.hansy.tyly.admin.dao.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_bus_order_change")
public class TBusOrderChange {
    /**
     * 主键
     */
    @Id
    @Column(name = "table_key")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String tableKey;

    /**
     * 订单编号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 变动状态
     */
    @Column(name = "change_status")
    private String changeStatus;

    /**
     * 描述
     */
    @Column(name = "change_desc")
    private String changeDesc;

    /**
     * 变动时间
     */
    @Column(name = "change_date")
    private Date changeDate;

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
     * 获取订单编号
     *
     * @return order_no - 订单编号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单编号
     *
     * @param orderNo 订单编号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取变动状态
     *
     * @return change_status - 变动状态
     */
    public String getChangeStatus() {
        return changeStatus;
    }

    /**
     * 设置变动状态
     *
     * @param changeStatus 变动状态
     */
    public void setChangeStatus(String changeStatus) {
        this.changeStatus = changeStatus;
    }

    /**
     * 获取描述
     *
     * @return change_desc - 描述
     */
    public String getChangeDesc() {
        return changeDesc;
    }

    /**
     * 设置描述
     *
     * @param changeDesc 描述
     */
    public void setChangeDesc(String changeDesc) {
        this.changeDesc = changeDesc;
    }

    /**
     * 获取变动时间
     *
     * @return change_date - 变动时间
     */
    public Date getChangeDate() {
        return changeDate;
    }

    /**
     * 设置变动时间
     *
     * @param changeDate 变动时间
     */
    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }
}