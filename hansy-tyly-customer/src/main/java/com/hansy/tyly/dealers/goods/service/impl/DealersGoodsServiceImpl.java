package com.hansy.tyly.dealers.goods.service.impl;

import com.github.pagehelper.Page;
import com.hansy.tyly.admin.dao.model.SysCodeInfo;
import com.hansy.tyly.admin.system.service.SysUserRoleMenuService;
import com.hansy.tyly.admin.utils.RandomUtil;
import com.hansy.tyly.common.enumClass.CodeEnum;
import com.hansy.tyly.common.enumClass.CodeNameEnum;
import com.hansy.tyly.common.utils.Context;
import com.hansy.tyly.common.utils.NumberUtil;
import com.hansy.tyly.core.helper.NPageHelper;
import com.hansy.tyly.dealers.goods.dao.mapper.*;
import com.hansy.tyly.dealers.goods.dao.model.*;
import com.hansy.tyly.dealers.goods.service.IDealersGoodsService;
import com.hansy.tyly.dealers.goods.dao.mapper.TGoodsBaseInfoDaoMapper;
import com.hansy.tyly.merchants.orders.dao.mapper.TpubDistributorInfoMapper;
import com.hansy.tyly.merchants.orders.dao.model.TpubDistributorInfo;
import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;
import org.apache.commons.lang3.StringUtils;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Transactional
@Service
public class DealersGoodsServiceImpl implements IDealersGoodsService{
    @Autowired
    private TGoodsBaseInfoMapper goodsBaseInfoMapper;
    @Autowired
    private TGoodsFilesMapper goodsFilesMapper;
    @Autowired
    private TGoodsDistributorMapper goodsDistributorMapper;
    @Autowired
    private TGoodsBaseInfoDaoMapper goodsBaseInfoDao;
    @Autowired
    private TGoodsDistributorMerMapper distributorMerMapper;
    @Autowired
    public SysUserRoleMenuService sysUserRoleMenuService;
    @Autowired
    private TBusShoppCartMapper shoppCartMapper;
    @Autowired
    private CartOfGoodsDaoMapper cartOfGoodsDaoMapper;
    @Autowired
    private TGoodsTypeMapper goodsTypeMapper;
    @Autowired
    private TpubDistributorInfoMapper distributorInfoMapper;



    @Override
    public  String getCodeValueByName(String str,String code){
        Map<String,Object> map=new HashMap<>();
        map.put("codeTypeId",code );
        map.put("status",CodeEnum.getStatusByCode(code));
        List<SysCodeInfo> list= sysUserRoleMenuService.queryCodeInfo(map);
        for (SysCodeInfo node:list) {
            if(node.getCodeInfoName().equals(str)){
                return node.getCodeInfoValue();
            }
        }
        return "";
    }



    /**
     * 经销商添加商品方法
     * @param goodsBaseInfos
     * @throws Exception
     */
    @Override
    public void addGoods(GoodsBaseInfos goodsBaseInfos) throws Exception{



        //获取商品基本信息然后保存
        TGoodsBaseInfo baseInfo= goodsBaseInfos.gettGoodsBaseInfo();

        //保存商品附件
        List<TGoodsFiles> goodsFilesList=goodsBaseInfos.getGoodsFilesList();
        if(null!=goodsFilesList && goodsFilesList.size()>0){
            goodsFilesList.forEach(node -> {
                node.setGoodsNo(baseInfo.getGoodsNo());
                node.setUploadDate(new Date());
                //设置文件状态
                node.setFileStatus(getCodeValueByName(
                        CodeNameEnum.using.getName(),
                        CodeEnum.goodsFileStatus.getCode()));

                goodsFilesMapper.insertSelective(node);
                if(node.getIsMain()){
                    baseInfo.setMianPicFileNo(node.getFileNo());
                }

            });
        }




        baseInfo.setGoodsAscription(getCodeValueByName(CodeNameEnum.dealers.getName(),CodeEnum.userType.getCode()));
        baseInfo.setGoodsStatus(getCodeValueByName(CodeNameEnum.normal.getName(),
                CodeEnum.goodsStaus.getCode()));
        //初始销量为0
        baseInfo.setGoodsSaleNum(0);
        //保存商品信息
        goodsBaseInfoMapper.insertSelective(baseInfo);

        //保存成功后将商品编号 存入 附件和经销商商品信息中
        TGoodsDistributor goodsDistributor=goodsBaseInfos.getGoodsDistributor();
        goodsDistributor.setGoodsNo(baseInfo.getGoodsNo());
        goodsDistributorMapper.insertSelective(goodsDistributor);

    }

