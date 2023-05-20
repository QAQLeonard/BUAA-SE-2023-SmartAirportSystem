package com.se.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
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
