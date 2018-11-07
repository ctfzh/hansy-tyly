package com.hansy.tyly.admin.log.mapper;

import com.hansy.tyly.admin.dao.model.TBusiOpLog;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface TBusiOpLogMapper extends Mapper<TBusiOpLog> {

    List<Map<String, Object>> getOpLogLists(Map<String, Object> map);
}