package com.example.abhinavkr.module1introduction.dto;

import com.example.abhinavkr.module1introduction.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class EmployeeDTO {
    private Long id;

    @NotBlank(message = "Name cannot be Blank")
    @Size(min = 3, max = 10, message = "Name must be between 3 and 10 characters")
    private String name;

    @NotBlank(message = "Email cannot be Blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Age cannot be null")
    @Max(value = 80, message = "Age should not be greater than 80")
    @Min(value = 18, message = "Age should not be less than 18")
    private Integer age;

    @NotBlank(message = "Role cannot be Blank")
//    @Pattern(regexp = "^(ADMIN|USER)$", message = "Role must be ADMIN, USER,")
    @EmployeeRoleValidation
    private String role;


    @NotNull(message = "Salary cannot be null")
    @Positive(message = "Salary must be positive")
    @Digits(integer = 6, fraction = 2, message = "Salary must be a valid monetary amount")
    @DecimalMax(value = "100000.99", message = "Salary must be at least 100000.99")
    @DecimalMin(value = "100.99", message = "Salary must be at least 100.99")
    private Double salary;

    @PastOrPresent(message = "Date of Joining cannot be in the future")
    private LocalDate dateOfJoining;

    @AssertTrue(message = "Employee must be active")
    private boolean isActive;


public EmployeeDTO(){
    // default constructor
}
    public EmployeeDTO(Long id, String name, String email, Integer age, LocalDate dateOfJoining, boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.dateOfJoining = dateOfJoining;
        this.isActive = isActive;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getSalary() {
        return salary;
    }
    public void setSalary(Double salary) {
        this.salary = salary;
    }

}
