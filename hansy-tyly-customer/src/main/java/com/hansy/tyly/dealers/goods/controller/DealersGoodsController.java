package com.hansy.tyly.dealers.goods.controller;

import com.github.pagehelper.Page;
import com.hansy.tyly.admin.base.controller.BaseController;
import com.hansy.tyly.admin.system.service.SysUserRoleMenuService;
import com.hansy.tyly.admin.utils.AppPropertiesUtil;
import com.hansy.tyly.admin.utils.RandomUtil;
import com.hansy.tyly.common.enumClass.CodeEnum;
import com.hansy.tyly.common.enumClass.CodeNameEnum;
import com.hansy.tyly.common.utils.*;
import com.hansy.tyly.core.command.CryptoCmd;
import com.hansy.tyly.dealers.goods.dao.model.*;
import com.hansy.tyly.dealers.goods.service.IDealersGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.*;


@Api(description = "经销商商品管理")
@RestController
@RequestMapping("/dealers/goods")
public class DealersGoodsController extends BaseController{

    private  final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IDealersGoodsService dealersGoodsService;
    @Autowired
    public SysUserRoleMenuService sysUserRoleMenuService;



    @ApiOperation(value = "经销商添加商品", notes = "包括 商品基本信息，商品附件，经销商编号 库存")
    @ResponseBody
    @RequestMapping(value = "/addGoods", method = RequestMethod.POST)
    public Respones addGoods(@RequestBody GoodsBaseInfos goodsBaseInfos) {
        Respones cmd=new Respones();
        try{
            dealersGoodsService.addGoods(goodsBaseInfos);
            cmd.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            cmd.setMessage(Context.ADD_FALSE);
            cmd.setResult(false);
        }
        return cmd;
    }



    @ApiOperation(value = "获取经销商商品列表", notes = "展示字段 商品基本信息，商品附件，经销商编号 库存")
    @ResponseBody
    @RequestMapping(value = "/getGoodsList", method = RequestMethod.POST)
    public Respones getGoodsList(@RequestParam(required = false,defaultValue = "10") Integer pageSize,
                                 @RequestParam(required = false,defaultValue = "1") Integer pageNo,
                                 @RequestParam(required = false) String orderBy,
                                 @RequestParam String distributorNo,
                                 @RequestParam(required = false) String merNo,
                                 @RequestParam(required = false) String notMerNo,
                                 @RequestParam(required = false) String onStatus,
                                 @RequestParam(required = false) String typeNo,
                                 @RequestParam(required = false) String goodsName,
                                 @RequestParam(required = false) String goodsNo
                                 ) {
        Respones respones=new Respones();
        Map<String,Object> params=new HashMap<>();
        try{
            params.put("pageSize",pageSize);
            params.put("pageNo",pageNo);
            params.put("orderBy", ParamsUtils.isNull(orderBy));
            params.put("distributorNo", ParamsUtils.isNull(distributorNo));
            params.put("typeNo", ParamsUtils.isNull(typeNo));
            params.put("goodsName", ParamsUtils.isNull(goodsName));
            params.put("merNo", ParamsUtils.isNull(merNo));
            params.put("notMerNo", ParamsUtils.isNull(notMerNo));
            params.put("onStatus", ParamsUtils.isNull(onStatus));
            params.put("goodsNo", ParamsUtils.isNull(goodsNo));
            params.put("goodsStatus",dealersGoodsService.getCodeValueByName(
                    CodeNameEnum.normal.getName(),CodeEnum.goodsStaus.getCode()));

            List<GoodsBaseInfos> list= dealersGoodsService.getGoodsList(params);
            respones.setReq(list);
            respones.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
           // respones.setMessage(Context.GET_LIST_FALSE);
            respones.setResult(false);
        }
        return respones;
    }

    @ApiOperation(value = "经销商修改商品信息", notes = "包括 商品基本信息，商品附件，经销商编号 库存")
    @ResponseBody
    @RequestMapping(value = "/editGoods",method = RequestMethod.POST)
    public Respones editGoods(@RequestBody GoodsBaseInfos goodsBaseInfos) {
        Respones respones=new Respones();
        try{
            dealersGoodsService.editGoods(goodsBaseInfos);
            respones.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            respones.setMessage(Context.EDIT_FALSE);
            respones.setResult(false);
        }
        return respones;
    }

