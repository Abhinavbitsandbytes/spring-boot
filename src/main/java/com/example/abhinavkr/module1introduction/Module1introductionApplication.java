package com.example.abhinavkr.module1introduction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Module1introductionApplication implements CommandLineRunner {

@Autowired
PaymentService paymentServiceObj;

@Autowired
PaymentService	paymentServiceObj2;

	public static void main(String[] args) {
		SpringApplication.run(Module1introductionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(paymentServiceObj.hashCode());
		System.out.println(paymentServiceObj2.hashCode());// both hash code are same because by default beans are singleton
// when we use @Scope("prototype"), then objects will different so will be the hashcode.
		paymentServiceObj.pay();
	}
}
