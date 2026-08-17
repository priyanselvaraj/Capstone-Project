package com.supplier.rating.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique=true) private String supplierCode;
    @Column(nullable=false) private String name;
    private String email;
    private String phone;
    private String address;\n    private String category;
    private boolean active = true;
    public Supplier() {}
    public Long getId(){ return id; }
    public String getSupplierCode(){ return supplierCode; }
    public void setSupplierCode(String v){ supplierCode=v; }
    public String getName(){ return name; }
    public void setName(String v){ name=v; }
    public String getEmail(){ return email; }
    public void setEmail(String v){ email=v; }
    public String getPhone(){ return phone; }
    public void setPhone(String v){ phone=v; }
    public String getAddress(){ return address; }
    public void setAddress(String v){ address=v; }
    public String getCategory(){ return category; }\n    public void setCategory(String v){ category=v; }\n    public boolean isActive(){ return active; }
    public void setActive(boolean v){ active=v; }
}
