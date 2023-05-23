package top.leonardsaikou.backend.entity;

import javax.persistence.*;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Entity
@TableName("merchant_applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantApplication
{
    @Id
    private String id;
    @Column(name = "merchant_id")
    private String merchantId;
    @Column(name = "store_address")
    private String storeAddress;
    @Column(name = "approver_id")
    private String approverId;
    private int status;
}
