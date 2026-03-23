package pens;

import enums.InkColor;
import interfaces.Openable;

public class NonClickablePen extends Pen implements Openable {

    public NonClickablePen(String brand, InkColor color) {
        super(brand, color);
    }

    @Override
    public void open() {
        isOpen = true;
        System.out.println(brand + ": Cap removed, pen is open.");
    }

    @Override
    public void close() {
        isOpen = false;
        System.out.println(brand + ": Cap placed, pen is closed.");
    }

    @Override
    public String toString() {
        return "NonClickablePen | " + brand + " | " + ink.getColor() + " | Ink: " + ink.getLevel() + "%";
    }
}
