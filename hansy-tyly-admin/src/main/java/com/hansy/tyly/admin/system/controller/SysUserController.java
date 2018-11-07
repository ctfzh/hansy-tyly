package com.hansy.tyly.admin.system.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hansy.tyly.admin.base.controller.BaseController;
import com.hansy.tyly.admin.dao.model.SysRole;
import com.hansy.tyly.admin.system.service.SysUserRoleMenuService;
import com.hansy.tyly.core.command.CryptoCmd;
import com.hansy.tyly.core.excepiton.ServiceException;
import com.hansy.tyly.core.helper.ExportExcelHelper;
import com.hansy.tyly.core.helper.FileNameHelper;

@Api(description = "系统用户管理")
@RestController
@RequestMapping("/api/user")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserRoleMenuService sysUserRoleMenuService;

    /**
     * 系统用户管理列表查询(条件查询)
     *
     * @return
     */
    @ApiOperation(value = "系统用户列表条件查询", notes = "{\"loginId\":\"admin\",\"roleName\":\"管理员\",\"userName\":\"管理员\"," +
            "\"status\":\"\"}")
    @PostMapping("/queryUser")
    public CryptoCmd queryUser(CryptoCmd cryptoCmd) throws IOException {
        Map<String, Object> params = cryptoCmd.getParams();
        List<Map<String, Object>> mapList = sysUserRoleMenuService.queryUserAndRole(params);
        cryptoCmd.setOut(mapList);
        return cryptoCmd;
    }

    @GetMapping("/export")
    public void export(CryptoCmd cryptoCmd, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> params = cryptoCmd.getParams();
        List<Map<String, Object>> mapList = sysUserRoleMenuService.queryUserAndRole(params);

        String title = "用户列表";
        String[] headers = "用户姓名,用户姓名2".split(",");
        String[] cols = "userName,userName".split(",");
        String[] widths = "200,150".split(",");
        Workbook workbook = ExportExcelHelper.export(title, headers, widths, cols, mapList);

        String filename = FileNameHelper.encode( request, title);
        OutputStream outputStream = null;
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition","attachment; filename="+ filename +".xls" );
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
        } catch (Exception e) {
            throw new ServiceException("导出Excel失败", e);
        } finally {
            if ( outputStream != null ) try {
                outputStream.close();
            } catch (Exception e) {
            }
        }

    }

    /**
     * 系统用户管理 添加用户
     *
     * @param
     * @return
     */
    @ApiOperation(value = "系统用户保存", notes = "{\"roleIds\":[\"1\",\"2\"],\"loginId\":\"test2\",\"userName\":\"测试\"}")
    @PostMapping("/saveUser")
    public CryptoCmd saveUser(CryptoCmd cryptoCmd) {
        Map<String, Object> params = cryptoCmd.getParams();
        sysUserRoleMenuService.saveUser(params, super.getCurrentUserProfile());
        return cryptoCmd;
    }

    /**
     * 系统用户管理 编辑用户
     *
     * @param
     * @return
     */
    @ApiOperation(value = "系统用户编辑", notes = "{\"userId\":\"1\",\"roleIds\":[\"1\",\"2\"],\"userName\":\"测试改\",}")
    @PostMapping("/updateUser")
    public CryptoCmd updateUser(CryptoCmd cryptoCmd) {
        Map<String, Object> params = cryptoCmd.getParams();
        ArrayList<String> roleIds = new ArrayList<>();
        roleIds.add(cryptoCmd.getInString("roleIds"));
        params.put("roleIds", roleIds);
        sysUserRoleMenuService.UpdateUser(params, super.getCurrentUserProfile());
        return cryptoCmd;
    }

    /**
     * 系统用户管理 删除用户
     *
     * @param
     * @return
     */
    @ApiOperation(value = "禁用启用", notes = "{\"userId\":\"7817efd435754a5a806989cce7dfa4ee\"}")
    @PostMapping("/deleteUser")
    public CryptoCmd deleteUser(CryptoCmd cryptoCmd) {
        String userId=cryptoCmd.getInString("userId");
        sysUserRoleMenuService.deleteUser(userId, super.getCurrentUserProfile());
        return cryptoCmd;
    }

    @ApiOperation(value = "重置密码", notes = "{\"userId\":\"7817efd435754a5a806989cce7dfa4ee\"}")
    @PostMapping("/restPassWord")
    public CryptoCmd restPassWord(CryptoCmd cryptoCmd) {
        String userId=cryptoCmd.getInString("userId");
        sysUserRoleMenuService.restPassWord(userId, null, super.getCurrentUserProfile());
        return cryptoCmd;
    }
    @ApiOperation(value = "查询后端所有角色")
    @PostMapping("/queryAllRole")
    public CryptoCmd queryAllRole(CryptoCmd cryptoCmd) {
        List<SysRole> sysRoles = sysUserRoleMenuService.queryAllRole();
        cryptoCmd.setOut(sysRoles);
        return cryptoCmd;
    }
}
