package com.snakeandladder.model;

/**
 * Represents a Ladder on the board.
 * Entry Point (Bottom) is at a lower number than Exit Point (Top).
 */
public class Ladder extends BoardEntity {

    public Ladder(int bottom, int top) {
        super(bottom, top);
        if (bottom >= top) {
            throw new IllegalArgumentException("Ladder bottom must be at a lower position than its top.");
        }
    }

    @Override
    public String getSymbol() {
        return "L" + getExitPoint();
    }

    @Override
    public String getDescription() {
        return "Ladder Bottom at " + entryPoint + " -> Top at " + exitPoint;
    }
}
