package com.hansy.tyly.admin.dao.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_pub_group")
public class TPubGroup {
    /**
     * 分组编号
     */
    @Id
    @Column(name = "group_no")
    private String groupNo;

    /**
     * 分组名称
     */
    @Column(name = "group_name")
    private String groupName;

    @Column(name = "group_type")
    private String groupType;

    /**
     * 创建类型(销售经销商)
     */
    @Column(name = "create_type")
    private String createType;

    /**
     * 创建者(销售编号或者经销商编号)
     */
    @Column(name = "create_no")
    private String createNo;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 分组状态
     */
    @Column(name = "group_status")
    private String groupStatus;

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
     * @return group_type
     */
    public String getGroupType() {
        return groupType;
    }

    /**
     * @param groupType
     */
    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    /**
     * 获取创建类型(销售经销商)
     *
     * @return create_type - 创建类型(销售经销商)
     */
    public String getCreateType() {
        return createType;
    }

    /**
     * 设置创建类型(销售经销商)
     *
     * @param createType 创建类型(销售经销商)
     */
    public void setCreateType(String createType) {
        this.createType = createType;
    }

    /**
     * 获取创建者(销售编号或者经销商编号)
     *
     * @return create_no - 创建者(销售编号或者经销商编号)
     */
    public String getCreateNo() {
        return createNo;
    }

    /**
     * 设置创建者(销售编号或者经销商编号)
     *
     * @param createNo 创建者(销售编号或者经销商编号)
     */
    public void setCreateNo(String createNo) {
        this.createNo = createNo;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取分组状态
     *
     * @return group_status - 分组状态
     */
    public String getGroupStatus() {
        return groupStatus;
    }

    /**
     * 设置分组状态
     *
     * @param groupStatus 分组状态
     */
    public void setGroupStatus(String groupStatus) {
        this.groupStatus = groupStatus;
    }
}