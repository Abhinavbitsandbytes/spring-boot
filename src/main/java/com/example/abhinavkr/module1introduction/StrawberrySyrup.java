package com.example.abhinavkr.module1introduction;
import org.springframework.stereotype.Component;

@Component("strawberrySyrup")
public class StrawberrySyrup implements Syrup {
    @Override
    public String getSyrupType() {
        return "Strawberry Syrup";
    }
}
