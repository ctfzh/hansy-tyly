package com.hansy.tyly.admin.prod.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hansy.tyly.admin.base.controller.BaseController;
import com.hansy.tyly.admin.dao.model.TBusiProd;
import com.hansy.tyly.admin.prod.service.CloudProdService;
import com.hansy.tyly.core.command.CryptoCmd;

@Api(description = "风控产品")
@RestController
@RequestMapping("/api/prod")
public class CloudProdController extends BaseController {

    @Autowired
    private CloudProdService cloudProdService;

    @ApiOperation(value = "查询产品列表", notes = "{\"prodName\":\"1\",\"mngType\":\"\",\"status\":\"\"}")
    @PostMapping("/queryList")
    public CryptoCmd queryList(CryptoCmd cryptoCmd) {
        Map<String, Object> params = cryptoCmd.getParams();
        List<Map<String, Object>> mapList = cloudProdService.queryList(params);
        cryptoCmd.setOut(mapList);
        return cryptoCmd;
    }

    @ApiOperation(value = "新增或者保存产品", notes = "{\"tBusiProd\":{\"prodName\":\"1\",\"prodRemark\":\"\"," +
            "\"costAmt\":\"\"," +
            "\"mngType\":\"\",\"ruleId\":\"\",\"scoreId\":\"\",\"prodId\":\"\"},\"prodLibPath\":\"\"}")
    @PostMapping("/saveOrUpdateProd")
    public CryptoCmd saveOrUpdateProd(CryptoCmd cryptoCmd) {
        TBusiProd tBusiProd = cryptoCmd.getInObject("tBusiProd", TBusiProd.class);
        String imageUrl=cryptoCmd.getInString("prodLibPath");
        cloudProdService.saveOrUpdateProd(tBusiProd,imageUrl, super.getCurrentUserProfile());
        return cryptoCmd;
    }

    @ApiOperation(value = "上下架", notes = "{\"prodId\":\"\"}")
    @PostMapping("/alterStatus")
    public CryptoCmd alterStatus(CryptoCmd cryptoCmd) {
        String prodId = cryptoCmd.getInString("prodId");
        cloudProdService.alterStatus(prodId, super.getCurrentUserProfile());
        return cryptoCmd;
    }

    @ApiOperation(value = "查询产品已关联指标", notes = "{\"prodId\":\"\",\"indicName\":\"\"}")
    @PostMapping("/getIndicatorOnProd")
    public CryptoCmd getIndicatorOnProd(CryptoCmd cryptoCmd) {
        Map<String, Object> params = cryptoCmd.getParams();
       /* String prodId = cryptoCmd.getInString("prodId");
        String prodName = cryptoCmd.getInString("indicName");*/
        List<Map<String, Object>> indicatorOnProd = cloudProdService.getIndicatorOnProd(params);
        cryptoCmd.setOut(indicatorOnProd);
        return cryptoCmd;
    }

    @ApiOperation(value = "查询所有指标", notes = "{\"prodId\":\"\",\"indicName\":\"\"}")
    @PostMapping("/getAllIndicator")
    public CryptoCmd getAllIndicator(CryptoCmd cryptoCmd) {
        Map<String, Object> params = cryptoCmd.getParams();
        /*String prodId = cryptoCmd.getInString("prodId");
        String prodName = cryptoCmd.getInString("indicName");*/
        List<Map<String, Object>> allIndicator = cloudProdService.getAllIndicator(params);
        cryptoCmd.setOut(allIndicator);
        return cryptoCmd;
    }

    @ApiOperation(value = "关联新的指标", notes = "{\"indicatorIds\":[\"1\",\"2\"],\"prodId\":\"\"}")
    @PostMapping("/doIndicatorProdRel")
    public CryptoCmd doIndicatorProdRel(CryptoCmd cryptoCmd) {
        String prodId = cryptoCmd.getInString("prodId");
        List<String> indicatorId = cryptoCmd.getInArrayString("indicatorIds");
        cloudProdService.doIndicatorProdRel(prodId, indicatorId, super.getCurrentUserProfile());
        return cryptoCmd;
    }

    @ApiOperation(value = "删除指标", notes = "{\"sysUuid\":\"test2\"}")
    @PostMapping("/alterProIndicatorStatus")
    public CryptoCmd alterProIndicatorStatus(CryptoCmd cryptoCmd) {
        String sysUuid = cryptoCmd.getInString("sysUuid");
        cloudProdService.alterProIndicatorStatus(sysUuid, super.getCurrentUserProfile());
        return cryptoCmd;
    }

    @PostMapping("/uploadFile")
    @ExceptionHandler(MultipartException.class)
    public CryptoCmd uploadFile(CryptoCmd cryptoCmd, HttpServletRequest request) {

        if (request instanceof MultipartHttpServletRequest) {
            MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
            String uploadFile = cloudProdService.uploadFile(file);
            cryptoCmd.setOut(uploadFile);
        } else {
            String ret = "非MultipartFile请求，请加上multipart/form-data";
            cryptoCmd.setOut(ret);
        }
        return cryptoCmd;
    }


}
