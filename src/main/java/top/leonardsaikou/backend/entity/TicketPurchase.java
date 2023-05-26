package top.leonardsaikou.backend.entity;

import javax.persistence.*;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(schema = "se_db", name = "ticket_purchases")
@Data
public class TicketPurchase
{
    @Id
    private String id;
    @Column(name = "passenger_id")
    private String passengerId;
    @Column(name = "ticket_id")
    private String ticketId;
    /**
     * Payment Method:
     * 0: Not paid
     * 1: Paid by credit card
     * 2: Paid by cash
     * -1: Refunded
     */
    @Column(name = "payment_method")
    private int paymentMethod;
    @Column(name = "purchase_datetime")
    private LocalDateTime purchaseDateTime;
}
