package com.hansy.tyly.admin.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;

import com.hansy.tyly.admin.base.bean.UserProfile;
import com.hansy.tyly.admin.constant.SysCodeConstant;
import com.hansy.tyly.admin.dao.model.SysCodeInfo;
import com.hansy.tyly.admin.dao.model.SysCodeType;
import com.hansy.tyly.admin.dao.model.SysLoginLog;
import com.hansy.tyly.admin.dao.model.SysMenu;
import com.hansy.tyly.admin.dao.model.SysRRoleMenu;
import com.hansy.tyly.admin.dao.model.SysRUserRole;
import com.hansy.tyly.admin.dao.model.SysRole;
import com.hansy.tyly.admin.dao.model.SysUser;
import com.hansy.tyly.admin.system.mapper.SysCodeInfoMapper;
import com.hansy.tyly.admin.system.mapper.SysCodeTypeMapper;
import com.hansy.tyly.admin.system.mapper.SysLoginLogMapper;
import com.hansy.tyly.admin.system.mapper.SysMenuMapper;
import com.hansy.tyly.admin.system.mapper.SysRRoleMenuMapper;
import com.hansy.tyly.admin.system.mapper.SysRUserRoleMapper;
import com.hansy.tyly.admin.system.mapper.SysRoleMapper;
import com.hansy.tyly.admin.system.mapper.SysUserMapper;
import com.hansy.tyly.admin.system.service.SysUserRoleMenuService;
import com.hansy.tyly.admin.utils.Bean2MapUtil;
import com.hansy.tyly.admin.utils.PasswordUtil;
import com.hansy.tyly.admin.utils.RandomUtil;
import com.hansy.tyly.admin.utils.ValidUtil;
import com.hansy.tyly.common.enumClass.CodeEnum;
import com.hansy.tyly.core.excepiton.ParameterException;
import com.hansy.tyly.core.helper.NPageHelper;
import com.hansy.tyly.core.holder.RequestHolder;
import com.hansy.tyly.core.shiro.ShiroChainBean;

@Transactional
@Service
public class SysUserRoleMenuServiceImpl implements SysUserRoleMenuService {

    private final static Logger LOGGER = LoggerFactory.getLogger(SysUserRoleMenuServiceImpl.class);


    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRUserRoleMapper sysRUserRoleMapper;
    @Autowired
    private SysRRoleMenuMapper sysRRoleMenuMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysCodeTypeMapper sysCodeTypeMapper;
    @Autowired
    private SysCodeInfoMapper sysCodeInfoMapper;
    @Autowired
    private SysLoginLogMapper sysLoginLogMapper;

    /**
     * 查询所有菜单
     *
     * @return List<SysMenu> 菜单列表
     */
    @Override
    public List<ShiroChainBean> QueryAllMenu() {
        List<SysMenu> sysMenus = sysMenuMapper.selectAllBack(SysCodeConstant.MENU_TYPE_BACK, SysCodeConstant
                .STATUS_YES);
        List<ShiroChainBean> shiroChainBeans = new ArrayList<>();
        for (SysMenu sysMenu : sysMenus) {
            ShiroChainBean shiroChainBean = new ShiroChainBean();
            shiroChainBean.setCodes(sysMenu.getMenuId());
            shiroChainBean.setUrl(sysMenu.getMenuUrl());
            shiroChainBeans.add(shiroChainBean);
        }
        return shiroChainBeans;
    }

    /**
     * 查询用户所拥有的角色
     *
     * @param userId 用户唯一ID
     * @return List<SysRole> 角色列表
     */
    @Override
    public List<String> QueryUserRoles(String userId) {
        List<SysRole> sysRoles = sysUserMapper.queryUserRolesByUserId(userId, SysCodeConstant.ROLE_TYPE_BACK,
                SysCodeConstant.STATUS_YES);
        List<String> list = new ArrayList<>();
        for (SysRole sysRole : sysRoles) {
            list.add(sysRole.getRoleId());
        }
        return list;
    }

