package com.hansy.tyly.customer.system.service;

import com.hansy.tyly.customer.system.model.TBusiOpLog;

import java.util.List;
import java.util.Map;

public interface TBusiOpLogService {

    List<Map<String,Object>> getOpLogLists(Map<String, Object> map);

    void insertOpLogs(TBusiOpLog tBusiOpLog);
}
