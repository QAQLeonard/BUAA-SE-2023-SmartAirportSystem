package top.leonardsaikou.backend.entity;

import javax.persistence.*;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Entity
@Table(schema = "se_db",name = "tickets")
@TableName("tickets")
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
