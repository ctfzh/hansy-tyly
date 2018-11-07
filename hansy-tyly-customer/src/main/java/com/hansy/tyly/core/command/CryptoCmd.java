package com.hansy.tyly.core.command;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pagehelper.Page;
import com.hansy.tyly.core.crypto.CryptoHelper;
import com.hansy.tyly.core.excepiton.CryptoException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.*;

/**
 * Created by MIfengHe on 2017/5/31.
 */
public class CryptoCmd {
    private static final Logger LOGGER = LoggerFactory.getLogger(CryptoCmd.class);
    private String uuid = UUID.randomUUID().toString().replaceAll("-", StringUtils.EMPTY);
    private long startTime = new Date().getTime();
    private String in;
    private String out;

    private String crypt;
    private boolean cryptEnable = false;
    private Boolean success = true;
    private Integer status = 200;
    private String message;

    private boolean inSupportCryptEnable = true;
    private boolean outSupportCryptEnable = true;
    private String innerAESRule;
    private Map<String, Object> innerInMap = null;
    private Map<String, Object> innerOutMap = new HashMap<>();

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return StringUtils.isNotBlank(message)
                ? message
                : (success ? "操作成功" : "操作失败");
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCrypt() {
        if (crypt == null) return StringUtils.EMPTY;
        return crypt;
    }

    public void setCrypt(String crypt) {
        this.crypt = crypt;
    }

    public boolean isCryptEnable() {
        return cryptEnable;
    }

    public void setCryptEnable(boolean cryptEnable) {
        this.cryptEnable = cryptEnable;
    }

    /**
     * 不启用加密模式
     *
     * @param inSupportCryptEnable  入参加密模式
     * @param outSupportCryptEnable 出参加密模式
     */
    public void setSupportCryptEnable(boolean inSupportCryptEnable, boolean outSupportCryptEnable) {
        this.inSupportCryptEnable = inSupportCryptEnable;
        this.outSupportCryptEnable = outSupportCryptEnable;
    }

    public String getIn() {
        if (in == null) return StringUtils.EMPTY;
        return in;
    }

    public void setIn(Map<String, Object> in) {
        this.in = JSONObject.toJSONString(in);
    }

