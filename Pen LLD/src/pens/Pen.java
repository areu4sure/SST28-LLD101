package pens;

import enums.InkColor;
import interfaces.Refillable;
import interfaces.Writable;
import models.Ink;

public abstract class Pen implements Writable, Refillable {

    protected String brand;
    protected Ink ink;
    protected boolean isOpen;

    public Pen(String brand, InkColor color) {
        this.brand = brand;
        this.ink = new Ink(color);
        this.isOpen = false;
    }

    @Override
    public void write(String text) {
        if (!isOpen) {
            System.out.println(brand + ": Pen is closed, can't write.");
            return;
        }
        if (ink.isEmpty()) {
            System.out.println(brand + ": Ink is empty, please refill.");
            return;
        }
        ink.use();
        System.out.println(brand + " [" + ink.getColor() + ", Ink: " + ink.getLevel() + "%] -> " + text);
    }

    @Override
    public void refill() {
        ink.refill();
    }
}
