package com.hansy.tyly.admin.base.service;

import com.hansy.tyly.core.shiro.ShiroChainBean;

import java.util.List;

/**
 * Created by MIfengHe on 2017/10/23.
 */
public interface ShiroService {

    /**
     * 获取角色
     * @return
     */
    List<String> getRoles(String principal);

    /**
     * 获取许可
     * @return
     */
    List<String> getPerms(String principal);

    /**
     * 获取过滤器定义
     * @return
     */
    List<ShiroChainBean> getShiroPermssionChainBeans();
}
