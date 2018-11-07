package com.hansy.tyly.core.helper;

import org.apache.commons.lang3.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class NQueryHelper {
    private Example.Criteria criteria;
    private Map<String, Object> params = new HashMap<>();

    public NQueryHelper(Example.Criteria criteria, Map<String, Object> params) {
        this.criteria = criteria;
        this.params.putAll(params);
    }

    public NQueryHelper(Example.Criteria criteria) {
        this.criteria = criteria;
    }

    public NQueryHelper andEqualTo(String ...keys) {

        String value;
        for (String key : keys) {
            if (!this.params.containsKey(key)) continue;
            value = (String)params.get(key);
            if (StringUtils.isBlank(value)) continue;
            this.criteria.andEqualTo(key, value);
        }
        return this;
    }

    public NQueryHelper andLike(String ...keys) {
        String value;
        for (String key : keys) {
            if (!this.params.containsKey(key)) continue;
            value = (String)params.get(key);
            if (StringUtils.isBlank(value)) continue;
            this.criteria.andLike(key, '%' + value + '%');
        }
        return this;
    }

    public NQueryHelper andIn(String ...keys) {
        Collection value;
        for (String key : keys) {
            if (!this.params.containsKey(key)) continue;
            value = (Collection)params.get(key);
            if (value == null || value.isEmpty()) continue;
            this.criteria.andIn(key, value);
        }
        return this;
    }

    public NQueryHelper addParams(Map<String, Object> params) {
        this.params.putAll(params);
        return this;
    }
    public NQueryHelper addParam(String key, Object value) {
        this.params.put(key, value);
        return this;
    }
    public NQueryHelper removeParam(String key) {
        this.params.remove(key);
        return this;
    }
    public static NQueryHelper create(Example.Criteria criteria) {
        return new NQueryHelper(criteria);
    }
    public static NQueryHelper create(Example.Criteria criteria, Map<String, Object> params) {
        return new NQueryHelper(criteria, params);
    }

    public NQueryHelper andLessThanOrEqualTo(String prop, String key, String suffix) {
        if (!this.params.containsKey(key)) return this;
        String value = (String) this.params.get(key);
        if (StringUtils.isBlank(value)) return this;
        if (StringUtils.isNotBlank(suffix)) value += suffix;
        this.criteria.andLessThanOrEqualTo(prop, value);
        return this;
    }

    public NQueryHelper andLessThan(String prop, String key, String suffix) {
        if (!this.params.containsKey(key)) return this;
        String value = (String) this.params.get(key);
        if (StringUtils.isBlank(value)) return this;
        if (StringUtils.isNotBlank(suffix)) value += suffix;
        this.criteria.andLessThan(prop, value);
        return this;
    }

    public NQueryHelper andLessThanOrEqualTo(String prop, String key) {
        return this.andLessThanOrEqualTo(prop, key, null);
    }
    public NQueryHelper andLessThan(String prop, String key) {
        return this.andLessThan(prop, key, null);
    }

    public NQueryHelper andGreaterThanOrEqualTo(String prop, String key, String suffix) {
        if (!this.params.containsKey(key)) return this;
        String value = (String) this.params.get(key);
        if (StringUtils.isBlank(value)) return this;
        if (StringUtils.isNotBlank(suffix)) value += suffix;
        this.criteria.andGreaterThanOrEqualTo(prop, value);
        return this;
    }

    public NQueryHelper andGreaterThan(String prop, String key, String suffix) {
        if (!this.params.containsKey(key)) return this;
        String value = (String) this.params.get(key);
        if (StringUtils.isBlank(value)) return this;
        if (StringUtils.isNotBlank(suffix)) value += suffix;
        this.criteria.andGreaterThan(prop, value);
        return this;
    }

    public NQueryHelper andGreaterThanOrEqualTo(String prop, String key) {
        return this.andGreaterThanOrEqualTo(prop, key, null);
    }
    public NQueryHelper andGreaterThan(String key, String prop) {
        return this.andGreaterThan(prop, key, null);
    }
}