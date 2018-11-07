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
import com.hansy.tyly.admin.dao.model.TBusiOrg;
import com.hansy.tyly.admin.prod.service.CloudProdService;
import com.hansy.tyly.core.command.CryptoCmd;


@Api(description = "机构管理")
@RestController
@RequestMapping("/api/busiOrg")
public class BusiOrgController extends BaseController{

    @Autowired
    private BusiCustAndOrgService busiCustAndOrgService;
    @Autowired
    private CloudProdService cloudProdService;


    @ApiOperation(value = "条件查询机构", notes = "{\"orgName\":\"\",\"instDate\":\"\"}")
    @PostMapping("/queryOrg")
    public CryptoCmd queryOrg(CryptoCmd cryptoCmd){
        Map<String, Object> params = cryptoCmd.getParams();
        List<Map<String, Object>> mapList = busiCustAndOrgService.queryBusiOrg(params);
        cryptoCmd.setOut(mapList);
        return cryptoCmd;
    }

    @ApiOperation(value = "新增机构", notes = "{tBusiOrg:{\"orgName\":\"\",\"orgAddr\":\"\",\"orgIndustryType\":\"\"," +
            "\"orgTel\":\"\"}}")
    @PostMapping("/saveOrg")
    public CryptoCmd saveOrg(CryptoCmd cryptoCmd){
        TBusiOrg tBusiOrg=cryptoCmd.getInObject("tBusiOrg",TBusiOrg.class);
        busiCustAndOrgService.saveBusiOrg(tBusiOrg,super.getCurrentUserProfile());
        return cryptoCmd;
    }

    @ApiOperation(value = "编辑机构", notes = "{tBusiOrg:{\"orgName\":\"\",\"orgAddr\":\"\",\"orgIndustryType\":\"\"," +
            "\"orgTel\":\"\",\"orgId\":\"\"}}")
    @PostMapping("/editOrg")
    public CryptoCmd editOrg(CryptoCmd cryptoCmd){
        TBusiOrg tBusiOrg=cryptoCmd.getInObject("tBusiOrg",TBusiOrg.class);
        busiCustAndOrgService.editBusiOrg(tBusiOrg,super.getCurrentUserProfile());
        return cryptoCmd;
    }

    @ApiOperation(value = "启用禁用", notes = "{\"orgId\":\"\"}")
    @PostMapping("/delOrg")
    public CryptoCmd delOrg(CryptoCmd cryptoCmd){
        String id=cryptoCmd.getInString("orgId");
        busiCustAndOrgService.delBusiOrg(id,super.getCurrentUserProfile());
        return cryptoCmd;
    }
    @ApiOperation(value = "查询机构未关联所有产品", notes = "{\"orgId\":\"\",\"prodName\":\"\"}")
    @PostMapping("/queryOrgNoProd")
    public CryptoCmd queryOrgNoProd(CryptoCmd cryptoCmd){
        Map<String,Object> map=cryptoCmd.getParams();
        List<Map<String, Object>> mapList = cloudProdService.queryOrgNoProdList(map);
        cryptoCmd.setOut(mapList);
        return cryptoCmd;
    }

    @ApiOperation(value = "查询机构已关联产品", notes = "{\"orgId\":\"\",\"prodName\":\"\"}")
    @PostMapping("/queryOrgYesProd")
    public CryptoCmd queryOrgYesProd(CryptoCmd cryptoCmd){
        Map<String,Object> map=cryptoCmd.getParams();
        List<Map<String, Object>> mapList = cloudProdService.queryOrgYesProdList(map);
        cryptoCmd.setOut(mapList);
        return cryptoCmd;
    }

    @ApiOperation(value = "删除已绑定产品", notes = "{\"orgId\":\"\",\"prodId\":\"\"}")
    @PostMapping("/delOrgProd")
    public CryptoCmd delOrgProd(CryptoCmd cryptoCmd){
        String orgId=cryptoCmd.getInString("orgId");
        String prodId=cryptoCmd.getInString("prodId");
        cloudProdService.delOrgProd(orgId,prodId,super.getCurrentUserProfile());
        return cryptoCmd;
    }

    @ApiOperation(value = "绑定产品", notes = "{\"orgId\":\"\",\"prodIds\":\"[]\"}")
    @PostMapping("/saveOrgProd")
    public CryptoCmd saveOrgProd(CryptoCmd cryptoCmd){
        String orgId=cryptoCmd.getInString("orgId");
        List<String> prodIds = cryptoCmd.getInArrayString("prodIds");
        cloudProdService.saveOrgProd(orgId,prodIds,super.getCurrentUserProfile());
        return cryptoCmd;
    }
}
