package com.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.springboot.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private UserMapper userMapper;
	
	public boolean addUser(String userName, Integer age) {
		// mysql操作，插入数据项到表 springboot_tbl
		// 数据库名和账号密码在 application.yml 中声明
		String sql = "insert into springboot_tbl(userName,age) values(?,?);";
		return (jdbcTemplate.update(sql, userName, age) > 0) ? true : false;
	}
	
	public boolean insert(String userName, Integer age) {
		System.out.println("UserService.insert: userName = " + userName + ", age = " + age);
		return (userMapper.insert(userName, age)) ? true : false;
	}
}
