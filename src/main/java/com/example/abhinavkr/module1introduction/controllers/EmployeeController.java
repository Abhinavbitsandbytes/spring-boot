package com.example.abhinavkr.module1introduction.controllers;

import com.example.abhinavkr.module1introduction.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class EmployeeController {
    @GetMapping(path = "/employee/{employeeId}")
    // variable name employeeId should be same here and in the EmployeeDTO argument
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId) {
        return new EmployeeDTO(employeeId, "Abhinav", "abhinav@gmail.com", 30, LocalDate.of(2025, 8, 18), true);

    }

    @GetMapping(path = "/employees")
    public String getAllEmployees(@RequestParam Integer age, @RequestParam String sortBy) {
        return "Hi age " + age + " " + sortBy;
    }
}

//if url = "http://localhost:8080/employees?age=30&sortBy=price"
// output = "Hi age 30 price"
//if you want to make params optional
//public String getAllEmployees(@RequestParam(required=false) Integer age, @RequestParam(required=false) String sortBy)