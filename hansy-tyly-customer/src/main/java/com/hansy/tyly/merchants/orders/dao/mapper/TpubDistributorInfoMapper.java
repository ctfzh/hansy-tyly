package com.hansy.tyly.merchants.orders.dao.mapper;

import com.hansy.tyly.merchants.orders.dao.model.TpubDistributorInfo;
import com.hansy.tyly.merchants.orders.dao.model.TpubDistributorInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TpubDistributorInfoMapper extends Mapper<TpubDistributorInfo> {

    List<TpubDistributorInfo> selectDisByMer(String merNo)throws Exception;
}