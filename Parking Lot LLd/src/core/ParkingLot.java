package core;

import enums.SlotType;
import models.Bill;
import models.ParkingSlot;
import models.ParkingTicket;
import models.Vehicle;
import pricing.BillingService;

import java.time.LocalDateTime;
import java.util.*;

public class ParkingLot {
    private List<EntryGate> gates;
    private List<ParkingSlot> allSlots;
    private Map<String, ParkingTicket> activeTickets;
    private BillingService billingService;
    private int ticketCounter = 1;

    public ParkingLot(String name, List<EntryGate> gates, List<ParkingSlot> allSlots) {
        this.gates = gates;
        this.allSlots = allSlots;
        this.activeTickets = new HashMap<>();
        this.billingService = new BillingService();
    }

    public ParkingTicket park(Vehicle vehicle, LocalDateTime entryTime, SlotType requestedSlotType, int entryGateId) {
        EntryGate gate = getGate(entryGateId);
        if (gate == null) throw new IllegalArgumentException("Invalid gate ID: " + entryGateId);

        ParkingSlot slot = gate.findSlot(vehicle.getType(), requestedSlotType);
        if (slot == null) throw new RuntimeException("No compatible slot available.");

        slot.occupy();
        String ticketId = "TKT-" + ticketCounter++;
        ParkingTicket ticket = new ParkingTicket(ticketId, vehicle, slot, entryGateId, entryTime);
        activeTickets.put(ticketId, ticket);
        return ticket;
    }

    public Bill exit(ParkingTicket ticket, LocalDateTime exitTime) {
        if (!activeTickets.containsKey(ticket.getTicketId()))
            throw new IllegalArgumentException("Ticket not found or already checked out.");

        ticket.getSlot().vacate();
        activeTickets.remove(ticket.getTicketId());
        return billingService.generateBill(ticket, exitTime);
    }

    public void status() {
        Map<SlotType, Long> available = new LinkedHashMap<>();
        available.put(SlotType.SMALL, allSlots.stream().filter(s -> s.getType() == SlotType.SMALL && !s.isOccupied()).count());
        available.put(SlotType.MEDIUM, allSlots.stream().filter(s -> s.getType() == SlotType.MEDIUM && !s.isOccupied()).count());
        available.put(SlotType.LARGE, allSlots.stream().filter(s -> s.getType() == SlotType.LARGE && !s.isOccupied()).count());

        System.out.println("--- Parking Lot Status ---");
        available.forEach((type, count) -> System.out.println(type + ": " + count + " available"));
    }

    private EntryGate getGate(int gateId) {
        return gates.stream().filter(g -> g.getGateId() == gateId).findFirst().orElse(null);
    }
}
