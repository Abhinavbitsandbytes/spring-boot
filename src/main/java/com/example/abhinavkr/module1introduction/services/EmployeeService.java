package com.example.abhinavkr.module1introduction.services;

import com.example.abhinavkr.module1introduction.dto.EmployeeDTO;
import com.example.abhinavkr.module1introduction.entities.EmployeeEntity;
import com.example.abhinavkr.module1introduction.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.data.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

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

    public EmployeeDTO updateEmployeeId(Long employeeId, EmployeeDTO employeeDTO){
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);

        // if id is present then it will update the existing record
        // if id is not present then it will create a new record
    }

    public boolean isExistsByEmployeeId(Long employeeId){
        return employeeRepository.existsById(employeeId);
    }

    public boolean deleteEmployeeById(Long employeeId){
        boolean exists = isExistsByEmployeeId(employeeId);
        if (!exists) {
            return false;
        }
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        boolean exists = isExistsByEmployeeId(employeeId);
        if (!exists) {
            return null;
        }
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();

        updates.forEach((field, value) -> {
          Field fieldToBeUpdated =  ReflectionUtils.getRequiredField(EmployeeEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);

    }
//    Explanation (step-by-step)
//    Check existence:
//    boolean exists = isExistsByEmployeeId(employeeId); if (!exists) return null; — ensure record exists before updating.
//    Load entity:
//    EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get(); — fetch current entity from DB.
//    Apply partial updates (reflection):
//            updates.forEach((field, value) -> { ... }) — iterate over a map of field names -> new values.
//            ReflectionUtils.getRequiredField(EmployeeEntity.class, field) — at runtime find the Field object for the named field.
//            fieldToBeUpdated.setAccessible(true); — bypass Java access checks (private/protected).
//            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value); — set the field value on the instance.
//    Persist and return:
//            employeeRepository.save(employeeEntity) — save modified entity.
//            modelMapper.map(..., EmployeeDTO.class) — convert saved entity to DTO for the API response.
//    What reflection is and why it's used
//    Reflection = inspecting and manipulating classes, fields, methods, constructors at runtime (when you don’t know them at compile time).
//    Useful for frameworks and generic code: ORMs, DI containers, serializers, test tools, mappers, and admin/interop code that must work with types dynamically.
//    In this code it’s used to apply updates when the set of fields to change is dynamic (the Map<String,Object> keys are field names).
//    Is using reflection good practice?
//    Pros:
//    Enables dynamic behavior and generic utilities.
//    Useful in frameworks, libraries, tooling and for implementing generic “patch” logic.
//            Cons / Risks:
//    Breaks encapsulation (accesses private fields).
//    Loses compile-time type safety — possible ClassCastExceptions or invalid values.
//    Slower than direct calls.
//    Harder to read, debug and maintain.
//    Can introduce security issues if unvalidated input updates sensitive fields.
//            Verdict: avoid reflection in application business logic unless necessary. Prefer explicit, type-safe approaches.
//    When to use reflection
//    Use reflection when you truly need runtime discovery or generic behavior (frameworks, serializers, runtime wiring).
//    Don’t use it when you can:
//    call setters directly,
//    use a dedicated patch DTO,
//    use mapping libraries with proper config (MapStruct/ModelMapper) or
//    use Spring utilities that do type conversion safely.
//    Safer alternatives for this method
//    Accept a patch DTO with optional fields (explicit and type-safe).
//    Use BeanWrapperImpl (Spring) or ObjectMapper to convert/merge values with automatic type conversion, and validate allowed properties.
//    Whitelist fields and validate types before applying.
}

//Importance of service layer
//The service layer acts as a bridge between the persistence layer(responsible for data access) and the presentation layer(handling user interaction)
//Suppose we have 2 controllers (employee & dept)
//now both these controllers wants to get the data from EmployeeRepository.
//While getting the data we want to log something, authenticate the user. In that case we will be duplicating our code in all the controllers.
//The service layer will come between controllers and Repository.
//The employee Service will tell the controllers that if you want some data from EmployeeRepository, just tell me, I will get the data
//from employeeRepository, add logs, perform auth, and then give you the data.
