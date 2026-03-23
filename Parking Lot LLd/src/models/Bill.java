package models;

import java.time.LocalDateTime;

public class Bill {
    private ParkingTicket ticket;
    private long durationHours;
    private double amount;

    public Bill(ParkingTicket ticket, LocalDateTime exitTime, long durationHours, double amount) {
        this.ticket = ticket;
        this.durationHours = durationHours;
        this.amount = amount;
    }

    public double getAmount() { return amount; }

    @Override
    public String toString() {
        return "Ticket: " + ticket.getTicketId()
                + ", Vehicle: " + ticket.getVehicle().getNumber()
                + ", Slot: " + ticket.getAllocatedSlotType()
                + ", Duration: " + durationHours + " hr(s)"
                + ", Amount: Rs." + amount;
    }
}
