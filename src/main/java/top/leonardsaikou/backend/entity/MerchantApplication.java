package top.leonardsaikou.backend.entity;

import javax.persistence.*;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Entity
@Table(schema = "se_db",name = "merchant_applications")
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
    //审批员工号
    @Column(name = "approver_id")
    private String approverId;
    /**
     * status:
     * 0: refused
     * 1: approved
     */
    private int status;
}
