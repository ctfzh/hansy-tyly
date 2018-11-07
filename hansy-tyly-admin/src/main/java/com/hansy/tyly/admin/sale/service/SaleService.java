package com.hansy.tyly.admin.sale.service;

import com.hansy.tyly.admin.dao.model.SysUser;

import java.util.List;
import java.util.Map;

public interface SaleService {

    /**
     * 获取销售列表
     *
     * @return
     */
    List<SysUser> list(Map<String, Object> params);

    /**
     * 获取所有销售
     *
     * @param params
     * @return
     */
    List<SysUser> listAllSale(Map<String, Object> params);

    /**
     * 获取某个部门的销售
     *
     * @param params
     * @return
     */
    List<SysUser> listByOrgId(Map<String, Object> params);

    /**
     * 根据用户ID获取销售详情信息
     *
     * @param userId
     * @return
     */
    SysUser selectById(String userId);
    
    /**
     * 销售信息修改
     */
    int updateSale(SysUser sale);

}
