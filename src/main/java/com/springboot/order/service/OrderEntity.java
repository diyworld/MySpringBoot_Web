package com.springboot.order.service;

import java.sql.Date;

public class OrderEntity {
	private String name;
	private Integer id;
	private Date date;
	
	public String getName() {
		return name;
	}
	
	public Integer getId() {
		return id;
	}
	
	public Date getDate() {
		return date;
	}
}
