package com.se.backend.entity;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Table(name = "merchant_applications")
@Data
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
