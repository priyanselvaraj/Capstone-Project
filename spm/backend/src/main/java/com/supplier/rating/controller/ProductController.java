package com.supplier.rating.controller;

import com.supplier.rating.model.entity.Product;
import com.supplier.rating.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService service) { this.service = service; }

    @GetMapping public Map<String,Object> all() { return ok(service.findAll(), "Products fetched successfully"); }
    @GetMapping("/{id}") public Map<String,Object> one(@PathVariable Long id) { return ok(service.findById(id), "Product fetched successfully"); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Map<String,Object> create(@RequestBody Product p) { return ok(service.create(p), "Product created successfully"); }
    @PutMapping("/{id}") public Map<String,Object> update(@PathVariable Long id, @RequestBody Product p) { return ok(service.update(id,p), "Product updated successfully"); }
    @DeleteMapping("/{id}") public Map<String,Object> delete(@PathVariable Long id) { service.delete(id); return ok(null, "Product deleted successfully"); }
    private Map<String,Object> ok(Object data, String message) { return Map.of("success", true, "data", data == null ? Map.of() : data, "message", message); }
}
