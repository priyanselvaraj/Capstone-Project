package com.supplier.rating.repository;

import com.supplier.rating.model.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findBySupplierCodeIgnoreCase(String supplierCode);
    boolean existsBySupplierCodeIgnoreCase(String supplierCode);
}
