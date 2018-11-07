package com.hansy.tyly.admin.cust.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hansy.tyly.admin.base.controller.BaseController;
import com.hansy.tyly.admin.cust.service.BusiCustAndOrgService;
import com.hansy.tyly.admin.dao.model.SysRole;
import com.hansy.tyly.admin.dao.model.SysUser;
import com.hansy.tyly.admin.dao.model.TBusiCust;
import com.hansy.tyly.admin.dao.model.TBusiOrg;
import com.hansy.tyly.core.command.CryptoCmd;

@Api(description = "账户管理")
@RestController
@RequestMapping("/api/busiCust")
public class BusiCustController extends BaseController{

    @Autowired
    private BusiCustAndOrgService busiCustAndOrgService;


    @ApiOperation(value = "条件查询客户", notes = "{\"custName\":\"admin\",\"orgName\":\"测试\"}")
    @PostMapping("/queryCusts")
    public CryptoCmd queryCust(CryptoCmd cryptoCmd){
        Map<String, Object> params = cryptoCmd.getParams();
        List<Map<String, Object>> list = busiCustAndOrgService.queryBusiCust(params);
        cryptoCmd.setOut(list);
        return cryptoCmd;
    }
    @ApiOperation(value = "新增客户", notes = "{tBusiCust:{\"orgId\":\"\"},sysUser:{\"loginId\":\"\",\"userName\":\"\"," +
            "\"userTel\":\"\",\"userDept\":\"\"},\"roleId\":\"\"}")
    @PostMapping("/saveCust")
    public CryptoCmd saveCust(CryptoCmd cryptoCmd){
        TBusiCust tBusiCust=cryptoCmd.getInObject("tBusiCust",TBusiCust.class);
        SysUser sysUser=cryptoCmd.getInObject("sysUser",SysUser.class);
        String roleId=cryptoCmd.getInString("roleId");
        busiCustAndOrgService.saveBusiCust(tBusiCust,sysUser,roleId,super.getCurrentUserProfile());
        return cryptoCmd;
    }

    @ApiOperation(value = "编辑客户", notes = "{sysUser:{\"userName\":\"\",\"userTel\":\"\",\"userDept\":\"\"," +
            "\"userId\":\"\"},\"roleId\":\"\",\"custId\":\"\",\"sysUuid\":\"\"}")
    @PostMapping("/editCust")
    public CryptoCmd editCust(CryptoCmd cryptoCmd){
        SysUser sysUser=cryptoCmd.getInObject("sysUser",SysUser.class);
        String roleId=cryptoCmd.getInString("roleId");
        String custId=cryptoCmd.getInString("custId");
        String sysUuid=cryptoCmd.getInString("sysUuid");
        busiCustAndOrgService.editBusiCust(sysUser,roleId,custId,sysUuid,super.getCurrentUserProfile());
        return cryptoCmd;
    }
    @ApiOperation(value = "删除客户", notes = "{\"userId\":\"\",\"custId\":\"\"}")
    @PostMapping("/delCust")
    public CryptoCmd delCust(CryptoCmd cryptoCmd){
        String userId=cryptoCmd.getInString("userId");
        String custId=cryptoCmd.getInString("custId");
        busiCustAndOrgService.delBusiCust(userId,custId,super.getCurrentUserProfile());
        return cryptoCmd;
    }
    @ApiOperation(value = "重置密码", notes = "{\"userId\":\"\"}")
    @PostMapping("/restPwd")
    public CryptoCmd restPwd(CryptoCmd cryptoCmd){
        String id= cryptoCmd.getInString("userId");
        busiCustAndOrgService.restPwd(id,null,super.getCurrentUserProfile());
        return cryptoCmd;
    }
    @ApiOperation(value = "查询角色")
    @PostMapping("/queryOrgRoles")
    public CryptoCmd queryRoles(CryptoCmd cryptoCmd){
        List<SysRole> sysRoles = busiCustAndOrgService.queryOrgRoles();
        cryptoCmd.setOut(sysRoles);
        return cryptoCmd;
    }

    @ApiOperation(value = "查询机构")
    @PostMapping("/queryOrgs")
    public CryptoCmd queryOrgs(CryptoCmd cryptoCmd){
        List<TBusiOrg> tBusiOrgs = busiCustAndOrgService.queryOrgs();
        cryptoCmd.setOut(tBusiOrgs);
        return cryptoCmd;
    }
}
