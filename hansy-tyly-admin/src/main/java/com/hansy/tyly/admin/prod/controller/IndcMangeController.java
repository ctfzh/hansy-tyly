package com.hansy.tyly.admin.prod.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hansy.tyly.admin.base.controller.BaseController;
import com.hansy.tyly.admin.dao.model.TBusiIndicator;
import com.hansy.tyly.admin.prod.service.CloudProdService;
import com.hansy.tyly.core.command.CryptoCmd;

@Api(description = "指标管理")
@RestController
@RequestMapping("/api/indc")
public class IndcMangeController extends BaseController {
    @Autowired
    private CloudProdService cloudProdService;

    @ApiOperation(value = "查询树结构")
    @PostMapping("/getTreeList")
    public CryptoCmd getTreeList(CryptoCmd cryptoCmd) {
        List<Map<String, Object>> treeList = cloudProdService.getTreeList();
        cryptoCmd.setOut(treeList);
        return cryptoCmd;
    }

    @ApiOperation(value = "查询指标表格", notes = "{\"indicatorName\":\"\",\"id\":\"\",\"insertTime\":\"\"}")
    @PostMapping("/getIndiList")
    public CryptoCmd getIndiList(CryptoCmd cryptoCmd) {
        Map<String, Object> params = cryptoCmd.getParams();
        List<Map<String, Object>> indiList = cloudProdService.getIndiList(params);
        cryptoCmd.setOut(indiList);
        return cryptoCmd;
    }

    @ApiOperation(value = "禁用启用", notes = "{\"id\":\"\"}")
    @PostMapping("/alterIndiStatus")
    public CryptoCmd alterIndiStatus(CryptoCmd cryptoCmd) {
        String id = cryptoCmd.getInString("id");
        cloudProdService.alterIndiStatus(id, super.getCurrentUserProfile());
        return cryptoCmd;
    }

    @ApiOperation(value = "新增指标", notes = "{\"type\":\"\",\"tBusiIndicator\":{\"indicatorId\":\"\"," +
            "\"indicatorName\":\"\"," +
            "\"indicatorType\":\"\",\"status\":\"\",\"parentIndtId\":\"\"}}")
    @PostMapping("/saveIndi")
    public CryptoCmd saveIndi(CryptoCmd cryptoCmd) {
        String type = cryptoCmd.getInString("type");
        TBusiIndicator tBusiIndicator = cryptoCmd.getInObject("tBusiIndicator", TBusiIndicator.class);
        cloudProdService.saveIndi(type, tBusiIndicator, super.getCurrentUserProfile());
        return cryptoCmd;
    }

    @ApiOperation(value = "编辑指标", notes = "{\"type\":\"\",\"tBusiIndicator\":{\"indicatorId\":\"\"," +
            "\"indicatorName\":\"\"," +
            "\"indicatorType\":\"\",\"status\":\"\"}}")
    @PostMapping("/editIndi")
    public CryptoCmd editIndi(CryptoCmd cryptoCmd) {
        TBusiIndicator tBusiIndicator = cryptoCmd.getInObject("tBusiIndicator", TBusiIndicator.class);
        cloudProdService.editIndi(tBusiIndicator, super.getCurrentUserProfile());
        return cryptoCmd;
    }
}
