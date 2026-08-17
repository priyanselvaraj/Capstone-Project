package com.supplier.rating.model.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "deliveries")
public class Delivery {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional=false) @JoinColumn(name="order_id")
    private PurchaseOrder purchaseOrder;
    private LocalDate expectedDate;
    private LocalDate actualDate;
    private int quantityReceived;
    private String status;
    public Delivery() {}
    public Long getId(){ return id; }
    public PurchaseOrder getPurchaseOrder(){ return purchaseOrder; }
    public void setPurchaseOrder(PurchaseOrder v){ purchaseOrder=v; }
    public LocalDate getExpectedDate(){ return expectedDate; }
    public void setExpectedDate(LocalDate v){ expectedDate=v; }
    public LocalDate getActualDate(){ return actualDate; }
    public void setActualDate(LocalDate v){ actualDate=v; }
    public int getQuantityReceived(){ return quantityReceived; }
    public void setQuantityReceived(int v){ quantityReceived=v; }
    public String getStatus(){ return status; }
    public void setStatus(String v){ status=v; }
}
