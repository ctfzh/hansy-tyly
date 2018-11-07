package com.hansy.tyly.admin.dealers.service.impl;

import com.hansy.tyly.admin.dealers.dao.mapper.DistributorInfoMapper;
import com.hansy.tyly.admin.dealers.dao.mapper.GroupCustMapper;
import com.hansy.tyly.admin.dealers.dao.model.DistributorInfo;
import com.hansy.tyly.admin.dealers.dao.model.Group;
import com.hansy.tyly.admin.dealers.dao.model.GroupCust;
import com.hansy.tyly.admin.dealers.service.DistributorGroupCustService;
import com.hansy.tyly.admin.utils.RandomUtil;
import com.hansy.tyly.admin.utils.ValidUtil;
import com.hansy.tyly.admin.utils.constant.SysCodeConstant;
import com.hansy.tyly.core.excepiton.ParameterException;
import com.hansy.tyly.core.helper.NPageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class DistributorGroupCustServiceImpl implements DistributorGroupCustService {

    @Autowired
    private GroupCustMapper custMapper;

    @Autowired
    private DistributorInfoMapper distributorInfoMapper;

    @Autowired
    private DistributorGroupServiceImpl groupService;

    public GroupCust selectOne(String groupNo,String custNo){
        return custMapper.selectByGroupNoAndCustNo(groupNo, custNo);
    }

    @Override
    public int insertDistributor(String groupNo,String custNo) {
        if (!ValidUtil.valid(groupNo)){
            throw new ParameterException("groupNo为空");
        }
        if (!ValidUtil.valid(custNo)){
            throw new ParameterException("custNo为空");
        }

        DistributorInfo distributorInfo = distributorInfoMapper.selectByNo(custNo, SysCodeConstant.DISTRIBUTOR_STATUS_NORMAL);
        if (distributorInfo == null){
            throw new ParameterException("该经销商不存在");
        }

        Group group = groupService.selectByNo(groupNo);
        if (group == null || !SysCodeConstant.STATUS_YES.equals(group.getGroupStatus())){
            throw new ParameterException("该分组不存在");
        }

        if (selectOne(groupNo, custNo) != null){
            throw new ParameterException("该经销商已在此分组中");
        }

        GroupCust groupCust = new GroupCust();
        groupCust.setTableKey(RandomUtil.uuid());
        groupCust.setGroupNo(groupNo);
        groupCust.setGroupName(group.getGroupName());
        groupCust.setCustNo(custNo);
        groupCust.setInsertDate(new Date());
        return custMapper.insert(groupCust);
    }

    @Override
    public int deleteDistributor(String groupNo, String custNo) {
        if (!ValidUtil.valid(groupNo)){
            throw new ParameterException("groupNo为空");
        }
        if (!ValidUtil.valid(custNo)){
            throw new ParameterException("custNo为空");
        }
        if (selectOne(groupNo, custNo) == null){
            throw new ParameterException("该经销商不在此分组中");
        }
        return custMapper.deleteByGroupNoAndCustNo(groupNo, custNo);
    }

    public int moveToOtherGroup(String groupNo, String tableKey){
        if (!ValidUtil.valid(groupNo)){
            throw new ParameterException("groupNo为空");
        }
        if (!ValidUtil.valid(tableKey)){
            throw new ParameterException("tableKey为空");
        }
        GroupCust cust = custMapper.selectByPrimaryKey(tableKey);
        if (cust.getGroupNo().equals(groupNo)){
            throw new ParameterException("该经销商已在此分组中");
        }
        Group group = groupService.selectByNo(groupNo);
        GroupCust newCust = new GroupCust();
        newCust.setTableKey(cust.getTableKey());
        newCust.setCustNo(group.getGroupNo());
        newCust.setCustName(group.getGroupName());
        return custMapper.updateByPrimaryKeySelective(newCust);
    }

    @Override
    public List<GroupCust> listByGroupNo(Map<String, Object> map) {
        String groupNo = (String) map.get("groupNo");
        if (!ValidUtil.valid(groupNo)){
            throw new ParameterException("groupNo为空");
        }
        Group group = groupService.selectByNo(groupNo);
        if (group == null || SysCodeConstant.STATUS_NO.equals(group.getGroupStatus())){
            throw new ParameterException("当前分组不存在");
        }

        return custMapper.listByGroupNo(map);
    }

}
