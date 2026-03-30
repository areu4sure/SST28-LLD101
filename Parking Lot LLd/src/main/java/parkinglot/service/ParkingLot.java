package parkinglot.service;

import parkinglot.config.RateCard;
import parkinglot.enums.SlotSize;
import parkinglot.enums.SlotStatus;
import parkinglot.model.*;
import parkinglot.strategy.NearestSlotAllocator;
import parkinglot.strategy.SlotAllocator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

/**
 * The core controller for the Multilevel Parking Lot.
 * Implements the Singleton pattern.
 */
public class ParkingLot {
    private static ParkingLot instance;
    private final String name;
    private final List<ParkingLevel> levels;
    private final List<EntryGate> gates;
    private final RateCard rateCard;
    private final SlotAllocator allocator;

    private ParkingLot(String name) {
        this.name = name;
        this.levels = new ArrayList<>();
        this.gates = new ArrayList<>();
        this.rateCard = new RateCard();
        this.allocator = new NearestSlotAllocator();
    }

    public static synchronized ParkingLot getInstance(String name) {
        if (instance == null) {
            instance = new ParkingLot(name);
        }
        return instance;
    }

    public void addLevel(ParkingLevel level) {
        levels.add(level);
    }

    public void addGate(EntryGate gate) {
        gates.add(gate);
    }

    public RateCard getRateCard() {
        return rateCard;
    }

    /**
     * Requirement: park(vehicleDetails, entryTime, requestedSlotType, entryGateID)
     * Parks a vehicle and returns the generated parking ticket.
     */
    public synchronized ParkingTicket park(Vehicle vehicle, LocalDateTime entryTime, SlotSize requestedSize, String gateId) {
        ParkingSlot slot = allocator.findSlot(requestedSize, gateId, levels);
        if (slot == null) {
            System.out.println("No available " + requestedSize + " compatible slot found from gate " + gateId);
            return null;
        }

        slot.setStatus(SlotStatus.OCCUPIED);
        ParkingTicket ticket = new ParkingTicket(vehicle, slot, entryTime);
        System.out.println("Parked vehicle " + vehicle.getPlateNumber() + " at slot " + slot.getSlotId());
        return ticket;
    }

    /**
     * Requirement: status()
     * Returns the current availability of parking slots by slot type.
     */
    public synchronized Map<SlotSize, Integer> status() {
        Map<SlotSize, Integer> availability = new HashMap<>();
        for (SlotSize size : SlotSize.values()) {
            availability.put(size, 0);
        }

        for (ParkingLevel level : levels) {
            for (ParkingSlot slot : level.getSlots()) {
                if (slot.getStatus() == SlotStatus.AVAILABLE) {
                    availability.put(slot.getSize(), availability.get(slot.getSize()) + 1);
                }
            }
        }
        return availability;
    }

    /**
     * Requirement: exit(parkingTicket, exitTime)
     * Processes vehicle exit and returns the generated bill amount.
     */
    public synchronized double exit(ParkingTicket ticket, LocalDateTime exitTime) {
        if (ticket == null) return 0.0;

        ParkingSlot slot = ticket.getSlot();
        slot.setStatus(SlotStatus.AVAILABLE);

        Duration duration = Duration.between(ticket.getEntryTime(), exitTime);
        long hours = (long) Math.ceil(duration.toMinutes() / 60.0);
        if (hours == 0) hours = 1; // Minimum charge for 1 hour

        // Feature: Billing should be based on the allocated slot type, not the vehicle type.
        double rate = rateCard.getRate(slot.getSize());
        double amount = hours * rate;

        System.out.println("Vehicle " + ticket.getVehicle().getPlateNumber() + " exited from slot " + slot.getSlotId());
        System.out.println("Duration: " + hours + " hours. Rate: " + rate + "/hr. Total: " + amount);

        return amount;
    }
}
