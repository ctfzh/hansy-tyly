package com.hansy.tyly.admin.cust.service.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;

import com.hansy.tyly.admin.base.bean.UserProfile;
import com.hansy.tyly.admin.cust.mapper.SysRUserOrgMapper;
import com.hansy.tyly.admin.cust.mapper.TBusiCustMapper;
import com.hansy.tyly.admin.cust.mapper.TBusiOrgMapper;
import com.hansy.tyly.admin.cust.service.BusiCustAndOrgService;
import com.hansy.tyly.admin.dao.model.SysRUserOrg;
import com.hansy.tyly.admin.dao.model.SysRUserRole;
import com.hansy.tyly.admin.dao.model.SysRole;
import com.hansy.tyly.admin.dao.model.SysUser;
import com.hansy.tyly.admin.dao.model.TBusiCust;
import com.hansy.tyly.admin.dao.model.TBusiOrg;
import com.hansy.tyly.admin.system.mapper.SysRUserRoleMapper;
import com.hansy.tyly.admin.system.mapper.SysRoleMapper;
import com.hansy.tyly.admin.system.mapper.SysUserMapper;
import com.hansy.tyly.admin.utils.PasswordUtil;
import com.hansy.tyly.admin.utils.RandomUtil;
import com.hansy.tyly.admin.utils.ValidUtil;
import com.hansy.tyly.admin.utils.constant.SysCodeConstant;
import com.hansy.tyly.core.excepiton.ParameterException;
import com.hansy.tyly.core.excepiton.ServiceException;
import com.hansy.tyly.core.helper.NPageHelper;

@Service
public class BusiCustAndOrgServiceImpl implements BusiCustAndOrgService {

    private final static Logger LOGGER = LoggerFactory.getLogger(BusiCustAndOrgServiceImpl.class);

    @Autowired
    private TBusiCustMapper tBusiCustMapper;
    @Autowired
    private TBusiOrgMapper tBusiOrgMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRUserRoleMapper sysRUserRoleMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysRUserOrgMapper sysRUserOrgMapper;

