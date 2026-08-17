package com.supplier.rating.controller;

import com.supplier.rating.model.entity.SupplierRating;
import com.supplier.rating.service.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    private final RatingService service;
    public RatingController(RatingService service) { this.service = service; }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Map<String,Object> create(@RequestBody SupplierRating r) {
        return Map.of("success",true,"data",service.create(r),"message","Rating calculated and saved successfully");
    }
    @GetMapping("/supplier/{supplierId}")
    public Map<String,Object> bySupplier(@PathVariable Long supplierId) {
        return Map.of("success",true,"data",service.findBySupplier(supplierId),"message","Supplier ratings fetched successfully");
    }
    @GetMapping("/ranking")
    public Map<String,Object> ranking() {
        return Map.of("success",true,"data",service.ranking(),"message","Supplier ranking fetched successfully");
    }
}
