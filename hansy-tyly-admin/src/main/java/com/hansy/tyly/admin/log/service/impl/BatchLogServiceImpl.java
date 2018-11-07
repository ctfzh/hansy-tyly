package com.hansy.tyly.admin.log.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hansy.tyly.admin.log.mapper.TScIntLogMapper;
import com.hansy.tyly.admin.log.service.BatchLogService;
import com.hansy.tyly.core.helper.NPageHelper;

@Service
@Transactional
public class BatchLogServiceImpl implements BatchLogService {
    @Autowired
    private TScIntLogMapper tScIntLogMapper;

    @Override
    public List<Map<String, Object>> getLogLists(Map<String, Object> map) {
        NPageHelper.startPage(map);
        List<Map<String, Object>> logLists = tScIntLogMapper.getLogLists(map);
        return logLists;
    }
}
