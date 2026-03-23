package pricing;

import models.Bill;
import models.ParkingTicket;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class BillingService {
    private PricingConfig config = new PricingConfig();

    public Bill generateBill(ParkingTicket ticket, LocalDateTime exitTime) {
        long hours = ChronoUnit.HOURS.between(ticket.getEntryTime(), exitTime);
        if (hours == 0) hours = 1;

        double rate = config.getRatePerHour(ticket.getAllocatedSlotType());
        double amount = hours * rate;

        return new Bill(ticket, exitTime, hours, amount);
    }
}
