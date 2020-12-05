package com.springboot.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
	@ResponseBody
	@RequestMapping("/IndexController")
	public String index() {
		return "This Index Controller Class.";
	}
	
	@RequestMapping("/freemarkerIndex")
	public String freemarkerIndex(Map<Object, Object> resultMap) {
		resultMap.put("name", "新柳");
		resultMap.put("sex", "2");
		ArrayList<Object> objects = new ArrayList<>();
		objects.add("LagaLaga");
		objects.add("-lalalallal");
		resultMap.put("objects", objects);
		return "freemarkerIndex";
	}
}
