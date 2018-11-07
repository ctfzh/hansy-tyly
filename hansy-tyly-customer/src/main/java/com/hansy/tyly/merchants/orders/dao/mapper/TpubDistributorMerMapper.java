package com.hansy.tyly.merchants.orders.dao.mapper;

import com.hansy.tyly.merchants.orders.dao.model.TpubDistributorMer;
import com.hansy.tyly.merchants.orders.dao.model.TpubDistributorMerExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TpubDistributorMerMapper extends Mapper<TpubDistributorMer> {

    List<Map<String,Object>> selectDisByMer(String merNo);
}