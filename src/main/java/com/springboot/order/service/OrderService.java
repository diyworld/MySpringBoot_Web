package com.springboot.order.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.config.OrderConfig;
import com.springboot.order.mapper.OrderMapper;

@Service
/* @Transactional: 事务底层原理采用 aop技术增强, 即如果函数后续的处理逻辑不正确，则不会把数据填写到数据库 */
/* 统一使用全局事务管理器 */
@Transactional()
@EnableConfigurationProperties(OrderConfig.class)
public class OrderService {
	@Autowired
	private OrderMapper orderMapper;

	/* http://localhost:8080/findOrderByName?orderName=order1 */
	public OrderEntity findOrderByName(String orderName) {
		return orderMapper.findByName(orderName);
	}

	/* http://localhost:8080/findOrderById?orderId=1 */
	public OrderEntity findOrderById(Integer orderId) {
		int j = 1/orderId; //测试项
		return orderMapper.findById(orderId);
	}
	
	/* http://localhost:8080/insertOrder?orderName=order2&date=2020-12-6 */
	public String insertOrder(String orderName, Date date) {
		return orderMapper.insert(orderName, date) ? "success" : "fail";
	}
}

