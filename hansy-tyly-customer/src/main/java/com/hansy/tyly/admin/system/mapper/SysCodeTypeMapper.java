package com.hansy.tyly.admin.system.mapper;

import com.hansy.tyly.admin.dao.model.SysCodeType;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysCodeTypeMapper extends Mapper<SysCodeType> {

    List<SysCodeType> queryCodeTypeByCodeName(Map<String, Object> map);

    int countCodeInfoOnType(List<String> ids);

    int deletCodeTypeByIds(List<String> ids);

    int updateStatusById(@Param("id") String id, @Param("status") String status);
}