package com.hansy.tyly.admin.utils.constant;

import java.util.List;

public class SysCodeConstant {
    /*状态 有效*/
    public final static String STATUS_YES="00001001";
    /*状态 无效*/
    public final static String STATUS_NO="00001002";
    /*菜单类型 后台*/
    public final static String MENU_TYPE_BACK="10001001";
    /*菜单类型 前端*/
    public final static String MENU_TYPE_BEFORE="10001002";
    /*业务角色*/
    public final static String ROLE_TYPE_BEFROE="10002002";
    /*系统角色*/
    public final static String ROLE_TYPE_BACK="10002001";
    /*系统用户*/
    public final static String USER_TYPE_BACK="10003001";
    /*业务用户*/
    public final static String USER_TYPE_BEFORE="10003002";
    /*账单类型 充值*/
    public final static String BILL_TYPE_CHONGZHI="10009001";
    /*账单类型 赠送*/
    public final static String BILL_TYPE_ZENGSONG="10009002";
    /*账单类型 消费*/
    public final static String BILL_TYPE_XIAOFEI="10009003";
    /*账单类型 冲销*/
    public final static String BILL_TYPE_CHONGXIAO="10009004";
    /*产品频次类型 天*/
    public final static String PROD_FETTYPE_DAY="10011001";
    /*产品频次类型 月*/
    public final static String PROD_FETTYPE_MOTCH="10011002";

    public final static String INDICATOR_TYPE_FUHE="10008001";
    public final static String INDICATOR_TYPE_JICU="10008002";

    public final static String CUST_MNG_STATE_YES="10014001";
    public final static String CUST_MNG_STATE_NO="10014002";
    // 商品上架
    public final static  String GOODS_ON_STATUS_YES = "10022011";

    // 经销商身份
    public final static String DEALERS_IDENTITY = "10022021";
    // 销售身份
    public final static String SALE_IDENTITY = "10022020";
    // 销售角色权限
    public final static  String SALE_ROLE = "sales";
    // 销售主管权限
    public final static String SALES_ADMINISTRATOR = "salesAdministrator";
    // 订单交易状态-待付款
    public final static  String TRANS_STATUS_PAY_NO = "10024001";
    // 订单交易状态-待发货
    public final static  String TRANS_STATUS_SEND_NO = "10024002";
    // 订单交易状态-待收货
    public final static  String TRANS_STATUS_RECIEVE_NO = "10024003";
    // 订单交易状态-待退款
    public final static  String TRANS_STATUS_REFUND_NO = "10024004";
    // 订单交易状态-已成功
    public final static  String TRANS_STATUS_SUCCESS = "10024005";
    // 订单交易状态-已取消
    public final static  String TRANS_STATUS_CANCEL = "10024006";
    // 订单交易状态-已退款
    public final static  String TRANS_STATUS_REFUND_YES = "10024007";
    // 订单交易状态-已结算
    public final static  String TRANS_STATUS_SETTLEMENT = "10024008";
    // 商户状态-待审核
    public final static String MERCHANT_STATUS_AUDIT = "10025001";
    // 商户状态-正常
    public final static String MERCHANT_STATUS_NORMAL = "10025002";
    // 商户状态-审核失败
    public final static String MERCHANT_STATUS_AUDIT_FAILD = "10025003";
    // 商户状态-冻结
    public final static String MERCHANT_STATUS_FREEZE = "10025004";

    // 执行成功
    public static final String RESPONSE_STATUS_OK = "200";
    // 执行失败
    public static final String RESPONSE_STATUS_ERROR = "500";

    // 分组类型-销售
    public static  final String CUST_TYPE_MERCHANT = "1006001";
    // 分组类型-经销商
    public static final String CUST_TYPE_DEALERS = "1006002";

    // 商户类型-个体工商户
    public static final String MERCHANT_TYPE_SOHO = "10030001";
    // 商户类型-门店
    public static final String MERCHANT_TYPE_STORE = "10030002";

    // 商户状态码值字段
    public static final String MERCHANT_CODE_NAME = "distributorStatus";


    /**
     * 经销商状态 - 正常
     */
    public final static String DISTRIBUTOR_STATUS_NORMAL = "10025002";

    /**
     * 经销商状态 - 失败
     */
    public final static String DISTRIBUTOR_STATUS_FAILD = "10025003";

    /**
     * 经销商状态 - 冻结
     */
    public final static String DISTRIBUTOR_STATUS_FOREN  = "10025004";

    /**
     * 分组创建类型 - 经销商
     */
    public static final String CREATE_TYPE_DISTRIBUTOR_CODE = "10023001";

    /**
     * 分组创建类型 - 销售
     */
    public static final String CREATE_TYPE_SALE_CODE = "10023002";

    /**
     * 待审
     */
    public static final String NEED_AUDIT = "1";

    /**
     * 已审核
     */
    public static final String AUDIT_SUCCESS = "2";
    /**
     * 审核未通过
     */
    public static final String AUDIT_FAIL = "3";

    /**
     * 订单状态 - 已成功
     */
    public static final String EDIT_ORDER_STATUS = "10024005";

    /**
     * 经销商用户状态 - 冻结
     */
    public final static String DISTRIBUTOR_USER_STATUS_FOREN = "10025004";

    /**
     * 经销商用户状态 - 正常
     */
    public final static String DISTRIBUTOR_USER_STATUS_NORMAL = "10025002";

    /**
     * 订单类型 - 平台
     */
    public static final String ORDER_TYPE_PLAT = "10022007";

    /**
     * 订单类型 - 经销商
     */
    public static final String ORDER_TYPE_DISTRIBUTOR = "10022006";

    /**
     * 分组类型 - 商户
     */
    public static final String GRUOUP_TYPE_MET = "1006001";

    /**
     * 分组类型 - 经销商
     */
    public static final String GRUOUP_TYPE_DISTRIBUTOR = "1006002";

    /**
     * 拜访类型 - 商户
     */
    public static final String CUST_USER_TYPE_MET = "1006001";

    /**
     * 拜访客户类型 - 经销商
     */
    public static final String CUST_USER_TYPE_DISTRIBUTOR = "1006002";


}
