package models;

import enums.SlotType;

public class ParkingSlot {
    private int slotId;
    private SlotType type;
    private int floor;
    private int distanceFromGate;
    private boolean isOccupied;

    public ParkingSlot(int slotId, SlotType type, int floor, int distanceFromGate) {
        this.slotId = slotId;
        this.type = type;
        this.floor = floor;
        this.distanceFromGate = distanceFromGate;
        this.isOccupied = false;
    }

    public int getSlotId() { return slotId; }
    public SlotType getType() { return type; }
    public int getFloor() { return floor; }
    public int getDistanceFromGate() { return distanceFromGate; }
    public boolean isOccupied() { return isOccupied; }

    public void occupy() { isOccupied = true; }
    public void vacate() { isOccupied = false; }
}
