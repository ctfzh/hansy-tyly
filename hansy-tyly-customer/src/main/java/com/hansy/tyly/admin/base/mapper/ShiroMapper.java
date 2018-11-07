package com.hansy.tyly.admin.base.mapper;

import com.hansy.tyly.core.shiro.ShiroChainBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