    /**
     * 查询用户所拥有的菜单
     *
     * @param userId 用户唯一ID
     * @return List<SysMenu> 菜单列表
     */
    @Override
    public List<String> QueryUserMenus(String userId) {
        List<SysMenu> sysMenus = sysUserMapper.queryUserMenusByUserId(userId, SysCodeConstant.MENU_TYPE_BACK,
                SysCodeConstant.STATUS_YES);
        List<String> list = new ArrayList<>();
        for (SysMenu sysMenu : sysMenus) {
            list.add(sysMenu.getMenuUrl());
        }
        return list;
    }
    @Override
    public List<SysRole> queryAllRole(){
        Example example = new Example(SysRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleType", SysCodeConstant.ROLE_TYPE_BACK)
        .andEqualTo("status",SysCodeConstant.STATUS_YES);
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(example);
        return sysRoles;

    }
    //用户登陆
    @Override
    public SysUser login(String userName, String passWord, String id) {
        if (!ValidUtil.valid(userName)) return null;
        if (!ValidUtil.valid(passWord)) return null;
        SysUser sysUser = sysUserMapper.querySysUserByUserName(userName,null,null);
        if (sysUser == null) {
            throw new ParameterException("账号/密码有误");
        }
        if (!sysUser.getUserPwd().equals(PasswordUtil.md5(passWord))) {
            throw new ParameterException("账号/密码有误");
        }
        if(!sysUser.getUserType().equals(SysCodeConstant.USER_TYPE_BACK)){
            throw new ParameterException("该用户不是系统用户");
        }
        if(sysUser.getStatus().equals(SysCodeConstant.STATUS_NO)){
            throw new ParameterException("该用户已被删除");
        }
        List<SysRole> sysRoles = sysUserMapper.queryUserRolesByUserId(sysUser.getUserId(), SysCodeConstant
                .ROLE_TYPE_BACK, SysCodeConstant.STATUS_YES);
        if(sysRoles==null || sysRoles.size()<1){
            throw new ParameterException("该用户没有系统权限");
        }
        UserProfile userProfile = new UserProfile();
        BeanUtils.copyProperties(sysUser, userProfile);
        userProfile.setSysRoles(sysRoles);
        RequestHolder.getHttpSession().setAttribute("CURRENT_USER_PROFILE_KEY", userProfile);
        SecurityUtils.getSubject().login(new UsernamePasswordToken(sysUser.getLoginId(), sysUser.getUserPwd()));

        //写入登陆日志
        SysLoginLog sysLoginLog=new SysLoginLog();
        sysLoginLog.setSysUuid(RandomUtil.uuid());
        sysLoginLog.setUserId(sysUser.getUserId());
        sysLoginLog.setIp(id);
        sysLoginLog.setLoginTime(new Date());
        sysLoginLog.setStatus(sysUser.getStatus());
        sysLoginLog.setInsertTime(new Date());
        sysLoginLog.setInsertUserId("SYSTEM");
        sysLoginLogMapper.insert(sysLoginLog);
        return null;
    }

    /*查询用户列表*/
    @Override
    public List<Map<String, Object>> queryUserAndRole(Map<String, Object> map) {
        map.put("status", SysCodeConstant.STATUS_YES);
        map.put("userType", SysCodeConstant.USER_TYPE_BACK);
        List<Map<String, Object>> mapList = sysUserMapper.queryUsersAndRole(map);
        List<Map<String, Object>> retList = new ArrayList<>();
        for (int i = 0; i < mapList.size(); i++) {
            Map<String, Object> stringObjectMap = mapList.get(i);
            String userId = stringObjectMap.get("userId").toString();
            String roleNames = sysUserMapper.queryRoleById(userId);
            stringObjectMap.put("roleNames", roleNames);
            retList.add(stringObjectMap);
        }
        return retList;
    }

    //新增用户
    @Transactional
    @Override
    public String saveUser(Map<String, Object> reqMap, UserProfile userProfile) {
        SysUser sysUser = new SysUser();
        Bean2MapUtil.transMap2Bean2(reqMap, sysUser);
        if(sysUser.getLoginId()==null || sysUser.getLoginId().equals("")){
            throw new ParameterException("账号为空");
        }
        if(sysUser.getLoginId().length()>36){
            throw new ParameterException("账号过长");
        }
        if(sysUser.getUserName()==null || sysUser.getUserName().equals("")){
            throw new ParameterException("用户姓名为空");
        }
        if(sysUser.getUserName().length()>36){
            throw new ParameterException("用户姓名过长");
        }
        /*判断用户名是否已存在*/
        SysUser osysUser = sysUserMapper.querySysUserByUserName(sysUser.getLoginId(), "", "");
        if (!ValidUtil.valid(osysUser)) {
            throw new ParameterException("用户名已存在");
        }
        String userId = RandomUtil.uuid();
        sysUser.setUserId(userId);
        //密码默认为123456   密码md5 加密
        sysUser.setUserPwd(PasswordUtil.md5("123456"));
        sysUser.setStatus(SysCodeConstant.STATUS_YES);
        sysUser.setUserType(SysCodeConstant.USER_TYPE_BACK);
        sysUser.setInsertTime(new Date());
        sysUser.setInsertUserId(userProfile.getUserId());
        sysUserMapper.insert(sysUser);
        /*插入角色关联*/
        List<String> roleIds = (List<String>) reqMap.get("roleIds");
        if (roleIds.size() < 1) throw new ParameterException("角色ID不能为空");
        for (int i = 0; i < roleIds.size(); i++) {
            SysRUserRole sysRUserRole = new SysRUserRole();
            sysRUserRole.setStatus(SysCodeConstant.STATUS_YES);
            sysRUserRole.setRoleId(roleIds.get(i));
            sysRUserRole.setUserId(userId);
            sysRUserRole.setSysUuid(RandomUtil.uuid());
            sysRUserRole.setInsertTime(new Date());
            sysRUserRole.setInsertUserId(userProfile.getUserId());
            sysRUserRoleMapper.insert(sysRUserRole);
        }
        return null;
    }

    //修改用户
    @Transactional
    @Override
    public String UpdateUser(Map<String, Object> reqMap, UserProfile userProfile) {
        SysUser sysUser = new SysUser();
        Bean2MapUtil.transMap2Bean2(reqMap, sysUser);
        if (!ValidUtil.valid(sysUser.getUserId())) {
            throw new ParameterException("用户ID为空");
        }
        if(sysUser.getUserName()==null || sysUser.getUserName().equals("")){
            throw new ParameterException("用户姓名为空");
        }
        if(sysUser.getUserName().length()>36){
            throw new ParameterException("用户姓名过长");
        }
        /*判断用户名是否已存在*/
        SysUser osysUser = sysUserMapper.querySysUserByUserName(sysUser.getLoginId(), "", "");
        if (!ValidUtil.valid(osysUser)) {
            throw new ParameterException("用户名已存在");
        }
        sysUser.setUpdateTime(new Date());
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        //修改角色关联 先禁用原来的 在插入新的
        List<String> roleIds = (List<String>) reqMap.get("roleIds");
        if (roleIds.size() < 1) throw new ParameterException("角色ID不能为空");
        Example example = new Example(SysRUserRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", sysUser.getUserId());
        SysRUserRole sysRUserRoleOld=new SysRUserRole();
        sysRUserRoleOld.setUserId(sysUser.getUserId());
        sysRUserRoleOld.setStatus(SysCodeConstant.STATUS_NO);
        sysRUserRoleMapper.updateByExampleSelective(sysRUserRoleOld,example);
        for (int i = 0; i < roleIds.size(); i++) {
            SysRUserRole sysRUserRole = new SysRUserRole();
            sysRUserRole.setStatus(SysCodeConstant.STATUS_YES);
            sysRUserRole.setRoleId(roleIds.get(i));
            sysRUserRole.setUserId(sysUser.getUserId());
            sysRUserRole.setSysUuid(RandomUtil.uuid());
            sysRUserRole.setInsertTime(new Date());
            sysRUserRole.setUpdateTime(new Date());
            sysRUserRole.setInsertUserId(userProfile.getUserId());
            sysRUserRoleMapper.insert(sysRUserRole);
        }
        return null;
    }

    //删除用户（改变用户在用状态）
    @Override
    public String deleteUser(String userId, UserProfile userProfile) {
        if(!ValidUtil.valid(userId)){
            throw new ParameterException("用户数据为空");
        }
        SysUser sysUser = new SysUser();
        SysRUserRole sysRUserRole = new SysRUserRole();
        sysUser = sysUserMapper.selectByPrimaryKey(userId);
        if (sysUser.getStatus().equals(SysCodeConstant.STATUS_NO)) {
            sysUser.setStatus(SysCodeConstant.STATUS_YES);
            sysRUserRole.setStatus(SysCodeConstant.STATUS_YES);
        } else {
            sysUser.setStatus(SysCodeConstant.STATUS_NO);
            sysRUserRole.setStatus(SysCodeConstant.STATUS_NO);
        }
        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", sysUser.getUserId());
        sysUser.setUpdateTime(new Date());
        sysUserMapper.updateByExampleSelective(sysUser, example);
        //删除用户关联角色
        Example example1 = new Example(SysRUserRole.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("userId", sysUser.getUserId());
        sysRUserRole.setUpdateTime(new Date());
        sysRUserRoleMapper.updateByExampleSelective(sysRUserRole, example1);
        return null;
    }

    /*重置密码*/
    @Override
    public String restPassWord(String userId, String passWord, UserProfile userProfile) {
        if (!ValidUtil.valid(userId)) throw new ParameterException("用户ID为空");
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setUserPwd(PasswordUtil.md5("123456"));
        sysUser.setUpdateTime(new Date());
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        return null;
    }

    /*查询角色列表*/
    @Override
    public List<SysRole> queryRoles(Map<String, Object> roleName) {
        roleName.put("status", SysCodeConstant.STATUS_YES);
        return sysRoleMapper.queryRoles(roleName);
    }

    @Override
    public String saveRole(String roleName, String roleType, UserProfile userProfile) {
        if(roleName==null || roleName.equals("")){
            throw new ParameterException("角色名称为空");
        }
        if(roleName.length()>36){
            throw new ParameterException("角色名称过长");
        }
        Example example = new Example(SysRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleName", roleName).andEqualTo("roleType",roleType);
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(example);
        if(sysRoles.size()>0){
            throw new ParameterException("角色名重复");
        }
        SysRole sysRole = new SysRole();
        sysRole.setRoleName(roleName);
        sysRole.setRoleId(RandomUtil.uuid());
        sysRole.setRoleType(roleType);
        sysRole.setInsertTime(new Date());
        sysRole.setInsertUserId(userProfile.getUserId());
        sysRole.setStatus(SysCodeConstant.STATUS_YES);
        sysRoleMapper.insert(sysRole);
        return null;
    }

    @Transactional
    @Override
    public String deleteRole(String roleId, UserProfile userProfile) {
        Example example = new Example(SysRUserRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", roleId).andEqualTo("status", SysCodeConstant.STATUS_YES);
        int countByExample = sysRUserRoleMapper.selectCountByExample(example);
        if (countByExample > 0) {
            throw new ParameterException("该角色下有用户不允许被删除");
        }
        /*SysRole sysRole=new SysRole();
        sysRole.setStatus(SysCodeConstant.STATUS_NO);
        Example example1=new Example(SysRole.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("roleId",roleId);
        sysRoleMapper.updateByExampleSelective(sysRole,example1);*/
        //删除无效的用户角色关联
        Example example1 = new Example(SysRUserRole.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("roleId", roleId);
        SysRUserRole sysRUserRole=new SysRUserRole();
        sysRUserRole.setStatus(SysCodeConstant.STATUS_NO);
        sysRUserRole.setRoleId(roleId);
        sysRUserRole.setUpdateTime(new Date());
        sysRUserRoleMapper.updateByExampleSelective(sysRUserRole,example1);
        //删除角色的菜单关联
        SysRRoleMenu sysRRoleMenu=new SysRRoleMenu();
        sysRRoleMenu.setStatus(SysCodeConstant.STATUS_NO);
        sysRRoleMenu.setRoleId(roleId);
        sysRRoleMenu.setUpdateTime(new Date());
        Example example2 = new Example(SysRUserRole.class);
        Example.Criteria criteria2 = example2.createCriteria();
        criteria2.andEqualTo("roleId", roleId);
        sysRRoleMenuMapper.updateByExampleSelective(sysRRoleMenu,example2);
        //删除角色
        SysRole sysRole=new SysRole();
        sysRole.setStatus(SysCodeConstant.STATUS_NO);
        sysRole.setRoleId(roleId);
        sysRole.setUpdateTime(new Date());
        sysRoleMapper.updateByPrimaryKeySelective(sysRole);
        return null;
    }

    @Override
    public Map<String, Object> queryRoleHasMenuId(String roleId, String roleType) {
        Map retMap = new HashMap();
        //查询出角色类型的所有的菜单
        if (roleType.equals(SysCodeConstant.ROLE_TYPE_BACK)) {
            //查询后端的菜单列表
            List<Map<String, Object>> mapList = sysMenuMapper.queryMenuByMenuType(SysCodeConstant.MENU_TYPE_BACK,
                    SysCodeConstant.STATUS_YES);
            retMap.put("allMenus", mapList);
        } else if (roleType.equals(SysCodeConstant.ROLE_TYPE_BEFROE)) {
            //查询前端的菜单列表
            List<Map<String, Object>> mapList = sysMenuMapper.queryMenuByMenuType(SysCodeConstant.MENU_TYPE_BEFORE,
                    SysCodeConstant.STATUS_YES);
            retMap.put("allMenus", mapList);
        } else {
            LOGGER.info("错误的菜单类型");
            throw new ParameterException("错误的菜单类型");
        }
        List<String> strings = sysRoleMapper.queryRoleHasMenuId(roleId, SysCodeConstant.STATUS_YES);
        retMap.put("roleMenus", strings);
        return retMap;
    }

    @Override
    public String alterRoleMenus(List<String> menuId, String roleId, UserProfile userProfile) {
        if (!ValidUtil.valid(roleId)) {
            throw new ParameterException("角色ID为空");
        }
        //删除原来的关联菜单
        sysRRoleMenuMapper.updateMenuRoleStatus(roleId, SysCodeConstant.STATUS_NO);
        //插入新的菜单列表
        for (int i = 0; i < menuId.size(); i++) {
            SysRRoleMenu sysRRoleMenu = new SysRRoleMenu();
            sysRRoleMenu.setMenuId(menuId.get(i));
            sysRRoleMenu.setSysUuid(RandomUtil.uuid());
            sysRRoleMenu.setRoleId(roleId);
            sysRRoleMenu.setStatus(SysCodeConstant.STATUS_YES);
            sysRRoleMenu.setInsertTime(new Date());
            sysRRoleMenu.setInsertUserId(userProfile.getUserId());
            sysRRoleMenu.setUpdateTime(new Date());
            sysRRoleMenuMapper.insert(sysRRoleMenu);
        }
        return null;
    }

    @Override
    public Map<String, Object> queryMenusOnMenuType() {
        Map<String, Object> map = new HashMap<>();
        List<SysMenu> sysMenusBack = sysMenuMapper.queryMenusOnType(SysCodeConstant.MENU_TYPE_BACK, SysCodeConstant
                .STATUS_YES);
        List<SysMenu> sysMenusBefore = sysMenuMapper.queryMenusOnType(SysCodeConstant.MENU_TYPE_BEFORE, SysCodeConstant
                .STATUS_YES);
        map.put("backMenu", sysMenusBack);
        map.put("beforMenu", sysMenusBefore);
        return map;
    }

    @Override
    public String saveMenu(SysMenu sysMenu,String type, UserProfile userProfile) {
        int i = sysMenuMapper.queryCountMenuByMenuName(sysMenu.getMenuName());
        if (i > 0) {
            LOGGER.info("菜单名称已经存在");
            throw new ParameterException("菜单已经存在");
        }
        if(sysMenu.getMenuName().length()>16){
            throw new ParameterException("菜单名称过长");
        }
        if(sysMenu.getMenuUrl().length()>16){
            throw new ParameterException("菜单链接过长");
        }
        SysMenu pSysMenu = new SysMenu();
        if(type.equals("root")){
            int i1 = sysMenuMapper.countMenusByMenuType(sysMenu.getMenuType());
            if(i1>0){
                throw new ParameterException("已存在该类型菜单，无法添加");
            }
        }else{
            Example example = new Example(SysMenu.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("menuId", sysMenu.getParentMenuId());
            pSysMenu = sysMenuMapper.selectByExample(example).get(0);
            sysMenu.setMenuType(pSysMenu.getMenuType());
        }
        sysMenu.setMenuId(RandomUtil.uuid());
        sysMenu.setInsertTime(new Date());
        sysMenu.setInsertUserId(userProfile.getUserId());
        sysMenuMapper.insert(sysMenu);
        return null;
    }

    @Override
    public String updateMenu(SysMenu sysMenu, UserProfile userProfile) {
        int i = sysMenuMapper.queryCountMenuByMenuName(sysMenu.getMenuName());
        if (i > 0) {
            LOGGER.info("菜单名称已经存在");
            throw new ParameterException("菜单已经存在");
        }
        if(sysMenu.getMenuName().length()>16){
            throw new ParameterException("菜单名称过长");
        }
        if(sysMenu.getMenuUrl().length()>16){
            throw new ParameterException("菜单连接过长");
        }
        if(sysMenu.getMenuId()=="" || sysMenu.getMenuId()==null){
            throw new ParameterException("菜单Id为空");
        }
        sysMenu.setUpdateTime(new Date());
        sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
        return null;
    }

    @Override
    public String deleteMenu(String id,String type, UserProfile userProfile) {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setMenuId(id);
        SysMenu sysMenu1 = sysMenuMapper.selectByPrimaryKey(id);
        if (sysMenu1.getStatus().equals(SysCodeConstant.STATUS_YES)) {
            if(type.equals("on")) throw new ParameterException("该菜单已经启用，无法操作");
            sysMenu.setStatus(SysCodeConstant.STATUS_NO);
            //判断菜单是否有有效角色
            Example example = new Example(SysRRoleMenuMapper.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("menuId", sysMenu.getMenuId())
                    .andEqualTo("stauts",SysCodeConstant.STATUS_YES);
            List<SysRRoleMenu> sysRRoleMenus = sysRRoleMenuMapper.selectByExample(example);
            if(sysRRoleMenus.size()>0){
                throw new ParameterException("该菜单已被绑定角色，不能禁用");
            }
            //判断是否有 有效的子菜单
            Example example1 = new Example(SysMenu.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("parentMenuId", sysMenu.getMenuId())
            .andEqualTo("status",SysCodeConstant.STATUS_YES);
            List<SysMenu> sysMenus = sysMenuMapper.selectByExample(example1);
            if(sysMenus.size()>0){
                throw new ParameterException("该菜单下有有效的子菜单，不能禁用");
            }
        } else {
            if(type.equals("off")) throw new ParameterException("该菜单已经禁用，无法操作");
            sysMenu.setStatus(SysCodeConstant.STATUS_YES);
            //判断是否有无效的父菜单
            Example example = new Example(SysMenu.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("menuId", sysMenu1.getParentMenuId())
                    .andEqualTo("status",SysCodeConstant.STATUS_NO);
            List<SysMenu> sysMenus = sysMenuMapper.selectByExample(example);
            if(sysMenus.size()>0){
                throw new ParameterException("父菜单为无效，不能直接启用子菜单");
            }
        }
        sysMenu.setUpdateTime(new Date());
        sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
        return null;
    }

    @Override
    public List<SysCodeType> queryCodeType(Map<String, Object> map) {
        NPageHelper.startPage(map);
        return sysCodeTypeMapper.queryCodeTypeByCodeName(map);
    }

    @Override
    public String deleteCodeType(List<String> codeTypeId, UserProfile userProfile) {
        int sun = sysCodeTypeMapper.countCodeInfoOnType(codeTypeId);
        if (sun > 0) {
            throw new ParameterException("码类下存在码值，不能删除");
        }
        int deleteCodeTypeCount = sysCodeTypeMapper.deletCodeTypeByIds(codeTypeId);
        return null;
    }

    @Override
    public String updateCodeTypeStatusById(String id, UserProfile userProfile) {
        //查询该码类当前状态
        SysCodeType sysCodeType = sysCodeTypeMapper.selectByPrimaryKey(id);
        String status = SysCodeConstant.STATUS_YES;
        if (sysCodeType.getStatus().equals(status)) {
            status = SysCodeConstant.STATUS_NO;
            //查询该码类下是否有码值
            int i = sysCodeInfoMapper.queryCodeInfoByTypeId(id, SysCodeConstant.STATUS_YES);
            if (i > 0) {
                LOGGER.error("不能禁用码类，该码类下存在有效的码值");
                throw new ParameterException("码类下存在码值，不能禁用");
            }
        }
        sysCodeTypeMapper.updateStatusById(id, status);
        return null;
    }

    @Override
    public String saveCodeType(SysCodeType sysCodeType, String type, UserProfile userProfile) {
        if (type.equals("save")) {
            SysCodeType sysCodeType1 = sysCodeTypeMapper.selectByPrimaryKey(sysCodeType.getCodeTypeId());
            if (sysCodeType1 != null) throw new ParameterException("码类编码已经存在");
            sysCodeType.setInsertTime(new Date());
            sysCodeType.setInsertUserId(userProfile.getUserId());
            sysCodeTypeMapper.insert(sysCodeType);
        } else {
            sysCodeType.setUpdateTime(new Date());
            sysCodeTypeMapper.updateByPrimaryKeySelective(sysCodeType);
        }
        return null;
    }

    @Override
    public List<SysCodeInfo> queryCodeInfo(Map<String, Object> codeTypeId) {
        return sysCodeInfoMapper.queryCodeInfo(codeTypeId);
    }

    @Override
    public String deletCodeInfo(List<String> codeId, UserProfile userProfile) {
        int deletCodeInfoCount = sysCodeInfoMapper.deletCodeInfoByIds(codeId);
        return null;
    }

    @Override
    public String updateCodeInfoStatusById(String id, UserProfile userProfile) {
        //查询该码值当前状态1111
        SysCodeInfo sysCodeInfo = sysCodeInfoMapper.selectByPrimaryKey(id);
        String status = SysCodeConstant.STATUS_YES;
        if (sysCodeInfo.getStatus().equals(status)) {
            status = SysCodeConstant.STATUS_NO;
        }
        sysCodeInfoMapper.updateStatusById(id, status);
        return null;
    }

    @Override
    public String saveCodeInfo(SysCodeInfo sysCodeInfo, String type, UserProfile userProfile) {
        if (type.equals("save")) {
            sysCodeInfo.setInsertTime(new Date());
            sysCodeInfo.setInsertUserId(userProfile.getUserId());
            sysCodeInfoMapper.insert(sysCodeInfo);
        } else {
            sysCodeInfo.setUpdateTime(new Date());
            sysCodeInfoMapper.updateByPrimaryKeySelective(sysCodeInfo);
        }
        return null;
    }
    
    @Override
    public String alterPwd(String userId, String oldPwd, String nPwd, String retPwd, UserProfile userProfile) {
        //验证旧密码是否正确
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
        if(!PasswordUtil.md5(oldPwd).equals(sysUser.getUserPwd())){
            throw new ParameterException("原密码校验失败！");
        }
        if(!retPwd.equals(nPwd)){
            throw new ParameterException("两次新密码输入不一致！");
        }
        SysUser sysUser1= new SysUser();
        sysUser1.setUserId(userId);
        sysUser1.setUserPwd(PasswordUtil.md5(nPwd));
        sysUser1.setUpdateTime(new Date());
        sysUserMapper.updateByPrimaryKeySelective(sysUser1);
        return null;
    }

    @Override
    public  String getCodeValueByName(String str,String code){
        Map<String,Object> map=new HashMap<>();
        map.put("status", CodeEnum.getStatusByCode(code));
        map.put("codeTypeId",code );
        List<SysCodeInfo> list= sysCodeInfoMapper.queryCodeInfo(map);
        for (SysCodeInfo node:list) {
            if(node.getCodeInfoName().equals(str)){
                return node.getCodeInfoValue();
            }
        }
        return "";
    }

    @Override
    public String getCodeNameByValue(String code) {
        return sysCodeInfoMapper.getCodeNameByValue(code);
    }
}
