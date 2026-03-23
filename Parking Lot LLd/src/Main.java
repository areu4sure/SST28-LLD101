import core.ParkingLot;
import enums.SlotType;
import enums.VehicleType;
import factory.ParkingLotFactory;
import models.Bill;
import models.ParkingTicket;
import models.Vehicle;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        ParkingLot lot = ParkingLotFactory.create("CityMall Parking", 3, 5, 2);

        lot.status();

        Vehicle bike = new Vehicle("MH12AB1234", VehicleType.TWO_WHEELER);
        Vehicle car  = new Vehicle("MH14XY9090", VehicleType.CAR);
        Vehicle bus  = new Vehicle("MH01ZZ0001", VehicleType.BUS);

        LocalDateTime now = LocalDateTime.now();

        ParkingTicket t1 = lot.park(bike, now,               SlotType.SMALL,  1);
        ParkingTicket t2 = lot.park(car,  now.minusHours(2), SlotType.MEDIUM, 2);
        ParkingTicket t3 = lot.park(bus,  now.minusHours(5), SlotType.LARGE,  1);
        ParkingTicket t4 = lot.park(bike, now.minusHours(3), null,            2);

        System.out.println(t1);
        System.out.println(t2);
        System.out.println(t3);
        System.out.println(t4);

        lot.status();

        Bill b1 = lot.exit(t1, now.plusHours(1));
        Bill b2 = lot.exit(t2, now);
        Bill b3 = lot.exit(t3, now);

        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);

        lot.status();
    }
}
