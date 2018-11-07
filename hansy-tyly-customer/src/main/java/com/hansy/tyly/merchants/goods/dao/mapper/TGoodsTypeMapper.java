package com.hansy.tyly.merchants.goods.dao.mapper;

import com.hansy.tyly.merchants.goods.dao.model.TGoodsType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface TGoodsTypeMapper extends Mapper<TGoodsType> {
    List<TGoodsType> selectByParams(Map<String, Object> params)throws Exception;
}