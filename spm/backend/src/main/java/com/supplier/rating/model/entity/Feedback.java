package com.supplier.rating.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional=false) @JoinColumn(name="supplier_id")
    private Supplier supplier;
    @ManyToOne(optional=false) @JoinColumn(name="user_id")
    private User user;
    @Column(nullable=false, length=1000)
    private String comments;
    private LocalDateTime createdAt = LocalDateTime.now();
    public Feedback() {}
    public Long getId(){ return id; }
    public Supplier getSupplier(){ return supplier; }
    public void setSupplier(Supplier v){ supplier=v; }
    public User getUser(){ return user; }
    public void setUser(User v){ user=v; }
    public String getComments(){ return comments; }
    public void setComments(String v){ comments=v; }
    public LocalDateTime getCreatedAt(){ return createdAt; }
}
