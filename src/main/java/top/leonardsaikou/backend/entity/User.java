package top.leonardsaikou.backend.entity;

import javax.persistence.*;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Entity
@Table(schema = "se_db",name = "users")
@TableName("users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User
{
    @Id
    private String username;

    private String password;
    private String id;
    private String avatar;
    private String role;

}
