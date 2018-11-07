package com.hansy.tyly.admin.log.service;

import java.util.List;
import java.util.Map;

public interface BatchLogService {

    List<Map<String,Object>> getLogLists(Map<String,Object> map);
}
