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

    public EmployeeDTO getEmployeeByIddummy(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        ModelMapper mapper = new ModelMapper();
         EmployeeDTO employeeDTO = mapper.map(employeeEntity, EmployeeDTO.class); //mapper.map(source, destinationType)
        return employeeDTO;
        // ModelMapper is used to map the entity to DTO
        // here we are using ModelMapper to map EmployeeEntity to EmployeeDTO
        // we can also do the mapping manually
        // but using ModelMapper is a good practice
        // ModelMapper will go through all the fields of EmployeeEntity and EmployeeDTO
        // and will map the fields with same name and type
        // if the field names are different then we can use @Mapping annotation to map the fields
        // but here the field names are same so we don't need to use @Mapping annotation
        // if any field is missing in EmployeeDTO then it will be ignored
        // if any field is missing in EmployeeEntity then it will be set to null in EmployeeDTO

        // ModelMapper mapper = new ModelMapper(); this will get repeated every time we need to map in any method
        // better to define it as a bean in configuration class and autowire it here in service class

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
        // 1) DTO -> Entity
        // ModelMapper copies values from inputEmployee to a new EmployeeEntity instance.
        // It maps properties by name/type (including nested properties if configured).
        // This Entity is suitable for persistence (JPA/Hibernate).
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);

        // 2) Persist the entity
        // employeeRepository.save(...) persists the entity and returns the saved instance.
        // The returned instance typically has database-generated values populated
        // (for example, the primary key `id`, timestamps, defaults, etc.).
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntity);

        // 3) Entity -> DTO
        // Map the persisted entity back to a DTO to return to the caller.
        // Returning a DTO avoids leaking persistence internals (lazy associations, JPA annotations).
        // If you need to include newly generated fields (like `id`), they will be present here.
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
