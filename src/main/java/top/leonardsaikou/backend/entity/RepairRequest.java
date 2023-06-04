package top.leonardsaikou.backend.entity;

import javax.persistence.*;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(schema = "se_db",name = "repair_requests")
@TableName("repair_requests")
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
    private String position;
    /**
     * status:
     * 0: Unresolved
     * 1: Resolved
     */
    private String status;
    private String description;
}
