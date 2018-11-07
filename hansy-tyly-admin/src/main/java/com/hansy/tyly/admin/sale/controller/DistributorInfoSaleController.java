package com.hansy.tyly.admin.sale.controller;

import com.github.pagehelper.PageInfo;
import com.hansy.tyly.admin.dao.model.MerchantVO;
import com.hansy.tyly.admin.dao.model.TPubMerInfo;
import com.hansy.tyly.admin.dealers.dao.model.DistributorInfo;
import com.hansy.tyly.admin.dealers.service.DistributorInfoService;
import com.hansy.tyly.admin.merchant.service.MerchantManageService;
import com.hansy.tyly.admin.sale.vo.DistributorInfoSearchVO;
import com.hansy.tyly.admin.sale.vo.DistributorInfoVo;
import com.hansy.tyly.admin.utils.Bean2MapUtil;
import com.hansy.tyly.admin.utils.Respones;
import com.hansy.tyly.admin.utils.ValidUtil;
import com.hansy.tyly.admin.utils.constant.SaleConstant;
import com.hansy.tyly.core.command.CryptoCmd;
import com.hansy.tyly.core.excepiton.ParameterException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "销售方经销商信息管理")
@RestController
@RequestMapping("/sales/dealers/infos")
public class DistributorInfoSaleController {

    @Autowired
    private DistributorInfoService infoService;

    @Autowired
    private MerchantManageService merchantManageService;

    @ApiOperation(value = "经销商列表查看和搜索", notes = "搜索条件（可选）  \nloginId:\"admin\",distributorName:\"经销商1\", startTime:\"2018-8-7\", endTime:\"2018-8-8\", distributorStatus:\"00001001\"")
    @PostMapping("/list")
    public Respones list(DistributorInfoSearchVO infoVO) {
        Map<String, Object> params = Bean2MapUtil.transBean2Map(infoVO);
        List<DistributorInfo> distributorInfos = infoService.list(params);
        Respones respones = new Respones();
        respones.setCode(SaleConstant.SUCCESS_CODE);
        respones.setReq(new PageInfo<>(distributorInfos));
        respones.setResult(true);
        return respones;
    }

    @ApiOperation(value = "经销商详情信息查看", notes = "{distributorNo: \"19146f4699ee11e8871d98be94f9\"}")
    @PostMapping("/getOne")
    public Respones getOne(String distributorNo) {
        DistributorInfo byNo = infoService.selectByNo(distributorNo);
        Respones respones = new Respones();
        respones.setCode(SaleConstant.SUCCESS_CODE);
        respones.setReq(byNo);
        respones.setResult(true);
        return respones;
    }

    @ApiOperation(value = "经销商销售额查看", notes = "{distributorNo: \"19146f4699ee11e8871d98be94f9\"}")
    @PostMapping("/getSaleAmount")
    public Respones getSaleAmount(String distributorNo) {
        BigDecimal saleAmount = infoService.getSaleAmount(distributorNo);
        Map<String, Object> map = new HashMap<String, Object>(){{
            put("saleAmount", saleAmount);
        }};
        Respones respones = new Respones();
        respones.setCode(SaleConstant.SUCCESS_CODE);
        respones.setReq(map);
        respones.setResult(true);
        return respones;
    }

    @ApiOperation(value = "经销商的商户列表", notes = "{distributorNo: \"19146f4699ee11e8871d98be94f9\"}")
    @PostMapping("/mers")
    public Respones getMers(String loginId, String distributorNo, String merName) throws Exception {
        if(!ValidUtil.valid(distributorNo)){
            throw new ParameterException("distributorNo为空");
        }
        MerchantVO merchantVO = new MerchantVO();
        merchantVO.setDistributor_no(distributorNo);
        merchantVO.setMer_name(merName);
        merchantVO.setLoginId(loginId);
        return merchantManageService.searchMerchants(merchantVO);
    }
}
