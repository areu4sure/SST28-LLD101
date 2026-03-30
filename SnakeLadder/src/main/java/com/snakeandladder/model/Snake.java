package com.snakeandladder.model;

/**
 * Represents a Snake on the board.
 * Entry Point (Head) is at a higher number than Exit Point (Tail).
 */
public class Snake extends BoardEntity {

    public Snake(int head, int tail) {
        super(head, tail);
        if (head <= tail) {
            throw new IllegalArgumentException("Snake head must be at a higher position than its tail.");
        }
    }

    @Override
    public String getSymbol() {
        return "S" + getExitPoint();
    }

    @Override
    public String getDescription() {
        return "Snake Head at " + entryPoint + " -> Tail at " + exitPoint;
    }
}
