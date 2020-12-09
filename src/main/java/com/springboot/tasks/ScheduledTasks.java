package com.springboot.tasks;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
	private static final SimpleDateFormat dateFormat = 
			new SimpleDateFormat("HH:mm:ss");
	
	/* 5s打印一个时间戳 */
	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
		System.out.println("现在时间: " + dateFormat.format(new Date()));
	}
	
}
