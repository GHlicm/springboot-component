package com.example.demo.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTask {
	private int count = 0;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Scheduled(cron="*/6 * * * * ?")
	private void process() {
		System.out.println("this is the scheduler task runing: " + (count++));
	}
	
	@Scheduled(fixedRate = 6000)
	private void reportCurrentTime() {
		System.out.println("the current time is: " + dateFormat.format(new Date()));
	}
}
