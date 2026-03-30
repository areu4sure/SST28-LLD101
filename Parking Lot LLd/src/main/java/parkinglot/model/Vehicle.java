package parkinglot.model;

import parkinglot.enums.VehicleType;

/**
 * Represents a vehicle entering the parking lot.
 */
public class Vehicle {
    private final String plateNumber;
    private final VehicleType type;

    public Vehicle(String plateNumber, VehicleType type) {
        this.plateNumber = plateNumber;
        this.type = type;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public VehicleType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "plateNumber='" + plateNumber + '\'' +
                ", type=" + type +
                '}';
    }
}