    @Transactional
    @Override
    public String saveBusiCust(TBusiCust tBusiCust, SysUser sysUser, String roleId, UserProfile userProfile) {
        if (tBusiCust == null) throw new ParameterException("tBusiCust为空");
        if (sysUser == null) throw new ParameterException("sysUser为空");
        if (!ValidUtil.valid(sysUser.getLoginId())) throw new ParameterException("账号为空");
        if (!ValidUtil.valid(tBusiCust.getOrgId())) throw new ParameterException("机构为空");
        if (!ValidUtil.valid(sysUser.getUserName())) throw new ParameterException("姓名为空");
        //if (!ValidUtil.valid(sysUser.getUserTel())) throw new ParameterException("电话号码为空");
        if (!ValidUtil.valid(sysUser.getUserDept())) throw new ParameterException("部门为空");
        if (!ValidUtil.valid(roleId)) throw new ParameterException("角色为空");
        //查询登陆账号是否已经存在
        SysUser osysUser = sysUserMapper.querySysUserByUserName(sysUser.getLoginId(), null, null);
        if (!ValidUtil.valid(osysUser)) {
            LOGGER.info("账号已经存在");
            throw new ParameterException("客户账号已经存在");
        }
        //插入用户表信息
        String userId = RandomUtil.uuid();
        sysUser.setUserId(userId);
        sysUser.setInsertUserId(userProfile.getUserId());
        sysUser.setUserPwd(PasswordUtil.md5("123456"));
        sysUser.setUserType(SysCodeConstant.USER_TYPE_BEFORE);
        sysUser.setStatus(SysCodeConstant.STATUS_YES);
        sysUser.setInsertTime(new Date());
        sysUser.setUserTel(sysUser.getLoginId());
        sysUserMapper.insert(sysUser);
        //插入客户信息表
        /*String custId=RandomUtil.uuid();
        tBusiCust.setCustId(custId);
        tBusiCust.setInsertTime(new Date());
        tBusiCust.setCustName(sysUser.getUserName());
        tBusiCust.setCustTel(sysUser.getUserTel());
        tBusiCust.setInsertUserId(userProfile.getUserId());
        tBusiCust.setStatus(SysCodeConstant.STATUS_YES);
        tBusiCust.setUserId(userId);
        tBusiCustMapper.insert(tBusiCust);*/

        String orgRelCusts = sysRUserOrgMapper.queryParam("orgRelCusts");
        int i = sysRUserOrgMapper.querySumOrgRelCusts(tBusiCust.getOrgId());
        if (i >= Integer.parseInt(orgRelCusts)) {
            throw new ServiceException(MessageFormat.format("一个机构只能关联{0}账户", orgRelCusts));
        }

        //插入机构关联表
        String orgId = tBusiCust.getOrgId();
        SysRUserOrg sysRUserOrg = new SysRUserOrg();
        sysRUserOrg.setInsertTime(new Date());
        sysRUserOrg.setInsertUserId(userProfile.getUserId());
        sysRUserOrg.setOrgId(orgId);
        sysRUserOrg.setSysUuid(RandomUtil.uuid());
        sysRUserOrg.setUserId(userId);
        sysRUserOrg.setStatus(SysCodeConstant.STATUS_YES);
        sysRUserOrgMapper.insert(sysRUserOrg);

        //插入角色关联
        SysRUserRole sysRUserRole = new SysRUserRole();
        sysRUserRole.setStatus(SysCodeConstant.STATUS_YES);
        sysRUserRole.setRoleId(roleId);
        sysRUserRole.setUserId(userId);
        sysRUserRole.setSysUuid(RandomUtil.uuid());
        sysRUserRole.setInsertTime(new Date());
        sysRUserRole.setInsertUserId(userProfile.getUserId());
        sysRUserRoleMapper.insert(sysRUserRole);
        return null;
    }

    @Transactional
    @Override
    public String editBusiCust(SysUser sysUser, String roleId, String custId, String sysUuid, UserProfile userProfile) {
        if (sysUser == null) throw new ParameterException("sysUser为空");
        if (!ValidUtil.valid(sysUser.getLoginId())) throw new ParameterException("账号为空");
        if (!ValidUtil.valid(sysUser.getUserId())) throw new ParameterException("userId为空");
        if (!ValidUtil.valid(sysUser.getUserName())) throw new ParameterException("姓名为空");
        //if (!ValidUtil.valid(sysUser.getUserTel())) throw new ParameterException("电话号码为空");
        if (!ValidUtil.valid(sysUser.getUserDept())) throw new ParameterException("部门为空");
        if (!ValidUtil.valid(roleId)) throw new ParameterException("角色为空");
        if (!ValidUtil.valid(sysUuid)) throw new ParameterException("角色为空");
        //修改客户表
        /*TBusiCust tBusiCust=new TBusiCust();
        tBusiCust.setUpdateTime(new Date());
        tBusiCust.setCustTel(sysUser.getUserTel());
        tBusiCust.setCustName(sysUser.getUserName());
        tBusiCust.setCustId(custId);
        tBusiCustMapper.updateByPrimaryKeySelective(tBusiCust);*/
        //修改机构关联表

        //修改登陆表
        sysUser.setUpdateTime(new Date());
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        //修改角色表
        SysRUserRole sysRUserRole = new SysRUserRole();
        sysRUserRole.setRoleId(roleId);
        sysRUserRole.setUpdateTime(new Date());
        sysRUserRole.setSysUuid(sysUuid);
        sysRUserRoleMapper.updateByPrimaryKeySelective(sysRUserRole);
        return null;
    }

