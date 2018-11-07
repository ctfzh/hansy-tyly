package com.hansy.tyly.admin.dao.mapper;

import com.hansy.tyly.admin.dao.model.TReportTableConfDtl;
import com.hansy.tyly.admin.dao.model.TReportTableConfDtlExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

public interface TReportTableConfDtlMapper extends Mapper<TReportTableConfDtl> {
  void deleteColmn(Map<String, Object> map);
  
  List<TReportTableConfDtl> selectCloum(Map<String, Object> map);
  
  List<TReportTableConfDtl> getUseClom(Map<String, Object> map);
  
  List<TReportTableConfDtl> selectCloumcount(Map<String, Object> map);
  

  
}