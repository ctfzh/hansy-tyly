package com.hansy.tyly;

import com.hansy.tyly.core.holder.SpringContextHolder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class Startup {

    private static final Logger LOGGER = LoggerFactory.getLogger(Startup.class);

    @Bean
    public SpringContextHolder getSpringContextHolder() {
        return new SpringContextHolder();
    }

    public static void main(String[] args) {
    	//SpringApplication application = new SpringApplication(Startup.class);
    	 /*
         * Banner.Mode.OFF:关闭;
         * Banner.Mode.CONSOLE:控制台输出，默认方式;
         * Banner.Mode.LOG:日志输出方式;
         */
        //application.setBannerMode(Banner.Mode.OFF); 
    	//application.run(args);
        SpringApplication.run(Startup.class, args);
    	
    }

    @Bean
    public CommandLineRunner printRunEnvInfo(ApplicationContext applicationContext) {
        return args -> {
            LOGGER.info("# ————————————————————————————————————————————————— ");
            LOGGER.info("# 优闪配后台系统(Watchdog Customer V1.0.0) 启动");
            LOGGER.info("# 系统运行环境：" + applicationContext.getEnvironment().getProperty("spring.profiles.active", "default(默认)"));
            LOGGER.info("# 系统临时目录：" + applicationContext.getEnvironment().getProperty("java.io.tmpdir"));
            LOGGER.info("# ————————————————————————————————————————————————— ");
        };
    }
}
