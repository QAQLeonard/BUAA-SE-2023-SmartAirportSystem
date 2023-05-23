package top.leonardsaikou.backend.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "merchant_applications")
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