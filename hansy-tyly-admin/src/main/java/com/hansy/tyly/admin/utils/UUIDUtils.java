package com.hansy.tyly.admin.utils;

import java.util.UUID;

/**
 * @Auther: 18383
 * @Date: 2018/8/8 11:17
 * @Description:
 */
public class UUIDUtils {
    private UUIDUtils(){}
    public static final String getUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
