package com.example.abhinavkr.module1introduction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Module1introductionApplication implements CommandLineRunner {
	@Autowired
	Map<String, NotificationService> notificationServiceMap = new HashMap<>();
	public static void main(String[] args) {
		SpringApplication.run(Module1introductionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		notificationServiceObj.send("hello");
	for(var notificationService: notificationServiceMap.entrySet()){
		System.out.println(notificationService.getKey());
		notificationService.getValue().send("Hello");
	}
	}
//	output
//	emailNotificationService
//	email sending...Hello
//	smsNotificationService
//	sms sending...Hello
}
