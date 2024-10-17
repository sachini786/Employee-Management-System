package com.example.employeeManegmentSystem.controll;

import com.example.employeeManegmentSystem.exception.ResourceNotFondException;
import com.example.employeeManegmentSystem.model.Employee;
import com.example.employeeManegmentSystem.repository.EmployeeRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee API", description = "Manage employee data")
public class EmployeeControll {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Operation(summary = "Get all employees")
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    @PatchMapping
    public Employee updateUser(@RequestBody Employee employee){
        return employeeRepository.save(employee);
    }

    @Operation(summary = "Get employee by ID")
    @GetMapping("/{Id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFondException("Employee not found with id: " + id));
    }

    @Operation(summary = "Create a new employee")
    @PostMapping("/Employee")
    public ResponseEntity<Employee> createUser(@RequestBody Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        System.out.println(savedEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);

    }

    @Operation(summary = "Update an employee")
    @PutMapping("{Id}")
    public ResponseEntity <Employee> updateUser(@PathVariable Integer Id,@RequestBody Employee EmployeeDetail){
        Employee updateEmployee = employeeRepository.findById(Id)
                .orElseThrow(()->new ResourceNotFondException("User not exist id" +Id));
        updateEmployee.setFirstName(EmployeeDetail.getFirstName());
        updateEmployee.setLastName(EmployeeDetail.getLastName());
        updateEmployee.setEmail(EmployeeDetail.getEmail());
        updateEmployee.setDepartment(EmployeeDetail.getDepartment());
        updateEmployee.setPosition(EmployeeDetail.getPosition());


        employeeRepository.save(updateEmployee);
        return ResponseEntity.ok(updateEmployee);
    }

    @Operation(summary = "Delete an employee")
    @DeleteMapping("{Id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Integer Id){
        Employee employee = employeeRepository.findById(Id)
                .orElseThrow(()->new ResourceNotFondException("User not exist id" +Id));
        employeeRepository.delete(employee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
