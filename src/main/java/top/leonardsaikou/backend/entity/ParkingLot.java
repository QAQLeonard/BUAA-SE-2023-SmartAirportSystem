package top.leonardsaikou.backend.entity;

import javax.persistence.*;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Entity
@Table(schema = "se_db",name = "parkinglot")
@TableName("parkinglot")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLot
{
    @Id
    private String id;
    private String location;
    /**
     * Status represents the availability of the parking space throughout the day.
     * The day is divided into 24 time slots. Status is a binary number.
     * If the (i+1)th time slot of a day (0<=i<=23) is reserved,
     * then the ith bit of status is set to 1 (status |= (1<<i)).
     */
    private int status;
}
