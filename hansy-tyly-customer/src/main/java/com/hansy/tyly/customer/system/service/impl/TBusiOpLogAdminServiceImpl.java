package com.hansy.tyly.customer.system.service.impl;



import com.hansy.tyly.core.helper.NPageHelper;
import com.hansy.tyly.customer.system.mapper.TBusiOpLogMapper;
import com.hansy.tyly.customer.system.model.TBusiOpLog;
import com.hansy.tyly.customer.system.service.TBusiOpLogService;

import com.hansy.tyly.customer.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TBusiOpLogAdminServiceImpl implements TBusiOpLogService {
    @Autowired
    private TBusiOpLogMapper tBusiOpLogMapper;

    @Override
    public List<Map<String, Object>> getOpLogLists(Map<String, Object> map) {
        NPageHelper.startPage(map);
        return tBusiOpLogMapper.getOpLogLists(map);
    }

    @Override
    public void insertOpLogs(TBusiOpLog tBusiOpLog) {
        if(tBusiOpLog==null) return;
        tBusiOpLog.setSysUuid(RandomUtil.uuid());
        tBusiOpLog.setInsertTime(new Date());
        tBusiOpLog.setUpdateTime(new Date());
        tBusiOpLogMapper.insert(tBusiOpLog);
    }
}
