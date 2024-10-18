package com.example.employeeManegmentSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "First name cannot be null")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must be a string and contain only letters")
    @Column(name = "FirstName", nullable = false)
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must be a string and contain only letters")
    @Column(name = "LastName", nullable = false)
    private String lastName;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    @Column(name = "Email", nullable = false)
    private String email;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "Department must be a string and contain only letters")
    @Column(name = "department")
    private String department;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "Position must be a string and contain only letters")
    @Column(name = "Position")
    private String position;

    public Employee(String firstName, String lastName, String email, String department, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.position = position;
    }
}
