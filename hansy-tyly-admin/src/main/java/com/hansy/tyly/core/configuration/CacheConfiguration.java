package com.hansy.tyly.core.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
@EnableCaching(proxyTargetClass = true)
@ComponentScan(basePackages = "com.hansy.tyly.**.service")
public class CacheConfiguration implements CachingConfigurer {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheConfiguration.class);

    @Bean("ehCacheManager")
    public net.sf.ehcache.CacheManager getEhCacheManger() {
        try {
            return new net.sf.ehcache.CacheManager(new ClassPathResource("ehcache/ehcache-spring.xml").getInputStream());
        } catch (IOException e) {
            LOGGER.error("读取 classpath: ehcache.xml 异常", e);
        }
        return null;
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(this.getEhCacheManger());
    }

    @Override
    public CacheResolver cacheResolver() {
        return new CacheResolver() {
            @Autowired private CacheManager cacheManager;
            @Override
            public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> cacheOperationInvocationContext) {
                List<Cache> caches = new ArrayList<Cache>();
                for(String cacheName : cacheOperationInvocationContext.getOperation().getCacheNames()) {
                    System.out.println(cacheName);
                    caches.add(cacheManager.getCache(cacheName));
                }
                return caches;
            }
        };
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new CacheErrorHandler() {

            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object o) {
                LOGGER.error("缓存读取异常：" + cache.getName(), e);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object o, Object o1) {
                LOGGER.error("缓存保存异常：" + cache.getName(), e);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object o) {
                LOGGER.error("缓存选择存储异常：" + cache.getName(), e);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {LOGGER.error("缓存读取异常", e);
                LOGGER.error("缓存清理(删除)异常：" + cache.getName(), e);
            }
        };
    }
}
