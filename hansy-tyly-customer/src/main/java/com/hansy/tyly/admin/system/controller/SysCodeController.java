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
import com.hansy.tyly.admin.dao.model.SysCodeInfo;
import com.hansy.tyly.admin.dao.model.SysCodeType;
import com.hansy.tyly.admin.system.service.SysUserRoleMenuService;
import com.hansy.tyly.core.command.CryptoCmd;

@Api(description = "码表管理")
@RestController
@RequestMapping("/api/code")
public class SysCodeController extends BaseController {

    @Autowired
    private SysUserRoleMenuService sysUserRoleMenuService;

    @ApiOperation(value = "查询码表-码类", notes = "{\"codeTypeName\":\"userType\"}")
    @PostMapping("/queryCodeType")
    public CryptoCmd queryCodeType(CryptoCmd cryptoCmd) {
        Map<String, Object> objectMap = cryptoCmd.getParams();
        List<SysCodeType> sysCodeTypes = sysUserRoleMenuService.queryCodeType(objectMap);
        cryptoCmd.setOut(sysCodeTypes);
        return cryptoCmd;
    }

    @ApiOperation(value = "删除码表-码类", notes = "{\"codeTypeIds\":[\"1\",\"2\"]}")
    @PostMapping("/deletCodeType")
    public CryptoCmd deletCodeType(CryptoCmd cryptoCmd) {
        List<String> codeTypeIds = cryptoCmd.getInArrayString("codeTypeIds");
        sysUserRoleMenuService.deleteCodeType(codeTypeIds, super.getCurrentUserProfile());
        return cryptoCmd;
    }

    @ApiOperation(value = "启用禁用-码类", notes = "{\"codeTypeId\":\"1\"}")
    @PostMapping("/updateCodeTypeStatus")
    public CryptoCmd updateCodeTypeStatus(CryptoCmd cryptoCmd) {
        String id = cryptoCmd.getInString("codeTypeId");
        sysUserRoleMenuService.updateCodeTypeStatusById(id, super.getCurrentUserProfile());
        return cryptoCmd;
    }

    @ApiOperation(value = "新增码类-码类", notes = "{sysCodeType:{\"codeTypeName\":\"测试1\",\"codeTypeId\":\"1\"," +
            "\"status\":\"1\"},\"type\":\"save/edit\"}")
    @PostMapping("/saveCoeType")
    public CryptoCmd saveCoeType(CryptoCmd cryptoCmd) {
        SysCodeType sysCodeType = cryptoCmd.getInObject("sysCodeType", SysCodeType.class);
        String type=cryptoCmd.getInString("type");
        sysUserRoleMenuService.saveCodeType(sysCodeType,type, super.getCurrentUserProfile());
        return cryptoCmd;
    }

    @ApiOperation(value = "查询码表-码值",notes = "{\"codeTypeId\":\"userType\"}")
    @PostMapping("/queryCodeValue")
    public CryptoCmd queryCodeValue(CryptoCmd cryptoCmd) {
        Map<String, Object> params = cryptoCmd.getParams();
        List<SysCodeInfo> list = sysUserRoleMenuService.queryCodeInfo(params);
        cryptoCmd.setOut(list);
        return cryptoCmd;
    }

    @ApiOperation(value = "删除码表-码值", notes = "{\"codeInfoIds\":[\"1\",\"2\"]}")
    @PostMapping("/deletCodeInfo")
    public CryptoCmd deletCodeInfo(CryptoCmd cryptoCmd) {
        List<String> codeInfoIds = cryptoCmd.getInArrayString("codeInfoIds");
        sysUserRoleMenuService.deletCodeInfo(codeInfoIds, super.getCurrentUserProfile());
        return cryptoCmd;
    }

    @ApiOperation(value = "启用禁用-码值", notes = "{\"codeInfoId\":\"1\"}")
    @PostMapping("/updateCodeInfoStatus")
    public CryptoCmd updateCodeInfoStatus(CryptoCmd cryptoCmd) {
        String codeInfoId = cryptoCmd.getInString("codeInfoId");
        sysUserRoleMenuService.updateCodeInfoStatusById(codeInfoId, super.getCurrentUserProfile());
        return cryptoCmd;
    }

    @ApiOperation(value = "新增码值-码值",notes = "{sysCodeInfo:{\"codeInfoName\":\"测试1\",\"codeInfoValue\":\"1\"," +
            "\"status\":\"1\",\"codeTypeId\":\"1\"},\"type\":\"save/edit\"}")
    @PostMapping("/saveCodeInfo")
    public CryptoCmd saveCodeInfo(CryptoCmd cryptoCmd) {
        SysCodeInfo sysCodeInfo = cryptoCmd.getInObject("sysCodeInfo", SysCodeInfo.class);
        String type=cryptoCmd.getInString("type");
        sysUserRoleMenuService.saveCodeInfo(sysCodeInfo,type, super.getCurrentUserProfile());
        return cryptoCmd;
    }
}
