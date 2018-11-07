package com.hansy.tyly.admin.system.service;

import java.util.List;
import java.util.Map;

import com.hansy.tyly.admin.base.bean.UserProfile;
import com.hansy.tyly.admin.dao.model.SysCodeInfo;
import com.hansy.tyly.admin.dao.model.SysCodeType;
import com.hansy.tyly.admin.dao.model.SysMenu;
import com.hansy.tyly.admin.dao.model.SysRole;
import com.hansy.tyly.admin.dao.model.SysUser;
import com.hansy.tyly.core.shiro.ShiroChainBean;

public interface SysUserRoleMenuService {
    /*查询所有菜单*/
    List<ShiroChainBean> QueryAllMenu();
    /*查询用户角色*/
    List<String> QueryUserRoles(String userId);
    /*查询用户菜单*/
    List<String> QueryUserMenus(String userId);
    /*查询后端角色*/
    List<SysRole> queryAllRole();
    /*登陆*/
    SysUser login(String userName,String passWord,String ip);
    /*查询用户列表（包含用户信息及其角色信息）*/
    List<Map<String,Object>> queryUserAndRole(Map<String,Object> map);
    /*保存用户*/
    String saveUser(Map<String,Object> reqMap, UserProfile userProfile);
    /*编辑用户*/
    String UpdateUser(Map<String,Object> reqMap, UserProfile userProfile);
    /*刪除用戶*/
    String deleteUser(String userId, UserProfile userProfile);
    /*重置密码*/
    String restPassWord(String userId,String passWord, UserProfile userProfile);
    /*查询所有角色*/
    List<SysRole> queryRoles(Map<String, Object> roleName);
    /*保存角色*/
    String saveRole(String roleName,String roleType, UserProfile userProfile);
    /*删除角色*/
    String deleteRole(String roleId, UserProfile userProfile);
    /*查询角色关联菜单，及所有菜单*/
    Map<String,Object> queryRoleHasMenuId(String roleId,String roleType);
    /*修改角色关联菜单*/
    String alterRoleMenus(List<String> menuId,String roleId, UserProfile userProfile);
    /*分类查询 前后系统菜单*/
    Map<String,Object> queryMenusOnMenuType();
    /*新增菜单*/
    String saveMenu(SysMenu sysMenu,String type, UserProfile userProfile);
    /*编辑菜单*/
    String updateMenu(SysMenu sysMenu, UserProfile userProfile);
    /*删除菜单*/
    String deleteMenu(String id,String type, UserProfile userProfile);
    /*查询码类*/
    List<SysCodeType> queryCodeType(Map<String, Object> codeTypeName);
    /*删除码类*/
    String deleteCodeType(List<String> codeTypeId, UserProfile userProfile);
    /*修改码类状态*/
    String updateCodeTypeStatusById(String id, UserProfile userProfile);
    /*新增码类*/
    String saveCodeType(SysCodeType sysCodeType,String type, UserProfile userProfile);
    /*查询码值*/
    List<SysCodeInfo> queryCodeInfo(Map<String, Object> codeTypeId);
    /*删除码值*/
    String deletCodeInfo(List<String> codeInfoId, UserProfile userProfile);
    /*修改码值状态*/
    String updateCodeInfoStatusById(String id, UserProfile userProfile);
    /*新增码值111*/
    String saveCodeInfo(SysCodeInfo sysCodeInfo,String type, UserProfile userProfile);
    /*修改密码*/
    String alterPwd(String userId,String oldPwd,String nPwd,String retPwd,UserProfile userProfile);
    //通过状态 和状态类型 获取 码值
    String getCodeValueByName(String str,String code);
    //通过状态 和状态类型 获取 码值
    String getCodeNameByValue(String code);
}
