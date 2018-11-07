package com.hansy.tyly.merchants.goods.dao.mapper;

import com.hansy.tyly.merchants.goods.dao.model.GoodsBaseInfos;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public interface TGoodsBaseInfoDaoMapper {
   List<GoodsBaseInfos> findGoodsListByParams(Map<String, Object> map)throws Exception;

   List<GoodsBaseInfos> getHotGoods(@Param("merNo")String merchantsNo,@Param("onStatus")String onStatus,
                                    @Param("goodsStatus")String goodsStatus,@Param("disNo")String disNo
                                    )throws Exception;

   GoodsBaseInfos getGoodsInfo(@Param("goodsNo") String goodsNo,@Param("merNo")String merNo,@Param("disNo")String  disNo)throws Exception;

    List<Map<String,Object>> selectDisByGoodsNo(@Param("merNo")String merNo,@Param("goodsNo")String goodsNo) throws Exception;
}
