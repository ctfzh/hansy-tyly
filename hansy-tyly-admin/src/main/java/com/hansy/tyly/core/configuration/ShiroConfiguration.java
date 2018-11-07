package com.hansy.tyly.core.configuration;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.hansy.tyly.admin.base.service.ShiroService;
import com.hansy.tyly.core.shiro.ShiroChainBean;
import com.hansy.tyly.core.shiro.core.ShiroDataSource;
import com.hansy.tyly.core.shiro.core.ShiroManager;

/**
 * Shiro 配置
 */
@Configuration
public class ShiroConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheConfiguration.class);


    public net.sf.ehcache.CacheManager getEhCacheManger() {
        try {
            return new net.sf.ehcache.CacheManager(new ClassPathResource("ehcache/ehcache-shiro.xml").getInputStream());
        } catch (IOException e) {
            LOGGER.error("读取 classpath: ehcache.xml 异常", e);
        }
        return null;
    }

    @Bean("ehCacheMangerShiro")
    public EhCacheManager getEhCacheManagerShiro() {
//        CacheManager ehCacheManger = SpringContextHolder.getBean("ehCacheManger", CacheManager.class);
        EhCacheManager ehCacheManagerShiro = new EhCacheManager();
        ehCacheManagerShiro.setCacheManager(this.getEhCacheManger());
        return ehCacheManagerShiro;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(AuthorizingRealm authorizingRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(authorizingRealm);
        defaultWebSecurityManager.setCacheManager(this.getEhCacheManagerShiro());
        //defaultWebSecurityManager.setSessionManager(sessionManager());
        //DefaultWebSessionManager-用于Web环境的实现，自己维护着会话，直接废弃了Servlet容器的会话管理。
        defaultWebSecurityManager.setSessionManager(new DefaultWebSessionManager());
        return defaultWebSecurityManager;
    }

/*    @Bean(name = "sessionManager")
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        CachingSessionDAO cacheSessionDAO = new EnterpriseCacheSessionDAO();
        cacheSessionDAO.setCacheManager(this.getEhCacheManagerShiro());
        sessionManager.setSessionDAO(cacheSessionDAO);
        //sessionManager.setGlobalSessionTimeout(1800);
        //sessionManager.setDeleteInvalidSessions(true);
        return sessionManager;
    }*/

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager
                                                                                              securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/api/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/api/unauthorized");

        shiroFilterFactoryBean.getFilters().put("permsOr", new AuthorizationFilter() {
            @Override
            public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
                    throws IOException {
                Subject subject = this.getSubject(request, response);
                String[] permsArr = (String[]) mappedValue;
                if (permsArr == null || permsArr.length == 0) return true;
                boolean checked = false;
                for (String perms : permsArr) {
                    if (subject.isPermitted(perms)) {
                        checked = true;
                        break;
                    }
                }
                return checked;
            }
        });


        Map<String, String> filterChainDefinitionMap = this.getShiroDataSource().getFilterChainDefinitionMap();
        filterChainDefinitionMap.put("/sales/*", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public AuthorizingRealm getShiroRealm() {
        AuthorizingRealm authorizingRealm = new AuthorizingRealm() {

            @Autowired
            ShiroDataSource shiroDataSource;
            @Override
            protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
                String principal = (String) principalCollection.getPrimaryPrincipal();
                List<String> perms = shiroDataSource.getPerms(principal);
                List<String> roles = shiroDataSource.getRoles(principal);
                SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
                if (roles != null) authorizationInfo.addRoles(roles);
                if (perms != null) authorizationInfo.addStringPermissions(perms);
                return authorizationInfo;
            }

            @Override
            protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws
                    AuthenticationException {

                UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
                Subject currentUser = SecurityUtils.getSubject();
                String newSessionId = currentUser.getSession().getId().toString();
                DefaultWebSessionManager sessionManager = (DefaultWebSessionManager )((DefaultSecurityManager) SecurityUtils
                        .getSecurityManager()).getSessionManager();
                //apache shiro获取所有在线用户
                Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();
                for (Session session : sessions) {
                    String loginUsername = String.valueOf(session.getAttribute(DefaultSubjectContext
                            .PRINCIPALS_SESSION_KEY));//获得session中已经登录用户的名字
                    if (token.getUsername().equals(loginUsername)) {  //这里的username也就是当前登录的username
                        session.setTimeout(0);  //这里就把session清除，
                    }
                }
                return new SimpleAuthenticationInfo(token.getPrincipal(), token.getPassword(), token.getUsername());
            }

        };
        authorizingRealm.setCacheManager(this.getEhCacheManagerShiro());
        return authorizingRealm;
    }

    @Bean
    public ShiroManager getShiroManger() {
        return new ShiroManager() {
            @Autowired
            ShiroFilterFactoryBean shiroFilterFactoryBean;
            @Autowired
            AbstractShiroFilter shiroFilter;
            @Autowired
            AuthorizingRealm authorizingRealm;
            @Autowired
            ShiroDataSource shiroDataSource;
        };
    }


    @Bean("shiroDataSource")
    public ShiroDataSource getShiroDataSource() {
        return new ShiroDataSource() {

            @Autowired
            private ShiroService shiroService;

            @Override
            public List<String> getRoles(String principal) {
                return shiroService.getRoles(principal);
            }

            @Override
            public List<String> getPerms(String principal) {
                List<String> perms = shiroService.getPerms(principal);
                return perms;
            }

            @Override
            public Map<String, String> getFilterChainDefinitionMap() {
                Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

                // 从原数据源读出过滤器配置(兼容ShiroChainBean写法)
                List<ShiroChainBean> shiroChainBeans = this.shiroService.getShiroPermssionChainBeans();
                Map<String, String> filterChainDefinitionMapByPermissions = this
                        .getFilterChainDefinitionMapByPermissions(shiroChainBeans);
                filterChainDefinitionMap.putAll(filterChainDefinitionMapByPermissions);

                // 内置写法
                filterChainDefinitionMap.put("/api/login", "anon");
                filterChainDefinitionMap.put("/api/login/**", "anon");
//                filterChainDefinitionMap.put("/api/1", "perms[1]");
                filterChainDefinitionMap.put("/api/**", "authc");
                return filterChainDefinitionMap;
            }
        };
    }


}
