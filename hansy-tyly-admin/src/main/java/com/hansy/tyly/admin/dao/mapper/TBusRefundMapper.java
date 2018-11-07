package com.hansy.tyly.admin.dao.mapper;

import com.hansy.tyly.admin.dao.model.TBusRefund;
import com.hansy.tyly.admin.dao.model.TBusRefundExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TBusRefundMapper extends Mapper<TBusRefund> {

    List<TBusRefund> getGoodsNumByOrderNo(String orderNo);
}