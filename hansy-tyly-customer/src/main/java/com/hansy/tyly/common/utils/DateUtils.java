package com.hansy.tyly.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: 18383
 * @Date: 2018/8/8 17:53
 * @Description:
 */
public class DateUtils {

    private DateUtils(){}

    public static final String getDate(){
        return new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
    }
}
