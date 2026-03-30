package parkinglot;

import parkinglot.enums.SlotSize;
import parkinglot.enums.VehicleType;
import parkinglot.model.*;
import parkinglot.service.ParkingLot;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Main class to demonstrate the Multilevel Parking Lot Design.
 */
public class Main {
    public static void main(String[] args) {
        // 1. Setup the Parking Lot (Singleton)
        ParkingLot lot = ParkingLot.getInstance("G-Mall Parking");

        // 2. Initialize Gates
        EntryGate gateA = new EntryGate("GATE_A");
        EntryGate gateB = new EntryGate("GATE_B");
        lot.addGate(gateA);
        lot.addGate(gateB);

        // 3. Initialize Levels and Slots
        // Level 1: 2 Small, 2 Medium, 1 Large
        ParkingLevel level1 = new ParkingLevel(1);
        addSlotWithDistances(level1, "L1-S1", SlotSize.SMALL, 5, 10);
        addSlotWithDistances(level1, "L1-S2", SlotSize.SMALL, 10, 5);
        addSlotWithDistances(level1, "L1-M1", SlotSize.MEDIUM, 15, 20);
        addSlotWithDistances(level1, "L1-M2", SlotSize.MEDIUM, 20, 15);
        addSlotWithDistances(level1, "L1-L1", SlotSize.LARGE, 25, 25);
        lot.addLevel(level1);

        // Level 2: 1 Small, 1 Medium, 1 Large
        ParkingLevel level2 = new ParkingLevel(2);
        addSlotWithDistances(level2, "L2-S1", SlotSize.SMALL, 30, 40);
        addSlotWithDistances(level2, "L2-M1", SlotSize.MEDIUM, 35, 35);
        addSlotWithDistances(level2, "L2-L1", SlotSize.LARGE, 40, 30);
        lot.addLevel(level2);

        // 4. Initial Status
        printStatus(lot);

        // 5. Test Case 1: Bike enters via Gate A, requests Small slot
        System.out.println("\n--- Testing Bike Entry (Gate A) ---");
        Vehicle bike = new Vehicle("KA-01-AB-1234", VehicleType.TWO_WHEELER);
        ParkingTicket ticket1 = lot.park(bike, LocalDateTime.now().minusHours(2), SlotSize.SMALL, "GATE_A");
        
        // 6. Test Case 2: Car enters via Gate B, requests Medium slot
        System.out.println("\n--- Testing Car Entry (Gate B) ---");
        Vehicle car = new Vehicle("TS-09-CD-5678", VehicleType.CAR);
        ParkingTicket ticket2 = lot.park(car, LocalDateTime.now().minusHours(3), SlotSize.MEDIUM, "GATE_B");

        // 7. Test Case 3: Bus enters via Gate A, requests Large slot
        System.out.println("\n--- Testing Bus Entry (Gate A) ---");
        Vehicle bus = new Vehicle("DL-01-EE-9999", VehicleType.BUS);
        ParkingTicket ticket3 = lot.park(bus, LocalDateTime.now().minusHours(4), SlotSize.LARGE, "GATE_A");

        // 8. Test Case 4: Bike enters via Gate B, but Small is full? (Let's make it full)
        // L1-S2 is nearer to Gate B (dist 5). L1-S1 is dist 10.
        // Let's park another bike to fill Small slots.
        System.out.println("\n--- Testing Filling Small Slots ---");
        lot.park(new Vehicle("BIKE-2", VehicleType.TWO_WHEELER), LocalDateTime.now(), SlotSize.SMALL, "GATE_B");
        
        System.out.println("\n--- Status after parkings ---");
        printStatus(lot);

        // 9. Test Case 5: Exit and Billing
        System.out.println("\n--- Testing Exit and Billing ---");
        LocalDateTime exitTime = LocalDateTime.now();
        lot.exit(ticket1, exitTime); // Bike (Small slot, 2 hrs) -> 10 * 2 = 20
        lot.exit(ticket2, exitTime); // Car (Medium slot, 3 hrs) -> 20 * 3 = 60
        lot.exit(ticket3, exitTime); // Bus (Large slot, 4 hrs) -> 50 * 4 = 200

        // 10. Final Status
        System.out.println("\n--- Final Status ---");
        printStatus(lot);
    }

    private static void addSlotWithDistances(ParkingLevel level, String id, SlotSize size, int distA, int distB) {
        ParkingSlot slot = new ParkingSlot(id, size);
        slot.addDistance("GATE_A", distA);
        slot.addDistance("GATE_B", distB);
        level.addSlot(slot);
    }

    private static void printStatus(ParkingLot lot) {
        System.out.println("Availability: " + lot.status());
    }
}
