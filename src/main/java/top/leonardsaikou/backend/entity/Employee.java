package top.leonardsaikou.backend.entity;

import javax.persistence.*;
import lombok.*;


@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee
{
    @Id
    private String id;
    private String name;
    private String username;
    private String gender;
    private String phone;
}