    @Transactional
    @Override
    public String delBusiCust(String userId, String custId, UserProfile userProfile) {
        if (!ValidUtil.valid(userId)) throw new ParameterException("用户Id为空");
        SysUser sysUser = new SysUser();
        SysRUserRole sysRUserRole = new SysRUserRole();
        TBusiCust tBusiCust = new TBusiCust();
        SysRUserOrg sysRUserOrg = new SysRUserOrg();
        sysUser = sysUserMapper.selectByPrimaryKey(userId);
        if (sysUser.getStatus().equals(SysCodeConstant.STATUS_NO)) {
            sysUser.setStatus(SysCodeConstant.STATUS_YES);
            sysRUserRole.setStatus(SysCodeConstant.STATUS_YES);
            sysRUserOrg.setStatus(SysCodeConstant.STATUS_YES);
            String orgRelCusts = sysRUserOrgMapper.queryParam("orgRelCusts");
            int i = sysRUserOrgMapper.querySumOrgRelCusts(tBusiCust.getOrgId());
            if (i >= Integer.parseInt(orgRelCusts)) {
                throw new ServiceException(MessageFormat.format("一个机构只能关联{0}账户", orgRelCusts));
            }
        } else {
            sysUser.setStatus(SysCodeConstant.STATUS_NO);
            sysRUserRole.setStatus(SysCodeConstant.STATUS_NO);
            sysRUserOrg.setStatus(SysCodeConstant.STATUS_NO);

        }
        //修改用户表
        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        sysUser.setUpdateTime(new Date());
        sysUserMapper.updateByExampleSelective(sysUser, example);
        //修改用户关联角色
        Example example1 = new Example(SysRUserRole.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("userId", userId);
        sysRUserRole.setUpdateTime(new Date());
        sysRUserRoleMapper.updateByExampleSelective(sysRUserRole, example1);
        //修改用户机构关联表
        Example example2 = new Example(SysRUserOrg.class);
        Example.Criteria criteria2 = example2.createCriteria();
        criteria2.andEqualTo("userId", userId);
        sysRUserOrg.setUpdateTime(new Date());
        sysRUserOrgMapper.updateByExampleSelective(sysRUserOrg, example2);
        return null;
    }

    @Override
    public List<Map<String, Object>> queryBusiCust(Map<String, Object> map) {
        map.put("userType", SysCodeConstant.USER_TYPE_BEFORE);
        NPageHelper.startPage(map);
        List<Map<String, Object>> mapList = tBusiCustMapper.queryCustDetailByCondtion(map);
        return mapList;
    }

    @Override
    public String restPwd(String id, String pwd, UserProfile userProfile) {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(id);
        sysUser.setUserPwd(PasswordUtil.md5("123456"));
        sysUserMapper.updateByPrimaryKeySelective(sysUser);
        return null;
    }

    @Override
    public List<SysRole> queryOrgRoles() {
        Example example = new Example(SysRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", SysCodeConstant.STATUS_YES)
                .andEqualTo("roleType", SysCodeConstant.ROLE_TYPE_BEFROE);
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(example);
        return sysRoles;
    }

    @Override
    public String saveBusiOrg(TBusiOrg tBusiOrg, UserProfile userProfile) {
        if (tBusiOrg == null) throw new ParameterException("机构信息为空");
        if (!ValidUtil.valid(tBusiOrg.getOrgName())) throw new ParameterException("机构名称为空");
        if (!ValidUtil.valid(tBusiOrg.getOrgAddr())) throw new ParameterException("机构地址为空");
        if (!ValidUtil.valid(tBusiOrg.getOrgIndustryType())) throw new ParameterException("机构行业为空");
        if (!ValidUtil.valid(tBusiOrg.getOrgTel())) throw new ParameterException("机构电话为空");
        //查询机构名称是否已经存在
        TBusiOrg tBusiOrg1 = tBusiOrgMapper.queryTBusiOrgByOrgName(tBusiOrg.getOrgName().trim());
        if (tBusiOrg1 != null) {
            throw new ParameterException("机构名称已经存在");
        }
        tBusiOrg.setOrgId(RandomUtil.uuid());
        tBusiOrg.setInsertTime(new Date());
        tBusiOrg.setBal(new BigDecimal(0.00));
        tBusiOrg.setOrgName(tBusiOrg.getOrgName().trim());
        tBusiOrg.setInsertUserId(userProfile.getUserId());
        tBusiOrg.setStatus(SysCodeConstant.STATUS_YES);
        tBusiOrgMapper.insert(tBusiOrg);
        return null;
    }

    @Override
    public String editBusiOrg(TBusiOrg tBusiOrg, UserProfile userProfile) {
        if (tBusiOrg == null) throw new ParameterException("机构信息为空");
        if (!ValidUtil.valid(tBusiOrg.getOrgId())) throw new ParameterException("机构id为空");
        if (!ValidUtil.valid(tBusiOrg.getOrgName())) throw new ParameterException("机构名称为空");
        if (!ValidUtil.valid(tBusiOrg.getOrgAddr())) throw new ParameterException("机构地址为空");
        if (!ValidUtil.valid(tBusiOrg.getOrgIndustryType())) throw new ParameterException("机构行业为空");
        if (!ValidUtil.valid(tBusiOrg.getOrgTel())) throw new ParameterException("机构电话为空");
        //查询机构名称是否已经存在
        TBusiOrg tBusiOrg1 = tBusiOrgMapper.queryTBusiOrgByOrgName(tBusiOrg.getOrgName());
        TBusiOrg tBusiOrg2 = tBusiOrgMapper.selectByPrimaryKey(tBusiOrg.getOrgId());
        if (!tBusiOrg2.getOrgName().equals(tBusiOrg.getOrgName())) {
            if (tBusiOrg1 != null) {
                throw new ParameterException("机构名称已经存在");
            }
        }
        tBusiOrg.setOrgName(tBusiOrg.getOrgName().trim());
        tBusiOrg.setUpdateTime(new Date());
        tBusiOrgMapper.updateByPrimaryKeySelective(tBusiOrg);
        return null;
    }

    @Override
    public String delBusiOrg(String id, UserProfile userProfile) {
        if (!ValidUtil.valid(id)) throw new ParameterException("机构id为空");
        //查询该机构下是否存在账户
        /*int i = tBusiOrgMapper.queryCountCustOnOrg(id);
        if (i > 0) {
            throw new ParameterException("机构下存在用户，不能禁用");
        }*/
        TBusiOrg tBusiOrg = new TBusiOrg();

        TBusiOrg tBusiOrg1 = tBusiOrgMapper.selectByPrimaryKey(id);
        if (tBusiOrg1 == null) throw new ParameterException("无效的Id");
        if (tBusiOrg1.getStatus().equals(SysCodeConstant.STATUS_YES)) {
            tBusiOrg.setStatus(SysCodeConstant.STATUS_NO);
        } else {
            tBusiOrg.setStatus(SysCodeConstant.STATUS_YES);
        }
        tBusiOrg.setUpdateTime(new Date());
        tBusiOrg.setOrgId(id);
        Example example = new Example(TBusiOrg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orgId", id);
        tBusiOrgMapper.updateByExampleSelective(tBusiOrg, example);
        return null;
    }

    @Override
    public List<Map<String, Object>> queryBusiOrg(Map<String, Object> map) {
        NPageHelper.startPage(map);
        List<Map<String, Object>> mapList = tBusiOrgMapper.queryBusiOrgByCondi(map);
        return mapList;
    }

    @Override
    public List<TBusiOrg> queryOrgs() {
        Example example = new Example(TBusiOrg.class);
        Example.Criteria criteria = example.createCriteria();
        Example.OrderBy orderBy = example.orderBy("insertTime").desc();
        criteria.andEqualTo("status", SysCodeConstant.STATUS_YES);
        List<TBusiOrg> tBusiOrgs = tBusiOrgMapper.selectByExample(example);
        return tBusiOrgs;
    }
}
