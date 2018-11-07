package com.hansy.tyly.core.configuration;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.hansy.tyly.core.excepiton.BaseException;
import com.hansy.tyly.core.excepiton.CryptoException;
import com.hansy.tyly.core.excepiton.LoginException;
import com.hansy.tyly.core.holder.SpringContextHolder;
import com.hansy.tyly.customer.utils.StringUtil;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {


    @ControllerAdvice
    public static class ExceptionGlobalHandler {

        private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionGlobalHandler.class);
        private static String activeEnv = null;


        @ResponseBody
        @ExceptionHandler(value = Exception.class)
        public Map<String, Object> defaultErrorHandler(HttpServletResponse response, Exception exception) throws Exception {
            Map<String, Object> map = new HashMap<>();
            response.setStatus(500);
            map.put("message", "服务异常");
            map.put("success", false);
            map.put("status", 500);
            if (exception instanceof BaseException) {
                if (exception instanceof LoginException) {
                    response.setStatus(403);
                    response.setHeader("Access-Control-Allow-Origin", "*");
                    map.put("status", 403);
                    if(StringUtil.isEmpty(activeEnv)){
                        activeEnv = SpringContextHolder.getContext().getEnvironment().getProperty("spring.profiles.active", "default");
                    }
                    if("prod".equals(activeEnv)){
//                        map.put("location","https://www.51kanjiagou.com/index.html");
                    }
                }else if (exception instanceof CryptoException) {
                    response.setStatus(513);
                    map.put("status", 513);
                } else {
                    response.setStatus(505);
                    map.put("status", 505);
                }
                map.put("message", exception.getMessage());
                LOGGER.warn("# 异常状态码：" + response.getStatus(), exception);
            } else if (exception instanceof TimeoutException){
                map.put("message", "请求超时");
            } else {
                LOGGER.error("# 异常状态码：" + response.getStatus(), exception);
            }
            return map;
        }

        @ResponseBody
        @ExceptionHandler(value = NoHandlerFoundException.class)
        public Map<String, Object> noHandlerFoundException(Exception exception) {
            Map<String, Object> map = new HashMap<>();
            map.put("message", "无效请求");
            map.put("success", "false");
            return map;
        }

    }
}
