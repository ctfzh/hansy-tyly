package com.hansy.tyly.customer.system.service.impl;

import java.util.List;

import com.hansy.tyly.customer.system.mapper.SysMenuMapper;
import com.hansy.tyly.customer.system.mapper.SysUserMapper;
import com.hansy.tyly.customer.system.model.SysMenu;
import com.hansy.tyly.customer.system.service.OrgUserRoleMenuService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrgUserRoleMenuServiceImpl implements OrgUserRoleMenuService {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrgUserRoleMenuServiceImpl.class);

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;
    
	@Override
	public List<SysMenu> queryMenusByUserId(String userId) {
		return sysMenuMapper.selectMenusByUserId(userId);
	}


}
