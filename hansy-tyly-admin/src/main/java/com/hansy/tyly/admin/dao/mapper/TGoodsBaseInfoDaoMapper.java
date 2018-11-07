package com.hansy.tyly.admin.dao.mapper;


import com.hansy.tyly.admin.dao.model.GoodsBaseInfos;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface TGoodsBaseInfoDaoMapper {
   List<GoodsBaseInfos> findGoodsListByParams(Map<String, Object> map)throws Exception;

   List<GoodsBaseInfos> getHotGoods(@Param("merNo") String merchantsNo, @Param("onStatus") String onStatus,
                                    @Param("goodsStatus") String goodsStatus
   )throws Exception;

   GoodsBaseInfos getGoodsInfo(@Param("goodsNo") String goodsNo)throws Exception;
}
