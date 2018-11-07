package com.hansy.tyly.admin.sale.dao.mapper;

import com.hansy.tyly.admin.sale.dao.model.VisitRecord;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface VisitRecordMapper extends Mapper<VisitRecord> {

    List<VisitRecord> listByStaffNo(Map<String, Object> map);

    VisitRecord selectById(String visitNo);

}