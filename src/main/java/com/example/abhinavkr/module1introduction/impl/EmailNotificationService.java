package com.example.abhinavkr.module1introduction.impl;

import com.example.abhinavkr.module1introduction.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Primary
@Component
@Qualifier("emailNotif")
public class EmailNotificationService implements NotificationService {

    @Override
    public void send(String message){
        System.out.println("email sending..."+ message);
    }
}
