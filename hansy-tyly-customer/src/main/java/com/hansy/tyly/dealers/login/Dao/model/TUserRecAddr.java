package com.hansy.tyly.dealers.login.Dao.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_user_rec_addr")
public class TUserRecAddr {
    /**
     * 主键
     */
    @Id
    @Column(name = "table_key")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String tableKey;

    /**
     * 用户编号
     */
    @Column(name = "user_no")
    private String userNo;

    /**
     * 用户名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 收货人名称
     */
    @Column(name = "rec_name")
    private String recName;

    /**
     * 收货地址
     */
    @Column(name = "rec_addr")
    private String recAddr;

    /**
     * 收货人电话
     */
    @Column(name = "rec_phone")
    private String recPhone;

    /**
     * 省份
     */
    @Column(name = "rec_province")
    private String recProvince;

    /**
     * 市
     */
    @Column(name = "rec_city")
    private String recCity;

    /**
     * 区/县
     */
    @Column(name = "rec_county")
    private String recCounty;

    /**
     * 邮政编码
     */
    @Column(name = "post_code")
    private String postCode;

    /**
     * 是否默认收货地址
     */
    @Column(name = "is_default")
    private String isDefault;

    /**
     * 使用状态
     */
    private String status;

    /**
     * 新增时间
     */
    @Column(name = "insert_date")
    private Date insertDate;

    /**
     * 更新时间
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
     * 获取用户编号
     *
     * @return user_no - 用户编号
     */
    public String getUserNo() {
        return userNo;
    }

    /**
     * 设置用户编号
     *
     * @param userNo 用户编号
     */
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    /**
     * 获取用户名称
     *
     * @return user_name - 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名称
     *
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取收货人名称
     *
     * @return rec_name - 收货人名称
     */
    public String getRecName() {
        return recName;
    }

    /**
     * 设置收货人名称
     *
     * @param recName 收货人名称
     */
    public void setRecName(String recName) {
        this.recName = recName;
    }

    /**
     * 获取收货地址
     *
     * @return rec_addr - 收货地址
     */
    public String getRecAddr() {
        return recAddr;
    }

    /**
     * 设置收货地址
     *
     * @param recAddr 收货地址
     */
    public void setRecAddr(String recAddr) {
        this.recAddr = recAddr;
    }

    /**
     * 获取收货人电话
     *
     * @return rec_phone - 收货人电话
     */
    public String getRecPhone() {
        return recPhone;
    }

    /**
     * 设置收货人电话
     *
     * @param recPhone 收货人电话
     */
    public void setRecPhone(String recPhone) {
        this.recPhone = recPhone;
    }

    /**
     * 获取省份
     *
     * @return rec_province - 省份
     */
    public String getRecProvince() {
        return recProvince;
    }

    /**
     * 设置省份
     *
     * @param recProvince 省份
     */
    public void setRecProvince(String recProvince) {
        this.recProvince = recProvince;
    }

    /**
     * 获取市
     *
     * @return rec_city - 市
     */
    public String getRecCity() {
        return recCity;
    }

    /**
     * 设置市
     *
     * @param recCity 市
     */
    public void setRecCity(String recCity) {
        this.recCity = recCity;
    }

    /**
     * 获取区/县
     *
     * @return rec_county - 区/县
     */
    public String getRecCounty() {
        return recCounty;
    }

    /**
     * 设置区/县
     *
     * @param recCounty 区/县
     */
    public void setRecCounty(String recCounty) {
        this.recCounty = recCounty;
    }

    /**
     * 获取邮政编码
     *
     * @return post_code - 邮政编码
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * 设置邮政编码
     *
     * @param postCode 邮政编码
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * 获取是否默认收货地址
     *
     * @return is_default - 是否默认收货地址
     */
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * 设置是否默认收货地址
     *
     * @param isDefault 是否默认收货地址
     */
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * 获取使用状态
     *
     * @return status - 使用状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置使用状态
     *
     * @param status 使用状态
     */
    public void setStatus(String status) {
        this.status = status;
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
     * 获取更新时间
     *
     * @return update_date - 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新时间
     *
     * @param updateDate 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}