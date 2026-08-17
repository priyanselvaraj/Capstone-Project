package com.supplier.rating.model.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "purchase_orders")
public class PurchaseOrder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique=true) private String orderNumber;
    @ManyToOne(optional=false) @JoinColumn(name="supplier_id")
    private Supplier supplier;
    private LocalDate orderDate;
    private double totalAmount;
    private String status;
    public PurchaseOrder() {}
    public Long getId(){ return id; }
    public String getOrderNumber(){ return orderNumber; }
    public void setOrderNumber(String v){ orderNumber=v; }
    public Supplier getSupplier(){ return supplier; }
    public void setSupplier(Supplier v){ supplier=v; }
    public LocalDate getOrderDate(){ return orderDate; }
    public void setOrderDate(LocalDate v){ orderDate=v; }
    public double getTotalAmount(){ return totalAmount; }
    public void setTotalAmount(double v){ totalAmount=v; }
    public String getStatus(){ return status; }
    public void setStatus(String v){ status=v; }
}
