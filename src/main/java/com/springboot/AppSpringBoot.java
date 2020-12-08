package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication 等同于 EnableAutoConfiguration和 ComponentScan
//@SpringBootApplication 下扫描的是 AppStringBoot对应的包所属的同级文件
@SpringBootApplication
@EnableScheduling
//@EnableConfigurationProperties(MemberConfig.class, OrderConfig.class)
public class AppSpringBoot {
	public static void main(String[] args) {
		SpringApplication.run(AppSpringBoot.class, args);
	}
}
