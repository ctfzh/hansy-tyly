package com.hansy.tyly.admin.dao.mapper;

import java.util.List;
import java.util.Map;

import com.hansy.tyly.admin.dao.model.TUserNews;
import tk.mybatis.mapper.common.Mapper;

public interface TUserNewsMapper extends Mapper<TUserNews> {

	List<TUserNews> selectNews(Map<String, Object> inMap);

    Integer selectNewsCount(Map<String, Object> inMap)throws Exception;
}