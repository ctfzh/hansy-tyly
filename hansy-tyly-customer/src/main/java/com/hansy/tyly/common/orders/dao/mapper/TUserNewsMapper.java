package com.hansy.tyly.common.orders.dao.mapper;

import com.hansy.tyly.common.orders.dao.model.TUserNews;
import com.hansy.tyly.common.orders.dao.model.TUserNewsExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TUserNewsMapper extends Mapper<TUserNews> {
    long countByExample(TUserNewsExample example);

    int deleteByExample(TUserNewsExample example);

    List<TUserNews> selectByExample(TUserNewsExample example);

    int updateByExampleSelective(@Param("record") TUserNews record, @Param("example") TUserNewsExample example);

    int updateByExample(@Param("record") TUserNews record, @Param("example") TUserNewsExample example);

	List<TUserNews> selectNews(Map<String, Object> inMap);
}