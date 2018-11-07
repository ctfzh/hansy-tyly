package com.hansy.tyly.admin.sale.service.impl;

import com.hansy.tyly.admin.dao.mapper.SysRoleMapper;
import com.hansy.tyly.admin.dao.mapper.SysUserMapper;
import com.hansy.tyly.admin.dao.model.SysUser;
import com.hansy.tyly.admin.sale.service.SaleService;
import com.hansy.tyly.admin.utils.ValidUtil;
import com.hansy.tyly.admin.utils.constant.SaleConstant;
import com.hansy.tyly.core.excepiton.ParameterException;
import com.hansy.tyly.core.helper.NPageHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public List<SysUser> list(Map<String, Object> params) {

        String loginId = (String) params.get("loginId");
        if(loginId == null){
            throw new ParameterException("loginId为空");
        }
        List<String> roleIds = roleMapper.selectIdByUserLoginId(loginId);

        if (roleIds.contains(SaleConstant.SALE_ROLE_ID)){
            //销售的查询
            return selectByLoginId(loginId);
        } else if(roleIds.contains(SaleConstant.SALE_ADMIN_ROLE_ID)){
            //销售主管的查询
            List<String> orgIds = userMapper.selectOrgIdByLoginId(loginId);
            // 没有部门直接返回自己的信息
            if (orgIds == null || orgIds.size() <= 0){
                return selectByLoginId(loginId);
            }
            params.put("orgIds", orgIds);
            return listByOrgId(params);
        } else {
            //上级的查询
            return listAllSale(params);
        }
    }

    @Override
    public SysUser selectById(String userId) {
        if (!ValidUtil.valid(userId)){
            throw new ParameterException("userId为空");
        }
        return userMapper.selectByPrimaryKey(userId);
    }

    public List<SysUser> listAllSale(Map<String, Object> params) {
        params.put("roleIds", SaleConstant.SALE_ROLE_IDS);
        NPageHelper.startPage(params);
        return userMapper.listByRoles(params);
    }

    public List<SysUser> listByOrgId(Map<String, Object> params){
        params.put("roleIds", SaleConstant.SALE_ROLE_IDS);
        NPageHelper.startPage(params);
        return userMapper.listByRolesAndOrgId(params);
    }

    private List<SysUser> selectByLoginId(String loginId){
        SysUser sysUser = userMapper.selectByLoginId(loginId);
        List<SysUser> users = new ArrayList<>();
        users.add(sysUser);
        return users;
    }

	@Override
	public int updateSale(SysUser sale) {
		sale.setUpdateTime(new Date());
		return userMapper.updateByPrimaryKeySelective(sale);
	}
}
