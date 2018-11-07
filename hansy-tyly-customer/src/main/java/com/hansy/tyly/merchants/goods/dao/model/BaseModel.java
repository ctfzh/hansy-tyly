package com.hansy.tyly.merchants.goods.dao.model;

import java.util.Map;

public class BaseModel {
    private Map<String,Object> map;

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
