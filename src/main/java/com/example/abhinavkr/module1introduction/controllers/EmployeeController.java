package com.example.abhinavkr.module1introduction.controllers;
import com.example.abhinavkr.module1introduction.dto.EmployeeDTO;
import com.example.abhinavkr.module1introduction.exceptions.ResourceNotFoundException;
import com.example.abhinavkr.module1introduction.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id) {
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);

        return  employeeDTO.map(EmployeeDTO1 -> ResponseEntity.ok(EmployeeDTO1))
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id:" + id));
    }

    @GetMapping(path = "")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee) {
   EmployeeDTO savedEmployee = employeeService.createEmployee(inputEmployee);
   return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeId(@RequestBody @Valid EmployeeDTO employeeDTO, @PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeService.updateEmployeeId(employeeId, employeeDTO));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId) {
        boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);
        if (gotDeleted) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path= "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates, @PathVariable Long employeeId) {
    EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(employeeId, updates);
    if(employeeDTO==null){
        return ResponseEntity.notFound().build();
    } else {
        return ResponseEntity.ok(employeeDTO);
    }
    }
}