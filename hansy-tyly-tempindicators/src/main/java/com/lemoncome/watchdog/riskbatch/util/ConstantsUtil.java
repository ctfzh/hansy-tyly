package com.lemoncome.watchdog.riskbatch.util;

/**
 * @Author YuanYan
 * @Describe 系统常量类
 */
public class ConstantsUtil {
	
	/*状态 有效*/
	public final static String STATUS_NORMAL = "00001001";
	/*状态 无效*/
	public final static String STATUS_INVALID = "00001002";
	/*状态 有效*/
    public final static String STATUS_YES = "00001001";
    /*状态 无效*/
    public final static String STATUS_NO = "00001002";
    /*菜单类型 后台*/
    public final static String MENU_TYPE_BACK = "10001001";
    /*菜单类型 前端*/
    public final static String MENU_TYPE_BEFORE = "10001002";
    /*业务角色*/
    public final static String ROLE_TYPE_BEFROE = "10002002";
    /*系统角色*/
    public final static String ROLE_TYPE_BACK = "10002001";
    /*系统用户*/
    public final static String USER_TYPE_BACK = "10003001";
    /*业务用户*/
    public final static String USER_TYPE_BEFORE = "10003002";
    /*机构管理员角色代码 */
    public final static String ORG_ADMIN_ROLE_CODE ="Org_Admin";
    /*默认角色代码(机构客户经理)*/
    public final static String DEFAULT_ROLE_CODE = "Org_Cust_Manager";
    /*机构客户经理角色代码*/
    public final static String ORG_CUST_MANGER_ROLE_CODE = "Org_Cust_Manager";
    /*管理状态-已管理*/
    public final static String MNG_STATUS_YES = "10014001";
    /*管理状态-未管理*/
    public final static String MNG_STATUS_NO = "10014002";
    
    public final static String INT_TYPE_A5 = "10015001";
    public final static String INT_TYPE_A7 = "10015002";
    public final static String INT_TYPE_Q = "10015003";
    public final static String SCHEDULE_STATUS_A5 = "10016001";
    public final static String SCHEDULE_STATUS_A7 = "10016002";
    public final static String SCHEDULE_STATUS_Q = "10016003";
    
    /*决策结果-风险*/
    public final static String DCS_RST_01 = "10017001";
    /*决策结果-警告*/
    public final static String DCS_RST_02 = "10017002";
    /*决策结果-安全*/
    public final static String DCS_RST_03 = "10017003";
    /*决策结果-暂无*/
    public final static String DCS_RST_04 = "10017004";

    public final static String SUCC_CODE = "00003001";
    public final static String FAIL_CODE = "00003002";
    
    public final static String YES_CODE = "00003001";//是
    public final static String NO_CODE = "00003002";//否

    public final static String LOG_SUCC_CODE = "10019001";
    public final static String LOG_FAIL_CODE = "10019002";
    //appKey标识(免费、收费)
    public final static String APP_KEY_MF = "kanjiagoumf";
    public final static String APP_KEY_SF = "kanjiagousf";
    

}
