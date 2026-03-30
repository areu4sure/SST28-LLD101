package parkinglot.strategy;

import parkinglot.enums.SlotSize;
import parkinglot.enums.SlotStatus;
import parkinglot.model.ParkingLevel;
import parkinglot.model.ParkingSlot;
import java.util.List;

/**
 * Concrete strategy that finds the nearest available compatible slot from a given gate.
 */
public class NearestSlotAllocator implements SlotAllocator {

    @Override
    public ParkingSlot findSlot(SlotSize requestedSize, String gateId, List<ParkingLevel> levels) {
        ParkingSlot nearestSlot = null;
        int minDistance = Integer.MAX_VALUE;

        for (ParkingLevel level : levels) {
            for (ParkingSlot slot : level.getSlots()) {
                // Feature: A smaller vehicle can park in a larger slot if needed.
                // Feature: Small -> Small, Med, Large; Med -> Med, Large; Large -> Large.
                if (slot.getStatus() == SlotStatus.AVAILABLE && slot.isCompatible(requestedSize)) {
                    int distance = slot.getDistance(gateId);
                    if (distance < minDistance) {
                        minDistance = distance;
                        nearestSlot = slot;
                    }
                }
            }
        }
        return nearestSlot;
    }
}
