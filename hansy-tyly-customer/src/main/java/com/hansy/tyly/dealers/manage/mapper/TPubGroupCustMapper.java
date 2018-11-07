package com.hansy.tyly.dealers.manage.mapper;

import com.hansy.tyly.dealers.manage.domain.TPubGroupCust;
import com.hansy.tyly.merchants.orders.dao.model.TpubMerInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface TPubGroupCustMapper extends Mapper<TPubGroupCust> {
    /**
     * 批量插入商户到商户分组
     */
    int insertBatch(List<TPubGroupCust> tPubGroupCusts);
    /**
     * 批量从商户分组删除商户
     */
    int deleteBatch(List<String> custNos,String groupNo);
    /**
     * 修改商户分组名
     */
    int updateGroupName(String toString, String group_name);

    List<Map<String,Object>> getGroupedMerchants(String groupNo);
}