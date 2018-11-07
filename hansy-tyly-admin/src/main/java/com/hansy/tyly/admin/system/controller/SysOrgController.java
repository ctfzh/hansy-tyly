package com.hansy.tyly.admin.system.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hansy.tyly.admin.base.bean.UserProfile;
import com.hansy.tyly.admin.base.controller.BaseController;
import com.hansy.tyly.admin.dao.model.SysOrg;
import com.hansy.tyly.admin.system.service.ISysOrgService;
import com.hansy.tyly.core.command.CryptoCmd;

@Api(description = "机构管理")
@RestController
@RequestMapping("/api/org")
public class SysOrgController extends BaseController {

	@Resource
	private ISysOrgService sysOrgService;
	
	@ApiOperation(value = "查询机构列表")
	@PostMapping("/queryOrgs")
	public CryptoCmd queryOrgs(CryptoCmd cryptoCmd){
		List<SysOrg> orgList = sysOrgService.queryOrgList();
		cryptoCmd.setOut(orgList);
		return cryptoCmd;
	}
	
	/**
     * 新增机构  type: root 添加跟机构  son：添加子机构
     * @return
     */
    @ApiOperation(value = "添加机构")
    @PostMapping("/saveOrg")
    public CryptoCmd saveOrg(CryptoCmd cryptoCmd){
    	UserProfile userProfile= super.getCurrentUserProfile();
    	SysOrg sysOrg=cryptoCmd.getInObject("sysOrg",SysOrg.class);
    	sysOrg.setInsertUserId(userProfile.getUserId());
        sysOrgService.saveOrg(sysOrg);
        return cryptoCmd;
    }

    /**
     * 编辑机构
     * @return
     */
    @ApiOperation(value = "编辑机构")
    @PostMapping("/updateOrg")
    public CryptoCmd updateOrg(CryptoCmd cryptoCmd){
    	SysOrg sysOrg=cryptoCmd.getInObject("sysOrg",SysOrg.class);
    	sysOrgService.updateOrg(sysOrg);
        return cryptoCmd;
    }

    /**
     * 删除机构
     * @return
     */
    @ApiOperation(value = "改变机构状态",notes = "{\"menuId\":\"2\",\"type\":\"on/off\"}")
    @PostMapping("/deleteOrg")
    public CryptoCmd deleteOrg(CryptoCmd cryptoCmd){
    	SysOrg sysOrg=cryptoCmd.getInObject("sysOrg",SysOrg.class);
        sysOrgService.deleteOrg(sysOrg);
        return cryptoCmd;
    }
	
}
