package com.se.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Table(name = "flights")
@Data
public class Flight
{
    @Id
    private String id;
    @Column(name = "departure_date_time")
    private LocalDateTime departureDateTime;
    @Column(name = "arrival_date_time")
    private LocalDateTime arrivalDateTime;
    @Column(name = "departure_city")
    private String departureCity;
    @Column(name = "arrival_city")
    private String arrivalCity;
    private float fare;
    private String status;
    @Column(name = "available_seats")
    private int availableSeats;
    @Column(name = "remaining_seats")
    private int remainingSeats;
}
