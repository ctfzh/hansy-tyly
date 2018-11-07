package com.hansy.tyly.admin.dao.mapper;

import com.hansy.tyly.admin.dao.model.TUserBaseInfo;
import com.hansy.tyly.admin.dao.model.TUserBaseInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TUserBaseInfoMapper extends Mapper<TUserBaseInfo> {

    int updateStatusByUserNo(@Param("userNo") String userNo, @Param("userStatus") String userStatus);

}