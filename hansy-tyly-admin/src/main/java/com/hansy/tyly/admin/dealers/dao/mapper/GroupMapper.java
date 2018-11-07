package com.hansy.tyly.admin.dealers.dao.mapper;

import com.hansy.tyly.admin.dealers.dao.model.Group;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface GroupMapper extends Mapper<Group> {

    List<Group> listByCreateNo(Map<String,Object> map);

    /**
     * 根据ID和分组名称查询经销商分组
     *
     * @param groupName
     * 分组名称
     * @param loginId
     * 用户登录ID
     * @param groupType
     * 分组类型
     * @return
     */
    Group selectByNameAndLoginId(@Param("groupName") String groupName, @Param("loginId") String loginId,
                                 @Param("groupType") String groupType);

}