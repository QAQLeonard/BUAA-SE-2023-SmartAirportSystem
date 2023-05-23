package top.leonardsaikou.backend.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
