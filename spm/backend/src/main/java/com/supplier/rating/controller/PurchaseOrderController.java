package com.supplier.rating.controller;

import com.supplier.rating.model.entity.PurchaseOrder;
import com.supplier.rating.service.PurchaseOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class PurchaseOrderController {
    private final PurchaseOrderService service;
    public PurchaseOrderController(PurchaseOrderService service) { this.service = service; }

    @GetMapping public Map<String,Object> all() { return ok(service.findAll(), "Orders fetched successfully"); }
    @GetMapping("/{id}") public Map<String,Object> one(@PathVariable Long id) { return ok(service.findById(id), "Order fetched successfully"); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Map<String,Object> create(@RequestBody PurchaseOrder o) { return ok(service.create(o), "Order created successfully"); }
    @PutMapping("/{id}") public Map<String,Object> update(@PathVariable Long id, @RequestBody PurchaseOrder o) { return ok(service.update(id,o), "Order updated successfully"); }
    private Map<String,Object> ok(Object data, String message) { return Map.of("success", true, "data", data, "message", message); }
}
