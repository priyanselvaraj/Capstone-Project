package com.supplier.rating.service;

import com.supplier.rating.model.entity.Supplier;
import com.supplier.rating.model.entity.SupplierRating;
import com.supplier.rating.repository.SupplierRatingRepository;
import com.supplier.rating.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class RatingService {
    private final SupplierRatingRepository ratings;
    private final SupplierRepository suppliers;
    private final RatingCalculationService calculator;

    public RatingService(SupplierRatingRepository ratings, SupplierRepository suppliers, RatingCalculationService calculator) {
        this.ratings = ratings; this.suppliers = suppliers; this.calculator = calculator;
    }

    @Transactional
    public SupplierRating create(SupplierRating input) {
        if (input.getSupplier() == null || input.getSupplier().getId() == null)
            throw new IllegalArgumentException("Supplier id is required");
        Supplier supplier = suppliers.findById(input.getSupplier().getId())
                .orElseThrow(() -> new IllegalArgumentException("Supplier not found"));
        input.setSupplier(supplier);
        double score = calculator.calculate(input.getQualityScore(), input.getDeliveryScore(), input.getCostScore(),
                input.getQuantityAccuracyScore(), input.getCommunicationScore(), input.getComplianceScore());
        input.setOverallScore(score);
        input.setRatingCategory(calculator.category(score));
        if (input.getRatingDate() == null) input.setRatingDate(LocalDate.now());
        return ratings.save(input);
    }

    public List<SupplierRating> findBySupplier(Long supplierId) {
        if (!suppliers.existsById(supplierId)) throw new IllegalArgumentException("Supplier not found: " + supplierId);
        return ratings.findBySupplierIdOrderByRatingDateDesc(supplierId);
    }

    public List<SupplierRating> ranking() { return ratings.findAllByOrderByOverallScoreDesc(); }
}
