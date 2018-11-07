package com.hansy.tyly.core.configuration;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class HttpHandlerInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        // 验证用户是否登陆
        String x_forwarded_for = request.getHeader("x-forwarded-proto");
        System.out.println("======================"+x_forwarded_for+"======================");
       /* if(x_forwarded_for==null || "http".equals(x_forwarded_for) || "".equals(x_forwarded_for)){
            response.sendRedirect("https://admin.51kanjiagou.com/index.html");
            return false;
        }*/
        return true;
    }

}
