package top.leonardsaikou.backend.entity;

import javax.persistence.*;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;


@Entity
@Table(schema = "se_db",name = "employees")
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

