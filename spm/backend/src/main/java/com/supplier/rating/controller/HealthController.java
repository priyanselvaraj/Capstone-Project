package com.supplier.rating.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Basic health endpoint used to verify that the API is running.
 */
@RestController
public class HealthController {

    @GetMapping("/api/health")
    public Map<String, Object> health() {
        return Map.of(
                "success", true,
                "data", Map.of("status", "UP"),
                "message", "Supplier Rating API is running"
        );
    }
}
