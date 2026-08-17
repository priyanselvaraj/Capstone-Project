package com.supplier.rating.repository;

import com.supplier.rating.model.entity.SupplierRating;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SupplierRatingRepository extends JpaRepository<SupplierRating, Long> {
    List<SupplierRating> findBySupplierIdOrderByRatingDateDesc(Long supplierId);
    List<SupplierRating> findAllByOrderByOverallScoreDesc();
}
