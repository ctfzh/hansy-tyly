package com.hansy.tyly.admin.dealers.controller;

import com.github.pagehelper.PageInfo;
import com.hansy.tyly.admin.dao.model.TPubMerInfo;
import com.hansy.tyly.admin.dealers.dao.model.DistributorInfo;
import com.hansy.tyly.admin.dealers.service.DistributorInfoService;
import com.hansy.tyly.core.command.CryptoCmd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "平台经销商信息管理")
@RestController
@RequestMapping("/api/dealers/infos")
public class DistributorInfoPlatController {

    @Autowired
    private DistributorInfoService infoService;


    @ApiOperation(value = "经销商列表查看和搜索", notes = "搜索条件（可选）  \n{distributorName:\"经销商1\", startTime:\"2018-8-7\", endTime:\"2018-8-8\", distributorStatus:\"00001001\", distributorNo=\"\", staffName=\"\"}")
    @PostMapping("/list")
    public CryptoCmd list(CryptoCmd cryptoCmd) {
        Map<String, Object> params = cryptoCmd.getParams();
        params.put("loginId", SecurityUtils.getSubject().getPrincipal());
        List<DistributorInfo> distributorInfos = infoService.list(params);
        cryptoCmd.setOut(distributorInfos);
        return cryptoCmd;
    }

    @ApiOperation(value = "经销商详情信息查看", notes = "{distributorNo: \"19146f4699ee11e8871d98be94f9\"}")
    @PostMapping("/getOne")
    public CryptoCmd getOne(CryptoCmd cryptoCmd) {
        String distributorNo = cryptoCmd.getInString("distributorNo");
        DistributorInfo byNo = infoService.selectByNo(distributorNo);
        cryptoCmd.setOut(byNo);
        return cryptoCmd;
    }

    @ApiOperation(value = "经销商销售额查看", notes = "{distributorNo: \"19146f4699ee11e8871d98be94f9\"}")
    @PostMapping("/getSaleAmount")
    public CryptoCmd getSaleAmount(CryptoCmd cryptoCmd) {
        String distributorNo = cryptoCmd.getInString("distributorNo");
        BigDecimal saleAmount = infoService.getSaleAmount(distributorNo);
        Map<String, Object> map = new HashMap<String, Object>(){{
            put("saleAmount", saleAmount);
        }};
        cryptoCmd.setOut(map);
        return cryptoCmd;
    }

    @ApiOperation(value = "经销商信息修改", notes = "要修改的信息（编号必填，其余可选）  \n{distributorInfo: {distributorNo: \"19146f4699ee11e8871d98be94f9\",\"distributorName\": \"经销商1\",\"legalName\": \"法人\",\"distributorRegNo\": \"868\",\"companyType\": \"1\",\"distributorAddre\": \"四川省成都市\", \"distributorContact\": \"联系人\", \"distributorContactPhone\": \"15112341234\", \"distributorStatus\": \"00001001\", \"staffNo\": \"12345\" }}")
    @PostMapping("/update")
    public CryptoCmd update(CryptoCmd cryptoCmd) throws InvocationTargetException, IllegalAccessException {
        DistributorInfo distributorInfo = cryptoCmd.getInObject("distributorInfo", DistributorInfo.class);
        int flag = infoService.updateByNo(distributorInfo);
        cryptoCmd.setSuccess(flag > 0);
        return cryptoCmd;
    }

    @ApiOperation(value = "经销商冻结", notes = "{distributorNo: \"19146f4699ee11e8871d98be94f9\"}")
    @PostMapping("/frozen")
    public CryptoCmd frozen(CryptoCmd cryptoCmd){
        String distributorNo = cryptoCmd.getInString("distributorNo");
        int flag = infoService.frozen(distributorNo);
        cryptoCmd.setSuccess(flag > 0);
        return cryptoCmd;
    }

    @ApiOperation(value = "经销商恢复", notes = "{distributorNo: \"19146f4699ee11e8871d98be94f9\"}")
    @PostMapping("/recovery")
    public CryptoCmd recovery(CryptoCmd cryptoCmd){
        String distributorNo = cryptoCmd.getInString("distributorNo");
        int flag = infoService.recovery(distributorNo);
        cryptoCmd.setSuccess(flag > 0);
        return cryptoCmd;
    }

    @ApiOperation(value = "经销商的商户列表", notes = "{distributorNo: \"19146f4699ee11e8871d98be94f9\"}")
    @PostMapping("/mers")
    public CryptoCmd listMers(CryptoCmd cryptoCmd){
        Map<String, Object> params = cryptoCmd.getParams();
        List<TPubMerInfo> merInfos = infoService.listMerInfos(params);
        cryptoCmd.setOut(merInfos);
        cryptoCmd.setSuccess(true);
        return cryptoCmd;
    }
}
