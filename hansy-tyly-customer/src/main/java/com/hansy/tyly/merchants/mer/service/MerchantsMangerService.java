package com.hansy.tyly.merchants.mer.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.hansy.tyly.merchants.orders.dao.model.TpubMerInfo;
import com.hansy.tyly.merchants.orders.dao.model.TuserBaseInfo;

public interface MerchantsMangerService {
	/*
	 *更新商户，经销商信息表
	 */
      Map<String, Object> updateMerChantsInfo(String mId,String dId);
    /*
     * 跟新商户信息表  
     */
      int updateMerChants(Map<String, Object> map);
      
      /*
       * 查询商户审核状态
       */
      String getMerstatus(String merNo);
      /*
       * 查看商户基本信息
       */
      TpubMerInfo slelectPersonalInfo(String merNo);
      /*
       * 判断商户是否存在
       */
       List<TuserBaseInfo> selectIstrue(String openid);
       
       List<Map<String, Object>> MerchantsDealersInfo(String merNo);
       
       void insertToken(String token,Integer count,String type);
       
       void  updateToken(Integer count,String type);
       
        Map<String,Object>  selectToken(String type);
        
     
        
      
}
