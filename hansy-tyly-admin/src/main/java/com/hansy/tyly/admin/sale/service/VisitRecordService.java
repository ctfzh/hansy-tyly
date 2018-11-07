package com.hansy.tyly.admin.sale.service;

import com.hansy.tyly.admin.sale.dao.model.VisitRecord;

import java.util.List;
import java.util.Map;

public interface VisitRecordService {

    /**
     * 根据销售编号获取销售拜访记录，包含文件
     *
     * @param map
     * @return
     */
    List<VisitRecord> listByStaffNo(Map<String, Object> map);

    /**
     * 获取个人销售拜访记录
     *
     * @param map
     *
     * @return
     */
    List<VisitRecord> list(Map<String, Object> map);
    /**
     * 根据销售拜访记录编号获取拜访记录详情，包含文件
     *
     * @param visitNo
     * 销售拜访记录编号
     * @return
     */
    VisitRecord selectById(String visitNo);

    /**
     * 新增拜访记录
     *
     * @param visitRecord
     * @return
     */
    int insert(VisitRecord visitRecord, String loginId);
}
