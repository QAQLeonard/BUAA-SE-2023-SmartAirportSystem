package com.se.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class Users
{
    @Id
    private String username;
    private String password;
    private String id;
    private String avatar;
    private String role;
}