    /**
     * 价格  经销商编号

     * @return
     */
    @ApiOperation(value = "经销商上浮商品协议价格", notes = "经销商上浮商品协议价格")
    @ResponseBody
    @RequestMapping(value = "/editGoodsPrice",method = RequestMethod.POST)
    public Respones editGoodsPrice(@RequestBody GoodsPriceDisToMers goodsPriceDisToMers
                                   ) {
        Respones respones=new Respones();
        try{
            dealersGoodsService.editGoodsPrice(goodsPriceDisToMers);
            respones.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            respones.setMessage(Context.EDIT_FALSE);
            respones.setResult(false);
        }
        return respones;
    }

    /**
     * 价格  经销商编号
     * @param disNo
     * @return
     */
    @ApiOperation(value = "经销商获取协议价列表", notes = "经销商获取协议价列表")
    @ResponseBody
    @RequestMapping(value = "/getGoodsDM",method = RequestMethod.POST)
    public Respones getGoodsDM( @RequestParam(required = false) String disNo,
                                   @RequestParam(required = false) String goodsNo
                                   ) {
        Respones respones=new Respones();
        try{
            List<Map> list=dealersGoodsService.getGoodsDM(disNo,goodsNo);
            respones.setResult(true);
            respones.setReq(list);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            respones.setResult(false);
            respones.setMessage(Context.GET_LIST_FALSE);
        }
        return respones;
    }

    /**
     * 价格  经销商编号
     * @param disNo
     * @return
     */
    @ApiOperation(value = "经销商绑定商户但未绑定", notes = "经销商获取协议价列表")
    @ResponseBody
    @RequestMapping(value = "/getNotGoodsDM",method = RequestMethod.POST)
    public Respones getNotGoodsDM(
            @RequestParam String disNo,
            @RequestParam String goodsNo
    ) {
        Respones respones=new Respones();
        try{
            List<Map> list=dealersGoodsService.getNotGoodsDM(disNo,goodsNo);
            respones.setResult(true);
            respones.setReq(list);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            respones.setMessage(Context.GET_LIST_FALSE);
            respones.setResult(false);
        }
        return respones;
    }




    /**
     * editGoodsDM

     * @return
     */
    @ApiOperation(value = "经销商批量修改库存", notes = "经销商批量修改库存")
    @ResponseBody
    @RequestMapping(value = "/editGoodsDM",method = RequestMethod.POST)
    public Respones editGoodsDM(
            @RequestBody GoodsDistirbutors distirbutorList
    ) {
        Respones respones=new Respones();
        try{
            dealersGoodsService.editGoodsDM(distirbutorList);
            respones.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            respones.setMessage(Context.GET_LIST_FALSE);
            respones.setResult(false);
        }
        return respones;
    }




    @ApiOperation(value = "经销商上下架商品", notes = "经销商上下架商品")
    @ResponseBody
    @RequestMapping(value = "/onSaleOrOffShelvesGoods",method = RequestMethod.POST)
    public Respones onSaleOrOffShelvesGoods(@RequestBody TGoodsDisMerList distributorMers) {
        Respones respones=new Respones();
        try{
            dealersGoodsService.editGoodsDistributorMer(distributorMers);
            respones.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            respones.setMessage(e.getMessage());
            respones.setResult(false);
        }
        return respones;
    }


    @ApiOperation(value = "经销商删除商品", notes = "删除商品信息")
    @ResponseBody
    @RequestMapping(value = "/deleteGoods", method = RequestMethod.POST)
    public Respones deleteGoods(@RequestParam String goodsNo,@RequestParam String distributorNo) {
        Respones respones=new Respones();
        try{
            dealersGoodsService.deleteGoods(goodsNo,distributorNo);
            respones.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            respones.setMessage(Context.DELETE_FALSE);
            respones.setResult(false);
        }
        return respones;
    }

    @ApiOperation(value = "商查看商品详情", notes = "经销商查看商品详情")
    @ResponseBody
    @RequestMapping(value = "/getGoodsBaseInfo", method = RequestMethod.POST)
    public Respones getGoodsBaseInfo(@RequestParam String goodsNo) {
        Respones respones=new Respones();
        try{
            GoodsBaseInfos baseInfo=dealersGoodsService.getGoodsBaseInfo(goodsNo);
            respones.setReq(baseInfo);
            respones.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            respones.setMessage(Context.GET_INFO_FALSE);
            respones.setResult(false);
        }
        return respones;
    }

