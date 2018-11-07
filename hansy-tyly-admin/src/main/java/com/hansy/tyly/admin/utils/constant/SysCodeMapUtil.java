package com.hansy.tyly.admin.utils.constant;

import net.sf.json.JSONObject;

import java.util.Map;

public class SysCodeMapUtil {

    public final static String codeMap="{'00001001':'正常'," +
            "'00001002':'失效'," +
            "'10011001':'天'," +
            "'10011002':'周'," +
            "'10011003':'半月'," +
            "'10011004':'月'}";

    public static void main(String[] args) {
        JSONObject  jasonObject = JSONObject.fromObject(codeMap);
        Map map = (Map)jasonObject;
        System.out.println(map.get("00001001").toString());
    }

    public static String getNameByCode(String code){
        JSONObject  jasonObject = JSONObject.fromObject(codeMap);
        Map map = (Map)jasonObject;
        if (!map.containsKey(code)) return null;
        return map.get(code).toString();
    }


}
