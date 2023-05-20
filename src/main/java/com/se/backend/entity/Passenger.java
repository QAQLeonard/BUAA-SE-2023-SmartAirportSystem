package com.se.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "passengers")
@Data
public class Passenger
{
    @Id
    private String id;
    private String username;
    private String name;
    private String gender;
    private String phone;
    @Column(name = "ticket_id")
    private String ticketId;
}
