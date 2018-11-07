package com.hansy.tyly.admin.base.service.impl;

import com.hansy.tyly.admin.base.mapper.CacheMapper;
import com.hansy.tyly.admin.base.service.CacheService;
import com.hansy.tyly.admin.dao.model.SysCodeInfo;
import com.hansy.tyly.admin.dao.model.SysMenu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MIfengHe on 2017/10/24.
 */
@Service
public class CacheServiceImpl implements CacheService{
    private static final Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);
    @Autowired private CacheMapper cacheMapper;

    @Override
    @Transactional(readOnly = true)
    public Map<String, List<SysCodeInfo>> cacheCodes(List<String> codeTypes) {
        Map<String, List<SysCodeInfo>> map = new HashMap<>();
        for(String codeType: codeTypes) {
            map.put(codeType, new ArrayList<>());
        }

        List<SysCodeInfo> sysCodeInfos = cacheMapper.selectCodes(codeTypes);
        for(SysCodeInfo sysCodeInfo : sysCodeInfos) {
            map.get(sysCodeInfo.getCodeTypeId()).add(sysCodeInfo);
        }
        return map;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> cacheAuthCodes(String loginNo) {
        return this.cacheMapper.selectAuthCodePerms(loginNo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SysMenu> cacheResources(String currentLoginNo) {
        List<SysMenu> sysMenus = this.cacheMapper.selectResources(currentLoginNo);
        SysMenu sysMenu = sysMenus.get(0);
        if("backMenu".equals(sysMenu.getMenuId())){
            logger.info("##################################");
            logger.info("### 资源菜单移除backMenu记录......");
            sysMenus.remove(0);
        }

        return sysMenus;
    }
}
