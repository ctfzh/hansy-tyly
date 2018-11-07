package com.hansy.tyly.admin.dao.mapper;

import com.hansy.tyly.admin.dao.model.TBusOrder;
import com.hansy.tyly.admin.dao.model.TBusOrderExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TBusOrderMapper extends Mapper<TBusOrder> {

    List<TBusOrder> listByOrderType(Map<String, Object> params);

    List<TBusOrder> listByOrderNos(@Param("orderNos") List<String> orderNos);

    /**
     * 批量修改订单状态
     *
     *  <ur>
     *      <li>orderNos - 订单编号集合</li>
     *      <li>oldTransStatus - 订单当前状态, 可为 null</li>
     *      <li>newTransStatus - 订单修改后的状态</li>
     *      <li>updateDate - 订单修改日期</li>
     *  </ur>
     * @return
     */
    int updateOrdersTransStatus(Map<String, Object> map);

    /**
     * 修改订单状态
     *
     * @param orderNo
     * 订单编号
     * @param oldTransStatus
     * 订单当前状态, 可为 null
     * @param newTransStatus
     * 订单修改后的状态
     * @return
     */
    int updateTransStatus(@Param("orderNo") String orderNo,
                          @Param("oldTransStatus") String oldTransStatus,
                          @Param("newTransStatus") String newTransStatus);

    int updateStatusByPrimaryKey(Map<String, Object> pmap);
}