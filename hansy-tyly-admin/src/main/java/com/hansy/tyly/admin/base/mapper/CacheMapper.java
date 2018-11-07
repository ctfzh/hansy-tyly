package com.hansy.tyly.admin.base.mapper;

import com.hansy.tyly.admin.dao.model.SysCodeInfo;
import com.hansy.tyly.admin.dao.model.SysMenu;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by MIfengHe on 2017/10/24.
 */
public interface CacheMapper {

    List<SysCodeInfo> selectCodes(@Param("codeTypes") List<String> codeTypes);

    List<String> selectAuthCodePerms(@Param("loginNo") String loginNo);

    List<SysMenu> selectResources(@Param("loginNo") String loginNo);
}
