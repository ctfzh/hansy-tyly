package com.hansy.tyly.admin.constant;

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

    public final static String USER_TYPE_SALE = "10003005";
    // 商户状态-待审核
    public final static String MERCHANT_STATUS_AUDIT = "10025001";
    // 商户状态-正常
    public final static String MERCHANT_STATUS_NORMAL = "10025002";
    // 商户状态-审核失败
    public final static String MERCHANT_STATUS_AUDIT_FAILD = "10025003";
    // 商户状态-冻结
    public final static String MERCHANT_STATUS_FREEZE = "10025004";

    // 分组类型-销售
    public static  final String CUST_TYPE_MERCHANT = "1006001";
    // 分组类型-经销商
    public static final String CUST_TYPE_DEALERS = "1006002";



    // 商品上架
    public final static  String GOODS_ON_STATUS_YES = "10022011";
    // 商品下架
    public final static  String GOODS_ON_STATUS_NO = "10022012";
    // 经销商角色权限
    public final static  String SALE_ROLE = "sales";
    // 经销商身份
    public final static String DEALERS_IDENTITY = "10022021";
    // 销售身份
    public final static String SALE_IDENTITY = "10022020";
    // 停止供货
    public static final String SUPPLY_STATUS_STOP = "10022018";
    // 消息未读
    public static final String IS_READ_NO = "0";
    // 消息已读
    public static final String IS_READ_OK = "1";
    // 消息类型-商户
    public static final String NEWS_TYPE_MERCHANTS = "10022008";
    // 消息类型-经销商
    public static final String NEWS_TYPE_DEALER = "10022006";
    //消息类型-销售
    public static final String NEW_TYPE_SALE = "10022007";

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

    // 经销商状态码值字段
    public final static String DEALER_STATUS = "distributorStatus";
    // 企业类型码值字段
    public final static String COMPANY_TYPE = "enterpriseType";
}
