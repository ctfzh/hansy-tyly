package com.hansy.tyly.admin.dao.mapper;

import com.hansy.tyly.admin.dao.model.TReportConf;
import com.hansy.tyly.admin.dao.model.TReportConfExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

public interface TReportConfMapper extends Mapper<TReportConf> {
	
	List<TReportConf>  getReportInfo(Map<String, Object> params);
	
	void deleteReport(Map<String, Object> params);
	
	List<Map<String,Object>> getDataJson(StringBuffer str);
 	
}