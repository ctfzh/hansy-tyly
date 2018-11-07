package com.hansy.tyly.common.message.service;

import java.util.List;
import java.util.Map;

import com.hansy.tyly.common.orders.dao.model.TUserNews;

public interface newsService {
	List<TUserNews> getNewsList(Map<String, Object> inMap,String userNo);
	Map<String,Object> getNewsDetail(String orderNo);

	Integer isNotReadNews(String userNo) throws Exception;
}
