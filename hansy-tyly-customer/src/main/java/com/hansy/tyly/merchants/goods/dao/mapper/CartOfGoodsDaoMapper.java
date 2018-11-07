package com.hansy.tyly.merchants.goods.dao.mapper;

import com.hansy.tyly.merchants.goods.dao.model.CartOfGoods;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CartOfGoodsDaoMapper {
     List<CartOfGoods> findCartOfGoodsByParams(Map<String, Object> map)throws Exception;

    BigDecimal selectXYAmtByGoodsNo(@Param("goodsNo") String goodsNo,@Param("custNo") String custNo)throws Exception;
}
