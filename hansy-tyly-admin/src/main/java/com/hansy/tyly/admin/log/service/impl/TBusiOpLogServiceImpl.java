package com.hansy.tyly.admin.log.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hansy.tyly.admin.log.mapper.TBusiOpLogMapper;
import com.hansy.tyly.admin.log.service.TBusiOpLogService;
import com.hansy.tyly.core.helper.NPageHelper;

@Service
@Transactional
public class TBusiOpLogServiceImpl implements TBusiOpLogService {
    @Autowired
    private TBusiOpLogMapper tBusiOpLogMapper;

    @Override
    public List<Map<String, Object>> getOpLogLists(Map<String, Object> map) {
        NPageHelper.startPage(map);
        return tBusiOpLogMapper.getOpLogLists(map);
    }
}
