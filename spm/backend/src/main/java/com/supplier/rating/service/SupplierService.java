package com.supplier.rating.service;

import com.supplier.rating.model.entity.Supplier;
import com.supplier.rating.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class SupplierService {
    private final SupplierRepository repository;

    public SupplierService(SupplierRepository repository) { this.repository = repository; }

    public List<Supplier> findAll() { return repository.findAll(); }
    public Supplier findById(Long id) { return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Supplier not found: " + id)); }

    @Transactional
    public Supplier create(Supplier supplier) {
        if (supplier.getSupplierCode() == null || supplier.getSupplierCode().isBlank())
            throw new IllegalArgumentException("Supplier code is required");
        if (supplier.getName() == null || supplier.getName().isBlank())
            throw new IllegalArgumentException("Supplier name is required");
        if (repository.existsBySupplierCodeIgnoreCase(supplier.getSupplierCode()))
            throw new IllegalArgumentException("Supplier code already exists");
        supplier.setActive(true);
        return repository.save(supplier);
    }

    @Transactional
    public Supplier update(Long id, Supplier input) {
        Supplier s = findById(id);
        if (input.getSupplierCode() != null && !input.getSupplierCode().equalsIgnoreCase(s.getSupplierCode())
                && repository.existsBySupplierCodeIgnoreCase(input.getSupplierCode()))
            throw new IllegalArgumentException("Supplier code already exists");
        if (input.getSupplierCode() != null && !input.getSupplierCode().isBlank()) s.setSupplierCode(input.getSupplierCode());
        if (input.getName() != null && !input.getName().isBlank()) s.setName(input.getName());
        s.setEmail(input.getEmail()); s.setPhone(input.getPhone()); s.setAddress(input.getAddress());
        s.setActive(input.isActive());
        return repository.save(s);
    }

    @Transactional
    public void delete(Long id) { repository.delete(findById(id)); }
}
