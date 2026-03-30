package parkinglot.enums;

/**
 * Represents the size of a parking slot and its compatibility with vehicle types.
 */
public enum SlotSize {
    SMALL,
    MEDIUM,
    LARGE;

    /**
     * Determines if a slot of this size can accommodate a specific vehicle type.
     * Rules:
     * - SMALL: 2-wheelers only.
     * - MEDIUM: 2-wheelers and cars.
     * - LARGE: 2-wheelers, cars, and buses.
     *
     * @param type The type of vehicle.
     * @return true if compatible, false otherwise.
     */
    public boolean isCompatible(VehicleType type) {
        switch (this) {
            case SMALL:
                return type == VehicleType.TWO_WHEELER;
            case MEDIUM:
                return type == VehicleType.TWO_WHEELER || type == VehicleType.CAR;
            case LARGE:
                return true; // All types can fit in Large
            default:
                return false;
        }
    }
}
