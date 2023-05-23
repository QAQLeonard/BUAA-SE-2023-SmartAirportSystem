package top.leonardsaikou.backend.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "users")
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
