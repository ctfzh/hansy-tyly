package com.hansy.tyly.admin.sale.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hansy.tyly.admin.dao.model.SysUser;

import tk.mybatis.mapper.common.Mapper;

public interface LoginSaleMapper extends Mapper<SysUser>{
       List<SysUser> getSaleInfo(@Param("openId") String openId);
}
