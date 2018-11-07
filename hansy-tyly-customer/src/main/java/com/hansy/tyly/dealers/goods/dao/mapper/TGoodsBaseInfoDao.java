package com.hansy.tyly.dealers.goods.dao.mapper;

import com.hansy.tyly.dealers.goods.dao.model.GoodsBaseInfos;

import java.util.List;
import java.util.Map;

public interface TGoodsBaseInfoDao {
   List<GoodsBaseInfos> findGoodsListByParams(Map<String, Object> map)throws Exception;
}
