package com.example.employeeManegmentSystem;

import com.example.employeeManegmentSystem.model.Employee;
import com.example.employeeManegmentSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeManagementSystem implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementSystem.class, args);
    }

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        employeeRepository.save(new Employee("John", "Doe", "john.doe@example.com", "Engineering", "SE"));
    }
}
