package com.example.abhinavkr.module1introduction.controllers;
import com.example.abhinavkr.module1introduction.dto.EmployeeDTO;
import com.example.abhinavkr.module1introduction.services.EmployeeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    // constructor
    public EmployeeController(EmployeeService employeeService){
        // this is a constructor injection
        this.employeeService = employeeService;
    }


    @GetMapping(path = "/{employeeId}") // there was a typo. s was missing.
    // variable name employeeId should be same here and in the EmployeeDTO argument
    public EmployeeDTO getEmployeeById(@PathVariable(name="employeeId") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping(path = "")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        return employeeService.createEmployee(inputEmployee);
    }

    @PutMapping
    public String updateEmployeeId(){
        return "Hello from put";
    }
    // here we have injected the EmployeeRepository inside the controller
    // It is not a good practice to inject the repository inside the controller
    // we should inject the repository inside the service layer
    // and then inject the service layer inside the controller
    // but for simplicity we are injecting the repository inside the controller because we will study about service layer in next module


//    --------------

    //    EmployeeEntity — a JPA/persistence object. Annotated with @Entity. Represents how data is stored in the database (id, audit fields, relations, internal fields). Not ideal to return this directly from controllers because it may expose internal details or lazy-loaded associations.
//    EmployeeDTO — a data transfer object used at the API boundary. Shapes what you accept/return from controllers (validation annotations possible). Keeps internal/persistence concerns separate. You map between DTO and Entity when saving/reading.

//    How they might look when printed (JSON returned by a controller)
//    EmployeeDTO (what you should return from REST):
//    {
//        "id": 420,
//            "name": "Alice",
//            "age": 30
//    }
//    EmployeeEntity (if serialized directly, may include extra/internal fields):
//{
//    "id": 420,
//        "name": "Alice",
//        "age": 30,
//        "internalNote": "migrated from legacy system",
//        "createdAt": "2025-12-27T10:15:30"
//    Summary
//    Use Entity for DB operations and DTO for controller input/output.
//    Map between them (manually or with a mapper library) to avoid leaking persistence internals and to control API shape and validation.

}

