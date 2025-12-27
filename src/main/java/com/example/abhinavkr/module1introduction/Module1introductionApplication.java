package com.example.abhinavkr.module1introduction;

import com.example.abhinavkr.module1introduction.impl.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Module1introductionApplication implements CommandLineRunner {

	final NotificationService notificationServiceObj;

	public Module1introductionApplication(
			@Qualifier("smsNotif") NotificationService notificationServiceObj){
			this.notificationServiceObj = notificationServiceObj; // constructor DI (preferred instead of Field injection)

	}
	public static void main(String[] args) {
		SpringApplication.run(Module1introductionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		notificationServiceObj.send("hello");
	}
}
