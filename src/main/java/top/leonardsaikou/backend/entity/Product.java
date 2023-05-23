package top.leonardsaikou.backend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import javax.persistence.*;

@Entity
@TableName("products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product
{
    @Id
    private String id;
    @Column(name = "merchant_id")
    private String merchantId;
    private String name;
    private Float price;
    private Integer quantity;
}
