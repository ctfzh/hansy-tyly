package com.hansy.tyly;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.hansy.tyly.core.excepiton.ServiceException;
import com.hansy.tyly.core.holder.SpringContextHolder;
import com.hansy.tyly.riskbatch.service.BatchResultMsgService;
import com.hansy.tyly.riskbatch.service.LemonA5SubmitService;
import com.hansy.tyly.riskbatch.service.LemonA7QueryService;
import com.hansy.tyly.riskbatch.service.LemonProdIndcQueryService;
import com.hansy.tyly.riskbatch.service.OrgMessageService;

/**
 * Created by MIfengHe on 2017/10/30.
 */
@SpringBootApplication(exclude = {MongoAutoConfiguration.class,MongoDataAutoConfiguration.class})
@EnableTransactionManagement
public class Startup {
    private static final Logger LOGGER = LoggerFactory.getLogger(Startup.class);
    public static String BUSI_DATE = "";

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
            LOGGER.info("# 看家狗跑批系统(Watchdog Procbatch V1.0.0) 启动");
            LOGGER.info("# 系统运行环境：" + applicationContext.getEnvironment().getProperty("spring.profiles.active", "default(默认)"));
            LOGGER.info("# 系统临时目录：" + applicationContext.getEnvironment().getProperty("java.io.tmpdir"));
            LOGGER.info("# ————————————————————————————————————————————————— ");
        };
    }
	private String threeNull(Object obj) {
		return (null == obj || "".equals(obj.toString().trim())) ? null : obj.toString().trim();
	}
    @Bean
    public CommandLineRunner startService(ApplicationContext applicationContext) {
        return args -> {
        	/*TempindicatorsService testService = applicationContext.getBean(TempindicatorsService.class);
        	testService.queryTempindicators();*/
        	String serviceName = applicationContext.getEnvironment().getProperty("service.name");
        	if (StringUtils.isBlank(serviceName)) throw new ServiceException("请提供服务名称[--service.name=?]");
        	BUSI_DATE = applicationContext.getEnvironment().getProperty("service.busiDate");
			Map paramMap=new HashMap();
			paramMap.put("batchNo",threeNull(applicationContext.getEnvironment().getProperty("service.batchNo")));
        	//if (StringUtils.isBlank(BUSI_DATE)) throw new ServiceException("请提供业务日期[--service.busiDate=?],格式为[2017-10-09]...");
        	/**
        	 * service.name服务名：
        	 * 110->查询风控具体指标结果(查A7结果明细)
        	 * 111->风控A5接口提交
        	 * 112->风控A7接口提交(查询A5结果)
        	 * 113->机构短信通知服务
			 * 114->批处理结果短信通知
        	 * */
        	switch (serviceName) {
			case "110":
				applicationContext.getBean(LemonProdIndcQueryService.class).startService();
				break;
			case "111":
				applicationContext.getBean(LemonA5SubmitService.class).startService(paramMap);
				break;
			case "112":
				applicationContext.getBean(LemonA7QueryService.class).startService();
				break;
			case "113":
				applicationContext.getBean(OrgMessageService.class).startService();
				break;
			case "114":
				applicationContext.getBean(BatchResultMsgService.class).startService();
				break;
			default:
				throw new ServiceException(MessageFormat.format("服务[--service.name={0}]暂不支持", serviceName));
			}
        };
    }

}
