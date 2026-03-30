package com.snakeandladder.model;

/**
 * Represents a single cell on the n x n board.
 * Holds its position and any entity present on it.
 */
public class Cell {
    private final int position;
    private BoardEntity occupant;

    public Cell(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public BoardEntity getOccupant() {
        return occupant;
    }

    public void setOccupant(BoardEntity occupant) {
        this.occupant = occupant;
    }

    public boolean hasOccupant() {
        return occupant != null;
    }
}
