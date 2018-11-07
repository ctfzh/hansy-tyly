package com.hansy.tyly.admin.merchant.mapper;

import com.hansy.tyly.admin.dao.model.TPubGroupCust;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TPubGroupCustMapper extends Mapper<TPubGroupCust> {
    /**
     * 批量插入商户到商户分组
     */
    int insertBatch(List<TPubGroupCust> tPubGroupCusts);
    /**
     * 批量从商户分组删除商户
     */
    int deleteBatch(List<String> custNos, String groupNo);

    /**
     * 修改分组名
     */
    void updateGroupName(String toString, String group_name);
}