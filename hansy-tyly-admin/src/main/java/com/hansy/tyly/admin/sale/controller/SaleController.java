package com.hansy.tyly.admin.sale.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hansy.tyly.admin.dao.model.SysUser;
import com.hansy.tyly.admin.dealers.dao.model.DistributorInfo;
import com.hansy.tyly.admin.sale.dao.model.VisitRecord;
import com.hansy.tyly.admin.sale.service.SaleService;
import com.hansy.tyly.admin.sale.service.VisitRecordService;
import com.hansy.tyly.admin.sale.vo.SaleSearchVo;
import com.hansy.tyly.admin.utils.*;
import com.hansy.tyly.admin.utils.constant.SaleConstant;
import com.hansy.tyly.core.command.CryptoCmd;
import com.hansy.tyly.core.excepiton.ParameterException;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "销售方销售管理")
@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private VisitRecordService visitRecordService;

    @ApiOperation(value = "销售列表查询", notes = "搜索条件（可选）  \n{saleName:\"销售2\", startTime:\"2018-8-7\", endTime:\"2018-8-31\"}")
    @PostMapping("/info/list")
    public Respones list(SaleSearchVo saleSearchVo) {
        Map<String, Object> params = Bean2MapUtil.transBean2Map(saleSearchVo);
        List<SysUser> sysUsers = saleService.list(params);
        Respones respones = new Respones();
        respones.setCode(SaleConstant.SUCCESS_CODE);
        respones.setReq(sysUsers);
        respones.setResult(true);
        return respones;
    }

    @ApiOperation(value = "销售详情查询", notes = "{userId: \"3ac00b7953bc43969c490d2915568650\"}")
    @PostMapping("/info/getOne")
    public Respones get(String userId) {
        SysUser sysUser = saleService.selectById(userId);
        Respones respones = new Respones();
        respones.setCode(SaleConstant.SUCCESS_CODE);
        respones.setReq(sysUser);
        respones.setResult(true);
        return respones;
    }

    @ApiOperation(value = "销售拜访记录新增", notes = "{custName:\"\",cust_no:\"\",visitAddre:\"\",visitContent:\"\", latitude : \"123\", longitude : \"123\",custType:\"\", visitFiles:[{filePath:\"\", fileName:\"\"}]}, loginId:\"\"")
    @PostMapping("/record/insert")
    public Respones insertVisitRecords(String visitRecord, String loginId) {
        VisitRecord visitRecord1 = null;
        try{
            visitRecord1 = JSONObject.parseObject(visitRecord, VisitRecord.class);
        } catch (Exception e){
            throw new ParameterException("visitRecord参数错误");
        }
        int flag = visitRecordService.insert(visitRecord1, loginId);
        Respones respones = new Respones();
        respones.setCode(SaleConstant.SUCCESS_CODE);
        respones.setResult(flag > 0);
        return respones;
    }

    @ApiOperation(value = "销售拜访记录附件上传")
    @RequestMapping(value = "/record/picture", method = RequestMethod.POST)
    public Respones uploadPicture(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        Respones respones = new Respones();
        try {
            String uuid = RandomUtil.uuid();

            Map<String, Object> map = new HashMap<>();
            List<String> list = new ArrayList<>();

            StringBuffer fileName = new StringBuffer(Context.VISIT_IMG);
            fileName.append(uuid).append(".jpg");
            try {
                list.add(AliyunOssUtil.uploadImg(fileName.toString(), file.getInputStream()));
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            map.put("filePath", list.get(0));
            map.put("fileName", uuid);
            respones.setMessage("上传成功");
            respones.setResult(true);
            respones.setReq(map);

        } catch (Exception e) {
            respones.setResult(false);
            respones.setMessage("上传失败");
        }

        return respones;

    }

    @ApiOperation(value = "销售拜访记录查询", notes = "查询条件（其余可选）  \n{time: \"2018-8-9\"}")
    @PostMapping("/record/list")
    public Respones listVisitRecords(SaleSearchVo saleSearchVo) {
        Map<String, Object> params = Bean2MapUtil.transBean2Map(saleSearchVo);
        List<VisitRecord> visitRecords = visitRecordService.list(params);
        Respones respones = new Respones();
        respones.setCode(SaleConstant.SUCCESS_CODE);
        respones.setReq(new PageInfo<>(visitRecords));
        respones.setResult(true);
        return respones;
    }

    @ApiOperation(value = "销售拜访记录详情查询", notes = "{visitNo: \"9973b3509b7311e8871d98be94f9306\"}")
    @PostMapping("/record/getOne")
    public Respones getVisitRecords(String visitNo) {
        VisitRecord visitRecord = visitRecordService.selectById(visitNo);
        Respones respones = new Respones();
        respones.setCode(SaleConstant.SUCCESS_CODE);
        respones.setReq(visitRecord);
        respones.setResult(true);
        return respones;
    }

    @ApiOperation(value = "销售信息修改")
    @PostMapping("/info/update")
    public Respones updateSale(SysUser sale) {
        Respones respones = new Respones();
        int flag = saleService.updateSale(sale);
        respones.setResult(flag > 0);
        return respones;
    }

}
