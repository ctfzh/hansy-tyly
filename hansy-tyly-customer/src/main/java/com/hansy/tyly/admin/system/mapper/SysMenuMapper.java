package com.hansy.tyly.admin.system.mapper;

import com.hansy.tyly.admin.dao.model.SysMenu;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysMenuMapper extends Mapper<SysMenu>{

    List<SysMenu> selectAllBack(@Param("menuType") String menuType, @Param("status") String status);

    List<Map<String,Object>> queryMenuByMenuType(@Param("menuType") String menuType, @Param("status") String status);

    List<SysMenu> queryMenusOnType(@Param("menuType") String menuType, @Param("status") String status);

    int queryCountMenuByMenuName(String menuName);

    int countMenusByMenuType(String menuType);
}