package com.hansy.tyly.admin.dealers.controller;

import com.github.pagehelper.PageInfo;
import com.hansy.tyly.admin.dao.model.SysUser;
import com.hansy.tyly.admin.sale.dao.model.VisitRecord;
import com.hansy.tyly.admin.sale.service.SaleService;
import com.hansy.tyly.admin.sale.service.VisitRecordService;
import com.hansy.tyly.core.command.CryptoCmd;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(description = "平台销售管理")
@RestController
@RequestMapping("/api/sales")
public class SalePlatController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private VisitRecordService visitRecordService;

    @ApiOperation(value = "销售列表查询", notes = "搜索条件（可选）  \n{saleName:\"销售2\", startTime:\"2018-8-7\", endTime:\"2018-8-31\"}")
    @PostMapping("/info/list")
    public CryptoCmd list(CryptoCmd cryptoCmd){
        Map<String, Object> params = cryptoCmd.getParams();
        List<SysUser> sysUsers = saleService.listAllSale(params);
        cryptoCmd.setOut(sysUsers);
        return cryptoCmd;
    }

    @ApiOperation(value = "销售详情查询", notes = "{userId: \"3ac00b7953bc43969c490d2915568650\"}")
    @PostMapping("/info/getOne")
    public CryptoCmd get(CryptoCmd cryptoCmd){
        String userId = cryptoCmd.getInString("userId");
        SysUser sysUser = saleService.selectById(userId);
        cryptoCmd.setOut(sysUser);
        return cryptoCmd;
    }


    @ApiOperation(value = "销售拜访记录查询", notes = "查询条件（编号必填, 其余可选）  \n{staffNo: \"21341\", time: \"2018-8-9\"}")
    @PostMapping("/record/list")
    public CryptoCmd listVisitRecords(CryptoCmd cryptoCmd){
        Map<String, Object> params = cryptoCmd.getParams();
        List<VisitRecord> visitRecords = visitRecordService.listByStaffNo(params);
        cryptoCmd.setOut(visitRecords);
        return cryptoCmd;
    }

    @ApiOperation(value = "销售拜访记录详情查询", notes = "{visitNo: \"9973b3509b7311e8871d98be94f9306\"}")
    @PostMapping("/record/getOne")
    public CryptoCmd getVisitRecords(CryptoCmd cryptoCmd){
        String visitNo = cryptoCmd.getInString("visitNo");
        VisitRecord visitRecord = visitRecordService.selectById(visitNo);
        cryptoCmd.setOut(visitRecord);
        return cryptoCmd;
    }

}
