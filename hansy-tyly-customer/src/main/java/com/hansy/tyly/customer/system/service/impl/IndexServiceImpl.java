package com.hansy.tyly.customer.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hansy.tyly.admin.base.bean.UserProfile;
import com.hansy.tyly.admin.dao.model.SysRole;
import com.hansy.tyly.customer.system.mapper.IndexMapper;
import com.hansy.tyly.customer.system.service.IndexService;
import com.hansy.tyly.customer.utils.ConstantsUtil;

@Service
public class IndexServiceImpl implements IndexService {
	@Autowired
	private IndexMapper indexMapper;
	
	@Override
	public Map<String, String> queryCustMngGeneralData(UserProfile userProfile) {
		Map<String, Object> inMap = new HashMap<String, Object>();
		inMap.put("orgId",userProfile.getOrgId());
		inMap.put("userId", userProfile.getUserId());
		inMap.put("dcsRst", ConstantsUtil.DCS_RST_01);
		List<SysRole> sysRoles = userProfile.getSysRoles();
		for (SysRole sysRole : sysRoles) {
			if(ConstantsUtil.ORG_ADMIN_ROLE_CODE.equals(sysRole.getRoleId())){
				//具有机构管理员角色
				inMap.remove("userId");
			}
		}
		return indexMapper.queryCustMngGeneralData(inMap);
	}

	@Override
	public List<Map<String, String>> queryLastWeekMngData(UserProfile userProfile) {
		Map<String, Object> inMap = new HashMap<String, Object>();
		inMap.put("orgId",userProfile.getOrgId());
		inMap.put("userId", userProfile.getUserId());
		List<SysRole> sysRoles = userProfile.getSysRoles();
		for (SysRole sysRole : sysRoles) {
			if(ConstantsUtil.ORG_ADMIN_ROLE_CODE.equals(sysRole.getRoleId())){
				//具有机构管理员角色
				inMap.remove("userId");
			}
		}
		return indexMapper.queryLastWeekMngData(inMap);
	}

	@Override
	public Map<String, String> queryRiskGeneralData(UserProfile userProfile) {
		Map<String, Object> inMap = new HashMap<String, Object>();
		inMap.put("orgId",userProfile.getOrgId());
		inMap.put("userId", userProfile.getUserId());
		List<SysRole> sysRoles = userProfile.getSysRoles();
		for (SysRole sysRole : sysRoles) {
			if(ConstantsUtil.ORG_ADMIN_ROLE_CODE.equals(sysRole.getRoleId())){
				//具有机构管理员角色
				inMap.remove("userId");
			}
		}
		return indexMapper.queryRiskGeneralData(inMap);
	}
	
	@Override
	public List<Map<String, String>> queryWarnTrendsData(UserProfile userProfile) {
		Map<String, Object> inMap = new HashMap<String, Object>();
		inMap.put("orgId",userProfile.getOrgId());
		inMap.put("userId", userProfile.getUserId());
		List<SysRole> sysRoles = userProfile.getSysRoles();
		for (SysRole sysRole : sysRoles) {
			if(ConstantsUtil.ORG_ADMIN_ROLE_CODE.equals(sysRole.getRoleId())){
				//具有机构管理员角色
				inMap.remove("userId");
			}
		}
		return indexMapper.queryWarnTrendsData(inMap);
	}
	
}
