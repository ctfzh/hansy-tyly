package com.hansy.tyly.admin.base.controller;

import com.hansy.tyly.core.excepiton.LoginException;
import com.hansy.tyly.core.excepiton.UnauthorizedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class ErrorController extends BaseController{
    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);
    @RequestMapping("/unauthorized")
    public String unauthorized(HttpServletRequest request) {
        //SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(request);
//        LOGGER.info("# 无权限访问");
        //LOGGER.info("# url: " + savedRequest.getRequestUrl());
        //LOGGER.info("# method: " + savedRequest.getMethod());
        throw new UnauthorizedException("无权限访问");
    }

    @RequestMapping("/login")
    public String login() {
        LOGGER.info("===================ErrorController.login(S)=====================");
        LOGGER.info("===");
        LOGGER.info("=== ctxPath:" + this.getHttpServletRequestBasePath());
        LOGGER.info("===");
        LOGGER.info("===================ErrorController.login(E)=====================");
        throw new LoginException("用户未登录或已失效");
    }
}
