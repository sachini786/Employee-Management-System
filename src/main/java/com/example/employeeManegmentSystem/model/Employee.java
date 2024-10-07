package com.example.employeeManegmentSystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name = "FirstName", nullable = false)
    private String FirstName;

    @Column(name = "LastName", nullable = false)
    private String LastName;

    @Column(name = "Email")
    private String Email;
    @Column(name = "department")
    private String department;

    @Column(name = "Position ")
    private Double Position;
}
