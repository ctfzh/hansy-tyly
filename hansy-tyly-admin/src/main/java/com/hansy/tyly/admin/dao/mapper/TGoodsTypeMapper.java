package com.hansy.tyly.admin.dao.mapper;

import com.hansy.tyly.admin.dao.model.TGoodsType;
import com.hansy.tyly.admin.dao.model.TGoodsTypeExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TGoodsTypeMapper extends Mapper<TGoodsType> {

    List<TGoodsType> selectByParams(Map<String, Object> params)throws Exception;
}