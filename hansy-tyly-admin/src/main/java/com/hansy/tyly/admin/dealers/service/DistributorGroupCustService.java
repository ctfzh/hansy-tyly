package com.hansy.tyly.admin.dealers.service;

import com.hansy.tyly.admin.dealers.dao.model.GroupCust;

import java.util.List;
import java.util.Map;

public interface DistributorGroupCustService {

    /**
     * 判断分组有无此成员
     *
     * @param groupNo
     *  分组编号
     * @param custNo
     *  成员编号
     * @return
     */
    GroupCust selectOne(String groupNo,String custNo);

    /**
     * 向分组中新增成员
     *
     * @param groupNo
     * 分组编号
     * @param custNo
     * 成员编号
     * @return
     */
    int insertDistributor(String groupNo, String custNo);

    /**
     * 从分组中删除成员
     *
     * @param groupNo
     * 分组编号
     * @param custNo
     * 成员编号
     * @return
     */
    int deleteDistributor(String groupNo, String custNo);

    /**
     * 移动经销商到其他分组
     *
     * @param groupNo
     * @param tableKey
     * @return
     */
    int moveToOtherGroup(String groupNo, String tableKey);
    /**
     * 根据条件列出当前分组的所有成员
     *
     * @param map
     * @return
     */
    List<GroupCust> listByGroupNo(Map<String, Object> map);
}
