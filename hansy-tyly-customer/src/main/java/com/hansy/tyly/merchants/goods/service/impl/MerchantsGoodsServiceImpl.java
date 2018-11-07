package com.hansy.tyly.merchants.goods.service.impl;


import com.hansy.tyly.common.enumClass.CodeEnum;
import com.hansy.tyly.common.enumClass.CodeNameEnum;
import com.hansy.tyly.core.helper.NPageHelper;
import com.hansy.tyly.merchants.goods.dao.mapper.*;
import com.hansy.tyly.merchants.goods.dao.model.*;
import com.hansy.tyly.dealers.goods.service.IDealersGoodsService;
import com.hansy.tyly.merchants.goods.service.IMerchantsGoodsService;
import com.hansy.tyly.merchants.orders.dao.mapper.TpubDistributorInfoMapper;
import com.hansy.tyly.merchants.orders.dao.mapper.TpubDistributorMerMapper;
import com.hansy.tyly.merchants.orders.dao.mapper.TpubMerInfoMapper;
import com.hansy.tyly.merchants.orders.dao.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Transactional
@Service
public class MerchantsGoodsServiceImpl implements IMerchantsGoodsService {

    @Autowired
    private IDealersGoodsService dealersGoodsService;
    @Autowired
    private TGoodsBaseInfoDaoMapper tGoodsBaseInfoDaoMapper;
    @Autowired
    private TBusShoppCartMapper shoppCartMapper;
    @Autowired
    private CartOfGoodsDaoMapper cartOfGoodsDaoMapper;
    @Autowired
    private TGoodsFilesMapper goodsFilesMapper;
    @Autowired
    private TpubDistributorMerMapper distributorMerMapper;
    @Autowired
    private TpubDistributorInfoMapper distributorInfoMapper;
    @Autowired
    private TGoodsTypeMapper goodsTypeMapper;
    @Autowired
    private  TGoodsBaseInfoMapper goodsBaseInfoMapper;
    @Autowired
    private TpubMerInfoMapper merInfoMapper;

    //商户列表
    @Override
    public List<GoodsBaseInfos> getGoodsList(Map<String, Object> params) throws Exception{
        params.put("onStatus",dealersGoodsService.getCodeValueByName(
                     CodeNameEnum.onSale.getName(),CodeEnum.goodsOnStatus.getCode()));
        params.put("goodsStatus",dealersGoodsService.getCodeValueByName(
                     CodeNameEnum.normal.getName(),CodeEnum.goodsStaus.getCode()));
        NPageHelper.startPage(params);
        return tGoodsBaseInfoDaoMapper.findGoodsListByParams(params);
    }
    //商户推荐商品
    @Override
    public List<GoodsBaseInfos> getHotGoods(String merchantsNo,String disNo) throws Exception {
        return  tGoodsBaseInfoDaoMapper.getHotGoods(merchantsNo,
                    dealersGoodsService.getCodeValueByName(CodeNameEnum.onSale.getName(),CodeEnum.goodsOnStatus.getCode()),
                    dealersGoodsService.getCodeValueByName(CodeNameEnum.normal.getName(),CodeEnum.goodsStaus.getCode()),
                    disNo
                );
    }

    /**
     * 商品详情信息
     * @param goodsNo
     * @return
     * @throws Exception
     */
    @Override
    public GoodsBaseInfos getGoodsInfo(String goodsNo,String merNo,String disNo) throws Exception {
        GoodsBaseInfos goodsBaseInfos=  tGoodsBaseInfoDaoMapper.getGoodsInfo(goodsNo,merNo,disNo);

        if(StringUtils.isNotBlank(merNo)){
            //根据商户编号 产品编号
            List<Map<String,Object>> resMap=tGoodsBaseInfoDaoMapper.selectDisByGoodsNo(merNo,goodsNo);
            if(null!=resMap && resMap.size()>0){
                goodsBaseInfos.setMap(resMap.get(0));
            }
        }
        if(null!=goodsBaseInfos){
            TGoodsFilesExample example=new TGoodsFilesExample();
            TGoodsFilesExample.Criteria criteria=example.createCriteria();
            criteria.andGoodsNoEqualTo(goodsBaseInfos.getGoodsBaseInfo().getGoodsNo());
            criteria.andFileStatusEqualTo(dealersGoodsService.getCodeValueByName(
                    CodeNameEnum.using.getName(),CodeEnum.goodsFileStatus.getCode()));
            List<TGoodsFiles> filesList=goodsFilesMapper.selectByExample(example);
            if(null!=filesList && filesList.size()>0){
                goodsBaseInfos.setGoodsFilesList(filesList);
            }

        }



        return goodsBaseInfos;
    }

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

                TpubMerInfo merInfo= merInfoMapper.selectByPrimaryKey(shoppCart.getCustNo());
                TGoodsBaseInfo baseInfo=goodsBaseInfoMapper.selectByPrimaryKey(shoppCart.getGoodsNo());
                //获取经销商
                shoppCart.setGoodsPrice(cartOfGoodsDaoMapper.selectXYAmtByGoodsNo(shoppCart.getGoodsNo(),shoppCart.getCustNo()));
                shoppCart.setCustName(merInfo.getMerName());
                shoppCart.setGoodsName(baseInfo.getGoodsName());
                shoppCart.setInsertDate(new Date());
                shoppCart.setCustType(dealersGoodsService.getCodeValueByName(CodeNameEnum.Merchant.getName(),CodeEnum.userType.getCode()));
                shoppCartMapper.insertSelective(shoppCart);
            }else{
                TBusShoppCart oldshoppCart=list.get(0);
                oldshoppCart.setBuyNum(oldshoppCart.getBuyNum()+shoppCart.getBuyNum());
                oldshoppCart.setUpdateDate(new Date());
                shoppCartMapper.updateByPrimaryKeySelective(oldshoppCart);
            }

        }

    }

    @Override
    public List<CartOfGoods> getCartOfGoods(Map<String, Object> params) throws Exception {
        params.put("custType",dealersGoodsService.getCodeValueByName(
                CodeNameEnum.Merchant.getName(),CodeEnum.userType.getCode()));
        NPageHelper.startPage(params);
        return cartOfGoodsDaoMapper.findCartOfGoodsByParams(params);
    }

    /**
     * 获取商户绑定经销商列表
     * @param merNo
     * @return
     * @throws Exception
     */
    @Override
    public List<TpubDistributorInfo> getDistributorList(String merNo) throws Exception {

        return distributorInfoMapper.selectDisByMer(merNo);
    }

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

    @Override
    public List<TGoodsType> getAllGoodsType(Map<String, Object> params) throws Exception {
        return goodsTypeMapper.selectByParams(params);
    }

    @Override
    public Integer getCartOfGoodsCount(Map<String, Object> params) throws Exception {
        params.put("custType",dealersGoodsService.getCodeValueByName(
                CodeNameEnum.Merchant.getName(),CodeEnum.userType.getCode()));
        return cartOfGoodsDaoMapper.findCartOfGoodsByParams(params).size();
    }

    @Override
    public List<Map<String, Object>> getDisListByMer(String merNo) throws Exception {

        return distributorMerMapper.selectDisByMer(merNo);
    }
}
