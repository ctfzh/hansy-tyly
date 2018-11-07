package com.hansy.tyly.admin.dao.mapper;

import com.hansy.tyly.admin.dao.model.TPubDistributorMer;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TPubDistributorMerMapper extends Mapper<TPubDistributorMer> {
    int merchantAndDealerUnbind(List<String> merNo, List<String> distributorNo);
}