package com.hansy.tyly;

import com.hansy.tyly.core.holder.SpringContextHolder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Startup {

    private static final Logger LOGGER = LoggerFactory.getLogger(Startup.class);

    @Bean
    public SpringContextHolder getSpringContextHolder() {
        return new SpringContextHolder();
    }

    public static void main(String[] args) {
        SpringApplication.run(Startup.class, args);
    }

    @Bean
    public CommandLineRunner printRunEnvInfo(ApplicationContext applicationContext) {
        return args -> {
            LOGGER.info("# ————————————————————————————————————————————————— ");
            LOGGER.info("# 天宇粮油平台管理系统(Watchdog Admin V1.0.0) 启动");
            LOGGER.info("# 系统运行环境：" + applicationContext.getEnvironment().getProperty("spring.profiles.active", "default(默认)"));
            LOGGER.info("# 系统临时目录：" + applicationContext.getEnvironment().getProperty("java.io.tmpdir"));
            LOGGER.info("# ————————————————————————————————————————————————— ");
        };
    }
}
