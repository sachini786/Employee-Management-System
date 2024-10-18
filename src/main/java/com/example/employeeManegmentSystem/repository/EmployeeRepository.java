package com.example.employeeManegmentSystem.repository;

import com.example.employeeManegmentSystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    boolean existsByEmail(String email);

    Employee getEmployeeById(Integer id);
}
