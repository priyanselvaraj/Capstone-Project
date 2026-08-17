package com.supplier.rating.repository;

import com.supplier.rating.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductCodeIgnoreCase(String productCode);
    boolean existsByProductCodeIgnoreCase(String productCode);
}
