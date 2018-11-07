package com.hansy.tyly.admin.sale.controller;

import com.hansy.tyly.admin.dealers.dao.model.Group;
import com.hansy.tyly.admin.dealers.dao.model.GroupCust;
import com.hansy.tyly.admin.dealers.service.DistributorGroupCustService;
import com.hansy.tyly.admin.dealers.service.DistributorGroupService;
import com.hansy.tyly.admin.utils.Respones;
import com.hansy.tyly.admin.utils.constant.SaleConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "销售方经销商分组管理")
@RestController
@RequestMapping("/sales/dealers/groups")
public class DistributorGroupSaleController {

    @Autowired
    private DistributorGroupService groupService;

    @Autowired
    private DistributorGroupCustService groupCustService;

    @ApiOperation(value = "经销商分组列表")
    @PostMapping("/list")
    public Respones list(@RequestParam(required = false) Integer pageNo, @RequestParam(required = false) Integer pageSize,
                         String loginId){
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        params.put("loginId", loginId);
        List<Group> groups = groupService.listByCreateNo(params);
        Respones respones = new Respones();
        respones.setCode(SaleConstant.SUCCESS_CODE);
        respones.setReq(groups);
        respones.setResult(true);
        return respones;
    }

    @ApiOperation(value = "经销商分组添加", notes = "{groupName: \"food\",loginId: \"admin\"}")
    @PostMapping("/insert")
    public Respones insert(String groupName, String loginId){
        int flag = groupService.insert(groupName, loginId);
        Respones respones = new Respones();
        respones.setCode(SaleConstant.SUCCESS_CODE);
        respones.setResult(flag > 0);
        return respones;
    }

    @ApiOperation(value = "经销商分组编辑", notes = "{groupName: \"food\", groupNo: \"5b11995fef7a4d5fb6aeca9c4425823e\"}")
    @PostMapping("/update")
    public Respones update(String groupNo, String groupName){
        int flag = groupService.update(groupNo, groupName);
        Respones respones = new Respones();
        respones.setCode(SaleConstant.SUCCESS_CODE);
        respones.setResult(flag > 0);
        return respones;
    }

    @ApiOperation(value = "经销商分组删除", notes = "{groupNo: \"5b11995fef7a4d5fb6aeca9c4425823e\"}")
    @PostMapping("/delete")
    public Respones delete(String groupNo){
        int flag = groupService.logicDel(groupNo);
        Respones respones = new Respones();
        respones.setCode(SaleConstant.SUCCESS_CODE);
        respones.setResult(flag > 0);
        return respones;
    }

    @ApiOperation(value = "经销商分组详情", notes = "{groupNo: \"5b11995fef7a4d5fb6aeca9c4425823e\"}")
    @PostMapping("/getOne")
    public Respones getOne(@RequestParam(required = false) Integer pageNo,
                            @RequestParam(required = false) Integer pageSize,
                            String groupNo){
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        params.put("groupNo", groupNo);
        List<GroupCust> groupCusts = groupCustService.listByGroupNo(params);
        Respones respones = new Respones();
        respones.setCode(SaleConstant.SUCCESS_CODE);
        respones.setReq(groupCusts);
        respones.setResult(true);
        return respones;
    }

    @ApiOperation(value = "添加经销商到分组", notes = "{custNo: \"123\",groupNo: \"5b11995fef7a4d5fb6aeca9c4425823e\"}")
    @PostMapping("/insertToGroup")
    public Respones insertToGroup(String groupNo, String custNo){
        int flag = groupCustService.insertDistributor(groupNo, custNo);
        Respones respones = new Respones();
        respones.setCode(SaleConstant.SUCCESS_CODE);
        respones.setResult(flag > 0);
        return respones;
    }

    @ApiOperation(value = "移除分组的经销商", notes = "{custNo: \"123\",groupNo: \"5b11995fef7a4d5fb6aeca9c4425823e\"}")
    @PostMapping("/deleteFormGroup")
    public Respones deleteFormGroup(String groupNo, String custNo){
        int flag = groupCustService.deleteDistributor(groupNo, custNo);
        Respones respones = new Respones();
        respones.setCode(SaleConstant.SUCCESS_CODE);
        respones.setResult(flag > 0);
        return respones;
    }

}
