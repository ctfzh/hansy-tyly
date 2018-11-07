package com.hansy.tyly.admin.prod.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.hansy.tyly.admin.base.bean.UserProfile;
import com.hansy.tyly.admin.dao.model.TBusiIndicator;
import com.hansy.tyly.admin.dao.model.TBusiProd;

public interface CloudProdService {

    /*查询产品*/
    List<Map<String,Object>> queryList(Map<String,Object> map);
    /*添加产品*/
    String saveOrUpdateProd(TBusiProd tBusiProd,String imageUrl, UserProfile userProfile);
    /*上下架*/
    String alterStatus(String prodId, UserProfile userProfile);
    /*查询产品已关联指标*/
    List<Map<String,Object>> getIndicatorOnProd(Map<String, Object> params);
    /*查询所有指标*/
    List<Map<String,Object>> getAllIndicator(Map<String, Object> params);
    /*动作 关联指标*/
    String doIndicatorProdRel(String prodId,List<String> indicatorId, UserProfile userProfile);
    /*删除指标*/
    String alterProIndicatorStatus(String sysUuid,UserProfile userProfile);


    /*查询树指标*/
    List<Map<String, Object>> getTreeList();
    /*查询指标*/
    List<Map<String, Object>> getIndiList(Map<String,Object> map);
    /*指标禁用启用*/
    String alterIndiStatus(String indiId,UserProfile userProfile);
    /*新增 指标*/
    String saveIndi(String type, TBusiIndicator tBusiIndicator,UserProfile userProfile);
    /*编辑指标*/
    String editIndi(TBusiIndicator tBusiIndicator,UserProfile userProfile);

    /*查询机构未关联的产品*/
    List<Map<String,Object>> queryOrgNoProdList(Map<String,Object> map);
    /*查询机构已关联产品*/
    List<Map<String,Object>> queryOrgYesProdList(Map<String,Object> map);
    /*删除已绑定产品*/
    String delOrgProd(String orgId,String prodId,UserProfile userProfile);
    /*绑定新的产品*/
    String saveOrgProd(String orgId,List<String> prodId,UserProfile userProfile);


    String uploadFile(MultipartFile multipartFile);
}
