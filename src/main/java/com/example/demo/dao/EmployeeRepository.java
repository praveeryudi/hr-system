package com.example.demo.dao;

import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value="SELECT * " +
            "FROM employees " +
            "WHERE (id IN (SELECT manager_id FROM employees))", nativeQuery = true)
    List<Employee> fetchAllManagers();

}
