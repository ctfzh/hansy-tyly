package com.hansy.tyly.customer.system.mapper;

import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.hansy.tyly.customer.system.model.SysParameter;

import tk.mybatis.mapper.common.Mapper;

public interface SysParameterMapper extends Mapper<SysParameter> {
	
	@ResultType(String.class)
	@Select(value = "SELECT PARA_VALUE FROM SYS_PARAMETER  WHERE PARA_ID=#{paraId} AND STATUS='00001001'")
	String getParamByParaId(String paraId);
}