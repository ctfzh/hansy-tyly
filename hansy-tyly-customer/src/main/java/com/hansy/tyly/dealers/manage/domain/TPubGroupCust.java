package com.hansy.tyly.dealers.manage.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_pub_group_cust")
public class TPubGroupCust {
    /**
     * 主键
     */
    @Id
    @Column(name = "table_key")
    private String tableKey;

    /**
     * 分组编号
     */
    @Column(name = "group_no")
    private String groupNo;

    /**
     * 分组名称
     */
    @Column(name = "group_name")
    private String groupName;

    /**
     * 成员编号(商户编号或者经销商编号)
     */
    @Column(name = "cust_no")
    private String custNo;

    @Column(name = "insert_date")
    private Date insertDate;

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
     * 获取分组编号
     *
     * @return group_no - 分组编号
     */
    public String getGroupNo() {
        return groupNo;
    }

    /**
     * 设置分组编号
     *
     * @param groupNo 分组编号
     */
    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    /**
     * 获取分组名称
     *
     * @return group_name - 分组名称
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置分组名称
     *
     * @param groupName 分组名称
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 获取成员编号(商户编号或者经销商编号)
     *
     * @return cust_no - 成员编号(商户编号或者经销商编号)
     */
    public String getCustNo() {
        return custNo;
    }

    /**
     * 设置成员编号(商户编号或者经销商编号)
     *
     * @param custNo 成员编号(商户编号或者经销商编号)
     */
    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    /**
     * @return insert_date
     */
    public Date getInsertDate() {
        return insertDate;
    }

    /**
     * @param insertDate
     */
    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }
}