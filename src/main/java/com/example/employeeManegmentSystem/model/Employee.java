package com.example.employeeManegmentSystem.model;

import jakarta.persistence.*;
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
    private String Position;

    public Employee(String FirstName, String lastName, String Email, String department, String Position) {
        this.FirstName = FirstName;
        this.LastName = lastName;
        this.Email = Email;
        this.department = department;
        this.Position = Position;
    }

}

