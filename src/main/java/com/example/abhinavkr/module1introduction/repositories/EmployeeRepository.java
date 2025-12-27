package com.example.abhinavkr.module1introduction.repositories;

import com.example.abhinavkr.module1introduction.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}


// in the past before data jpa or hibernate we used to write the sql queries to interact with the database
// now with the help of spring data jpa we can interact with the database without writing any sql queries.
// spring data jpa internally uses hibernate to interact with the database.
// JpaRepository is a JPA specific extension of Repository.
// it contains the full API of CrudRepository and PagingAndSortingRepository.
// it contains the methods to perform the CRUD operations.
// JpaRepository<T, ID> where T is the entity type and ID is the type of the entity's identifier.
// here EmployeeEntity is the entity type and Long is the type of the entity's identifier.
// if any method is not present like isNameContainAbhinav then also we can define that method inside this interface
// and spring data jpa will provide the implementation for that method at runtime.