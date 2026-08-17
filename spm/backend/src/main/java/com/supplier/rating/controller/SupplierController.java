package com.supplier.rating.controller;

import com.supplier.rating.model.entity.Supplier;
import com.supplier.rating.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    private final SupplierService service;
    public SupplierController(SupplierService service) { this.service = service; }

    @GetMapping public Map<String,Object> all() { return ok(service.findAll(), "Suppliers fetched successfully"); }
    @GetMapping("/{id}") public Map<String,Object> one(@PathVariable Long id) { return ok(service.findById(id), "Supplier fetched successfully"); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Map<String,Object> create(@RequestBody Supplier s) { return ok(service.create(s), "Supplier created successfully"); }
    @PutMapping("/{id}") public Map<String,Object> update(@PathVariable Long id, @RequestBody Supplier s) { return ok(service.update(id,s), "Supplier updated successfully"); }
    @DeleteMapping("/{id}") public Map<String,Object> delete(@PathVariable Long id) { service.delete(id); return ok(null, "Supplier deleted successfully"); }

    private Map<String,Object> ok(Object data, String message) { return Map.of("success", true, "data", data == null ? Map.of() : data, "message", message); }
}
