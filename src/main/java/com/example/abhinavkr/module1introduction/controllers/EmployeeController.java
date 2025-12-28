package com.example.abhinavkr.module1introduction.controllers;
import com.example.abhinavkr.module1introduction.dto.EmployeeDTO;
import com.example.abhinavkr.module1introduction.services.EmployeeService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    // constructor
    public EmployeeController(EmployeeService employeeService) {
        // this is a constructor injection
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}")
    // variable name employeeId should be same here and in the EmployeeDTO argument
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping(path = "")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee) {
        return employeeService.createEmployee(inputEmployee);
    }

    @PutMapping(path = "/{employeeId}")
    public EmployeeDTO updateEmployeeId(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId) {
        return employeeService.updateEmployeeId(employeeId, employeeDTO);
    }

    @DeleteMapping(path = "/{employeeId}")
    public boolean deleteEmployeeById(@PathVariable Long employeeId) {
        return employeeService.deleteEmployeeById(employeeId);
    }

    @PatchMapping(path= "/{employeeId}")
    public EmployeeDTO updatePartialEmployeeById(@RequestBody Map<String, Object> updates, @PathVariable Long employeeId) {
    return employeeService.updatePartialEmployeeById(employeeId, updates);
    }
}