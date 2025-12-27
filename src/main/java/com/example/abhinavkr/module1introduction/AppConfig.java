package com.example.abhinavkr.module1introduction;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    @Scope("prototype")
    PaymentService paymentService(){
        // can add more logic
        return new PaymentService();
    }
@PostConstruct
    public void myAfterInit(){
    System.out.println("before paying");
    }

@PreDestroy
    public void myBeforeDestroy(){
        System.out.println("After Payment");
    }
}
