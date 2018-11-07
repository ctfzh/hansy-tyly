package com.lemoncome.watchdog.core.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.lemoncome.watchdog.core.holder.SpringContextHolder;
import com.lemoncome.watchdog.core.mybatis.CameHumpInterceptor;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

@Configuration
@Order(0)
public class MybatisConfiguration {

    @Bean
    public MapperScannerConfigurer getMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.lemoncome.watchdog.**.mapper");
        Properties properties = new Properties();
        properties.setProperty("mappers", "tk.mybatis.mapper.common.Mapper");
        mapperScannerConfigurer.setProperties(properties);
        mapperScannerConfigurer.setNameGenerator((beanDefinition, beanDefinitionRegistry) -> beanDefinition.getBeanClassName());
        return mapperScannerConfigurer;
    }


    @Bean
    public ConfigurationCustomizer getConfigurationCustomizer() {
        return configuration -> {
            // 映射实体类
            configuration.setMapUnderscoreToCamelCase(true);
            // 返回null map时
            configuration.setCallSettersOnNulls(true);
            // 返回map对象时，字段下划线转小驼峰
            configuration.addInterceptor(new CameHumpInterceptor());
        };
    }


    @Bean
    public CommandLineRunner testDbConnection() {
        return args -> {
            DruidDataSource dataSource = SpringContextHolder.getBean(DruidDataSource.class);
            dataSource.getConnection().prepareStatement("select 1 from dual").executeQuery();
        };
    }
}
