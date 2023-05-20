package com.se.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tickets")
@Data
public class Ticket
{
    @Id
    private String id;
    @Column(name = "flight_id")
    private String flightId;
    @Column(name = "passenger_id")
    private String passengerId;
    @Column(name = "seat_number")
    private Integer seatNumber;
    @Column(name = "ticket_price")
    private Double ticketPrice;
    @Column(name = "payment_status")
    private Integer paymentStatus;
}
