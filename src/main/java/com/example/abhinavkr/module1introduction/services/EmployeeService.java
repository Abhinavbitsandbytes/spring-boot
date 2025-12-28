package com.example.abhinavkr.module1introduction.services;

import com.example.abhinavkr.module1introduction.dto.EmployeeDTO;
import com.example.abhinavkr.module1introduction.entities.EmployeeEntity;
import com.example.abhinavkr.module1introduction.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO getEmployeeById(Long id) {
       EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
       return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities.stream()
                .map(entity -> modelMapper.map(entity, EmployeeDTO.class))
                .toList();
    }

    public EmployeeDTO createEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }
}

//Importance of service layer
//The service layer acts as a bridge between the persistence layer(responsible for data access) and the presentation layer(handling user interaction)
//Suppose we have 2 controllers (employee & dept)
//now both these controllers wants to get the data from EmployeeRepository.
//While getting the data we want to log something, authenticate the user. In that case we will be duplicating our code in all the controllers.
//The service layer will come between controllers and Repository.
//The employee Service will tell the controllers that if you want some data from EmployeeRepository, just tell me, I will get the data
//from employeeRepository, add logs, perform auth, and then give you the data.