    @ApiOperation(value = "经销商添加购物车", notes = "经销商添加购物车")
    @ResponseBody
    @PostMapping(value = "/addCart",consumes = "application/json")
    public Respones addCart(@RequestBody TBusShoppCart shoppCart) {
        Respones respones=new Respones();
        try{
            dealersGoodsService.addCart(shoppCart);
            respones.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            respones.setMessage(Context.ADD_FALSE);
            respones.setResult(false);
        }
        return respones;
    }

    @ApiOperation(value = "经销商删除购物车", notes = "经销商删除购物车")
    @ResponseBody
    @RequestMapping(value = "/deleteCart", method = RequestMethod.POST)
    public Respones deleteCart(@RequestParam String cartKey) {
        Respones respones=new Respones();
        try{
            dealersGoodsService.deleteCart(cartKey);
            respones.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            respones.setResult(false);
            respones.setMessage(Context.DELETE_FALSE);
        }
        return respones;
    }


    @ApiOperation(value = "经销商添加到购物车的商品列表", notes = "经销商添加到购物车的商品列表")
    @ResponseBody
    @RequestMapping(value = "/getCartOfGoods", method = RequestMethod.POST)
    public Respones getCartOfGoods(@RequestParam(required = false,defaultValue = "10") Integer pageSize,
                                   @RequestParam(required = false,defaultValue = "1") Integer pageNo,
                                   @RequestParam(required = false) String orderBy,
                                   @RequestParam String dealersNo
                                   ){
        Respones respones=new Respones();
        Map<String,Object> params=new HashMap<>();
        try{
            params.put("pageSize",pageSize);
            params.put("pageNo",pageNo);
            params.put("custNo", ParamsUtils.isNull(dealersNo));
            params.put("orderBy", ParamsUtils.isNull(orderBy));
            List<CartOfGoods> list=dealersGoodsService.getCartOfGoods(params);
            respones.setReq(list);
            respones.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            respones.setMessage(Context.GET_LIST_FALSE);
            respones.setResult(false);
        }
        return respones;
    }

    @ApiOperation(value = "经销商添加到购物车的商品个数", notes = "经销商添加到购物车的商品列表")
    @ResponseBody
    @RequestMapping(value = "/getCartOfGoodsCount", method = RequestMethod.POST)
    public Respones getCartOfGoodsCount(

                                   @RequestParam String dealersNo
    ){
        Respones respones=new Respones();
        Map<String,Object> params=new HashMap<>();
        try{

            params.put("custNo", ParamsUtils.isNull(dealersNo));
            respones.setReq(dealersGoodsService.getCartOfGoods(params).size());
            respones.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            respones.setMessage(Context.GET_LIST_FALSE);
            respones.setResult(false);
        }
        return respones;
    }





    @ApiOperation(value = "经销商查看平台商品列表", notes = "经销商添加到购物车的商品列表")
    @ResponseBody
    @RequestMapping(value = "/getPlatformGoodsList", method = RequestMethod.POST)
    public Respones getPlatformGoodsList(@RequestParam(required = false,defaultValue = "10") Integer pageSize,
                                         @RequestParam(required = false,defaultValue = "1") Integer pageNo,
                                         @RequestParam(required = false) String orderBy,
                                         @RequestParam(required = false) String typeNo,
                                         @RequestParam(required = false) String goodsName
    ){
        Respones respones=new Respones();
        Map<String,Object> params=new HashMap<>();
        try{
            params.put("pageSize",pageSize);
            params.put("pageNo",pageNo);
            params.put("typeNo", ParamsUtils.isNull(typeNo));
            params.put("orderBy",ParamsUtils.isNull(orderBy));
            params.put("goodsName",ParamsUtils.isNull(goodsName));

            List<GoodsBaseInfos> list=dealersGoodsService.getPlatformGoodsList(params);
            respones.setReq(list);
            respones.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            respones.setMessage(Context.GET_LIST_FALSE);
            respones.setResult(false);
        }
        return respones;
    }

    @ApiOperation(value = "经销商查看平台商品详情", notes = "经销商查看平台商品详情")
    @ResponseBody
    @RequestMapping(value = "/getPlatformGoodsInfo", method = RequestMethod.POST)
    public Respones getPlatformGoodsInfo(
                                         @RequestParam String goodsNo
    ){
        Respones respones=new Respones();
        try{

            GoodsBaseInfos baseInfos=dealersGoodsService.getPlatformGoodsInfo(goodsNo);
            respones.setReq(baseInfos);
            respones.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            respones.setMessage(Context.GET_INFO_FALSE);
            respones.setResult(false);
        }
        return respones;
    }


