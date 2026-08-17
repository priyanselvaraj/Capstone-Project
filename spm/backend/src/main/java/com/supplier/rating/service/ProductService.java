package com.supplier.rating.service;

import com.supplier.rating.model.entity.Product;
import com.supplier.rating.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;
    public ProductService(ProductRepository repository) { this.repository = repository; }
    public List<Product> findAll() { return repository.findAll(); }
    public Product findById(Long id) { return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found: " + id)); }

    @Transactional
    public Product create(Product p) {
        if (p.getProductCode() == null || p.getProductCode().isBlank() || p.getName() == null || p.getName().isBlank())
            throw new IllegalArgumentException("Product code and name are required");
        if (repository.existsByProductCodeIgnoreCase(p.getProductCode())) throw new IllegalArgumentException("Product code already exists");
        if (p.getUnitPrice() < 0) throw new IllegalArgumentException("Unit price cannot be negative");
        return repository.save(p);
    }

    @Transactional
    public Product update(Long id, Product input) {
        Product p = findById(id);
        if (input.getProductCode() != null && !input.getProductCode().equalsIgnoreCase(p.getProductCode())
                && repository.existsByProductCodeIgnoreCase(input.getProductCode()))
            throw new IllegalArgumentException("Product code already exists");
        if (input.getProductCode() != null && !input.getProductCode().isBlank()) p.setProductCode(input.getProductCode());
        if (input.getName() != null && !input.getName().isBlank()) p.setName(input.getName());
        p.setCategory(input.getCategory());
        if (input.getUnitPrice() < 0) throw new IllegalArgumentException("Unit price cannot be negative");
        p.setUnitPrice(input.getUnitPrice());
        return repository.save(p);
    }

    @Transactional
    public void delete(Long id) { repository.delete(findById(id)); }
}
