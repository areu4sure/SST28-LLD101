package strategy;

import enums.SlotType;
import enums.VehicleType;
import models.ParkingSlot;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NearestSlotStrategy implements SlotAssignmentStrategy {

    @Override
    public ParkingSlot assign(VehicleType vehicleType, SlotType requestedSlotType, List<ParkingSlot> slots) {
        List<SlotType> compatible = getCompatibleSlots(vehicleType, requestedSlotType);

        return slots.stream()
                .filter(s -> !s.isOccupied() && compatible.contains(s.getType()))
                .min(Comparator.comparingInt(ParkingSlot::getDistanceFromGate))
                .orElse(null);
    }

    private List<SlotType> getCompatibleSlots(VehicleType vehicleType, SlotType preferred) {
        List<SlotType> options = new ArrayList<>();
        switch (vehicleType) {
            case TWO_WHEELER:
                if (preferred != null) options.add(preferred);
                if (!options.contains(SlotType.SMALL)) options.add(SlotType.SMALL);
                if (!options.contains(SlotType.MEDIUM)) options.add(SlotType.MEDIUM);
                if (!options.contains(SlotType.LARGE)) options.add(SlotType.LARGE);
                break;
            case CAR:
                if (preferred != null && preferred != SlotType.SMALL) options.add(preferred);
                if (!options.contains(SlotType.MEDIUM)) options.add(SlotType.MEDIUM);
                if (!options.contains(SlotType.LARGE)) options.add(SlotType.LARGE);
                break;
            case BUS:
                options.add(SlotType.LARGE);
                break;
        }
        return options;
    }
}
