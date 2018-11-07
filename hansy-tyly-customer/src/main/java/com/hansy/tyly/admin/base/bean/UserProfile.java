package com.hansy.tyly.admin.base.bean;

import com.hansy.tyly.admin.dao.model.SysRole;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserProfile implements Serializable {
	private static final long serialVersionUID = 1L;
	//private String LoginId;
    private String userId;
    private String userName;
    private String userPwd;
    private String userType;
    private String userTel;
    private String userDept;
    private String status;
    private Date insertTime;
    private String insertUserId;
    private Date updateTime;
    private List<SysRole> sysRoles;
    private String loginId;
    private String userStat;
    private String orgId;
    private String orgStat;
    private Date lastLoginTime;
    private String currLoginId;

    public List<SysRole> getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(List<SysRole> sysRoles) {
        this.sysRoles = sysRoles;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
    	this.loginId = loginId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserDept() {
        return userDept;
    }

    public void setUserDept(String userDept) {
        this.userDept = userDept;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getInsertUserId() {
        return insertUserId;
    }

    public void setInsertUserId(String insertUserId) {
        this.insertUserId = insertUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getUserStat() {
		return userStat;
	}

	public void setUserStat(String userStat) {
		this.userStat = userStat;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgStat() {
		return orgStat;
	}

	public void setOrgStat(String orgStat) {
		this.orgStat = orgStat;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getCurrLoginId() {
		return currLoginId;
	}

	public void setCurrLoginId(String currLoginId) {
		this.currLoginId = currLoginId;
	}

}
