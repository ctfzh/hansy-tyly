package com.hansy.tyly.sale.personal.dao.mapper;

import com.hansy.tyly.sale.personal.dao.model.TPubVisitRecord;
import com.hansy.tyly.sale.personal.dao.model.TPubVisitRecordExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TPubVisitRecordMapper extends Mapper<TPubVisitRecord> {
    List<Map<String,Object>> getVisits(@Param("saleNo") String saleNo, @Param("dateType") String dateType,
                                       @Param("date")String date, @Param("list") List<Integer> list);

    List<Map<String,Object>> getUserCount(@Param("saleNo") String saleNo, @Param("dateType") String dateType,
                                          @Param("date")String date, @Param("list") List<Integer> list);

    List<Map<String,Object>> getConsumeCount(@Param("saleNo") String saleNo, @Param("dateType") String dateType,
                                             @Param("date")String date, @Param("list") List<Integer> list);

    List<Map<String,Object>> saleUserOrConsume(@Param("dateType") String dateType,
                                               @Param("date")String date);

    List<Map<String,Object>> saleUser(@Param("dateType") String dateType,
                                      @Param("date")String date);

    List<Map<String,Object>> consume(@Param("dateType") String dateType,
                                     @Param("date")String date);

    List<Map<String,Object>> getOneConsumeCount(@Param("saleNo") String saleNo, @Param("dateType") String dateType,
                                                @Param("date")String date, @Param("list") List<Integer> list);
}