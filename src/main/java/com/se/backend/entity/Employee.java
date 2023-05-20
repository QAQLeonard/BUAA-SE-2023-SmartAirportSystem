package com.se.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "employees")
@Data
public class Employee
{
    @Id
    private String id;
    private String name;
    private String username;
    private String gender;
    private String phone;
}

