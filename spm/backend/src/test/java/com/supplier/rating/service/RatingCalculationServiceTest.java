package com.supplier.rating.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class RatingCalculationServiceTest {

    private final RatingCalculationService service = new RatingCalculationService();

    @Test
    void shouldCalculateWeightedScore() {
        double score = service.calculate(90, 80, 85, 95, 90, 100);
        assertEquals(87.25, score, 0.001);
    }

    @Test
    void shouldReturnGoodCategory() {
        assertEquals("Good", service.category(88.25));
    }
}
