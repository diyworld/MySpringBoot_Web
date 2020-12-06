package com.springboot.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages = "com.springboot.order", sqlSessionTemplateRef="orderSqlSessionTemplate")
public class OrderDatabaseConfig {
	
	/**
	 * 创建数据源 DataSource
	 * @return
	 */
	@Bean("orderDataSource")
	/* 指定 .yml配置文件的数据源前缀 spring.datasource.springboot_order */
	@ConfigurationProperties(prefix="spring.datasource.order")
	public DataSource orderDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	/**
	 * 创建 SqlSessionFactory
	 * @param dataSource
	 * @return
	 * @throws Exception
	 */
	@Bean(name = "orderSqlSessionFactory")
	public SqlSessionFactory orderSqlSessionFactory(
			@Qualifier("orderDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(orderDataSource());
		return sqlSessionFactoryBean.getObject();
	}
	
	/**
	 * 创建 事务管理器
	 * @param dataSource
	 * @return
	 */
	@Bean(name = "orderTransactionManager")
	public DataSourceTransactionManager orderTransactionManager(
			@Qualifier("orderDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	/**
	 * 创建模板
	 * @param sqlSessionFactory
	 * @return
	 */
	@Bean(name = "orderSqlSessionTemplate")
	public SqlSessionTemplate orderSqlSessionTemplate(
			@Qualifier("orderSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
