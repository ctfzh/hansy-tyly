package com.hansy.tyly.dealers.login.Dao.mapper;

import com.hansy.tyly.merchants.orders.dao.model.TpubDistributorInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DealersManagerMapper extends Mapper<TpubDistributorInfo>{


    Map<String,Object> getuserNameAndAddr(String distributorNo);

    Map<String,Object> getSaleInfo(String distributorNo);

    Map<String,Object> getYuJinXinXi(String distributorNo);

    Map<String,Object> getBuyInfo(String distributorNo);
    
    List<Map<String, Object>> getConsumptionList(Map<String, Object> map);
    
    Map<String, Object> getConsumption(@Param("bigindate") Date bigindate,@Param("enddate") Date enddate,@Param("merNo") String merNo);
    
    Map<String,Object> getuserNameAndAddres(String distributorNo);
    
    Map<String, Object> getBuyInfoinfo(String distributorNo);
    
    Map<String, Object> getNews(String distributorNo);
    
    Map<String, Object> getUserImg(String distributorNo);

}
