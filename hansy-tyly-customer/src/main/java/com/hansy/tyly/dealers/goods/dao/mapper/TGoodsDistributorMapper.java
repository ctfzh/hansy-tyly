package com.hansy.tyly.dealers.goods.dao.mapper;

import com.hansy.tyly.dealers.goods.dao.model.TGoodsDistributor;
import com.hansy.tyly.dealers.goods.dao.model.TGoodsDistributorExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TGoodsDistributorMapper extends Mapper<TGoodsDistributor> {

    List<Map<String,Object>> getSaleOrBuy(@Param("disNo") String disNo,@Param("dateType")  String dateType, @Param("date") String date,
                                          @Param("list")List<Integer> list
                                          );

    List<Map<String,Object>> getBuyGoods(@Param("disNo") String disNo,@Param("dateType")  String dateType, @Param("date") String date
                                        ,@Param("num")Integer num);

    List<Map<String,Object>> getSaleGoods(@Param("disNo") String disNo,@Param("dateType")  String dateType, @Param("date") String date
                                        ,@Param("num")Integer num);

    List<Map<String,Object>> getLiveMer(@Param("disNo") String disNo,@Param("dateType")  String dateType, @Param("date") String date,
                                        @Param("list")List<Integer> list);
}