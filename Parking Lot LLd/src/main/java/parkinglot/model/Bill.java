package parkinglot.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Generated at the time of vehicle exit.
 */
public class Bill {
    private final String billId;
    private final ParkingTicket ticket;
    private final LocalDateTime exitTime;
    private final double amount;

    public Bill(ParkingTicket ticket, LocalDateTime exitTime, double amount) {
        this.billId = "BILL-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.ticket = ticket;
        this.exitTime = exitTime;
        this.amount = amount;
    }

    public String getBillId() {
        return billId;
    }

    public ParkingTicket getTicket() {
        return ticket;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId='" + billId + '\'' +
                ", ticketId=" + ticket.getTicketId() +
                ", exitTime=" + exitTime +
                ", amount=" + amount +
                '}';
    }
}
