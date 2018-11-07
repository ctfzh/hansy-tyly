package com.hansy.tyly.admin.cust.service;

import java.util.List;
import java.util.Map;

import com.hansy.tyly.admin.base.bean.UserProfile;
import com.hansy.tyly.admin.dao.model.SysRole;
import com.hansy.tyly.admin.dao.model.SysUser;
import com.hansy.tyly.admin.dao.model.TBusiCust;
import com.hansy.tyly.admin.dao.model.TBusiOrg;

public interface BusiCustAndOrgService {
    /*新增客户*/
    String saveBusiCust(TBusiCust tBusiCust, SysUser sysUser, String roleId, UserProfile userProfile);
    /*编辑客户*/
    String editBusiCust(SysUser sysUser, String roleId,String custId,String sysUuid, UserProfile userProfile);
    /*删除客户*/
    String delBusiCust(String userId,String custId,  UserProfile userProfile);
    /*条件查询客户*/
    List<Map<String, Object>> queryBusiCust(Map<String, Object> map);
    /*修改密码*/
    String restPwd(String id, String pwd, UserProfile userProfile);
    /*查询机构角色*/
    List<SysRole> queryOrgRoles();


    /*新增机构*/
    String saveBusiOrg(TBusiOrg tBusiOrg, UserProfile userProfile);
    /*编辑客户*/
    String editBusiOrg(TBusiOrg tBusiOrg, UserProfile userProfile);
    /*删除客户*/
    String delBusiOrg(String id, UserProfile userProfile);
    /*条件查询客户*/
    List<Map<String, Object>> queryBusiOrg(Map<String,Object> map);
    /*查询有效的机构*/
    List<TBusiOrg> queryOrgs();


    /*查询有效的产品*/

}
