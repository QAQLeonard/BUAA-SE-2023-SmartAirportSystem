package top.leonardsaikou.backend.entity;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "repair_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairRequest
{
    @Id
    private String id;
    @Column(name = "reporter_id")
    private String reporterId;
    @Column(name = "approver_id")
    private String approverId;
    @Column(name = "request_date")
    private LocalDate requestDate;
    private String status;
    private String description;
}
