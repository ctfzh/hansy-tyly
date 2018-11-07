package com.hansy.tyly.common.orders.dao.mapper;

import com.hansy.tyly.common.orders.dao.model.TBusRefund;
import com.hansy.tyly.common.orders.dao.model.TBusRefundExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TBusRefundMapper extends Mapper<TBusRefund> {
    List<TBusRefund> getGoodsNumByOrderNo(@Param("orderNo") String orderNo);
}