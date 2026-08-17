package com.supplier.rating.repository;

import com.supplier.rating.model.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findBySupplierIdOrderByCreatedAtDesc(Long supplierId);
}
