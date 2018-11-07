package com.hansy.tyly.dealers.goods.dao.mapper;

import com.github.pagehelper.Page;
import com.hansy.tyly.dealers.goods.dao.model.GoodsBaseInfos;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public interface TGoodsBaseInfoDaoMapper {
   List<GoodsBaseInfos> findGoodsListByParams(Map<String, Object> map)throws Exception;

   GoodsBaseInfos findGoodsByGoodsNo(@Param("goodsNo") String goodesNo,@Param("goodsAscription")String goodsAscription)throws Exception;

   List<GoodsBaseInfos> getPlatformGoodsList(Map<String, Object> map)throws Exception;

    GoodsBaseInfos getPlatformGoodsInfo(Map<String, Object> params)throws Exception;

    List<GoodsBaseInfos> selectGoodsStocksByDisNo(@Param("disNo") String disNo,@Param("goodsAscription")String goodsAscription)throws Exception;
}
