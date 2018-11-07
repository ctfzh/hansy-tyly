package com.hansy.tyly.admin.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hansy.tyly.admin.base.controller.BaseController;
import com.hansy.tyly.admin.dao.model.SysRole;
import com.hansy.tyly.admin.system.service.SysUserRoleMenuService;
import com.hansy.tyly.core.command.CryptoCmd;

@Api(description = "角色管理")
@RestController
@RequestMapping("/api/role")
public class SysRoleController extends BaseController {

    @Autowired
    private SysUserRoleMenuService sysUserRoleMenuService;

    @ApiOperation(value = "角色列表查询", notes = "{\"roleName\":\"管理员\",\"status\":\"\"}")
    @PostMapping("/queryRoles")
    public CryptoCmd queryRoles(CryptoCmd cryptoCmd) {
        Map<String, Object> params = cryptoCmd.getParams();
        List<SysRole> sysRoles = sysUserRoleMenuService.queryRoles(params);
        cryptoCmd.setOut(sysRoles);
        return cryptoCmd;
    }

    @ApiOperation(value = "新增角色", notes = "{\"roleName\":\"测试12\",\"roleType\":\"10002001\"}")
    @PostMapping("/saveRole")
    public CryptoCmd saveRole(CryptoCmd cryptoCmd) {
        String roleName = cryptoCmd.getInString("roleName");
        String roleType = cryptoCmd.getInString("roleType");
        sysUserRoleMenuService.saveRole(roleName, roleType, super.getCurrentUserProfile());
        return cryptoCmd;
    }

    @ApiOperation(value = "启用和禁用", notes = "{\"roleId\":\"5e76dba56834459680340ec2c173fa6f\"}")
    @PostMapping("/deleteRole")
    public CryptoCmd deleteRole(CryptoCmd cryptoCmd) {
        String roleId = cryptoCmd.getInString("roleId");
        sysUserRoleMenuService.deleteRole(roleId, super.getCurrentUserProfile());
        return cryptoCmd;
    }


    @ApiOperation(value = "查询角色拥有菜单", notes = "{\"roleId\":\"1\",\"roleType\":\"10002001\"}")
    @PostMapping("/queryRoleHasMenuId")
    public CryptoCmd queryRoleHasMenuId(CryptoCmd cryptoCmd) {
        String roleId = cryptoCmd.getInString("roleId");
        String roleType = cryptoCmd.getInString("roleType");
        Map<String, Object> objectMap = sysUserRoleMenuService.queryRoleHasMenuId(roleId, roleType);
        cryptoCmd.setOut(objectMap);
        return cryptoCmd;
    }

    @ApiOperation(value = "更新角色拥有菜单", notes = "{\"menuIds\":[\"1\"],\"roleId\":\"1\"}")
    @PostMapping("/saveRoleMenuId")
    public CryptoCmd saveRoleMenuId(CryptoCmd cryptoCmd) {
        String roleId = cryptoCmd.getInString("roleId");
        List<String> menuIds = cryptoCmd.getInArrayString("menuIds");
        sysUserRoleMenuService.alterRoleMenus(menuIds, roleId, super.getCurrentUserProfile());
        return cryptoCmd;
    }


}