    @ApiOperation(value = "经销商获取所有商品分类", notes = "经销商获取所有商品分类")
    @ResponseBody
    @RequestMapping(value = "/getAllGoodsType", method = RequestMethod.POST)
    public Respones getAllGoodsType(
            @RequestParam(required = false) String goodsAscription,
             @RequestParam(required = false) String disNo,
             @RequestParam(required = false) String notMerNo
    ) {
        Respones cmd=new Respones();
        Map<String,Object> params=new HashMap<>();
        try{
            params.put("typeStatus",dealersGoodsService.getCodeValueByName(
                    CodeNameEnum.enable.getName(), CodeEnum.goodsTypeStatus.getCode()
            ));
            params.put("goodsAscription",ParamsUtils.isNull(goodsAscription));
            params.put("disNo",ParamsUtils.isNull(disNo));
            params.put("notMerNo",ParamsUtils.isNull(notMerNo));

            List<TGoodsType> list=dealersGoodsService.getAllGoodsType(params);
            cmd.setReq(list);
            cmd.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            cmd.setMessage(Context.GET_LIST_FALSE);
            cmd.setResult(false);
        }
        return cmd;
    }

    @ApiOperation(value = "经销商获取低于库存的商品信息 及库存", notes = "经销商获取低于库存的商品信息")
    @ResponseBody
    @RequestMapping(value = "/getGoodsStocks", method = RequestMethod.POST)
    public Respones getGoodsStocks(@RequestParam String disNo) {
        Respones cmd=new Respones();
        try{
            List<GoodsBaseInfos> list=dealersGoodsService.getGoodsStocks(disNo);
            cmd.setReq(list);
            cmd.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            cmd.setMessage(Context.GET_LIST_FALSE);
            cmd.setResult(false);
        }
        return cmd;
    }



    /**
     * 经销商上传产品
     */
    @RequestMapping(value = "/picture",method = RequestMethod.POST)
    public Respones uploadPicture(@RequestParam(value = "file", required = false) List<MultipartFile> fileList
                                    ) throws Exception{
        //获取类型码值
        String goodsNo=creatGoodsNo(CodeNameEnum.dealers.getName());
        Respones respones = new Respones();
        try {
            String uuid=RandomUtil.uuid();
            String numOfPic=sysUserRoleMenuService.getCodeNameByValue(Context.NUM_OF_PIC);
            if(fileList.size()>Integer.valueOf(numOfPic)){
                respones.setMessage("超过上传数量限制");
                respones.setResult(false);
            }else{
                Map<String,Object> map=new HashMap<>();
                List<String> list=new ArrayList<>();
                fileList.forEach(file->{
                    StringBuffer fileName=new StringBuffer(AppPropertiesUtil.getValue("picture.GOODS_IMG"));
                    fileName.append(goodsNo).append("/").append(uuid).append(".jpg");
                    try {
                        list.add(AliyunOssUtil.uploadImg(fileName.toString(), file.getInputStream()));
                    } catch (ParseException e) {
                        e.printStackTrace();

                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                });
                map.put("url",list);
                map.put("goodsNo",goodsNo);
                map.put("fileName",uuid);
                respones.setMessage("上传成功");
                respones.setResult(true);
                respones.setReq(map);
            }

        } catch (Exception e) {
            respones.setResult(false);
            respones.setMessage("上传失败");
        }

        return respones;

    }

    //生成商品编号 码值+6位随机数+4位序列
    public String creatGoodsNo(String codeName)throws Exception{
        StringBuffer goodsNo=new StringBuffer(dealersGoodsService.getCodeValueByName(codeName, CodeEnum.userType.getCode()));
        if(goodsNo.length()>0){
            goodsNo.append(RandomUtil.number(6)).append(NumberUtil.integer(4));
        }
        return  goodsNo.toString();

    }

    @ApiOperation(value = "经销商获报表", notes = "经销商获报表")
    @ResponseBody
    @RequestMapping(value = "/getReport", method = RequestMethod.POST)
    public Respones getReport(@RequestParam String disNo,
                              @RequestParam(required = false) Integer type,
                              @RequestParam String dateType,
                              @RequestParam(required = false) String date) {
        Respones cmd=new Respones();
        try{
            Map<String,Object> list=dealersGoodsService.getReport(disNo,type,dateType,date);
            cmd.setReq(list);
            cmd.setResult(true);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            cmd.setMessage(Context.GET_LIST_FALSE);
            cmd.setResult(false);
        }
        return cmd;
    }


}
