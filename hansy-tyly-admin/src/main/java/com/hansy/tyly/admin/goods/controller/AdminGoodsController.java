package com.hansy.tyly.admin.goods.controller;

import com.hansy.tyly.admin.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hansy.tyly.admin.dao.model.GoodsBaseInfos;
import com.hansy.tyly.admin.dao.model.TGoodsBaseInfo;
import com.hansy.tyly.admin.dao.model.TGoodsType;
import com.hansy.tyly.admin.goods.service.IAdminGoodsService;
import com.hansy.tyly.admin.system.service.SysUserRoleMenuService;
import com.hansy.tyly.core.command.CryptoCmd;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

@Api(description = "平台产品管理")
@RestController
@RequestMapping("/api/goods")
public class AdminGoodsController {
    @Autowired
    private IAdminGoodsService adminGoodsService;
    @Autowired
    private SysUserRoleMenuService sysUserRoleMenuService;



    private  final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value = "平台添加商品", notes = "包括 商品基本信息，商品附件")
    @ResponseBody
    @RequestMapping(value = "/addGoods", method = RequestMethod.POST)
    public CryptoCmd addGoods(CryptoCmd cryptoCmd) {
    	GoodsBaseInfos goodsBaseInfos=cryptoCmd.getInObject("goodsBaseInfos", GoodsBaseInfos.class);
        try{
            adminGoodsService.addGoods(goodsBaseInfos);
            cryptoCmd.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            cryptoCmd.setMessage(Context.ADD_FALSE);
            cryptoCmd.setSuccess(false);
        }
        return cryptoCmd;
    }

    @ApiOperation(value = "平台修改商品信息", notes = "包括 商品基本信息，商品附件")
    @ResponseBody
    @RequestMapping(value = "/editGoods", method = RequestMethod.POST)
    public CryptoCmd editGoods(CryptoCmd cryptoCmd) {
    	GoodsBaseInfos goodsBaseInfos = cryptoCmd.getInObject("goodsBaseInfos",GoodsBaseInfos.class);
        CryptoCmd respones=new CryptoCmd();
        try{
            adminGoodsService.editGoods(goodsBaseInfos);
            respones.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            respones.setMessage(Context.EDIT_FALSE);
            respones.setSuccess(false);
        }
        return respones;
    }

    @ApiOperation(value = "获取平台商品列表", notes = "展示字段 商品基本信息，商品附件，经销商编号 库存")
    @ResponseBody
    @RequestMapping(value = "/getGoodsList", method = RequestMethod.POST)
    public CryptoCmd getGoodsList(CryptoCmd cryptoCmd) {
        Map<String,Object> params=new HashMap<>();
        try{
            params = cryptoCmd.getParams();
            List<GoodsBaseInfos> list= adminGoodsService.getGoodsList(params);
            cryptoCmd.setSuccess(true);
            cryptoCmd.setOut(list);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            cryptoCmd.setMessage(Context.GET_LIST_FALSE);
            cryptoCmd.setSuccess(false);
        }
        return cryptoCmd;
    }



    @ApiOperation(value = "平台上下架商品", notes = "平台上下架商品")
    @ResponseBody
    @RequestMapping(value = "/onSaleOrOffShelvesGoods", method = RequestMethod.POST)
    public CryptoCmd onSaleOrOffShelvesGoods(CryptoCmd cryptoCmd) {
    	List<TGoodsBaseInfo> baseInfo = cryptoCmd.getInArrayObject("baseInfo", TGoodsBaseInfo.class);

        try{
            adminGoodsService.onSaleOrOffShelvesGoods(baseInfo);
            cryptoCmd.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            cryptoCmd.setMessage(Context.ON_OFF_FALSE);
            cryptoCmd.setSuccess(false);
        }
        return cryptoCmd;
    }


    @ApiOperation(value = "平台删除商品", notes = "删除商品信息")
    @ResponseBody
    @RequestMapping(value = "/deleteGoods", method = RequestMethod.POST)
    public CryptoCmd deleteGoods(CryptoCmd cryptoCmd) {
    	String goodsNo = cryptoCmd.getInString("goodsNo");
        try{
            adminGoodsService.deleteGoods(goodsNo);
            cryptoCmd.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            cryptoCmd.setMessage(Context.DELETE_FALSE);
            cryptoCmd.setSuccess(false);
        }
        return cryptoCmd;
    }

    @ApiOperation(value = "平台查看商品详情", notes = "平台查看商品详情")
    @ResponseBody
    @RequestMapping(value = "/getGoodsBaseInfo", method = RequestMethod.POST)
    public CryptoCmd getGoodsBaseInfo(CryptoCmd cryptoCmd) {
    	String goodsNo = cryptoCmd.getInString("goodsNo");
        try{
            GoodsBaseInfos baseInfo=adminGoodsService.getGoodsBaseInfo(goodsNo);
            cryptoCmd.setOut(baseInfo);
            cryptoCmd.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            cryptoCmd.setMessage(Context.GET_INFO_FALSE);
            cryptoCmd.setSuccess(false);
        }
        return cryptoCmd;
    }

    @ApiOperation(value = "平台添加是商品分类", notes = "平台添加是商品分类")
    @ResponseBody
    @RequestMapping(value = "/addGoodsType", method = RequestMethod.POST)
    public CryptoCmd addGoodsType(CryptoCmd cryptoCmd) {
    	TGoodsType goodsType = cryptoCmd.getInObject("goodsType", TGoodsType.class);
        try{
            adminGoodsService.addGoodsType(goodsType);
            cryptoCmd.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            cryptoCmd.setMessage(Context.ADD_FALSE);
            cryptoCmd.setSuccess(false);
        }
        return cryptoCmd;
    }

    @ApiOperation(value = "平台修改商品分类", notes = "平台修改商品分类")
    @ResponseBody
    @RequestMapping(value = "/editGoodsType", method = RequestMethod.POST)
    public CryptoCmd editGoodsType(CryptoCmd cryptoCmd) {
    	TGoodsType goodsType = cryptoCmd.getInObject("goodsType", TGoodsType.class);
        try{
            adminGoodsService.editGoodsType(goodsType);
            cryptoCmd.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            cryptoCmd.setMessage(e.getMessage());
            cryptoCmd.setSuccess(false);
        }
        return cryptoCmd;
    }

    @ApiOperation(value = "平台获取所有商品分类", notes = "平台获取所有商品分类")
    @ResponseBody
    @RequestMapping(value = "/getAllGoodsType", method = RequestMethod.POST)
    public CryptoCmd getAllGoodsType(CryptoCmd cryptoCmd) {
        Map<String,Object> params=new HashMap<>();
        try{
        	params = cryptoCmd.getParams();
            List<TGoodsType> list=adminGoodsService.getAllGoodsType(params);
            cryptoCmd.setOut(list);
            cryptoCmd.setSuccess(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            cryptoCmd.setMessage(Context.GET_LIST_FALSE);
            cryptoCmd.setSuccess(false);
        }
        return cryptoCmd;
    }


    /**
     * 经销商上传产品
     */
    @RequestMapping(value = "/picture",method = RequestMethod.POST)
    public Respones uploadPicture(@RequestParam(value = "file", required = false) MultipartFile file
    ) throws Exception{
        Respones respones = new Respones();
        try {
            String uuid= RandomUtil.uuid();


            Map<String,Object> map=new HashMap<>();
            List<String> list=new ArrayList<>();

            StringBuffer fileName=new StringBuffer(AppPropertiesUtil.getValue("picture.GOODS_IMG"));
            fileName.append(uuid).append(".jpg");
            try {
                list.add(AliyunOssUtil.uploadImg(fileName.toString(), file.getInputStream()));
            } catch (ParseException e) {
                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();
            }
            map.put("url",list);
            map.put("fileName",uuid);
            respones.setMessage("上传成功");
            respones.setResult(true);
            respones.setReq(map);

        } catch (Exception e) {
            respones.setResult(false);
            respones.setMessage("上传失败");
        }

        return respones;

    }




}
