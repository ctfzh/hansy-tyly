package com.hansy.tyly.admin.log.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hansy.tyly.admin.base.controller.BaseController;
import com.hansy.tyly.admin.log.service.BatchLogService;
import com.hansy.tyly.admin.log.service.TBusiOpLogService;
import com.hansy.tyly.core.command.CryptoCmd;

@Api(description = "跑批日志")
@RestController
@RequestMapping("/api/log")
public class batchLogController extends BaseController{

    @Autowired
    private BatchLogService batchLogService;
    @Autowired
    private TBusiOpLogService tBusiOpLogService;

    @ApiOperation(value = "执行日志", notes = "{\"orgName\":\"\",\"prodName\":\"\",\"logState\":\"\",\"instDate\":\"\"}")
    @PostMapping("/getLogLists")
    public CryptoCmd getLogLists(CryptoCmd cryptoCmd) {
        Map<String, Object> params = cryptoCmd.getParams();
        List<Map<String, Object>> map=batchLogService.getLogLists(params);
        cryptoCmd.setOut(map);
        return cryptoCmd;
    }

    @ApiOperation(value = "操作日志", notes = "{\"orgName\":\"\",\"sBody\":\"\",\"busiOpType\":\"\",\"instDate\":\"\"}")
    @PostMapping("/getOpLogLists")
    public CryptoCmd getOpLogLists(CryptoCmd cryptoCmd) {
        Map<String, Object> params = cryptoCmd.getParams();
        List<Map<String, Object>> map=tBusiOpLogService.getOpLogLists(params);
        cryptoCmd.setOut(map);
        return cryptoCmd;
    }

}
