package com.hansy.tyly.admin.settlement.dao.mapper;

import com.hansy.tyly.admin.dao.model.TBusOrder;
import com.hansy.tyly.admin.settlement.dao.model.TFinaDistributorOrder;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface TFinaDistributorOrderMapper extends Mapper<TFinaDistributorOrder> {

    int insertBatchsBySettNo(@Param("orderNos") List<String> orderNos, @Param("settNo") String settNo);

    int insertBatchs(@Param("distributorOrders") List<TFinaDistributorOrder> distributorOrders);

    List<TBusOrder> listSettlementOrder(Map<String, Object> params);
}