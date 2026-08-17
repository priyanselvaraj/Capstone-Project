package com.supplier.rating.controller;

import com.supplier.rating.model.entity.Delivery;
import com.supplier.rating.service.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {
    private final DeliveryService service;
    public DeliveryController(DeliveryService service) { this.service = service; }
    @GetMapping public Map<String,Object> all() { return Map.of("success",true,"data",service.findAll(),"message","Deliveries fetched successfully"); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Map<String,Object> create(@RequestBody Delivery d) { return Map.of("success",true,"data",service.create(d),"message","Delivery created successfully"); }
    @PutMapping("/{id}") public Map<String,Object> update(@PathVariable Long id,@RequestBody Delivery d) { return Map.of("success",true,"data",service.update(id,d),"message","Delivery updated successfully"); }
}
