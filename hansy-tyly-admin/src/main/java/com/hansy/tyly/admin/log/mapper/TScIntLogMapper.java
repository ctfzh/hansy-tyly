package com.hansy.tyly.admin.log.mapper;

import com.hansy.tyly.admin.dao.model.TScIntLog;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface TScIntLogMapper extends Mapper<TScIntLog> {

    List<Map<String,Object>> getLogLists(Map<String,Object> map);
}