package com.hansy.tyly.admin.base.service;

import com.hansy.tyly.admin.dao.model.SysMenu;
import com.hansy.tyly.admin.dao.model.SysCodeInfo;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by MIfengHe on 2017/10/24.
 */
public interface CacheService {

    Map<String,List<SysCodeInfo>> cacheCodes(List<String> codeTypes);

    @Transactional(readOnly = true)
    List<String> cacheAuthCodes(String loginNo);

    List<SysMenu> cacheResources(String currentLoginNo);
}
