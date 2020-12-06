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

/* 相当于 MemberDatabaseConfig.xml的配置功能 */
@Configuration
@MapperScan(basePackages = "com.springboot.member", sqlSessionTemplateRef="memberSqlSessionTemplate")
public class MemberDatabaseConfig {
	
	/**
	 * 创建数据源 DataSource
	 * @return
	 */
	/* 注入到容器 */
	@Bean("memberDataSource")
	/* 指定前缀，从.yml文件获取 spring.datasource.springboot_member */
	@ConfigurationProperties(prefix = "spring.datasource.member")
	public DataSource memberDataSource() {
		return DataSourceBuilder.create().build();
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
		sqlSessionFactoryBean.setDataSource(memberDataSource());
		return sqlSessionFactoryBean.getObject();
	}
	
	/**
	 * 创建 事务管理器
	 * @param dataSource
	 * @return
	 */
	@Bean(name = "memberTransactionManager")
	public DataSourceTransactionManager memberTransactionManager(
			@Qualifier("memberDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

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
