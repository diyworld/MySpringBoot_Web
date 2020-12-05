package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.UserEntity;
import com.springboot.UserService;

@Controller
public class MyBatisController {
	@Autowired
	private UserService userService;

	
	@ResponseBody
	@RequestMapping("/addUser")
	public String addUser(String userName, Integer age) {
		// 在网页输入 http://localhost:8080/addUser?userName=ccx3&age=31
		return (userService.addUser(userName, age)) ? "success" : "fail";
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public String insert(String userName, Integer age) {
		System.out.println("MyBatisController.insert: userName = " + userName + ", age = " + age);
		return (userService.insert(userName, age)) ? "success" : "fail";
	}
	
	@ResponseBody
	@RequestMapping("/findByName")
	public UserEntity findByName(String userName) {
		System.out.println("MyBatisController.userEntity: userName = " + userName);
		return userService.findByName(userName);
	}
}
