package models;

import enums.SlotType;
import java.time.LocalDateTime;

public class ParkingTicket {
    private String ticketId;
    private Vehicle vehicle;
    private ParkingSlot slot;
    private int entryGateId;
    private LocalDateTime entryTime;

    public ParkingTicket(String ticketId, Vehicle vehicle, ParkingSlot slot, int entryGateId, LocalDateTime entryTime) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.slot = slot;
        this.entryGateId = entryGateId;
        this.entryTime = entryTime;
    }

    public String getTicketId() { return ticketId; }
    public Vehicle getVehicle() { return vehicle; }
    public ParkingSlot getSlot() { return slot; }
    public int getEntryGateId() { return entryGateId; }
    public LocalDateTime getEntryTime() { return entryTime; }
    public SlotType getAllocatedSlotType() { return slot.getType(); }

    @Override
    public String toString() {
        return "Ticket: " + ticketId
                + ", Vehicle: " + vehicle.getNumber()
                + ", Slot: " + slot.getSlotId() + " (" + slot.getType() + ")"
                + ", Floor: " + slot.getFloor()
                + ", Entry: " + entryTime;
    }
}
