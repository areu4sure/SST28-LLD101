package pricing;

import enums.SlotType;

public class PricingConfig {
    private static final double SMALL_RATE = 20.0;
    private static final double MEDIUM_RATE = 40.0;
    private static final double LARGE_RATE = 80.0;

    public double getRatePerHour(SlotType type) {
        switch (type) {
            case SMALL: return SMALL_RATE;
            case MEDIUM: return MEDIUM_RATE;
            case LARGE: return LARGE_RATE;
            default: return 0;
        }
    }
}
