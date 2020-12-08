package com.springboot.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mysql.cj.jdbc.MysqlXADataSource;

@Configuration
@MapperScan(basePackages = "com.springboot.order.mapper", sqlSessionTemplateRef="orderSqlSessionTemplate")
public class OrderDatabaseConfig {
	
	/**
	 * 创建数据源 DataSource
	 * @return
	 */
	@Bean("orderDataSource")
	/* 指定 .yml配置文件的数据源前缀 spring.datasource.springboot_order */
	@ConfigurationProperties(prefix="spring.datasource.order")
	public DataSource orderDataSource(OrderConfig orderConfig) throws SQLException {
		// 创建 xaDataSource
		MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
		mysqlXaDataSource.setUrl(orderConfig.getUrl());
		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
		mysqlXaDataSource.setPassword(orderConfig.getPassWord());
		mysqlXaDataSource.setUser(orderConfig.getUserName());
		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
		
		// 注册到全局事务上
		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(mysqlXaDataSource);
		xaDataSource.setUniqueResourceName(orderConfig.getUniqueResourceName());
		xaDataSource.setMinPoolSize(orderConfig.getMinPoolSize());
		xaDataSource.setMaxPoolSize(orderConfig.getMaxPoolSize());
		xaDataSource.setMaxLifetime(orderConfig.getMaxLifeTime());
		xaDataSource.setBorrowConnectionTimeout(orderConfig.getLoginTimeout());
		xaDataSource.setMaintenanceInterval(orderConfig.getMaintenanceInterval());
		xaDataSource.setMaxIdleTime(orderConfig.getMaxIdleTime());
		xaDataSource.setTestQuery(orderConfig.getTestQuery());
		
		return xaDataSource;
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
		sqlSessionFactoryBean.setDataSource(dataSource);
		return sqlSessionFactoryBean.getObject();
	}
	
	/**
	 * 创建 事务管理器
	 * @param dataSource
	 * @return
	 */
	/*
	@Bean(name = "orderTransactionManager")
	public DataSourceTransactionManager orderTransactionManager(
			@Qualifier("orderDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	*/

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
