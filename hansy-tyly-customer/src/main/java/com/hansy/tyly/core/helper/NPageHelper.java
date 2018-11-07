package com.hansy.tyly.core.helper;

import com.github.pagehelper.Page;
import com.github.pagehelper.util.PageObjectUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class NPageHelper extends  com.github.pagehelper.PageHelper {

    private static Integer getInteger(Object value, Integer defaultValue) {
        if (value == null) return defaultValue;
        String str = "" + value;

        if (!StringUtils.isNumeric(str)) return defaultValue;
        Integer result = Integer.valueOf(str);
        if (result <= 0) return defaultValue;
        return result;
    }

    public static <E> Page<E> startPage(Object params, boolean ignorePage) {
        if (params == null && ignorePage) return null;

        if (params instanceof Map) {
            Map<String, Object> map = (Map) params;

            Integer pageNo = getInteger(map.get("pageNo"), 0);
            Integer pageSize = getInteger(map.get("pageSize"), 10);
            String orderBy = (String)map.get("orderBy");

            if (pageNo <= 0 && ignorePage) return null;
            Map<String, Object> pageMap = new HashMap<>();
            pageMap.put("pageNo", pageNo);
            pageMap.put("pageSize", pageSize);
            if (StringUtils.isNotBlank(orderBy)) pageMap.put("orderBy", orderBy);
            params = pageMap;
        }

        Page page = PageObjectUtil.getPageFromObject(params, true);
        setLocalPage(page);
        return page;
    }

    public static <E> Page<E> startPage(Object params) {
        return startPage(params, true);
    }
}
