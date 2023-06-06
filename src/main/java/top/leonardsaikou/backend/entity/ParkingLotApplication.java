package top.leonardsaikou.backend.entity;

import javax.persistence.*;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Entity
@Table(schema = "se_db",name = "parkinglot_applications")
@TableName("parkinglot_applications")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ParkingLotApplication {

    @Id
    private String id;
    private String username;
    private int startTime;
    private int finishTime;
    private int status;
    private String pid;

}
