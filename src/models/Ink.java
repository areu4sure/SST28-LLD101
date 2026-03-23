package models;

import enums.InkColor;

public class Ink {

    private InkColor color;
    private int level; // starts at 100, goes down by 10 each write

    public Ink(InkColor color) {
        this.color = color;
        this.level = 100;
    }

    public void use() {
        level -= 10;
    }

    public void refill() {
        level = 100;
        System.out.println("Ink refilled!");
    }

    public boolean isEmpty() {
        return level <= 0;
    }

    public InkColor getColor() {
        return color;
    }

    public int getLevel() {
        return level;
    }
}
