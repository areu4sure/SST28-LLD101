package com.snakeandladder.model;

/**
 * Base abstract class for any board occupant like Snake or Ladder.
 * Uses Template Method pattern.
 */
public abstract class BoardEntity {
    protected final int entryPoint;
    protected final int exitPoint;

    protected BoardEntity(int entryPoint, int exitPoint) {
        this.entryPoint = entryPoint;
        this.exitPoint = exitPoint;
    }

    public int getEntryPoint() {
        return entryPoint;
    }

    public int getExitPoint() {
        return exitPoint;
    }

    /**
     * Displacement after landing on this entity.
     * @return Positive for Ladder, Negative for Snake.
     */
    public int getDisplacement() {
        return exitPoint - entryPoint;
    }

    /**
     * Symbol for UI representation.
     */
    public abstract String getSymbol();

    /**
     * Human-readable description.
     */
    public abstract String getDescription();
}
