package com.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

//@ConfigurationProperties 自动加载配置文件内容到变量中
@ConfigurationProperties(prefix = "spring.datasource.member")
@Data
public class MemberConfig {
	
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
	
	/*
	public String getUrl() {
		return this.url;
	}

	public String getPassWord() {
		// TODO Auto-generated method stub
		return this.passWord;
	}

	public String getUserName() {
		// TODO Auto-generated method stub
		return this.userName;
	}

	public String getUniqueResourceName() {
		// TODO Auto-generated method stub
		return this.uniqueResourceName;
	}

	public int getMinPoolSize() {
		// TODO Auto-generated method stub
		return this.minPoolSize;
	}

	public int getMaxPoolSize() {
		// TODO Auto-generated method stub
		return this.maxPoolSize;
	}

	public int getMaxLifetime() {
		// TODO Auto-generated method stub
		return this.maxLifeTime;
	}

	public int getLoginTimeout() {
		// TODO Auto-generated method stub
		return this.loginTimeout;
	}

	public int getMaintenanceInterval() {
		// TODO Auto-generated method stub
		return this.maintenanceInterval;
	}

	public int getMaxIdleTime() {
		// TODO Auto-generated method stub
		return this.maxIdleTime;
	}

	public String getTestQuery() {
		// TODO Auto-generated method stub
		return this.testQuery;
	}
	*/
}
