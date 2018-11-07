package com.lemoncome.watchdog.tempindicators.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Service("mongoDBUtils")

@PropertySource(value = {"classpath:conf_local.properties"},encoding="utf-8")  
public class MongoDBUtils {

	private static final Logger LOGGER = Logger.getLogger(MongoDBUtils.class);

	@Value("${mongo.host}")
	private String host;

	@Value("${mongo.max.maxConnection}")
	private String maxConnection;

	@Value("${mongo.max.connectionQueue}")
	private String connectionQueue;

	@Value("${mongo.max.maxWaitTime}")
	private String maxWaitTime;

	@Value("${mongo.max.connectTimeout}")
	private String connectTimeout;
	
	@Value("${mongo.userName}")
	private String userName;
	
	@Value("${mongo.password}")
	private String password;

	// 线程安全的, mongo是线程不安全的
	private static MongoClient mongoClient = null;
	
	//MongoDB默认端口号
	private final int DEFAULT_PORT = 27017;

	public MongoClient getMongoClient() {
		return mongoClient;
	}

	public void setMongoClient(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}

	@PostConstruct
	public void init() {
		if (mongoClient == null) {
			synchronized (MongoDBUtils.class) {
				if (mongoClient == null) {
					String mongodbAddress = host;
					MongoClientOptions.Builder build = new MongoClientOptions.Builder();
					build.connectionsPerHost(Integer.valueOf(maxConnection)); // 与目标数据库能够建立的最大connection数量为50
					build.threadsAllowedToBlockForConnectionMultiplier(Integer.valueOf(connectionQueue)); // 如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待
					build.maxWaitTime(Integer.valueOf(maxWaitTime));
					build.connectTimeout(Integer.valueOf(connectTimeout)); // 与数据库建立连接的timeout设置为1分钟
					MongoClientOptions myOptions = build.build();
					try {
						if (mongodbAddress != null && !mongodbAddress.trim().isEmpty()) {
							String[] address = mongodbAddress.split(",");
							List<ServerAddress> list = new ArrayList<ServerAddress>();
							for (String addr : address) {
								String[] ipAndPort = addr.split(":");
								String ip = ipAndPort[0];
								int port = DEFAULT_PORT;
								if (ipAndPort.length == 2) {
									port = Integer.valueOf(ipAndPort[1]);
								} 
								list.add(new ServerAddress(ip,port));
							}
							
							//初始化密码信息
							List<MongoCredential> credentialList = new ArrayList<MongoCredential>();
							MongoCredential credential = MongoCredential.createCredential(userName, "admin", password.toCharArray());  
							credentialList.add(credential);
							// 数据库连接实例
							mongoClient = new MongoClient(list, credentialList, myOptions);
						}
					} catch (Exception e) {
						LOGGER.error(e.getMessage(), e);
					}
				
				}
			}
		}

	}

	
	@PreDestroy
	public void close() {
		//关闭连接池
		if (mongoClient != null) {
			mongoClient.close();
		}
	}
	
}
