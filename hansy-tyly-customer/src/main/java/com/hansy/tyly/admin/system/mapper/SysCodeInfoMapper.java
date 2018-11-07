package com.hansy.tyly.admin.system.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.hansy.tyly.admin.dao.model.SysCodeInfo;

public interface SysCodeInfoMapper extends Mapper<SysCodeInfo> {

    List<SysCodeInfo> queryCodeInfo(Map<String, Object> codeTypeId);

    int deletCodeInfoByIds(List<String> codeId);

    int updateStatusById(@Param("id") String id, @Param("status") String status);

    int queryCodeInfoByTypeId(@Param("id") String id, @Param("status") String status);

	String getCodeNameByValue(@Param("code")String code);
}