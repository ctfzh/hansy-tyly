package com.hansy.tyly.admin.bill.mapper;

import com.hansy.tyly.admin.dao.model.TBusiBill;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface TBusiBillMapper extends Mapper<TBusiBill> {

    List<Map<String,Object>> getListByCondit(Map<String,Object> map);

    List<Map<String, Object>> queryBillDtl(Map<String, Object> map);
}