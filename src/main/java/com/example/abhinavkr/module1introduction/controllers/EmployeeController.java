package com.example.abhinavkr.module1introduction.controllers;
import com.example.abhinavkr.module1introduction.dto.EmployeeDTO;
import com.example.abhinavkr.module1introduction.entities.EmployeeEntity;
import com.example.abhinavkr.module1introduction.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    // constructor
    public EmployeeController(EmployeeRepository employeeRepository){
        // this is a constructor injection
        this.employeeRepository = employeeRepository;
    }


    @GetMapping(path = "/{employeeId}") // there was a typo. s was missing.
    // variable name employeeId should be same here and in the EmployeeDTO argument
    public EmployeeEntity getEmployeeById(@PathVariable(name="employeeId") Long id) {
        return employeeRepository.findById(id).orElse(null);
        // it will return optional<EmployeeEntity>
        // optional means it can have value or it can be empty
        // for time being we are returning EmployeeEntity directly instead of EmployeeDTO. But once we have our service layer ready we will handle the optional properly.
        // we should never import Entity class directly in the controller layer. We should use DTO(Data Transfer Object) for that.

    }

    @GetMapping(path = "")
    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee){
        return employeeRepository.save(inputEmployee);
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

