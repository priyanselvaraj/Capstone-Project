package com.supplier.rating.model.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "supplier_ratings")
public class SupplierRating {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional=false) @JoinColumn(name="supplier_id")
    private Supplier supplier;
    private double qualityScore;
    private double deliveryScore;
    private double costScore;
    private double quantityAccuracyScore;
    private double communicationScore;
    private double complianceScore;
    private double overallScore;
    private String ratingCategory;
    private LocalDate ratingDate;
    public SupplierRating() {}
    public Long getId(){ return id; }
    public Supplier getSupplier(){ return supplier; }
    public void setSupplier(Supplier v){ supplier=v; }
    public double getQualityScore(){ return qualityScore; }
    public void setQualityScore(double v){ qualityScore=v; }
    public double getDeliveryScore(){ return deliveryScore; }
    public void setDeliveryScore(double v){ deliveryScore=v; }
    public double getCostScore(){ return costScore; }
    public void setCostScore(double v){ costScore=v; }
    public double getQuantityAccuracyScore(){ return quantityAccuracyScore; }
    public void setQuantityAccuracyScore(double v){ quantityAccuracyScore=v; }
    public double getCommunicationScore(){ return communicationScore; }
    public void setCommunicationScore(double v){ communicationScore=v; }
    public double getComplianceScore(){ return complianceScore; }
    public void setComplianceScore(double v){ complianceScore=v; }
    public double getOverallScore(){ return overallScore; }
    public void setOverallScore(double v){ overallScore=v; }
    public String getRatingCategory(){ return ratingCategory; }
    public void setRatingCategory(String v){ ratingCategory=v; }
    public LocalDate getRatingDate(){ return ratingDate; }
    public void setRatingDate(LocalDate v){ ratingDate=v; }
}
