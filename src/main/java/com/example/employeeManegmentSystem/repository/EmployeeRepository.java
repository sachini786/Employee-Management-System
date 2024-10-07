package com.example.employeeManegmentSystem.repository;

import com.example.employeeManegmentSystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
