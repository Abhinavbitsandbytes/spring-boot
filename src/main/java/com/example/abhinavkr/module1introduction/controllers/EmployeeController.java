package com.example.abhinavkr.module1introduction.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @GetMapping(path = "/getSecretMessage")
    public String getMySuperSecretMessage() {
        return "secret message: 1A@secretMessage";
    }
}
// now if we will go to http://localhost:8080/getSecretMessage
// output will be: secret message: 1A@secretMessage
