package com.hansy.tyly.merchants.mer.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.fastjson.JSONObject;
import com.hansy.tyly.merchants.orders.dao.model.TpubDistributorMer;
import tk.mybatis.mapper.common.Mapper;
public interface MerchantsMangerDao extends Mapper<TpubDistributorMer> {
	/**
	 * 通过经销商编号和商户编号查询
	 * @param mId 商户编号
	 * @param dId 经销商编号
	 */
	Map<String, Object> selectByMIdAndDId(@Param("mId") String mId, @Param("dId") String dId);
	
	List<Map<String, Object>> MerchantsDealersInfo(@Param("mId") String mId);
	
	 void insertToken(@Param("token") String token, @Param("count") Integer count,@Param("type") String type);
	 
	 void updateToken(@Param("count") Integer count,@Param("type") String type);
	 
	 Map<String,Object> selectToken(@Param("type") String type); 
}
