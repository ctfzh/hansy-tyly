package com.hansy.tyly.core.shiro.core;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.hansy.tyly.core.shiro.ShiroChainBean;

/**
 * Created by MIfengHe on 2017/10/23.
 */
public abstract class ShiroDataSource {
    /**
     * 获取角色
     * @return
     */
    public abstract List<String> getRoles(String principal);

    /**
     * 获取许可
     * @return
     */
    public abstract List<String> getPerms(String principal);

    /**
     * 获取过滤器定义
     * @return
     */
    public abstract Map<String, String> getFilterChainDefinitionMap();

    /**
     * 获取
     * @param permissions
     * @return
     */
    protected Map<String, String> getFilterChainDefinitionMapByPermissions(List<ShiroChainBean> permissions) {
        Map<String, String> chainMap = new HashMap<>();
        String url;
        if (permissions != null && !permissions.isEmpty()) {
            for (ShiroChainBean chain : permissions) {
                if (StringUtils.isBlank(chain.getUrl())) continue;
                if (StringUtils.isBlank(chain.getCodes())) continue;
                url = chain.getUrl().replace("\\", "/").replace("////", "/").trim();
                if (!url.startsWith("/") && !url.startsWith("http://") && !url.startsWith("https://") && !url.startsWith("ftp://")) url = "/" + url;
                //if (!url.endsWith(":*")) url += "";
                chainMap.put(url, MessageFormat.format("perms[{0}]", chain.getCodes().trim()));
            }
        }
        return chainMap;

    }
}
