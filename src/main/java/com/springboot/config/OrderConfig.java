package com.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

//@ConfigurationProperties 自动加载配置文件内容到变量中
@ConfigurationProperties(prefix = "spring.datasource.order")
@Data
public class OrderConfig {
	
	private String url;
	private String userName;
	private String passWord;
	private int minPoolSize;
	private int maxPoolSize;
	private int maxLifeTime;
	private int borrowConnectionTimeout;
	private int loginTimeout;
	private int maintenanceInterval;
	private int maxIdleTime;
	private String testQuery;
	private String uniqueResourceName;
	
}
