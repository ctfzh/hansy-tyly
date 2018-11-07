package com.hansy.tyly.common.utils;

import org.apache.commons.lang3.StringUtils;

public class ParamsUtils {
    public static String isNull(String str){
        if(StringUtils.isNotBlank(str) && !"null".equals(str)){
            return  str;
        }else{
            return null;
        }
    }
}
