package com.hansy.tyly.admin.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hansy.tyly.admin.base.controller.BaseController;
import com.hansy.tyly.admin.dao.model.SysMenu;
import com.hansy.tyly.admin.system.service.SysUserRoleMenuService;
import com.hansy.tyly.core.command.CryptoCmd;

@Api(description = "菜单管理")
@RestController
@RequestMapping("/api/menu")
public class SysMenuController extends BaseController {
    @Autowired
    private SysUserRoleMenuService sysUserRoleMenuService;

    /**
     * 系统菜单查询
     * @return
     */
    @ApiOperation(value = "查询菜单列表")
    @PostMapping("/queryMenus")
    public CryptoCmd queryMenus(CryptoCmd cryptoCmd) {
        Map<String, Object> stringObjectMap = sysUserRoleMenuService.queryMenusOnMenuType();
        cryptoCmd.setOut(stringObjectMap);
        return cryptoCmd;
    }

    /**
     * 新增菜单  type: root 添加跟菜单  son：添加子菜单
     * @return
     */
    @ApiOperation(value = "添加菜单", notes = "{sysMenu:{\"menuName\":\"测试1\",\"menuIcon\":\"1\",\"menuOrder\":\"1\"," +
            "\"status\":\"00001001\",\"parentMenuId\":\"1\",\"menuUrl\":\"1\"},\"type\":\"root/son\"}")
    @PostMapping("/saveMenu")
    public CryptoCmd saveMenu(CryptoCmd cryptoCmd){
        SysMenu sysMenu=cryptoCmd.getInObject("sysMenu",SysMenu.class);
        String type=cryptoCmd.getInString("type");
        sysUserRoleMenuService.saveMenu(sysMenu,type,super.getCurrentUserProfile());
        return cryptoCmd;
    }

    /**
     * 编辑菜单
     * @return
     */
    @ApiOperation(value = "编辑菜单", notes = "{sysMenu:{\"menuName\":\"测试1\",\"menuIcon\":\"1\",\"menuOrder\":\"1\"," +
            "\"status\":\"00001001\",\"parentMenuId\":\"1\",\"menuUrl\":\"1\",\"menuId\":\"1\"}}")
    @PostMapping("/updateMenu")
    public CryptoCmd updateMenu(CryptoCmd cryptoCmd){
        SysMenu sysMenu=cryptoCmd.getInObject("sysMenu",SysMenu.class);
        sysUserRoleMenuService.updateMenu(sysMenu,super.getCurrentUserProfile());
        return cryptoCmd;
    }

    /**
     * 删除菜单
     * @return
     */
    @ApiOperation(value = "改变菜单状态",notes = "{\"menuId\":\"2\",\"type\":\"on/off\"}")
    @PostMapping("/deleteMenu")
    public CryptoCmd deleteMenu(CryptoCmd cryptoCmd){
        String id=cryptoCmd.getInString("menuId");
        String type=cryptoCmd.getInString("type");
        sysUserRoleMenuService.deleteMenu(id,type,super.getCurrentUserProfile());
        return cryptoCmd;
    }
}
