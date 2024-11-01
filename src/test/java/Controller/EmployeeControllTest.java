package Controller;
import com.example.employeeManegmentSystem.EmployeeManagementSystem;
import com.example.employeeManegmentSystem.controll.EmployeeControll;
import com.example.employeeManegmentSystem.exception.ResourceNotFondException;
import com.example.employeeManegmentSystem.model.Employee;
import com.example.employeeManegmentSystem.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(controllers = EmployeeControll.class)
@ContextConfiguration(classes = EmployeeManagementSystem.class)
public class EmployeeControllTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    void getAllEmployees_shouldReturnEmployeeList() throws Exception {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(
                new Employee("John", "Doe", "john@example.com", "IT", "Developer"),
                new Employee("Jane", "Smith", "jane@example.com", "HR", "Manager")
        ));

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[1].firstName").value("Jane"));
    }
    // Test for GET /api/employees/{id}
    @Test
    void getEmployeeById_shouldReturnEmployee() throws Exception {
        Employee employee = new Employee(1, "John", "Doe", "john@example.com", "IT", "Developer");
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        mockMvc.perform(get("/api/employees/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }
    // Test for PUT /api/employees/{id}
    @Test
    void updateEmployee_shouldReturnUpdatedEmployee() throws Exception {
        Employee existingEmployee = new Employee(1, "John", "Doe", "john@example.com", "IT", "Developer");
        Employee updatedEmployee = new Employee(1, "Johnny", "Doe", "john@example.com", "IT", "Lead Developer");

        when(employeeRepository.findById(1)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

        mockMvc.perform(put("/api/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"firstName\": \"Johnny\", \"lastName\": \"Doe\", \"email\": \"john@example.com\", \"department\": \"IT\", \"position\": \"Lead Developer\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Johnny"))
                .andExpect(jsonPath("$.position").value("Lead Developer"));
    }

    // Test for DELETE /api/employees/{id}
    @Test
    void deleteEmployee_shouldReturnNoContentStatus() throws Exception {
        Employee employee = new Employee(1, "John", "Doe", "john@example.com", "IT", "Developer");

        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        mockMvc.perform(delete("/api/employees/1"))
                .andExpect(status().isNoContent());
    }

}
