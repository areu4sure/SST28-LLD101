package parkinglot.strategy;

import parkinglot.enums.SlotSize;
import parkinglot.model.ParkingLevel;
import parkinglot.model.ParkingSlot;
import java.util.List;

/**
 * Strategy interface for finding a suitable parking slot.
 */
public interface SlotAllocator {
    /**
     * Finds an available compatible slot.
     *
     * @param requestedSize The slot size requested for the vehicle.
     * @param gateId        The ID of the entry gate.
     * @param levels        List of parking levels to search in.
     * @return A ParkingSlot if found, null otherwise.
     */
    ParkingSlot findSlot(SlotSize requestedSize, String gateId, List<ParkingLevel> levels);
}
