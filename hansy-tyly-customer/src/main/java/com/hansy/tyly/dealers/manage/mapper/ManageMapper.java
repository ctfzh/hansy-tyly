package com.hansy.tyly.dealers.manage.mapper;

import com.hansy.tyly.dealers.manage.domain.TUserNews;
import com.hansy.tyly.dealers.manage.domain.TPubMerInfo;
import com.hansy.tyly.merchants.orders.dao.model.TpubMerInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Auther: 18383
 * @Date: 2018/8/7 10:12
 * @Description:
 */
public interface ManageMapper extends Mapper<TPubMerInfo> {
    /**
     * 通过经销商no查询所属商户
     */
    List<Map<String, Object>> getAllMerchantsByDealer(Map<String, Object> param);

    /**
     * 上层获得所有商户信息
     */
    List<Map<String, Object>> getAllMerchants(Map<String, Object> param);

    /**
     * 获得商户的详细信息
     */
    List<Map<String,Object>> getMerchantDetail(Map<String, Object> params);

    /**
     * 选择商户,查看与经销商协议商品信息。
     */
    List<Map<String, Object>> getAgreementGoodsDetail(Map<String, Object> params);

    /**
     * 停止供货接口
     * 更改经销商与商户供货信息表
     */
    int stopSupply(Map<String, Object> params);
    /**
     * 通过供应商选择商户
     */
    List<String> selectUserNoByDistributorNo(Map<String,Object> map);

    /**
     * 向用户消息信息表插入通知消息
     */
    int pushStopSupplyMessage(List<TUserNews> newsList);

    /**
     * 条件查询商户列表
     */
    List<Map<String, Object>> searchMerchants(Map<String, Object> params);

    /**
     * 通过distributor_no和条件查询商户列表
     */
    List<Map<String, Object>> searchMerchantsByNo(Map<String, Object> params);
    /**
     * 商户的消费总数
     */
    Double selectTotalAmount(String mer_no, List<String> orderStatus,String distributorNo);
    /**
     * 条件查询销售主管下的所有销售的所有用户
     */
    List<Map<String, Object>> searchMerchantsBySaleAdmin(Map<String, Object> params);
    /**
     * 通过登录id查询销售主管下的所有销售的userId
     */
    List<String> selectAllSales(String loginId);
    /**
     * 查询销售主管下的所有销售的所有商户
     */
    List<Map<String, Object>> getMerchantsBySaleAdmin(Map<String, Object> params);

    List<Map<String, Object>> getGroupsByLoginId(Map<String, Object> params);

    List<Map<String, Object>> getNoGroupedManchats(Map<String, Object> params);

    List<Map<String, Object>> getAllSales(Map<String, Object> param);

    int selectTotalCount(Object goodsNo);
}
