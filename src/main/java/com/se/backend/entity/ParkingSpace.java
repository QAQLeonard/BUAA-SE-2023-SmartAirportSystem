package com.se.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "parking_spaces")
@Data
public class ParkingSpace
{
    @Id
    private String id;
    private String location;
    private Integer status;
}
