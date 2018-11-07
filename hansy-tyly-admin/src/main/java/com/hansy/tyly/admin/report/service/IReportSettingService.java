package com.hansy.tyly.admin.report.service;

import java.util.List;
import java.util.Map;

import com.hansy.tyly.admin.dao.model.TReportConf;
import com.hansy.tyly.admin.dao.model.TReportTableConfDtl;

public interface IReportSettingService {
	
    List<TReportConf> getReportInfo(Map<String, Object> params)throws Exception;
    
    String insertOrUpdateReportInfo(TReportConf tReportConf);
    
    TReportConf getReportLIstInfo(Map<String, Object> params);
    
    List<TReportTableConfDtl> getReportcolomnInfo(Map<String, Object> params);
    
    void insertOrUpdateClomn(TReportTableConfDtl tReportTableConfDtl);
    
    void deleteClomn(Map<String, Object> map);
    
    void deleteReport(Map<String, Object> map);
    
    List<Map<String, Object>> getDataJson(Map<String,Object> map);
    
    List<TReportTableConfDtl> getContitionType(Map<String, Object> map);
    
    List<TReportTableConfDtl> getReportcolomncount(Map<String, Object> params);
    
    Map<String, Object> getReportMapLIst(Map<String, Object> map);
}  
    
