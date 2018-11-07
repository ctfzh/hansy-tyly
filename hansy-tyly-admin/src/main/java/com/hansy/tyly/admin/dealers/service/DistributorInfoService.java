package com.hansy.tyly.admin.dealers.service;

import com.hansy.tyly.admin.dao.model.TPubMerInfo;
import com.hansy.tyly.admin.dealers.dao.model.DistributorInfo;
import com.hansy.tyly.core.excepiton.ParameterException;
import org.apache.ibatis.annotations.Param;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface DistributorInfoService {

    /**
     * 根据条件查询经销商列表<br/>
     * <ur>
     *     <li>如果查询人是销售，则根据销售编号查询</li>
     *     <li>如果查询人是销售主管，则根据部门查询</li>
     *     <li>如果查询人是上级，则全部查询</li>
     * </ur>
     *
     * @param map
     * @return
     */
    List<DistributorInfo> list(Map<String, Object> map) throws ParameterException;

    /**
     * 平台的查询，列出所有经销商信息
     *
     * @param map
     * @return
     * @throws ParameterException
     */
    List<DistributorInfo> listAll(Map<String, Object> map) throws ParameterException;

    /**
     * 根据登录ID列出所有未分组的经销商信息
     * @param loginId
     * @param groupType
     * @return
     */
    List<DistributorInfo> listUngrouped(String loginId, String groupType);

    List<DistributorInfo> listByMerNo(Map<String, Object> params);

    /**
     * 获取经销商的所有商户信息
     *
     * @return
     */
    List<TPubMerInfo> listMerInfos(Map<String, Object> param);

    List<TPubMerInfo> listMerInfos(String loginId, String distributorNo, String merName);

    /**
     * 根据经销商编号获取详情信息
     *
     * @param distributorNo
     * 经销商编号
     * @return
     */
    DistributorInfo selectByNo(String distributorNo) throws ParameterException;

    /**
     * 根据经销商编号获取销售额
     *
     * @param distributorNo
     * 经销商编号
     * @return
     */
    BigDecimal getSaleAmount(String distributorNo) throws ParameterException;

    /**
     * 修改经销商信息
     *
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    int updateByNo(DistributorInfo distributorInfo) throws ParameterException;

    /**
     * 根据经销商编号冻结经销商
     *
     * @param distributorNo
     * 经销商编号
     * @return
     */
    int frozen(String distributorNo) throws ParameterException;

    /**
     * 根据经销商编号恢复经销商
     *
     * @param distributorNo
     * 经销商编号
     * @return
     */
    int recovery(String distributorNo) throws ParameterException;

}
