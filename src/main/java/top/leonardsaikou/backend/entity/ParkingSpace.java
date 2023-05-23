package top.leonardsaikou.backend.entity;

import javax.persistence.*;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Entity
@TableName("parking_spaces")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpace
{
    @Id
    private String id;
    private String location;
    private Integer status;
}
