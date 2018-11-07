package com.hansy.tyly.admin.base.service.impl;

import com.hansy.tyly.admin.dao.model.SysMenu;
import com.hansy.tyly.admin.base.mapper.CacheMapper;
import com.hansy.tyly.admin.base.service.CacheService;
import com.hansy.tyly.admin.dao.model.SysCodeInfo;

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
        return this.cacheMapper.selectResources(currentLoginNo);
    }
}
