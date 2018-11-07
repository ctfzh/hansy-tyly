package com.hansy.tyly.admin.utils;

public class Context {

	/**
	 * 成功
	 */
	public final static String REGISTER_TRUE = "0000";

	/**
	 * 失败
	 */
	public final static String REGISTER_FALSE = "9999";

	/**
	 * 经销商
	 */
	public final static String DEALERS_IMG = "cust/distributor/";

	/**
	 * 商户
	 */
	public final static String MERCHANTS_IMG = "cust/mer/";
	
	public final static String SALES_IMG = "cust/sale/";

	/**
	 * 商品
	 */
	public final static String GOODS_IMG = "goods/";

	/**
	 * 拜访
	 */
	public final static String VISIT_IMG = "visit/";

	/**
	 * 注册成功
	 */
	public final static boolean  REGISTER_RESULT_TRUE = true;


	/**
	 * 注册失败
	 */
	public final static boolean REGISTER_RESULT_FALSE = false;

	/**
	 * 登录失败
	 */
	public final static String LOGIN_RESULT_FAIL = "00001111";
	/**
	 * 登录成功
	 */
	public final static String LOGIN_RESULT_SUCCESS = "11110000";
	/**
	 * 绑定成功
	 */
	public final static String LOGIN_MESSAGE_SUCCESS = "绑定成功";

	/**
	 * 绑定失败
	 */
	public final static String LOGIN_MESSAGE_FALSE = "绑定失败";
	/**
	 * 查询成功
	 */
	public final static String LOGIN_SELECT_SUCCESS = "查询成功";
	/**
	 * 查询失败
	 */
	public final static String LOGIN_SELECT_FASLE = "查询失败";

	/**
	 * 用户账号可用
	 */
	public final static String USER_STATUS_ENABLE = "1";
	/**
	 * 用户账号冻结
	 */
	public final static String USER_STATUS_DISABLE = "2";
	public final static String USER_STATUS_DISABLEA = "3";
	public final static String USER_STATUS_DISABLEB = "4";
	public final static String USER_STATUS_DISABLEC = "5";
	public final static String USER_STATUS_DISABLED = "6";

	/**
	 * 添加失败
	 */
	public final static String ADD_FALSE = "添加失败";
	/**
	 * 修改失败
	 */
	public final static String EDIT_FALSE = "修改失败";
	/**
	 * 删除失败
	 */
	public final static String DELETE_FALSE = "删除失败";
	/**
	 * 获取列表失败
	 */
	public final static String GET_LIST_FALSE = "获取列表失败";
	/**
	 * 获取详情失败
	 */
	public final static String GET_INFO_FALSE = "获取详情失败";
	/**
	 * 上下架失败
	 */
	public final static String ON_OFF_FALSE = "上下架失败";
	/**
	 * 图片张数码值
	 */
	public final static String NUM_OF_PIC = "10030003";

	/**
	 * 地址 使用状态
	 */
	public final static String ADDR_STATUS_USEING = "1";
	public final static String ADDR_STATUS_DELETE = "2";
	/**
	 * 是否默认 使用状态
	 */
	public final static String ADDR_DEFAULT_TRUE = "1";
	public final static String ADDR_DEFAULT_FALSE = "0";
	/**
	 * 是否已读
	 */
	public final static String READ_TRUE = "1";
	public final static String READ_FALSE = "0";
	/**
	 * 订单状态变更信息模板
	 */
	public final static String ORDER_NEWS_DEMO ="尊敬的{0},用户{1}向您提交了一份{2}订单{3}";
	/**
	 * 订单状态变更信息标题
	 */
	public final static String ORDER_NEWS_TITLE_DEMO ="您有{0}的订单";
	
	/**
	 * 经销商绑定二维码
	 */
	public final static String FILE_TYPE_DEALER_TWODIMENSION="10050051";
	/**
	 * 销售邀请商户二维码
	 */
	public final static String FILE_TYPE_MERCHANT_INVITE_TWODIMENSION="10050053";
	/**
	 * 销售邀请经销二维码维码
	 */
	public final static String FILE_TYPE_DEALER_INVITE_TWODIMENSION="10050052";
}
