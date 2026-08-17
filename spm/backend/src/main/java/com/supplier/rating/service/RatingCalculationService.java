package com.supplier.rating.service;

import org.springframework.stereotype.Service;

@Service
public class RatingCalculationService {
    public double calculate(double quality, double delivery, double cost,
                            double quantityAccuracy, double communication, double compliance) {
        validate(quality); validate(delivery); validate(cost);
        validate(quantityAccuracy); validate(communication); validate(compliance);
        return round(quality * 0.30 + delivery * 0.30 + cost * 0.15
                + quantityAccuracy * 0.10 + communication * 0.10 + compliance * 0.05);
    }

    private void validate(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value) || value < 0 || value > 100)
            throw new IllegalArgumentException("All rating scores must be between 0 and 100");
    }

    private double round(double value) { return Math.round(value * 100.0) / 100.0; }

    public String category(double score) {
        if (score >= 90) return "Excellent";
        if (score >= 75) return "Good";
        if (score >= 60) return "Average";
        if (score >= 40) return "Poor";
        return "Critical";
    }
}
