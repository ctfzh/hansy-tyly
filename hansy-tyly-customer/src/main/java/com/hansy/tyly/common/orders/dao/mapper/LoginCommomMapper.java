package com.hansy.tyly.common.orders.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hansy.tyly.merchants.orders.dao.model.TuserBaseInfo;

import tk.mybatis.mapper.common.Mapper;

public interface LoginCommomMapper extends Mapper<TuserBaseInfo> {
	Map<String, Object> selectUserInfo(@Param("openId") String openId);
	TuserBaseInfo selectByUsername(String username);
	List<Map<String, Object>> getEnterpriseType();
    List<Map<String, Object>> getmeriPHONE(@Param("merId") String merId);
    List<Map<String, Object>> getdealeriPHONE(@Param("dealerId") String dealerId);
    Map<String, Object> selectUserInfoIsTrue(@Param("userName") String userName);
    void updateUserinfo(String openId);
    

}
