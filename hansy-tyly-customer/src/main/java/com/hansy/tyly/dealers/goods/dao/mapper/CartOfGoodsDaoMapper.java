package com.hansy.tyly.dealers.goods.dao.mapper;

import com.hansy.tyly.dealers.goods.dao.model.CartOfGoods;

import java.util.List;
import java.util.Map;

public interface CartOfGoodsDaoMapper {
     List<CartOfGoods> findCartOfGoodsByParams(Map<String, Object> map)throws Exception;
}
