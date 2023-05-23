package top.leonardsaikou.backend.entity;

import javax.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "ticketpurchases")
@Data
public class TicketPurchase
{
    @Id
    private String id;
    @Column(name = "passenger_id")
    private String passengerId;
    @Column(name = "ticket_id")
    private String ticketId;
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "purchase_datetime")
    private LocalDateTime purchaseDateTime;
}
