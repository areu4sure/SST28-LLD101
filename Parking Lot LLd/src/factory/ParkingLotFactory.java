package factory;

import core.EntryGate;
import core.ParkingLot;
import enums.SlotType;
import models.ParkingSlot;
import strategy.NearestSlotStrategy;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotFactory {

    public static ParkingLot create(String name, int floors, int slotsPerFloor, int numGates) {
        List<ParkingSlot> allSlots = buildSlots(floors, slotsPerFloor);
        List<EntryGate> gates = buildGates(numGates, allSlots);
        return new ParkingLot(name, gates, allSlots);
    }

    private static List<ParkingSlot> buildSlots(int floors, int slotsPerFloor) {
        List<ParkingSlot> slots = new ArrayList<>();
        int id = 1;
        for (int floor = 1; floor <= floors; floor++) {
            for (int i = 0; i < slotsPerFloor; i++) {
                int dist = (floor - 1) * 100 + i * 3;
                slots.add(new ParkingSlot(id++, SlotType.SMALL,  floor, dist));
                slots.add(new ParkingSlot(id++, SlotType.MEDIUM, floor, dist + 1));
                slots.add(new ParkingSlot(id++, SlotType.LARGE,  floor, dist + 2));
            }
        }
        return slots;
    }

    private static List<EntryGate> buildGates(int numGates, List<ParkingSlot> slots) {
        List<EntryGate> gates = new ArrayList<>();
        NearestSlotStrategy strategy = new NearestSlotStrategy();
        for (int g = 1; g <= numGates; g++) {
            gates.add(new EntryGate(g, slots, strategy));
        }
        return gates;
    }
}
