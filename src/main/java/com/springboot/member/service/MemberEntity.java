package com.springboot.member.service;

import java.sql.Date;

public class MemberEntity {
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
