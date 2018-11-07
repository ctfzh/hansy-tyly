package com.hansy.tyly.core.shiro.core;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hansy.tyly.core.shiro.ShiroChainBean;

public abstract class AbstractShiroFilterChainDefinitions implements ShiroFilterChainDefinitions {

	private final static Logger LOGGER = LoggerFactory.getLogger(AbstractShiroFilterChainDefinitions.class);
	
	static {
		LOGGER.debug("shiro cache dir:" + System.getProperty("java.io.tmpdir") + "/ehcache");
	}

	private String filterChainDefinitions = "/** = authc";

	@Autowired private ShiroFilterFactoryBean shiroFilterFactoryBean;

	@PostConstruct
	public void intiPermission() {
		shiroFilterFactoryBean.setFilterChainDefinitionMap(obtainPermission());
		LOGGER.debug("init shiro permission success...");  
	}  

	public void updatePermission() {  

		synchronized (shiroFilterFactoryBean) {  

			AbstractShiroFilter shiroFilter = null;

			try {  
				shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
			} catch (Exception e) {  
				LOGGER.error(e.getMessage());  
			}  


			// 获取过滤管理器  
			PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
			DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();

			// 清空初始权限配置  
			manager.getFilterChains().clear();  
			shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();  

			// 重新构建生成  ``
			shiroFilterFactoryBean.setFilterChainDefinitions(filterChainDefinitions);  
			Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();  

			Map<String, String> permissionMap = initOtherPermission();  
			if (permissionMap != null && !permissionMap.isEmpty()) {  
				chains.remove("/**");
				chains.putAll(permissionMap);  
				chains.put("/**", "authc");
			}  

			for (Map.Entry<String, String> entry : chains.entrySet()) {  
				String url = entry.getKey();  
				String chainDefinition = entry.getValue().trim().replace(" ", "");  
				manager.createChain(url, chainDefinition);  
			}
			LOGGER.debug("# shiro final chain: " + chains.size());
			LOGGER.debug("update shiro permission success...");  
		}  
	}  
	
	/** 读取配置资源 */  
	private Section obtainPermission() {
		if (StringUtils.isBlank(this.filterChainDefinitions)) this.filterChainDefinitions = StringUtils.EMPTY;
		Ini ini = new Ini();
		ini.load(filterChainDefinitions); // 加载资源文件节点串
		Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
//		if (section == null) section =

		Map<String, String> permissionMap = initOtherPermission();  
		if (permissionMap != null && !permissionMap.isEmpty()) {  
			if (permissionMap.containsKey("/**") || permissionMap.containsKey("/*")) {
				section.clear();
				section.putAll(permissionMap);
			} else {
				section.putAll(permissionMap);
				section.remove("/**");
				section.put("/**", "authc");
			}
		}
		
		LOGGER.info("\nshiro filter chain definitions:" + filterChainDefinitions.replace("\n", ""));
		
		return section;  
	}
	

	public String getFilterChainDefinitions() {
		//if(StringUtils.isBlank(filterChainDefinitions)) filterChainDefinitions = "/assets/**=anon /anon/**=anon /**/login/**=anon /*.ico=anon /**=authc"; 
		return filterChainDefinitions;
	}
	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

	public abstract Map<String, String> initOtherPermission();

	public Map<String, String> getPermissionMap(List<ShiroChainBean> permissions) {
		Map<String, String> chainMap = new HashMap<>();
		String url;
		if (permissions != null && !permissions.isEmpty()) {
			for (ShiroChainBean chain : permissions) {
				if (StringUtils.isBlank(chain.getUrl())) continue;
				if (StringUtils.isBlank(chain.getCodes())) continue;
				url = chain.getUrl().replace("\\", "/").replace("////", "/").trim();
				if (!url.startsWith("/") && !url.startsWith("http://") && !url.startsWith("https://") && !url.startsWith("ftp://")) url = "/" + url;
				if (!url.endsWith(":*")) url += ":*";
				chainMap.put(url, MessageFormat.format(PREMISSION_STRING, chain.getCodes().trim()));
			}
		}
		return chainMap;

	}
	
	

}
