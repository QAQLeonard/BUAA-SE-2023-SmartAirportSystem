package top.leonardsaikou.backend.entity;

import javax.persistence.*;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Entity
@Table(schema = "se_db",name = "passengers")
@TableName("passengers")
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
