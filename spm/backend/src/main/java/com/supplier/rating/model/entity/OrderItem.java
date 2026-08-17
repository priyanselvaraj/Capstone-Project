package com.supplier.rating.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional=false) @JoinColumn(name="order_id")
    private PurchaseOrder purchaseOrder;
    @ManyToOne(optional=false) @JoinColumn(name="product_id")
    private Product product;
    private int quantity;
    private double unitPrice;
    public OrderItem() {}
    public Long getId(){ return id; }
    public PurchaseOrder getPurchaseOrder(){ return purchaseOrder; }
    public void setPurchaseOrder(PurchaseOrder v){ purchaseOrder=v; }
    public Product getProduct(){ return product; }
    public void setProduct(Product v){ product=v; }
    public int getQuantity(){ return quantity; }
    public void setQuantity(int v){ quantity=v; }
    public double getUnitPrice(){ return unitPrice; }
    public void setUnitPrice(double v){ unitPrice=v; }
}
