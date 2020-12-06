package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication 等同于 EnableAutoConfiguration和 ComponentScan
//@SpringBootApplication 下扫描的是 AppStringBoot对应的包所属的同级文件
@SpringBootApplication
@ComponentScan("com.springboot")
//@SpringBootApplication 无法扫到 mybatis, 需要专门的 @MapperScan来扫
//@MapperScan("com.springboot.member")
public class AppSpringBoot {
	public static void main(String[] args) {
		SpringApplication.run(AppSpringBoot.class, args);
	}
}
