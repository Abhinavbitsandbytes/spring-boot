package com.example.abhinavkr.module1introduction;

import com.example.abhinavkr.module1introduction.impl.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Module1introductionApplication implements CommandLineRunner {

	@Autowired
	NotificationService notificationServiceObj;
	public static void main(String[] args) {
		SpringApplication.run(Module1introductionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		notificationServiceObj.send("hello");
	}
}
// if we use @Component annotation in both EmailNotification and Sms Service we will get error -
// Field notificationServiceObj in com.example.abhinavkr.module1introduction.Module1introductionApplication required a single bean, but 2 were found:

//solution
//1 - annotate anyone with Primary. if you add in email, output - email sending...hello