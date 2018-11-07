package com.hansy.tyly.customer.system.mapper;

import java.util.List;
import java.util.Map;

import tk.mybatis.mapper.common.Mapper;

import com.hansy.tyly.admin.base.bean.UserProfile;
import com.hansy.tyly.admin.dao.model.SysRole;
import com.hansy.tyly.customer.system.model.SysUser;

public interface SysUserMapper extends Mapper<SysUser>{

    /**
     * @Author:YuanYan
     * @CreateAt:2017年10月23日下午5:54:45
     * @Params:
     * @Return:
     * @Description:以客户登录Id查询客户信息(登录专用)
     */
    UserProfile selectUserByLoginIdAndSataus(String loginId);
    
    List<Map<String,String>> selectSysUserList(Map<String, Object> inMap);
    
    List<SysRole> queryUserRolesByUserId(Map<String, Object> inMap);
    
    List<Map<String,String>> selectCustMangerByUserId(Map<String, Object> inMap);

}