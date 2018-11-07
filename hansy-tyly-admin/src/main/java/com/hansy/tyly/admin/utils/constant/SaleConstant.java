package com.hansy.tyly.admin.utils.constant;

import java.util.ArrayList;
import java.util.List;

public class SaleConstant {

    /**
     * 响应成功
     */
    public static final String SUCCESS_CODE = "200";

    /**
     * 响应失败
     */
    public static final String ERROR_CODE = "500";

    /**
     *  角色ID - 销售
     */
    public static final String SALE_ROLE_ID = "sales";

    /**
     *  角色ID - 销售主管
     */
    public static final String SALE_ADMIN_ROLE_ID = "salesAdministrator";

    /**
     *  角色ID - 上级
     */
    public static final String BOSS_ROLE_ID = "boss";

    /**
     *  属于销售的角色ID，包括销售和销售主管
     */
    public static final List<String> SALE_ROLE_IDS = new ArrayList<String>(){{
        add(SALE_ROLE_ID);
        add(SALE_ADMIN_ROLE_ID);
    }};
    
    public static final String REGISTERVIEW_URL = "https://ysp.hansyinfo.com/index.html#/registerview?saleId=";
}
