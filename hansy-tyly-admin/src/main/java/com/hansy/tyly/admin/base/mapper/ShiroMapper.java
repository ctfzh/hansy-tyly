package com.hansy.tyly.admin.base.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hansy.tyly.core.shiro.ShiroChainBean;

public interface ShiroMapper {

    /**
     * 获取角色
     * @return
     */
    List<String> getRoles(@Param("loginNo") String loginNo);

    /**
     * 获取许可
     * @return
     */
    List<String> getPerms(@Param("loginNo") String loginNo);

    /**
     * 获取过滤器定义
     * @return
     */
    List<ShiroChainBean> getShiroPermssionChainBeans();
}
