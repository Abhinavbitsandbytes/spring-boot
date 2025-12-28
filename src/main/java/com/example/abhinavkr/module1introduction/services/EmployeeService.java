package com.example.abhinavkr.module1introduction.services;

import com.example.abhinavkr.module1introduction.dto.EmployeeDTO;
import com.example.abhinavkr.module1introduction.entities.EmployeeEntity;
import com.example.abhinavkr.module1introduction.exceptions.ResourceNotFoundException;
import com.example.abhinavkr.module1introduction.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.data.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
       Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
       return employeeEntity.map(entity -> modelMapper.map(entity, EmployeeDTO.class));
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

    public EmployeeDTO updateEmployeeId(Long employeeId, EmployeeDTO employeeDTO){
        isExistsByEmployeeId(employeeId);
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);

        // if id is present then it will update the existing record
        // if id is not present then it will create a new record
    }

    public void isExistsByEmployeeId(Long employeeId){
        boolean isExists = employeeRepository.existsById(employeeId);
        if(!isExists){
            throw new ResourceNotFoundException("Employee not found with id: " + employeeId);
        }
    }

    public boolean deleteEmployeeById(Long employeeId){
        isExistsByEmployeeId(employeeId);
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        isExistsByEmployeeId(employeeId);
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();

        updates.forEach((field, value) -> {
          Field fieldToBeUpdated =  ReflectionUtils.getRequiredField(EmployeeEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);

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
