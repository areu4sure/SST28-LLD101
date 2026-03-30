package parkinglot.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a floor or level in the multilevel parking lot.
 */
public class ParkingLevel {
    private final int levelId;
    private final List<ParkingSlot> slots;

    public ParkingLevel(int levelId) {
        this.levelId = levelId;
        this.slots = new ArrayList<>();
    }

    public int getLevelId() {
        return levelId;
    }

    public List<ParkingSlot> getSlots() {
        return slots;
    }

    public void addSlot(ParkingSlot slot) {
        slots.add(slot);
    }

    @Override
    public String toString() {
        return "ParkingLevel{" +
                "levelId=" + levelId +
                ", slotsCount=" + slots.size() +
                '}';
    }
}
