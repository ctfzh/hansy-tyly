package com.hansy.tyly.admin.base.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hansy.tyly.admin.base.service.CacheService;
import com.hansy.tyly.admin.dao.model.SysCodeInfo;
import com.hansy.tyly.admin.dao.model.SysMenu;
import com.hansy.tyly.admin.dao.model.SysRole;
import com.hansy.tyly.core.command.CryptoCmd;

@Api(description = "缓存接口")
@RestController
@RequestMapping("/api/cache")
public class CacheController extends BaseController{

    @Autowired private CacheService cacheService;

    @ApiOperation(value = "缓存码表", notes = "{\"codeTypes\": [\"State\", \"Edu\"]}")
    @PostMapping("/codes")
    public CryptoCmd cacheCodes(CryptoCmd cryptoCmd) {
        List<String> codeTypes = cryptoCmd.getInArrayString("codeTypes");
        if (codeTypes == null || codeTypes.isEmpty()) return cryptoCmd;
        Map<String, List<SysCodeInfo>> codeMap = this.cacheService.cacheCodes(codeTypes);
        cryptoCmd.setOut(codeMap);
        return cryptoCmd;
    }

    @ApiOperation(value = "缓存权限许可")
    @PostMapping("/authCodes")
    public CryptoCmd cacheAuthCodes(CryptoCmd cryptoCmd) {
        List<String> authCodes = this.cacheService.cacheAuthCodes(super.getCurrentLoginNo());

        List<String> roles = new ArrayList<>();
        for (SysRole sysRole : super.getCurrentUserProfile().getSysRoles()) {
            roles.add(sysRole.getRoleId());
        }
        cryptoCmd.setOut("roles", roles);
        cryptoCmd.setOut("perms", authCodes);

        return cryptoCmd;
    }

    @ApiOperation(value = "缓存菜单目录")
    @PostMapping("/cacheResources")
    public CryptoCmd cacheResources(CryptoCmd cryptoCmd) {
        List<SysMenu> resources = this.cacheService.cacheResources(super.getCurrentLoginNo());

        cryptoCmd.setOut("resources", resources);
        return cryptoCmd;
    }
}