    /**
     * 经销商修改商品细腻系
     * @param goodsBaseInfos
     * @throws Exception
     */
    @Override
    public void editGoods(GoodsBaseInfos goodsBaseInfos) throws Exception {
        //获取商品基本信息然后保存
        TGoodsBaseInfo baseInfo= goodsBaseInfos.gettGoodsBaseInfo();
        //修改商品基本信息
        if(null!=baseInfo && null!=baseInfo.getGoodsNo()){
            goodsBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);
        }
        //保存成功后将商品编号 存入 附件和经销商商品信息中
        TGoodsDistributor goodsDistributor=goodsBaseInfos.getGoodsDistributor();
        if(null!=goodsDistributor.getTableKey()){
            goodsDistributorMapper.updateByPrimaryKeySelective(goodsDistributor);
        }
        //修改商品附件
        List<TGoodsFiles> goodsFilesList=goodsBaseInfos.getGoodsFilesList();
        if(null!=goodsFilesList && goodsFilesList.size()>0 && StringUtils.isNotBlank(goodsFilesList.get(0).getFilePath())){

            List<String> newPath=new ArrayList<>();
            List<String> tempPath=new ArrayList<>();
            List<String> oldPath=new ArrayList<>();
            goodsFilesList.forEach(node ->{

                newPath.add(node.getFilePath());


            });
            if(newPath.size()>0){
                List<TGoodsFiles> oldGoodsFilesList=goodsFilesMapper.
                        selectByGoodsNos(baseInfo.getGoodsNo(),
                        getCodeValueByName(CodeNameEnum.using.getName(),CodeEnum.goodsFileStatus.getCode()),
                                getCodeValueByName(CodeNameEnum.goodsFiles.getName(),CodeEnum.fileType.getCode()));
                oldGoodsFilesList.forEach(node->{

                     oldPath.add(node.getFilePath());
                     tempPath.add(node.getFilePath());
                });
            }
            //删除后 剩下的旧链接都是不要的
            oldPath.removeAll(newPath);
            if(null!=oldPath && oldPath.size()>0){
                TGoodsFilesExample filesExample=new TGoodsFilesExample();
                TGoodsFilesExample.Criteria fileCriteria=filesExample.createCriteria();
                fileCriteria.andGoodsNoEqualTo(baseInfo.getGoodsNo());
                fileCriteria.andFileTypeEqualTo(
                        getCodeValueByName(CodeNameEnum.goodsFiles.getName(),CodeEnum.fileType.getCode()));
                fileCriteria.andFilePathIn(oldPath);
                goodsFilesMapper.deleteByExample(filesExample);


               /* oldPath.forEach(node->{

                    *//*goodsFilesMapper.updateByGoodsAndFilePath(
                            getCodeValueByName(CodeNameEnum.deleted.getName(),CodeEnum.goodsFileStatus.getCode()),
                            baseInfo.getGoodsNo(),node,
                            getCodeValueByName(CodeNameEnum.goodsFiles.getName(),CodeEnum.fileType.getCode()));*//*
                });*/
            }
            tempPath.removeAll(oldPath);

            //中间的的链接就是需要修改的
            if(null!=tempPath && tempPath.size()>0){//新增的
                tempPath.forEach(node->{
                    goodsFilesList.forEach(f->{
                        if(f.getFilePath().equals(node)){
                            goodsFilesMapper.updateByFileNameAndFilePath(f.getIsMain(),f.getFileName(),f.getFilePath());
                        }
                    });


                });
            }


            newPath.removeAll(tempPath);

            TGoodsBaseInfo oldBaseInfo=goodsBaseInfoMapper.selectByPrimaryKey(baseInfo.getGoodsNo());
            TGoodsFiles oldFile=goodsFilesMapper.selectByPrimaryKey(oldBaseInfo.getMianPicFileNo());
            //如果 文件为删除状态
            if(null!=oldFile && oldFile.getFileStatus().equals(getCodeValueByName(
                    CodeNameEnum.deleted.getName(),CodeEnum.goodsFileStatus.getCode()))){
                //先把该文件的主图状态修改为false
                oldFile.setIsMain(false);
                goodsFilesMapper.updateByPrimaryKeySelective(oldFile);
            }

            //剩下的链接就是需要新增的
            if(null!=newPath && newPath.size()>0){//新增的
                newPath.forEach(node->{
                    TGoodsFiles goodsFiles=new TGoodsFiles();
                    goodsFiles.setFilePath(node);

                    goodsFilesList.forEach(f->{
                        if(f.getFilePath().equals(node)){
                            goodsFiles.setFileName(f.getFileName());
                            goodsFiles.setIsMain(f.getIsMain());
                        }
                    });

                    goodsFiles.setFileStatus(getCodeValueByName(
                            CodeNameEnum.using.getName(),CodeEnum.goodsFileStatus.getCode()));
                    goodsFiles.setUploadDate(new Date());
                    goodsFiles.setGoodsNo(baseInfo.getGoodsNo());
                    goodsFilesMapper.insertSelective(goodsFiles);
                });
            }

            TGoodsFilesExample example=new TGoodsFilesExample();
            TGoodsFilesExample.Criteria criteria=example.createCriteria();
            criteria.andGoodsNoEqualTo(oldBaseInfo.getGoodsNo());
            criteria.andIsMainEqualTo(true);
            List<TGoodsFiles> filesList=goodsFilesMapper.selectByExample(example);
            oldBaseInfo.setMianPicFileNo(filesList.get(0).getFileNo());
            goodsBaseInfoMapper.updateByPrimaryKeySelective(oldBaseInfo);

        }




    }

    /**
     * 查询商品列表（经销商）
     * @param params
     * @return
     * @throws Exception
     */
    @Override
    public List<GoodsBaseInfos> getGoodsList(Map<String, Object> params) throws Exception {
        params.put("goodsAscription",getCodeValueByName(
                                        CodeNameEnum.dealers.getName(),CodeEnum.userType.getCode()));
        if(null!=params.get("pageNo") && (int)params.get("pageNo")!=0){
            NPageHelper.startPage(params);
        }
        return goodsBaseInfoDao.findGoodsListByParams(params);

    }

    /**
     * 经销商上下架商品
     * @param distributorMerList
     * @throws Exception
     */
    @Override
    public void editGoodsDistributorMer(TGoodsDisMerList distributorMerList) throws Exception {
        //经销商与 商户绑定的商品
        distributorMerList.getDistributorMers().forEach(distributorMer -> {

            //criteria.andOnStatusEqualTo(distributorMer.getOnStatus());
         /*   List<TGoodsDistributorMer> list=distributorMerMapper.selectByExample(example);
            if(null!=list && list.size()>1){
                throw new RuntimeException("该商品已经上架");

            }else{*/
                //当为上架的时候*/
                if(null==distributorMer.getTableKey()){
                    TGoodsDistributorMerExample example=new TGoodsDistributorMerExample();
                    TGoodsDistributorMerExample.Criteria criteria=example.createCriteria();
                    criteria.andDistributorNoEqualTo(distributorMer.getDistributorNo());
                    criteria.andMerNoEqualTo(distributorMer.getMerNo());
                    criteria.andGoodsNoEqualTo(distributorMer.getGoodsNo());
                    List<TGoodsDistributorMer> list=distributorMerMapper.selectByExample(example);
                    setOndate(distributorMer);
                    if(null!=list && list.size()>0){
                        distributorMer.setTableKey(list.get(0).getTableKey());
                        distributorMerMapper.updateByPrimaryKeySelective(distributorMer);
                    }else{
                        distributorMer.setCreateDate(new Date());
                        distributorMerMapper.insertSelective(distributorMer);
                    }
                }else{
                    TGoodsDistributorMer old= distributorMerMapper.selectByPrimaryKey(distributorMer.getTableKey());
                    if(!distributorMer.getOnStatus().equals(old.getOnStatus())){
                        setOndate(distributorMer);
                    }
                    distributorMerMapper.updateByPrimaryKeySelective(distributorMer);
                }
            /*}*/

        });





    }

    /**
     * 删除商品（经销商部分）
     * @param goodsNo
     * @throws Exception
     */
    @Override
    public void deleteGoods(String goodsNo,String distributorNo) throws Exception {
        //先查询改商品是否与该经销商有关联
        TGoodsDistributorExample example=new TGoodsDistributorExample();
        TGoodsDistributorExample.Criteria criteria=example.createCriteria();
        criteria.andDistributorNoEqualTo(distributorNo);
        criteria.andGoodsNoEqualTo(goodsNo);
        List<TGoodsDistributor> list=goodsDistributorMapper.selectByExample(example);
        if(null!=list && list.size()>0){
            //删除商品信息表
            TGoodsBaseInfo baseInfo=goodsBaseInfoMapper.selectByPrimaryKey(goodsNo);
            baseInfo.setGoodsStatus(getCodeValueByName(CodeNameEnum.delete.getName(),CodeEnum.goodsStaus.getCode()));
            goodsBaseInfoMapper.updateByPrimaryKeySelective(baseInfo);
        }else{
          throw   new RuntimeException("该商品不属于此经销商");
        }
    }

    /**
     * 查看商品详情
     * @param goodsNo
     * @throws Exception
     */
    @Override
    public GoodsBaseInfos getGoodsBaseInfo(String goodsNo) throws Exception {
        GoodsBaseInfos goodsBaseInfos=  goodsBaseInfoDao.findGoodsByGoodsNo(goodsNo,
                getCodeValueByName(CodeNameEnum.dealers.getName(),CodeEnum.userType.getCode()));

        if(null!=goodsBaseInfos){
            TGoodsFilesExample example=new TGoodsFilesExample();
            TGoodsFilesExample.Criteria criteria=example.createCriteria();
            criteria.andGoodsNoEqualTo(goodsBaseInfos.gettGoodsBaseInfo().getGoodsNo());
            criteria.andFileStatusEqualTo(getCodeValueByName(
                    CodeNameEnum.using.getName(),CodeEnum.goodsFileStatus.getCode()));
            List<TGoodsFiles> filesList=goodsFilesMapper.selectByExample(example);
            if(null!=filesList && filesList.size()>0){
                goodsBaseInfos.setGoodsFilesList(filesList);
            }

        }
        return goodsBaseInfos;
    }
    //添加购物车
    @Override
    public void addCart(TBusShoppCart shoppCart) throws Exception {
        if(null!=shoppCart.getTableKey()){
            shoppCart.setUpdateDate(new Date());
            shoppCartMapper.updateByPrimaryKeySelective(shoppCart);
        }else{
            TBusShoppCartExample example=new TBusShoppCartExample();
            TBusShoppCartExample.Criteria criteria=example.createCriteria();
            criteria.andCustNoEqualTo(shoppCart.getCustNo());
            criteria.andGoodsNoEqualTo(shoppCart.getGoodsNo());
            List<TBusShoppCart> list= shoppCartMapper.selectByExample(example);

            if(null==list || list.size()==0){

                TpubDistributorInfo distributorInfo= distributorInfoMapper.selectByPrimaryKey(shoppCart.getCustNo());
                TGoodsBaseInfo baseInfo=goodsBaseInfoMapper.selectByPrimaryKey(shoppCart.getGoodsNo());
                shoppCart.setCustName(distributorInfo.getDistributorName());
                shoppCart.setGoodsName(baseInfo.getGoodsName());
                shoppCart.setGoodsPrice(baseInfo.getMarketAmt());
                shoppCart.setInsertDate(new Date());
                shoppCart.setCustType(getCodeValueByName(CodeNameEnum.dealers.getName(),CodeEnum.userType.getCode()));
                shoppCartMapper.insertSelective(shoppCart);
            }else{
                TBusShoppCart oldshoppCart=list.get(0);
                oldshoppCart.setBuyNum(oldshoppCart.getBuyNum()+shoppCart.getBuyNum());
                oldshoppCart.setUpdateDate(new Date());
                shoppCartMapper.updateByPrimaryKeySelective(oldshoppCart);
            }

        }
    }

    //查看购物车商品列表
    @Override
    public List<CartOfGoods> getCartOfGoods(Map<String, Object> params)throws Exception {
        params.put("custType",getCodeValueByName(CodeNameEnum.dealers.getName(),CodeEnum.userType.getCode()));
        NPageHelper.startPage(params);
        return cartOfGoodsDaoMapper.findCartOfGoodsByParams(params);
    }




    //查看平台商品列表
    @Override
    public List<GoodsBaseInfos> getPlatformGoodsList(Map<String, Object> params) throws Exception {
        params.put("goodsStatus",getCodeValueByName(CodeNameEnum.normal.getName(),CodeEnum.goodsStaus.getCode()));
        params.put("onStatus",getCodeValueByName(CodeNameEnum.onSale.getName(),CodeEnum.goodsOnStatus.getCode()));
        params.put("goodsAscription",getCodeValueByName(CodeNameEnum.platform.getName(),CodeEnum.userType.getCode()));
        NPageHelper.startPage(params);
        return goodsBaseInfoDao.getPlatformGoodsList(params);
    }

    @Override
    public GoodsBaseInfos getPlatformGoodsInfo(String goodsNo) throws Exception {
        Map<String, Object> params=new HashMap<>();
        params.put("goodsNo",goodsNo);
        params.put("goodsStatus",getCodeValueByName(CodeNameEnum.normal.getName(),CodeEnum.goodsStaus.getCode()));
        params.put("onStatus",getCodeValueByName(CodeNameEnum.onSale.getName(),CodeEnum.goodsOnStatus.getCode()));
        params.put("goodsAscription",getCodeValueByName(CodeNameEnum.platform.getName(),CodeEnum.userType.getCode()));

        GoodsBaseInfos goodsBaseInfos=  goodsBaseInfoDao.getPlatformGoodsInfo(params);

        if(null!=goodsBaseInfos){
            TGoodsFilesExample example=new TGoodsFilesExample();
            TGoodsFilesExample.Criteria criteria=example.createCriteria();
            criteria.andFileStatusEqualTo(getCodeValueByName(
                    CodeNameEnum.using.getName(),CodeEnum.goodsFileStatus.getCode()));
            criteria.andGoodsNoEqualTo(goodsBaseInfos.gettGoodsBaseInfo().getGoodsNo());
            List<TGoodsFiles> filesList=goodsFilesMapper.selectByExample(example);
            if(null!=filesList && filesList.size()>0){
                goodsBaseInfos.setGoodsFilesList(filesList);
            }

        }
        return goodsBaseInfos;
    }
    /**
     * 经销商删除购物车
     * @param cartKey
     * @throws Exception
     */
    @Override
    public void deleteCart(String cartKey) throws Exception {
        List<Integer> list=new ArrayList<>();
        for (String node:cartKey.split(",")) {
            list.add(Integer.valueOf(node));
        }
        TBusShoppCartExample example=new TBusShoppCartExample();
        TBusShoppCartExample.Criteria criteria=example.createCriteria();
        criteria.andTableKeyIn(list);
        shoppCartMapper.deleteByExample(example);

    }

    /**
     * 经销商上浮商品的协议价格

     * @throws Exception
     */
    @Override
    public void editGoodsPrice(GoodsPriceDisToMers goodsPriceDisToMers) throws Exception {

       /* goodsPriceDisToMers.getDistributorMer().forEach(distributorMer -> {
            distributorMerMapper.updateByPrimaryKeySelective(distributorMer);
        });*/
       switch (goodsPriceDisToMers.getType()){
           //浮动
           case Context.PRICE_FLOAT:
               goodsPriceDisToMers.getTableKeys().forEach(node->{
                  TGoodsDistributorMer distributorMer=distributorMerMapper.selectByPrimaryKey(node);
                   BigDecimal amt=distributorMer.getGoodsAmt().add(goodsPriceDisToMers.getAmt());
                   if(amt.compareTo(new BigDecimal("0"))==-1){//小于0
                       throw new RuntimeException("浮动价格不能小于0");
                   }

                  distributorMer.setGoodsAmt(amt);
                  distributorMerMapper.updateByPrimaryKeySelective(distributorMer);
               });
               break;
           //百分比
           case Context.PRICE_PERCENT:
               goodsPriceDisToMers.getTableKeys().forEach(node->{
                   TGoodsDistributorMer distributorMer=distributorMerMapper.selectByPrimaryKey(node);
                   BigDecimal amt=distributorMer.getGoodsAmt().add(
                           distributorMer.getGoodsAmt().multiply(goodsPriceDisToMers.getAmt()));
                   if(amt.compareTo(new BigDecimal("0"))==-1){//小于0
                        throw new RuntimeException("协议价格不能小于0");
                   }

                   distributorMer.setGoodsAmt(amt);
                   distributorMerMapper.updateByPrimaryKeySelective(distributorMer);
               });
               break;
           //价格
           case Context.PRICE_PRICE:
               goodsPriceDisToMers.getTableKeys().forEach(node->{
                   TGoodsDistributorMer distributorMer=distributorMerMapper.selectByPrimaryKey(node);
                   distributorMer.setGoodsAmt(goodsPriceDisToMers.getAmt());
                   distributorMerMapper.updateByPrimaryKeySelective(distributorMer);
               });
               break;

       }



    }

    @Override
    public List<TGoodsType> getAllGoodsType(Map<String, Object> params) throws Exception {
        return goodsTypeMapper.selectByParams(params);
    }

    @Override
    public List<GoodsBaseInfos> getGoodsStocks(String disNo) throws Exception {
        return goodsBaseInfoDao.selectGoodsStocksByDisNo(
                disNo,getCodeValueByName(
                        CodeNameEnum.dealers.getName(),CodeEnum.userType.getCode()
                )
        );
    }

    @Override
    public List<Map> getGoodsDM(String disNo,String goodsNo) throws Exception {
        return distributorMerMapper.getGoodsDM(disNo,
                getCodeValueByName(CodeNameEnum.onSale.getName(),CodeEnum.goodsOnStatus.getCode()),
                goodsNo);
    }

    @Override
    public void editGoodsDM(GoodsDistirbutors distirbutorList) throws Exception {
        List<TGoodsDistributor> list=distirbutorList.getDistributorList();
        if(null!=list && !list.isEmpty()){
            list.forEach(dis->{
                goodsDistributorMapper.updateByPrimaryKeySelective(dis);
            });
        }

    }

    @Override
    public List<Map> getNotGoodsDM(String disNo, String goodsNo) throws Exception {
        return distributorMerMapper.getNotGoodsDM(disNo,
                getCodeValueByName(CodeNameEnum.onSale.getName(),CodeEnum.goodsOnStatus.getCode()),
                goodsNo);
    }
    //销售采购图   采购商品占比  出售商品占比   活跃商户数
    @Override
    public Map<String, Object> getReport(String disNo, Integer num, String dateType, String date) throws Exception {
        Map<String, Object> map=new HashMap<>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        if("month".equals(dateType) && StringUtils.isEmpty(date)){
            date=sdf.format(new Date());
        }else if("year".equals(dateType) && StringUtils.isEmpty(date)){
            date=sdf.format(new Date());
        }
        List<Integer> list=new ArrayList<>();
        if(null==num){
            num=5;
        }
        for(int i=1;i< num+1; i++ ){
            list.add(i);
        }
        //销售采购图
        List<Map<String,Object>> saleOrBuys=goodsDistributorMapper.getSaleOrBuy(disNo,dateType,date,list);

        map.put("saleOrBuys",transDate(saleOrBuys,dateType));
        //采购商品占比
        List<Map<String,Object>> buyGoods=goodsDistributorMapper.getBuyGoods(disNo,dateType,date,num);
        map.put("buyGoods",buyGoods);
        //销售商品占比
        List<Map<String,Object>> saleGoods=goodsDistributorMapper.getSaleGoods(disNo,dateType,date,num);
        map.put("saleGoods",saleGoods);
        List<Map<String,Object>> liveMers=goodsDistributorMapper.getLiveMer(disNo,dateType,date,list);


        map.put("liveMers",transDate(liveMers,dateType));
        return map;
    }

    private  List<Map<String,Object>> transDate( List<Map<String,Object>> liveMers,String dateType){
        if(null!= liveMers && liveMers.size()>0){
            liveMers.forEach(node->{
                String clickDate=node.get("clickDate").toString();
                if("month".equals(dateType)){
                    clickDate=  clickDate.substring(5,7);
                    clickDate+="月";
                }else if("year".equals(dateType)){
                    clickDate =clickDate.substring(0,4);
                    clickDate+="年";
                }else  if("day".equals(dateType)){
                    clickDate=clickDate.substring(8,clickDate.length());
                    clickDate+="日";
                }
                node.put("clickDate",clickDate);
            });
        }
        return  liveMers;
    }



    private void setOndate(TGoodsDistributorMer distributorMer){
        //判断状态上下架 设置上下架时间
        if(distributorMer.getOnStatus().equals(
                getCodeValueByName(CodeNameEnum.onSale.getName(),CodeEnum.goodsOnStatus.getCode()))){//上架
            distributorMer.setOnDate(new Date());
        }else{
            distributorMer.setDownDate(new Date());
        }
    }







}
