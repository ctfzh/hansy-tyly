package com.hansy.tyly.dealers.goods.dao.mapper;

import com.hansy.tyly.dealers.goods.dao.model.TBusShoppCart;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TBusShoppCartMapper extends Mapper<TBusShoppCart> {
    void deleteByCustNo(@Param("custNo") String buyPerson,@Param("goodsNo") String goodsNo);
}