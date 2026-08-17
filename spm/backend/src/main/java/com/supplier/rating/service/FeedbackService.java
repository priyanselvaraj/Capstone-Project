package com.supplier.rating.service;

import com.supplier.rating.model.entity.Feedback;
import com.supplier.rating.repository.FeedbackRepository;
import com.supplier.rating.repository.SupplierRepository;
import com.supplier.rating.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class FeedbackService {
    private final FeedbackRepository feedback;
    private final SupplierRepository suppliers;
    private final UserRepository users;
    public FeedbackService(FeedbackRepository feedback, SupplierRepository suppliers, UserRepository users) {
        this.feedback = feedback; this.suppliers = suppliers; this.users = users;
    }
    @Transactional
    public Feedback create(Feedback input) {
        if (input.getSupplier() == null || input.getSupplier().getId() == null) throw new IllegalArgumentException("Supplier id is required");
        if (input.getUser() == null || input.getUser().getId() == null) throw new IllegalArgumentException("User id is required");
        if (input.getComments() == null || input.getComments().isBlank()) throw new IllegalArgumentException("Comments are required");
        input.setSupplier(suppliers.findById(input.getSupplier().getId()).orElseThrow(() -> new IllegalArgumentException("Supplier not found")));
        input.setUser(users.findById(input.getUser().getId()).orElseThrow(() -> new IllegalArgumentException("User not found")));
        return feedback.save(input);
    }
    public List<Feedback> findBySupplier(Long supplierId) {
        if (!suppliers.existsById(supplierId)) throw new IllegalArgumentException("Supplier not found: " + supplierId);
        return feedback.findBySupplierIdOrderByCreatedAtDesc(supplierId);
    }
}
