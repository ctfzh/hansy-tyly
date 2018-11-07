package com.hansy.tyly.admin.merchant.mapper;

import com.hansy.tyly.admin.dao.model.TPubGroupCust;
import com.hansy.tyly.admin.dao.model.TPubMerInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
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
    Map<String,Object> getMerchantDetail(Map<String, Object> params);

    /**
     * 选择商户,查看与经销商协议商品信息。
     */
    List<Map<String, Object>> getAgreementGoodsDetail(Map<String, Object> params);

    /**
     * 更新经销商与商户关系表
     */
    int modifyMerchants(List<String> merNo, List<String> distributorNo, Date updateTime);

    /**
     * 修改商户状态，不可登陆系统
     */
    int freezeMerchant(String status, String statusNo);

    /**
     * 条件查询商户列表
     */
    List<Map<String, Object>> searchMerchants(Map<String, Object> params);
    /**
     * 条件查询销售的商户列表
     */
    List<Map<String, Object>> searchMerchantsByLoginId(Map<String, Object> params);

    /**
     * 查询销售主管下的所有销售的所有商户
     */
    List<Map<String, Object>> getMerchantsBySaleAdmin(Map<String, Object> params);

    /**
     * 通过登录id查询销售主管下的所有销售的userId
     */
    List<String> selectAllSales(String loginId);

    /**
     * 条件查询销售主管下的所有销售的所有用户
     */
    List<Map<String, Object>> searchMerchantsBySaleAdmin(Map<String, Object> params);

    /**
     * 商户的消费总数
     */
    Double selectTotalAmount(String mer_no, List<String> orderStatus);

    /**
     * 通过loginId获取所有权限
     */
    List<String> getRolesByLoginId(String loginId,String useStatus);

    /**
     * 通过loginId获取所有分组编号
     */
    List<Map<String,Object>> getGroupsByLoginId(Map<String,Object> params);

    /**
     * 获得所有销售
     */
    List<Map<String, Object>> getAllSales(Map<String,Object> param);

    /**
     * 获得销售下的所有商户
     */
    List<Map<String, Object>> searchMerchantsBySaleLoginId(Map<String, Object> params);

    List<Map<String, Object>> getMerchantsByDealer(Map<String, Object> params);

    List<Map<String, Object>> getDealersByMerchant(Map<String, Object> params);

    int selectTotalCount(Object goodsNo);

    int deleteMerchantAndDealerGoodsAmt(List<String> merNo, List<String> distributorNo);

    List<Map<String, Object>> getDealerBySaleAndMer(Map<String, Object> param);

    List<Map<String, Object>> getDealerBySaleAdminAndMer(Map<String, Object> param);

    List<Map<String, Object>> getDealerByAdminAndMer(Map<String, Object> param);
}
