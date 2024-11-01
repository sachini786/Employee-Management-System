package com.example.StepDefinition;

import com.example.employeeManegmentSystem.model.Employee;
import com.example.employeeManegmentSystem.repository.EmployeeRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmployeeStepDefinition {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee createdEmployee;

    @Given("the employee management service is initialized")
    public void the_employee_management_service_is_initialized() {

    }

    @When("I create an employee with the following data firstName{string},lastName{string},email{string},department{string},position{string}")
    public void i_create_an_employee_with_the_following_data(String firstName, String lastName, String email, String department, String position) {
        // Create a new Employee object with the given data
        Employee employee = new Employee(firstName, lastName, email, department, position);

        // Save the employee to the repository
        createdEmployee = employeeRepository.save(employee);
    }

    @Then("the employee should be successfully created")
    public void the_employee_should_be_successfully_created() {
        assertNotNull(createdEmployee);
        assertNotNull(createdEmployee.getId());
    }
}
