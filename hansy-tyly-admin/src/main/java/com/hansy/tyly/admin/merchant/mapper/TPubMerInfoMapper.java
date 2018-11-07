package com.hansy.tyly.admin.merchant.mapper;

import com.hansy.tyly.admin.dao.model.TPubMerInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TPubMerInfoMapper extends Mapper<TPubMerInfo> {

    TPubMerInfo selectByName(String merName);

    List<TPubMerInfo> listByDistributorNo(String distributorNo);
}