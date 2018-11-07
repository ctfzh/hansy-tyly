package com.hansy.tyly.admin.dao.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_user")
public class SysUser {
    /**
     * 主键
     */
    @Id
    @Column(name = "USER_ID")
    private String userId;

    /**
     * 登录ID
     */
    @Column(name = "LOGIN_ID")
    private String loginId;

    /**
     * 用户名称
     */
    @Column(name = "USER_NAME")
    private String userName;

    /**
     * 用户密码
     */
    @Column(name = "USER_PWD")
    private String userPwd;

    /**
     * 用户类型
     */
    @Column(name = "USER_TYPE")
    private String userType;

    /**
     * 用户电话
     */
    @Column(name = "USER_TEL")
    private String userTel;

    /**
     * 用户所属部门
     */
    @Column(name = "USER_DEPT")
    private String userDept;

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
     * 性别
     */
	@Column(name = "SEX")
	private String sex;
	/**
	 * 身份证
	 */
	@Column(name = "USER_ID_CARD")
	private String userIdCard;
	/**
	 * 入职时间
	 */
	@Column(name = "ENTRY_TIME")
	private Date entryTime;
    /**
     * 获取主键
     *
     * @return USER_ID - 主键
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置主键
     *
     * @param userId 主键
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取登录ID
     *
     * @return LOGIN_ID - 登录ID
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * 设置登录ID
     *
     * @param loginId 登录ID
     */
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * 获取用户名称
     *
     * @return USER_NAME - 用户名称
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
     * 获取用户密码
     *
     * @return USER_PWD - 用户密码
     */
    public String getUserPwd() {
        return userPwd;
    }

    /**
     * 设置用户密码
     *
     * @param userPwd 用户密码
     */
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    /**
     * 获取用户类型
     *
     * @return USER_TYPE - 用户类型
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
     * 获取用户电话
     *
     * @return USER_TEL - 用户电话
     */
    public String getUserTel() {
        return userTel;
    }

    /**
     * 设置用户电话
     *
     * @param userTel 用户电话
     */
    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    /**
     * 获取用户所属部门
     *
     * @return USER_DEPT - 用户所属部门
     */
    public String getUserDept() {
        return userDept;
    }

    /**
     * 设置用户所属部门
     *
     * @param userDept 用户所属部门
     */
    public void setUserDept(String userDept) {
        this.userDept = userDept;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUserIdCard() {
		return userIdCard;
	}

	public void setUserIdCard(String userIdCard) {
		this.userIdCard = userIdCard;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
    
}