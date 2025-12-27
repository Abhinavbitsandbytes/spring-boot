package com.example.abhinavkr.module1introduction;

import com.example.abhinavkr.module1introduction.impl.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Module1introductionApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Module1introductionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		NotificationService notificationServiceObj = new EmailNotificationService();
		notificationServiceObj.send("hello");
//			this is tight coupling because if we want to use SmsNotificationService in the future, I will have to come here and change the code.
//			Its tight coupling
	}
}
