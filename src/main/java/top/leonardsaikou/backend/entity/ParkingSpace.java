package top.leonardsaikou.backend.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "parking_spaces")
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
