package com.example.abhinavkr.module1introduction.impl;

import com.example.abhinavkr.module1introduction.NotificationService;
import org.springframework.stereotype.Component;

@Component
public class SmsNotificationService implements NotificationService {

    @Override
    public void send(String message){
    System.out.println("sms sending..."+ message);
    }
}
