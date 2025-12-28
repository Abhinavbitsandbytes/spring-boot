package com.example.abhinavkr.module1introduction.annotations;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation, String> {

    @Override
    public boolean isValid(String inputRole, ConstraintValidatorContext constraintValidatorContext) {
        if(inputRole == null) return false;
        List<String> roles = List.of("USER", "ADMIN");
        return roles.contains(inputRole);
    }
}

//Short interview pointers for creating custom validation annotations
//- Purpose: explain why a custom constraint is needed (business rule not covered by built-in constraints) and prefer composition of existing constraints when possible.
//- Annotation definition: use @Target, @Retention(RUNTIME) and @Constraint(validatedBy = ...). Always include attributes message, groups, and payload.
//- Signature: the annotation typically looks like public @interface MyConstraint { String message() default "..."; Class<?>[] groups() default {}; Class<? extends Payload>[] payload() default {}; }.
//- Validator implementation: implement ConstraintValidator<AnnotationType, ValidatedType>, implement initialize(...) for config and isValid(value, context) for logic. Keep validators stateless and thread-safe.
//- Null handling: document behavior — by convention validators should treat null as valid (combine with @NotNull when non-null required).
//- Class-level constraints: use @Target(TYPE) and a validator that accepts the whole object for cross-field rules (e.g., start/end date logic).
// - Error building: use ConstraintValidatorContext to disable default violation and add property nodes for precise error paths (context.buildConstraintViolationWithTemplate(...).addPropertyNode(...).addConstraintViolation()).
//- DI in validators: in Spring Boot, validators can receive beans if registered with Spring’s LocalValidatorFactoryBean / SpringConstraintValidatorFactory or annotating validator with @Component (avoid manual static service lookups).
//- Message internationalization: place keys in ValidationMessages.properties and use placeholders in message for interpolation.
//- Validation groups: support groups to apply constraint conditionally (create marker interfaces for different flows like Create / Update).
//- Testing: unit test ConstraintValidator directly; add integration tests for controller DTO validation to assert HTTP error payloads.
//- Safety & performance: avoid expensive external calls inside isValid; whitelist fields and validate input types when using reflection; prefer explicit DTOs or safer merging strategies over reflective field writes in application logic.

