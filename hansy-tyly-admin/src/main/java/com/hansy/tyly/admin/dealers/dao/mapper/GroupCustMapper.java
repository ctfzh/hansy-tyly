package com.hansy.tyly.admin.dealers.dao.mapper;

import com.hansy.tyly.admin.dealers.dao.model.GroupCust;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface GroupCustMapper extends Mapper<GroupCust> {

    List<GroupCust> listByGroupNo(Map<String, Object> map);

    GroupCust selectByGroupNoAndCustNo(@Param("groupNo") String groupNo, @Param("custNo") String custNo);

    int deleteByGroupNoAndCustNo(@Param("groupNo") String groupNo, @Param("custNo") String custNo);

}