package core;

import enums.SlotType;
import models.ParkingSlot;
import strategy.SlotAssignmentStrategy;
import enums.VehicleType;
import java.util.*;

public class EntryGate {
    private int gateId;
    private List<ParkingSlot> slotsByDistance;
    private SlotAssignmentStrategy strategy;

    public EntryGate(int gateId, List<ParkingSlot> slots, SlotAssignmentStrategy strategy) {
        this.gateId = gateId;
        this.slotsByDistance = slots;
        this.strategy = strategy;
    }

    public int getGateId() { return gateId; }

    public ParkingSlot findSlot(VehicleType vehicleType, SlotType requestedSlotType) {
        return strategy.assign(vehicleType, requestedSlotType, slotsByDistance);
    }
}
