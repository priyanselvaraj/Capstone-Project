package com.supplier.rating.controller;

import com.supplier.rating.model.entity.Feedback;
import com.supplier.rating.service.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    private final FeedbackService service;
    public FeedbackController(FeedbackService service) { this.service = service; }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Map<String,Object> create(@RequestBody Feedback f) {
        return Map.of("success",true,"data",service.create(f),"message","Feedback submitted successfully");
    }
    @GetMapping("/supplier/{supplierId}")
    public Map<String,Object> bySupplier(@PathVariable Long supplierId) {
        return Map.of("success",true,"data",service.findBySupplier(supplierId),"message","Feedback fetched successfully");
    }
}
