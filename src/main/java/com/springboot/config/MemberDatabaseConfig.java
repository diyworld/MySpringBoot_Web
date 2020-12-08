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

/* 相当于 MemberDatabaseConfig.xml的配置功能 */
@Configuration
@MapperScan(basePackages = "com.springboot.member.mapper", sqlSessionTemplateRef="memberSqlSessionTemplate")
public class MemberDatabaseConfig {
	
	/**
	 * 创建数据源 DataSource, 将数据源统一交给全局的xa事务管理
	 * @return
	 * @throws SQLException 
	 */
	/* 注入到容器 */
	@Bean("memberDataSource")
	/* 指定前缀，从.yml文件获取 spring.datasource.member */
	@ConfigurationProperties(prefix = "spring.datasource.member")
	public DataSource memberDataSource(MemberConfig memberConfig) throws SQLException {
		// 创建 xaDataSource
		MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
		mysqlXaDataSource.setUrl(memberConfig.getUrl());
		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
		mysqlXaDataSource.setPassword(memberConfig.getPassWord());
		mysqlXaDataSource.setUser(memberConfig.getUserName());
		mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
		
		// 注册到全局事务上
		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(mysqlXaDataSource);
		xaDataSource.setUniqueResourceName(memberConfig.getUniqueResourceName());
		xaDataSource.setMinPoolSize(memberConfig.getMinPoolSize());
		xaDataSource.setMaxPoolSize(memberConfig.getMaxPoolSize());
		xaDataSource.setMaxLifetime(memberConfig.getMaxLifeTime());
		xaDataSource.setBorrowConnectionTimeout(memberConfig.getLoginTimeout());
		xaDataSource.setMaintenanceInterval(memberConfig.getMaintenanceInterval());
		xaDataSource.setMaxIdleTime(memberConfig.getMaxIdleTime());
		xaDataSource.setTestQuery(memberConfig.getTestQuery());
		
		return xaDataSource;
	}
	
	/**
	 * 创建 SqlSessionFactory
	 * @param dataSource
	 * @return
	 * @throws Exception
	 */
	@Bean(name = "memberSqlSessionFactory")
	public SqlSessionFactory memberSqlSessionFactory(
			@Qualifier("memberDataSource") DataSource dataSource) throws Exception {
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
	@Bean(name = "memberTransactionManager")
	public DataSourceTransactionManager memberTransactionManager(
			@Qualifier("memberDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	*/

	/**
	 * 创建模板
	 * @param sqlSessionFactory
	 * @return
	 */
	@Bean(name = "memberSqlSessionTemplate")
	public SqlSessionTemplate memberSqlSessionTemplate(
			@Qualifier("memberSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
