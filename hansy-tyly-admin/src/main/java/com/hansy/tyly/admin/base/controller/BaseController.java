package com.hansy.tyly.admin.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.hansy.tyly.admin.base.bean.UserProfile;
import com.hansy.tyly.core.excepiton.LoginException;

/**
 * Created by MIfengHe on 2017/10/23.
 */
public class BaseController {

    private static final String CURRENT_USER_PROFILE_KEY = "CURRENT_USER_PROFILE";

    /**
     * 获取HTTP-Request
     * @return
     */
    protected HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取HTTP-Response
     * @return
     */
    protected HttpServletResponse getHttpServletResponse() {
        return ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    protected HttpSession getHttpSession() {
        return this.getHttpServletRequest().getSession();
    }
    protected String getHttpServletRequestBasePath(){
        HttpServletRequest request = getHttpServletRequest();
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        if(basePath == null || basePath.equals("")) basePath = "/";
        return basePath;
    }

    /**
     * 是否登录状态
     * @return
     */
    protected Boolean isLoginStatus() {
        return SecurityUtils.getSubject().isAuthenticated();
    }

    /**
     * 获取用户信息
     * @return
     */
    protected UserProfile getCurrentUserProfile() {
        if (!this.isLoginStatus()) throw new LoginException("用户未登录或已失效");
        return (UserProfile) getHttpSession().getAttribute("CURRENT_USER_PROFILE_KEY");
    }

    /**
     * 获取登录账号
     * @return
     */
    protected String getCurrentLoginNo() {
        return this.getCurrentUserProfile().getLoginId();
    }
}
