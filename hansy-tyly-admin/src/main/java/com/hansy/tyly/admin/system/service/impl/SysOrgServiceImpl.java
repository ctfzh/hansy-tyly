package com.hansy.tyly.admin.system.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.hansy.tyly.admin.dao.mapper.SysOrgMapper;
import com.hansy.tyly.admin.dao.mapper.SysRUserOrgMapper;
import com.hansy.tyly.admin.dao.model.SysMenu;
import com.hansy.tyly.admin.dao.model.SysOrg;
import com.hansy.tyly.admin.dao.model.SysRRoleMenu;
import com.hansy.tyly.admin.dao.model.SysRUserOrg;
import com.hansy.tyly.admin.system.service.ISysOrgService;
import com.hansy.tyly.admin.utils.ValidUtil;
import com.hansy.tyly.admin.utils.constant.SysCodeConstant;
import com.hansy.tyly.core.excepiton.ParameterException;

@Service
public class SysOrgServiceImpl implements ISysOrgService{
	
	@Resource
	private SysOrgMapper sysOrgMapper;
	
	@Resource
	private SysRUserOrgMapper sysRUserOrgMapper;
	
	@Override
	public List<SysOrg> queryOrgList(){
		return sysOrgMapper.selectAll();
	}

	@Override
	public void saveOrg(SysOrg sysOrg){
		if(!ValidUtil.valid(sysOrg.getOrgId())) throw new ParameterException("机构ID为空");
		if(!ValidUtil.valid(sysOrg.getOrgName())) throw new ParameterException("机构名称为空");
		if(!ValidUtil.valid(sysOrg.getOrgType())) throw new ParameterException("机构类型为空");
		if(!ValidUtil.valid(sysOrg.getParentOrgId())) throw new ParameterException("上级机构ID为空");
		if(!ValidUtil.valid(sysOrg.getStatus())) throw new ParameterException("机构状态为空");
		sysOrg.setInsertTime(new Date());
		sysOrg.setUpdateTime(new Date());
		sysOrgMapper.insert(sysOrg);
	}
	
	@Override
	public void updateOrg(SysOrg sysOrg){
		if(!ValidUtil.valid(sysOrg.getOrgId())) throw new ParameterException("机构ID为空");
		if(!ValidUtil.valid(sysOrg.getOrgName())) throw new ParameterException("机构名称为空");
		if(!ValidUtil.valid(sysOrg.getOrgType())) throw new ParameterException("机构类型为空");
		if(!ValidUtil.valid(sysOrg.getParentOrgId())) throw new ParameterException("上级机构ID为空");
		if(!ValidUtil.valid(sysOrg.getStatus())) throw new ParameterException("机构状态为空");
		sysOrg.setUpdateTime(new Date());
		sysOrgMapper.updateByPrimaryKeySelective(sysOrg);
	}
	
	@Override
	public void deleteOrg(SysOrg sysOrg){
		if(!ValidUtil.valid(sysOrg.getOrgId())) throw new ParameterException("机构ID为空");
		
		if (sysOrg.getStatus().equals(SysCodeConstant.STATUS_YES)) {
			sysOrg.setStatus(SysCodeConstant.STATUS_NO);
			//判断机构下是否有有效员工
			Example example = new Example(SysRRoleMenu.class);
	        Example.Criteria criteria = example.createCriteria();
	        criteria.andEqualTo("orgId", sysOrg.getOrgId())
	                .andEqualTo("status", SysCodeConstant.STATUS_YES);
	        List<SysRUserOrg> SysRUserOrg = sysRUserOrgMapper.selectByExample(example);
	        if (SysRUserOrg.size() > 0) {
	            throw new ParameterException("该菜单已被绑定角色，不能禁用");
	        }
			
			//判断机构下是否有子机构
			Example example1 = new Example(SysOrg.class);
	        Example.Criteria criteria1 = example1.createCriteria();
	        criteria1.andEqualTo("parentOrgId", sysOrg.getParentOrgId())
	                .andEqualTo("status", SysCodeConstant.STATUS_YES);
	        List<SysOrg> sysOrgs = sysOrgMapper.selectByExample(example1);
	        if (sysOrgs.size() > 0) {
	            throw new ParameterException("该机构下有有效的子机构，不能禁用");
	        }
		}else{
			sysOrg.setStatus(SysCodeConstant.STATUS_YES);
            //判断是否有无效的父菜单
            Example example = new Example(SysMenu.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("parentOrgId", sysOrg.getParentOrgId())
                    .andEqualTo("status", SysCodeConstant.STATUS_NO);
            List<SysOrg> sysOrgs = sysOrgMapper.selectByExample(example);
            if (sysOrgs.size() > 0) {
                throw new ParameterException("父菜单为无效，不能直接启用子菜单");
            }
		}
		sysOrg.setUpdateTime(new Date());
		sysOrgMapper.updateByPrimaryKeySelective(sysOrg);
	}
}
