package com.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MybatisExceptionHandler {
	@ExceptionHandler({RuntimeException.class})
	@ResponseBody
	public Map<String, String> exceptionHandler() {
		HashMap<String, String> objectHashMap = new HashMap<>();
		objectHashMap.put("respCode", "500");
		objectHashMap.put("respMsg", "系统错误");
		return objectHashMap;
	}

}
