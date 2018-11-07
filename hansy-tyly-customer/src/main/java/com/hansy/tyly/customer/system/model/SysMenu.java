package com.hansy.tyly.customer.system.model;

import java.util.Date;

import javax.persistence.*;

@Table(name = "sys_menu")
public class SysMenu {
    /**
     * 主键
     */
    @Id
    @Column(name = "MENU_ID")
    private String menuId;

    /**
     * 菜单名称
     */
    @Column(name = "MENU_NAME")
    private String menuName;

    /**
     * 菜单链接
     */
    @Column(name = "MENU_URL")
    private String menuUrl;

    /**
     * 菜单类型
     */
    @Column(name = "MENU_TYPE")
    private String menuType;
    
    /**
     * 菜单视图
     * */
    @Column(name= "ACTION_TYPE")
    private String actionType;

    /**
     * 菜单图标
     */
    @Column(name = "MENU_ICON")
    private String menuIcon;

    /**
     * 上级菜单ID
     */
    @Column(name = "PARENT_MENU_ID")
    private String parentMenuId;

    /**
     * 状态
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 菜单顺序
     */
    @Column(name = "MENU_ORDER")
    private Integer menuOrder;

    /**
     * 授权代码
     */
    @Column(name = "AUTH_CODE")
    private String authCode;

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
     * 获取主键
     *
     * @return MENU_ID - 主键
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 设置主键
     *
     * @param menuId 主键
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    /**
     * 获取菜单名称
     *
     * @return MENU_NAME - 菜单名称
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 设置菜单名称
     *
     * @param menuName 菜单名称
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * 获取菜单链接
     *
     * @return MENU_URL - 菜单链接
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * 设置菜单链接
     *
     * @param menuUrl 菜单链接
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    /**
     * 获取菜单类型
     *
     * @return MENU_TYPE - 菜单类型
     */
    public String getMenuType() {
        return menuType;
    }

    /**
     * 设置菜单类型
     *
     * @param menuType 菜单类型
     */
    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    /**
     * 获取菜单图标
     *
     * @return MENU_ICON - 菜单图标
     */
    public String getMenuIcon() {
        return menuIcon;
    }

    /**
     * 设置菜单图标
     *
     * @param menuIcon 菜单图标
     */
    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    /**
     * 获取上级菜单ID
     *
     * @return PARENT_MENU_ID - 上级菜单ID
     */
    public String getParentMenuId() {
        return parentMenuId;
    }

    /**
     * 设置上级菜单ID
     *
     * @param parentMenuId 上级菜单ID
     */
    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
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
     * 获取菜单顺序
     *
     * @return MENU_ORDER - 菜单顺序
     */
    public Integer getMenuOrder() {
        return menuOrder;
    }

    /**
     * 设置菜单顺序
     *
     * @param menuOrder 菜单顺序
     */
    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    /**
     * 获取授权代码
     *
     * @return AUTH_CODE - 授权代码
     */
    public String getAuthCode() {
        return authCode;
    }

    /**
     * 设置授权代码
     *
     * @param authCode 授权代码
     */
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
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

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
    
}