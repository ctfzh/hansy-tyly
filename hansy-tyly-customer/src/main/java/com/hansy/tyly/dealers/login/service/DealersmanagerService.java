package com.hansy.tyly.dealers.login.service;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hansy.tyly.merchants.orders.dao.model.TpubDistributorInfo;
public interface DealersmanagerService {
    /*
     * 跟新经销商信息表  
     */
      int updateMerChants(Map<String, Object> map);
      
      /*
       * 查询商户审核状态
       */
      String getMerstatus(String distributorNo);
      
      /*
       * 查看经销商基本信息
       */
      TpubDistributorInfo slelectPersonalInfo(String distributorNo);
      
      void updateInfo(String url,String fileId,String distributorNo);
      
      //上传文件名
      void  insertImgeName(String ImgeName,String ImageId);
      
      //获取用户收货地址 用户名
      Map<String, Object> getuserNameAndAddr(String distributorNo);
      
      //获取用户默认收货地址
      Map<String, Object> getuserNameAndAddres(String distributorNo);

      
      //出售订单查询
      Map<String, Object> getMapSaleInfo(String distributorNo);
      
      //购买订单查询
      Map<String, Object> getBuyInfo(String distributorNo);
      //预警提醒
      Map<String, Object> getYuJinXinXi(String distributorNo);
      //统计消费金额
      Map<String, Object> getConsumption(String merNo,Date bingindate,Date enddate);
      //消费明细
      List<Map<String, Object>> getConsumptionList(Map<String, Object> map);
      
      Map<String, Object> getBuyInfoinfo(String distributorNo);
      
      Map<String, Object> getNews(String distributorNo);
      //获取用户头像
      Map<String, Object> getUserImge(String distributorNo);
      
      

}
