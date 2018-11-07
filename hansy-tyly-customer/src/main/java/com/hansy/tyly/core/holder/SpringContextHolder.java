package com.hansy.tyly.core.holder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获取Spring Context 上下文
 */
public class SpringContextHolder implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringContextHolder.class);

    @Override
    public void setApplicationContext(ApplicationContext context) {
        if (applicationContext != null) {
            LOGGER.error("# SpringContextHolder 已设定, 不能重复设定");
            return;
        }
        applicationContext = context;
        LOGGER.info("# SpringContextHolder 设定成功");
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> clazz) {
        return (T) applicationContext.getBean(beanName, clazz);
    }

    public static ApplicationContext getContext() {
        return applicationContext;
    }
}
