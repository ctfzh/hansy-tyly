package com.hansy.tyly.admin.settlement.service;

import com.hansy.tyly.admin.dao.model.TBusOrder;
import com.hansy.tyly.admin.settlement.dao.model.TFinaDistributorSett;
import com.hansy.tyly.core.excepiton.ParameterException;

import java.util.List;
import java.util.Map;

public interface DistributorOrderService {

    /**
     * 已完成的经销商订单查询
     *
     * @param params
     * @return
     */
    List<TBusOrder> listOrder(Map<String, Object> params);

    /**
     * 结算订单
     */
    boolean settle(List<String> orderNos) throws ParameterException;

    /**
     * 结算列表查询
     *
     * @param map
     * @return
     */
    List<TFinaDistributorSett> listSettlements(Map<String, Object> map);

    /**
     * 结算订单查询
     *
     * @param map
     * @return
     */
    List<TBusOrder> listSettlementOrders(Map<String, Object> map);
}
