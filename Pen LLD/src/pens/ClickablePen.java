package pens;

import enums.InkColor;
import interfaces.Openable;

public class ClickablePen extends Pen implements Openable {

    public ClickablePen(String brand, InkColor color) {
        super(brand, color);
    }

    public void click() {
        if (isOpen) {
            close();
        } else {
            open();
        }
    }

    @Override
    public void open() {
        isOpen = true;
        System.out.println(brand + ": *click* opened.");
    }

    @Override
    public void close() {
        isOpen = false;
        System.out.println(brand + ": *click* closed.");
    }

    @Override
    public String toString() {
        return "ClickablePen | " + brand + " | " + ink.getColor() + " | Ink: " + ink.getLevel() + "%";
    }
}
