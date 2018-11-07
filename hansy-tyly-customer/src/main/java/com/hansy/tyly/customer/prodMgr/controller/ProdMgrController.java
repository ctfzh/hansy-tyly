package com.hansy.tyly.customer.prodMgr.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hansy.tyly.admin.base.controller.BaseController;
import com.hansy.tyly.core.command.CryptoCmd;
import com.hansy.tyly.customer.prodMgr.service.ProdMgrService;

@Api(description = "W05-[机构产品、客户产品]机构、客户产品信息")
@RestController
@RequestMapping("/api/prodMgr")
public class ProdMgrController extends BaseController{

    @Autowired
    private ProdMgrService prodMgrService;
    
    private static final Logger logger = LoggerFactory.getLogger(ProdMgrController.class);
    
	
    @ApiOperation(value = "01-[客户管理相关]查询机构下有效的产品列表", notes =  "不需要传递任何参数")
	@ResponseBody
	@RequestMapping(value = "/queryProds", method = RequestMethod.POST)
    public CryptoCmd queryProdsByOrgId(CryptoCmd cmd) {
    	Map<String, Object> inMap = cmd.getParams();
    	inMap.put("orgId", this.getCurrentUserOrgId());
		cmd.setOut(prodMgrService.queryProdsByOrgId(inMap));
        return cmd;
    }
    

}
