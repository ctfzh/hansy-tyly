package com.hansy.tyly.core.shiro.core;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * shiro 管理类
 */
public class ShiroManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroManager.class);
    private ShiroFilterFactoryBean shiroFilterFactoryBean;
    private AbstractShiroFilter shiroFilter;
    private AuthorizingRealm authorizingRealm;
    private ShiroDataSource shiroDataSource;

    public void setShiroFilterFactoryBean(ShiroFilterFactoryBean shiroFilterFactoryBean) {
        this.shiroFilterFactoryBean = shiroFilterFactoryBean;
    }

    public void setShiroFilter(AbstractShiroFilter shiroFilter) {
        this.shiroFilter = shiroFilter;
    }

    public void setAuthorizingRealm(AuthorizingRealm authorizingRealm) {
        this.authorizingRealm = authorizingRealm;
    }

    public void setShiroDataSource(ShiroDataSource shiroDataSource) {
        this.shiroDataSource = shiroDataSource;
    }

    public ShiroManager() {}
    public ShiroManager(ShiroFilterFactoryBean shiroFilterFactoryBean, AbstractShiroFilter shiroFilter, AuthorizingRealm authorizingRealm, ShiroDataSource shiroDataSource) {
        this.shiroFilterFactoryBean = shiroFilterFactoryBean;
        this.shiroFilter = shiroFilter;
        this.authorizingRealm = authorizingRealm;
        this.shiroDataSource = shiroDataSource;
    }

    public synchronized void reLoad() {
        LOGGER.info("# shiro chains definition reload start...");
        PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) this.shiroFilter.getFilterChainResolver();
        DefaultFilterChainManager filterChainManager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
        filterChainManager.getFilterChains().clear();
        this.shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
        this.authorizingRealm.getAuthenticationCache().clear();

        Map<String, String> filterChainDefinitionMap = this.shiroDataSource.getFilterChainDefinitionMap();
        if (filterChainDefinitionMap == null) filterChainDefinitionMap = new HashMap<>();
        this.shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        for (Map.Entry<String, String> entry : filterChainDefinitionMap.entrySet()) {
            String url = entry.getKey();
            String chainDefinition = entry.getValue().trim().replace(" ", "");
            filterChainManager.createChain(url, chainDefinition);
        }
        LOGGER.info("# shiro map: " + JSON.toJSONString(filterChainDefinitionMap));
        LOGGER.info("# shiro chains definition reload end.");

    }

}
