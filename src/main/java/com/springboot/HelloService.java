package com.springboot;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

//当前类所有定义方法，统一返回 json
@RestController
//启动 SpringMVC
//@EnableAutoConfiguration
//指定@EnableAutoConfiguration的扫描包
//@ComponentScan("com.springboot")
public class HelloService {
	//@RestController 表示当前类所有定义方法，统一返回 json
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello SpringBoot.";
	}
	/*
	public static void main(String[] args) {
		//@EnableAutoConfiguration 启动 SpringMVC
		SpringApplication.run(HelloService.class, args);
	}
	*/
}
