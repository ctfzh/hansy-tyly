package com.hansy.tyly.admin.sale.dao.mapper;

import com.hansy.tyly.admin.sale.dao.model.VisitFiles;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface VisitFilesMapper extends Mapper<VisitFiles> {

    int insertBacth(@Param("visitFiles") List<VisitFiles> visitFiles);

}