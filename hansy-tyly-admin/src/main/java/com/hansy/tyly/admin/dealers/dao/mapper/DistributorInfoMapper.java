package com.hansy.tyly.admin.dealers.dao.mapper;

import com.hansy.tyly.admin.dealers.dao.model.DistributorInfo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface DistributorInfoMapper extends Mapper<DistributorInfo> {

    List<DistributorInfo> listAll(Map<String, Object> map);

    List<DistributorInfo> listAllOnSale(Map<String, Object> map);

    List<DistributorInfo> listUngrouped(@Param("loginId") String loginId, @Param("groupType") String groupType);

    List<DistributorInfo> listByMerNo(String merNo);

    List<DistributorInfo> listByStaffNo(Map<String, Object> map);

    List<DistributorInfo> listByUserDept(Map<String, Object> map);

    List<DistributorInfo> listByOrderNos(List<String> orderNos);

    DistributorInfo selectByNo(@Param("distributorNo") String distributorNo, @Param("distributorStatus") String distributorStatus);

    DistributorInfo selectByName(String distributorName);

    BigDecimal selectSaleAmount(@Param("distributorNo") String distributorNo, @Param("orderStatus") String orderStatus);

    BigDecimal selectPayAmount(@Param("distributorNo") String distributorNo, @Param("orderStatus") String orderStatus);
}