    @JsonIgnore
    public Map<String, Object> getParams() {
        Map<String, Object> params = new HashMap<>();
        this.initInnerInMap();
        params.putAll(this.innerInMap);
        return params;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public Object getOut() {
        if (this.innerInMap == null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            LOGGER.info(MessageFormat.format("#【{0}】请求接口：({1}){2}", this.uuid, request.getMethod(), request.getRequestURI()));
            LOGGER.info(MessageFormat.format("#【{0}】请求请求参数：", this.uuid) + this.in);
        }

        LOGGER.info(MessageFormat.format("#【{0}】请求请求费时：", this.uuid) + this.getDuration());
        if (!this.cryptEnable) return this.innerOutMap;
        String outStr = JSONObject.toJSONString(this.innerOutMap);
        if (this.outSupportCryptEnable && this.cryptEnable) {
            if (StringUtils.isBlank(this.innerAESRule)) this.innerAESRule = CryptoHelper.getAesRule(this.crypt, this.cryptEnable);
            this.out = CryptoHelper.encryptAES(outStr, this.innerAESRule);
        } else this.out = outStr;
        if (out == null) return StringUtils.EMPTY;
        return out;
    }

    public String getDuration() {
        return "总费时：" + (new Date().getTime() - this.startTime) / 1000.0 + '秒';
    }


    public <T> T getIn(String key, Class<T> clazz, T defaultValue) {

        this.initInnerInMap();

        String value = null;
        if (StringUtils.isNotBlank(key)) {
            Object obj = this.innerInMap.get(key);
            if (obj == null) value = null;
            else if (obj instanceof JSONObject) value = JSONObject.toJSONString(obj);
            else value = obj.toString();
        }


        if (value == null) {
            return defaultValue;
        }

        if (clazz == null && defaultValue != null) clazz = (Class<T>) defaultValue.getClass();



        if (clazz == null) throw new RuntimeException("类型Class<T> clazz不为空");

        if (clazz != String.class) value = value.trim();

        if (clazz == String.class) return (T) value;
        if (clazz == Integer.class) return (T) Integer.valueOf(value);
        if (clazz == Long.class) return (T) Long.valueOf(value);
        if (clazz == BigDecimal.class) return (T) new BigDecimal(value);
        if (clazz == Boolean.class) return (T) new Boolean(Boolean.parseBoolean(value));

        /*Map maps = (Map) JSON.parse(value);
        for (Object map : maps.entrySet()){
            if(((Map.Entry)map).getValue().equals("")){
                maps.put(((Map.Entry)map).getKey(),null);
            }
        }
        value=new JSONObject(maps).toString();*/
        return JSONObject.parseObject(value, clazz);
    }

    public <T> List<T> getInArray(String key, Class<T> clazz, List<T> defaultValue) {

        this.initInnerInMap();

        String value = null;
        if (StringUtils.isNotBlank(key)) {
            Object obj = this.innerInMap.get(key);
            if (obj == null) value = null;
            else if (obj instanceof JSONArray) value = JSONArray.toJSONString(obj);
            else value = obj.toString();
        }

        if (value == null) {
            return defaultValue;
        }

        return JSONArray.parseArray(value.trim(), clazz);
    }

    public <T> T getIn(String key, T defaultValue) {
        if (defaultValue != null && defaultValue.getClass() == Class.class) throw new RuntimeException("请提供实体对象");
        return this.getIn(key, (Class<T>) defaultValue.getClass(), defaultValue);
    }

    public <T> T getInObject(String key, Class<T> clazz) {
        return this.getIn(key, clazz, null);
    }

    public <T> List<T> getInArray(String key, List<T> defaultValue) {
        return this.getInArray(key, null, defaultValue);
    }

    public String getInString(String key) {
        return this.getIn(key, String.class, null);
    }

    public String getInTrimString(String key) {
        String value = getInString(key);
        return value == null ? value : value.trim();
    }

    public <T> List<T> getInArrayObject(String key, Class<T> clazz) {
        return this.getInArray(key, clazz, null);
    }

    public List<String> getInArrayString(String key) {
        return this.getInArray(key, String.class, null);
    }

    public String getInString(String key, String defaultValue) {
        return this.getIn(key, String.class, defaultValue);
    }

    public List<String> getInArrayString(String key, List<String> defaultValue) {
        return this.getInArray(key, String.class, defaultValue);
    }

    public Integer getInInteger(String key) {
        return this.getIn(key, Integer.class, null);
    }

    public List<Integer> getInArrayInteger(String key) {
        return this.getInArray(key, Integer.class, null);
    }

    public Integer getInInteger(String key, Integer defaultValue) {
        return this.getIn(key, Integer.class, defaultValue);
    }

    public List<Integer> getInArrayInteger(String key, List<Integer> defaultValue) {
        return this.getInArray(key, Integer.class, defaultValue);
    }

    public BigDecimal getInBigDecimal(String key) {
        return this.getIn(key, BigDecimal.class, null);
    }

    public List<BigDecimal> getInArrayBigDecimal(String key) {
        return this.getInArray(key, BigDecimal.class, null);
    }

    public BigDecimal getInBigDecimal(String key, BigDecimal defaultValue) {
        return this.getIn(key, BigDecimal.class, defaultValue);
    }

    public List<BigDecimal> getInArrayBigDecimal(String key, List<BigDecimal> defaultValue) {
        return this.getInArray(key, BigDecimal.class, defaultValue);
    }

    public Long getInLong(String key) {
        return this.getIn(key, Long.class, null);
    }

    public List<Long> getInArrayLong(String key) {
        return this.getInArray(key, Long.class, null);
    }

    public Long getInLong(String key, Long defaultValue) {
        return this.getIn(key, Long.class, defaultValue);
    }

    public List<Long> getInArrayLong(String key, List<Long> defaultValue) {
        return this.getInArray(key, Long.class, defaultValue);
    }

    /**
     * 设置出参
     * 默认key value
     *
     * @param value 出参值
     */
    public void setOut(Object value) {
        if (value instanceof Map) {
            this.innerOutMap.putAll((Map) value);
            return;
        }
        if (value instanceof Page) {

            Page page = (Page) value;

            long totalSize = page.getTotal();
            int pageSize = page.getPageSize();
            if (pageSize == 0) pageSize = 1;
            long pages = totalSize / pageSize +
                    (totalSize % pageSize == 0 ? 0 : 1);
            this.innerOutMap.put("pageNo", page.getPageNum());
            this.innerOutMap.put("pageSize", page.getPageSize());
            this.innerOutMap.put("pages", pages);
            this.innerOutMap.put("totalSize", totalSize);
            this.innerOutMap.put("list", page.getResult());
            return;
        }
        if (value instanceof List) {
            this.setOut("list", value);
            return;
        }
        this.setOut(null, value);
    }

    /**
     * 设置出参
     *
     * @param key   出参键
     * @param value 出参值
     */
    public void setOut(String key, Object value) {
        if (StringUtils.isBlank(key)) key = "value";
        this.innerOutMap.put(key, value);
    }


    /**
     * 初始化入参
     */
    protected void initInnerInMap() {
        if (this.innerInMap != null) return;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        LOGGER.info(MessageFormat.format("#【{0}】请求接口：({1}){2}", this.uuid, request.getMethod(), request.getRequestURI()));
        LOGGER.info(MessageFormat.format("#【{0}】请求请求参数：", this.uuid) + this.in);
        this.innerInMap = new HashMap<>();
        if (StringUtils.isBlank(this.in)) return;
        String inStr = null;
        try {
            this.innerAESRule = CryptoHelper.getAesRule(this.crypt, this.cryptEnable);
            if (this.inSupportCryptEnable && this.cryptEnable) {
                inStr = CryptoHelper.decryptAES(this.in, this.innerAESRule);
            } else inStr = this.in;
        } catch (RuntimeException e) {
            throw new CryptoException("无效签名");
        }
        if (StringUtils.isBlank(inStr)) throw new RuntimeException("无效入参[3051]");
        if (!(inStr.startsWith("{") && inStr.endsWith("}"))) throw new RuntimeException("无效入参[3052]");

        Map<String, String> data = JSONObject.parseObject(inStr, Map.class);
        this.innerInMap.putAll(data);
    }

    public <T> T populate(Class<T> clazz) {
        if (clazz == null) return null;
        this.initInnerInMap();
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(clazz);
        } catch (Exception e) {
            throw new RuntimeException("提取参数异常[类型错误]", e);
        }
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        Map<String, Object> data = new HashMap<>();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();

            if (this.innerInMap.containsKey(key)) {
                Object value = this.innerInMap.get(key);
                data.put(key, value);
            }
        }
        return JSONObject.parseObject(JSONObject.toJSONString(data), clazz);
    }

    public <T> T populate(T t) {
        if (t == null) return null;
        Object populate = this.populate(t.getClass());
        if (populate == null) return t;
        return (T) populate;
    }

}
