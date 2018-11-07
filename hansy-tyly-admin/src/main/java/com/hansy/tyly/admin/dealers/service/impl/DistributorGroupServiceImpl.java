package com.hansy.tyly.admin.dealers.service.impl;

import com.hansy.tyly.admin.dao.mapper.SysUserMapper;
import com.hansy.tyly.admin.dao.model.SysUser;
import com.hansy.tyly.admin.utils.RandomUtil;
import com.hansy.tyly.admin.utils.ValidUtil;
import com.hansy.tyly.admin.utils.constant.DealerConstant;
import com.hansy.tyly.admin.dealers.dao.mapper.GroupMapper;
import com.hansy.tyly.admin.dealers.dao.model.Group;
import com.hansy.tyly.admin.dealers.service.DistributorGroupService;
import com.hansy.tyly.admin.utils.constant.SaleConstant;
import com.hansy.tyly.admin.utils.constant.SysCodeConstant;
import com.hansy.tyly.core.excepiton.ParameterException;
import com.hansy.tyly.core.helper.NPageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class DistributorGroupServiceImpl implements DistributorGroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private SysUserMapper userMapper;

    public List<Group> listByCreateNo(Map<String,Object> map){

        // 设置分组类型为经销商
        map.put("groupType", SysCodeConstant.GRUOUP_TYPE_DISTRIBUTOR);

        map.put("status", SysCodeConstant.STATUS_YES);
        NPageHelper.startPage(map);
        return groupMapper.listByCreateNo(map);
    }

    public Group selectByNo(String groupNo){
        return groupMapper.selectByPrimaryKey(groupNo);
    }

    @Transactional
    public int insert(String groupName, String loginId) throws ParameterException {
        if (!ValidUtil.valid(groupName)){
            throw new ParameterException("groupName为空");
        }

        SysUser user = userMapper.selectByLoginId(loginId);
        if (user == null){
            throw new ParameterException("当前用户未登录或登录信息已失效");
        }

        if (isExist(loginId, groupName)){
            throw new ParameterException("当前分组已存在");
        }

        Group group = new Group();
        group.setGroupNo(RandomUtil.uuid());
        group.setGroupName(groupName);
        group.setCreateNo(user.getUserId());
        group.setCreateType(SysCodeConstant.CREATE_TYPE_SALE_CODE);
        group.setGroupType(SysCodeConstant.GRUOUP_TYPE_DISTRIBUTOR);
        group.setGroupStatus(SysCodeConstant.STATUS_YES);
        group.setCreateDate(new Date());
        return groupMapper.insert(group);
    }

    @Transactional
    public int update(String groupNo, String groupName) throws ParameterException {
        if (!ValidUtil.valid(groupNo)){
            throw new ParameterException("groupNo为空");
        }

        if (!ValidUtil.valid(groupName)){
            throw new ParameterException("groupName为空");
        }

        Group group = new Group();
        group.setGroupName(groupName);
        group.setGroupNo(groupNo);
        return groupMapper.updateByPrimaryKeySelective(group);
    }

    @Transactional
    public int logicDel(String groupNo) {
        if (!ValidUtil.valid(groupNo)){
            throw new ParameterException("groupNo为空");
        }

        Group group = new Group();
        group.setGroupNo(groupNo);
        group.setGroupStatus(SysCodeConstant.STATUS_NO);
        return groupMapper.updateByPrimaryKeySelective(group);
    }

    public boolean isExist(String loginId, String groupName){
        Group group = groupMapper.selectByNameAndLoginId(groupName, loginId, SysCodeConstant.GRUOUP_TYPE_DISTRIBUTOR);
        return group != null;
    }
}
