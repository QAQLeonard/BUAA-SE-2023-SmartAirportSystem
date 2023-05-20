package com.se.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "merchants")
@Data
public class Merchant
{
    @Id
    private String id;
    private String username;
    private String name;
    @Column(name = "contact_person")
    private String contactPerson;
    @Column(name = "contact_phone")
    private String contactPhone;
    @Column(name = "store_location")
    private String storeLocation;
}
