package com.supplier.rating.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique=true) private String productCode;
    @Column(nullable=false) private String name;
    private String category;
    private double unitPrice;
    public Product() {}
    public Long getId(){ return id; }
    public String getProductCode(){ return productCode; }
    public void setProductCode(String v){ productCode=v; }
    public String getName(){ return name; }
    public void setName(String v){ name=v; }
    public String getCategory(){ return category; }
    public void setCategory(String v){ category=v; }
    public double getUnitPrice(){ return unitPrice; }
    public void setUnitPrice(double v){ unitPrice=v; }
}
