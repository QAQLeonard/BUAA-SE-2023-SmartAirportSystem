package top.leonardsaikou.backend.entity;

import javax.persistence.*;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Entity
@Table(schema = "se_db",name = "merchants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Merchant
{
    @Id
    private String id;
    private String username;
    private String name;
    @Column(name = "contact_person")
    private String contactPerson;
    @Column(name = "contact_phone")
    private String contactPhone;
    @Column(name = "store_location")
    private String storeLocation;
}
