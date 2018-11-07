package com.hansy.tyly.core.configuration;

import com.hansy.tyly.core.holder.SpringContextHolder;
import com.hansy.tyly.customer.utils.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpHandlerInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpHandlerInterceptor.class);
    private static String activeEnv = null;
    private static String ctxPath = null;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        if(activeEnv == null){
            activeEnv =  SpringContextHolder.getContext().getEnvironment().getProperty("spring.profiles.active", "");
        }
        // 验证用户是否登陆
        String x_forwarded_for = request.getHeader("x-forwarded-proto");

        LOGGER.info("===================HttpHandlerInterceptor(S)=====================");
        LOGGER.info("===");
        if(StringUtil.isEmpty(ctxPath)){
            ctxPath =  request.getScheme()+"://"+request.getServerName()+request.getContextPath()+"/";
			 //ctxPath =  request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
        }
        LOGGER.info("=== activeEnv: " +activeEnv);
        LOGGER.info("=== ctxPath: " + ctxPath);
        LOGGER.info("=== Url: " + request.getServletPath());
        LOGGER.info("=== x_forwarded_for: " + x_forwarded_for);
        LOGGER.info("===");
        LOGGER.info("===================HttpHandlerInterceptor(E)=====================");
        //生产环境强制转向https
		/**
        if(StringUtil.isEmpty(activeEnv) || "prod".equals(activeEnv)){
            if(StringUtil.isEmpty(x_forwarded_for) || "http".equals(x_forwarded_for)){
                response.setStatus(301);
                //允许跨域访问
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.sendRedirect("https://www.51kanjiagou.com/index.html");
                return false;
            }
        }**/
		if(StringUtil.isEmpty(activeEnv) || "prod".equals(activeEnv)){
            if(StringUtil.isEmpty(x_forwarded_for) || "http".equals(x_forwarded_for)){
                String newUrl = ctxPath.replace("http","https");
                if(newUrl!= null && !newUrl.endsWith("index.html")){
                    newUrl = newUrl+"index.html";
                }
                response.setStatus(301);
                //允许跨域访问
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.sendRedirect(newUrl);
                //response.sendRedirect("https://www.51kanjiagou.com/index.html");
                return false;
            }
        }
        return true;
    }

}
