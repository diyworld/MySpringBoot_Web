package com.springboot;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexService {
	
	@RequestMapping("/index")
	public Map<String, String> index() {
		// respCode respMsg data
		Map<String, String> map = new HashMap<String, String>();
		map.put("respCode", "200");
		map.put("respMsg", "OK");
		map.put("data", "class is IndexService");
		return map;
	}
}
