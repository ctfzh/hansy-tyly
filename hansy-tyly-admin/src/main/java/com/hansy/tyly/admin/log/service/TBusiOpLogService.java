package com.hansy.tyly.admin.log.service;

import java.util.List;
import java.util.Map;

public interface TBusiOpLogService {

    List<Map<String,Object>> getOpLogLists(Map<String,Object> map);
}
