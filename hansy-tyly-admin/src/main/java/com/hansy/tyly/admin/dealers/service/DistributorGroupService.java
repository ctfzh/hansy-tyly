package com.hansy.tyly.admin.dealers.service;

import com.hansy.tyly.admin.dealers.dao.model.Group;
import com.hansy.tyly.core.excepiton.ParameterException;

import java.util.List;
import java.util.Map;

public interface DistributorGroupService {

    /**
     * 根据登录Id查询分组列表
     *
     * @param map
     * @return
     */
    List<Group> listByCreateNo(Map<String,Object> map);

    /**
     * 根据分组编号查询分组详细信息
     *
     * @param groupNo
     * 分组编号
     * @return
     */
    Group selectByNo(String groupNo);

    /**
     * 新增分组
     *
     * @param groupName
     * 分组名称
     * @return
     * @throws ParameterException
     */
    int insert(String groupName, String loginId) throws ParameterException;

    /**
     * 修改分组
     *
     * @param groupNo
     * 分组编号
     * @param groupName
     * 分组名称
     * @return
     * @throws ParameterException
     */
    int update(String groupNo, String groupName) throws ParameterException;

    /**
     * 逻辑删除
     *
     * @param groupNo
     * 分组编号
     * @return
     */
    int logicDel(String groupNo);

    boolean isExist(String loginId, String groupName);

}
