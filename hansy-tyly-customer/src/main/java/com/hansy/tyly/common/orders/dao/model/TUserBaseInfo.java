package com.hansy.tyly.common.orders.dao.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_user_base_info")
public class TUserBaseInfo {
    /**
     * 用户编号
     */
    @Id
    @Column(name = "user_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userNo;

    /**
     * 用户昵称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 登录密码
     */
    @Column(name = "log_passwd")
    private String logPasswd;

    /**
     * 用户手机号码
     */
    @Column(name = "user_phone")
    private String userPhone;

    /**
     * 用户类型
     */
    @Column(name = "user_type")
    private String userType;

    /**
     * 商户经销商编号
     */
    @Column(name = "relation_no")
    private String relationNo;

    /**
     * 用户状态
     */
    @Column(name = "user_status")
    private String userStatus;

    /**
     * 用户微信号
     */
    @Column(name = "wx_no")
    private String wxNo;

    /**
     * 用户注册时间
     */
    @Column(name = "reg_date")
    private Date regDate;

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
     * 获取用户昵称
     *
     * @return user_name - 用户昵称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户昵称
     *
     * @param userName 用户昵称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取登录密码
     *
     * @return log_passwd - 登录密码
     */
    public String getLogPasswd() {
        return logPasswd;
    }

    /**
     * 设置登录密码
     *
     * @param logPasswd 登录密码
     */
    public void setLogPasswd(String logPasswd) {
        this.logPasswd = logPasswd;
    }

    /**
     * 获取用户手机号码
     *
     * @return user_phone - 用户手机号码
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * 设置用户手机号码
     *
     * @param userPhone 用户手机号码
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    /**
     * 获取用户类型
     *
     * @return user_type - 用户类型
     */
    public String getUserType() {
        return userType;
    }

    /**
     * 设置用户类型
     *
     * @param userType 用户类型
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * 获取商户经销商编号
     *
     * @return relation_no - 商户经销商编号
     */
    public String getRelationNo() {
        return relationNo;
    }

    /**
     * 设置商户经销商编号
     *
     * @param relationNo 商户经销商编号
     */
    public void setRelationNo(String relationNo) {
        this.relationNo = relationNo;
    }

    /**
     * 获取用户状态
     *
     * @return user_status - 用户状态
     */
    public String getUserStatus() {
        return userStatus;
    }

    /**
     * 设置用户状态
     *
     * @param userStatus 用户状态
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * 获取用户微信号
     *
     * @return wx_no - 用户微信号
     */
    public String getWxNo() {
        return wxNo;
    }

    /**
     * 设置用户微信号
     *
     * @param wxNo 用户微信号
     */
    public void setWxNo(String wxNo) {
        this.wxNo = wxNo;
    }

    /**
     * 获取用户注册时间
     *
     * @return reg_date - 用户注册时间
     */
    public Date getRegDate() {
        return regDate;
    }

    /**
     * 设置用户注册时间
     *
     * @param regDate 用户注册时间
     */
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}