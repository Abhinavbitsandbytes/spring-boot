package com.example.abhinavkr.module1introduction.controllers;

import com.example.abhinavkr.module1introduction.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class EmployeeController {

    @GetMapping("/employee/{employeeId}") // variable name employeeId should be same here and in the EmployeeDTO argument
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId){
        return new EmployeeDTO(employeeId, "Abhinav", "abhinav@gmail.com", 30, LocalDate.of(2025, 8, 18), true);

    }
}

// if you will go to http://localhost:8080/employee/1997
//output ={
//"id": 1997,
//"name": "Abhinav",
//"email": "abhinav@gmail.com",
//"age": 30,
//"dateOfJoining": "2025-08-18",
//"active": true
//}

