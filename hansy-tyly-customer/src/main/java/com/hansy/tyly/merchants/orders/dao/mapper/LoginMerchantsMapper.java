package com.hansy.tyly.merchants.orders.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.hansy.tyly.merchants.orders.dao.model.TpubMerInfo;

import tk.mybatis.mapper.common.Mapper;

public interface LoginMerchantsMapper extends Mapper<TpubMerInfo> {
    
	void updateUserImge(@Param("headimgurl") String  headimgurl,@Param("userNo") String userNo);
	

}
