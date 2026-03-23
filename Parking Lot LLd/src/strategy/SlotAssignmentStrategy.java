package strategy;

import enums.SlotType;
import enums.VehicleType;
import models.ParkingSlot;
import java.util.List;

public interface SlotAssignmentStrategy {
    ParkingSlot assign(VehicleType vehicleType, SlotType requestedSlotType, List<ParkingSlot> slots);
}
