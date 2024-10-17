package com.example.employeeManegmentSystem.repository;

import com.example.employeeManegmentSystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;



public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
