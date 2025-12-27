package com.example.abhinavkr.module1introduction.controllers;
import com.example.abhinavkr.module1introduction.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    @GetMapping(path = "/{employeeId}") // there was a typo. s was missing.
    // variable name employeeId should be same here and in the EmployeeDTO argument
    public EmployeeDTO getEmployeeById(@PathVariable(name="employeeId") Long id) {
        return new EmployeeDTO(id, "Abhinav", "abhinav@gmail.com", 30, LocalDate.of(2025, 8, 18), true);

    }

    @GetMapping(path = "")
    public String getAllEmployees(@RequestParam(name="inputAge") Integer age, @RequestParam String sortBy) {
        return "Hi age " + age + " " + sortBy;
    }
//  simple post
//    @PostMapping
//    public String createNewEmployee(){
//        return "Hello from post";
//    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        // In real world, you would save this employee to the database
        // and return the saved employee with an assigned ID.
        inputEmployee.setId(420L); // Simulating that the employee got an ID after being saved.
        return inputEmployee;
    }


    @PostMapping
    public EmployeeDTO createNewEmployee2(@RequestBody EmployeeDTO inputEmployee){
        /*
         * Incoming request -> Java object:
         * - @RequestBody tells Spring to use an HttpMessageConverter to read the request body.
         * - By default Spring Boot registers MappingJackson2HttpMessageConverter (Jackson).
         * - Jackson deserializes the JSON into an EmployeeDTO instance by matching JSON keys
         *   to Java property names (using fields or setters). This is automatic — no manual
         *   mapping code is needed here.
         * - Requirements for successful mapping:
         *     - Request Content-Type should be "application/json"
         *     - EmployeeDTO should have a no-arg constructor and proper setters (or public fields)
         *     - JSON property names should match DTO property names (case-sensitive mapping rules apply)
         * - If mapping fails (type mismatch / invalid JSON), Spring will return a 4xx error.
         */

        // Simulate persistence assigning an ID after "saving" the entity.
        inputEmployee.setId(420L);

        /*
         * Java object -> HTTP response:
         * - Because the controller is a @RestController, the return value is treated like @ResponseBody.
         * - Spring uses the same HttpMessageConverter (Jackson) to serialize the EmployeeDTO to JSON.
         * - The HTTP response body will contain the JSON representation and Content-Type will be "application/json".
         *
         * Summary: mapping (data <-> object) is handled automatically by Spring + Jackson; your method
         * only operates on the already-populated DTO and returns it for automatic serialization.
         */

        return inputEmployee;
    }

    
    @PutMapping
    public String updateEmployeeId(){
        return "Hello from put";
    }
}
