package com.hansy.tyly.admin.sale.service;

import java.util.List;
import java.util.Map;

import com.hansy.tyly.admin.dao.model.TUserNews;

public interface MessageService {

	List<TUserNews> getNewsList(Map<String, Object> inMap, String userNo);

	Map getNewsDetail(String tableKey);

    Integer getNewsCount(Map<String, Object> inMap)throws Exception;
}
