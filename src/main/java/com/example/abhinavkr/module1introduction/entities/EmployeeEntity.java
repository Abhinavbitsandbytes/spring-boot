package com.example.abhinavkr.module1introduction.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="employees")
public class EmployeeEntity {
    @Id  // if you will not provide id then error - Persistent entity 'EmployeeEntity' should have primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    // below things are taken from DTO. can be different also.
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private LocalDate dateOfJoining;
    private boolean isActive;
}

// it will tell the hibernate that this is the java class which you need to create table inside database.
// hibernate will create a table with the name employee_entity
// by default the table name will be employee_entity but you can change it by using @Table annotation.

// inside the EmployeeEntity has been defined the table and how table structure will look like.
// fields inside the class will become the columns inside the table.

