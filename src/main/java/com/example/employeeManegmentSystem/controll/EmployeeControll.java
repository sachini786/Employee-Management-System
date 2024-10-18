package com.example.employeeManegmentSystem.controll;

import com.example.employeeManegmentSystem.exception.ResourceNotFondException;
import com.example.employeeManegmentSystem.model.Employee;
import com.example.employeeManegmentSystem.repository.EmployeeRepository;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

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

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return ResponseEntity.ok(employee.get());
        } else {
            throw new ResourceNotFondException("Employee not found with ID: " + id);
        }
    }

    @Operation(summary = "Create a new employee")
    @PostMapping("/Employee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {

        // Check if first name and last name are null
        if (employee.getFirstName() == null || employee.getLastName() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }


        if (employeeRepository.existsByEmail(employee.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }

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
