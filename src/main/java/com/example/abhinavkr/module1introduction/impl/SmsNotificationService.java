package com.example.abhinavkr.module1introduction.impl;

import com.example.abhinavkr.module1introduction.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@Qualifier("emailNotif")
@ConditionalOnProperty(name = "notification.type", havingValue ="sms")  // create the bean of this particular call only if notification.type has value "sms"
public class SmsNotificationService implements NotificationService {

    @Override
    public void send(String message){
    System.out.println("sms sending..."+ message);
    }
}

// it has made our object creation so loosely coupled. by using application properties, we are controlling which object to create
