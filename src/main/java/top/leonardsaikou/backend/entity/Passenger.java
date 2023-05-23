package top.leonardsaikou.backend.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "passengers")
@Data
@NoArgsConstructor
@AllArgsConstructor
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