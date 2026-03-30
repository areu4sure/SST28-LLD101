package parkinglot.model;

import parkinglot.enums.SlotSize;
import parkinglot.enums.SlotStatus;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an individual parking slot in the lot.
 */
public class ParkingSlot {
    private final String slotId;
    private final SlotSize size;
    private SlotStatus status;
    private final Map<String, Integer> distanceMap;

    public ParkingSlot(String slotId, SlotSize size) {
        this.slotId = slotId;
        this.size = size;
        this.status = SlotStatus.AVAILABLE;
        this.distanceMap = new HashMap<>();
    }

    public String getSlotId() {
        return slotId;
    }

    public SlotSize getSize() {
        return size;
    }

    public SlotStatus getStatus() {
        return status;
    }

    public void setStatus(SlotStatus status) {
        this.status = status;
    }

    public void addDistance(String gateId, int distance) {
        distanceMap.put(gateId, distance);
    }

    public int getDistance(String gateId) {
        return distanceMap.getOrDefault(gateId, Integer.MAX_VALUE);
    }

    /**
     * Checks if this slot can accommodate a vehicle of a certain size.
     * The requirement says:
     * - a 2-wheeler can park in small, medium, or large slot
     * - a car can park in medium or large slot
     * - a bus can park only in a large slot
     *
     * This logic is already in SlotSize, but we double-check here.
     */
    public boolean isCompatible(SlotSize requestedSize) {
        // A requested size (Small, Medium, Large) can be parked in a slot of its size or larger.
        if (requestedSize == SlotSize.SMALL) {
            return true; // Small can fit anywhere
        } else if (requestedSize == SlotSize.MEDIUM) {
            return this.size == SlotSize.MEDIUM || this.size == SlotSize.LARGE;
        } else {
            return this.size == SlotSize.LARGE;
        }
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "slotId='" + slotId + '\'' +
                ", size=" + size +
                ", status=" + status +
                '}';
    }
}
