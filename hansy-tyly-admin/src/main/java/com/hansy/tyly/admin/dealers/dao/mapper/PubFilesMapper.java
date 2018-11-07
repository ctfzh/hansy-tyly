package com.hansy.tyly.admin.dealers.dao.mapper;

import com.hansy.tyly.admin.dealers.dao.model.PubFiles;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PubFilesMapper extends Mapper<PubFiles> {

    List<PubFiles> selectByCustNo(String custNo);
    List<PubFiles> selectTwoDimensionCode(@Param(value="custNo") String custNo, @Param(value="fileType") String fileType);
    void deleteTwoDimensionCode(@Param(value="custNo") String custNo, @Param(value="fileType") String fileType);
}