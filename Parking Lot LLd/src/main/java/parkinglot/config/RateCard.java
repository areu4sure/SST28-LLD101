package parkinglot.config;

import parkinglot.enums.SlotSize;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages the hourly rates for different parking slot sizes.
 * Requirement: "The system should maintain different hourly parking charges for each slot type."
 */
public class RateCard {
    private final Map<SlotSize, Double> rates;

    public RateCard() {
        this.rates = new HashMap<>();
        // Default rates
        rates.put(SlotSize.SMALL, 10.0);
        rates.put(SlotSize.MEDIUM, 20.0);
        rates.put(SlotSize.LARGE, 50.0);
    }

    public void setRate(SlotSize size, double rate) {
        rates.put(size, rate);
    }

    public double getRate(SlotSize size) {
        return rates.getOrDefault(size, 0.0);
    }
}
