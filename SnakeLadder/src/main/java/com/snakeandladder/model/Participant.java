package com.snakeandladder.model;

/**
 * Represents a player in the game.
 */
public class Participant {
    private final int id;
    private final String name;
    private int currentPosition;
    private boolean finished;
    private int finishRank;

    public Participant(int id, String name) {
        this.id = id;
        this.name = name;
        this.currentPosition = 0; // Starts outside the board
        this.finished = false;
        this.finishRank = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public boolean isFinished() {
        return finished;
    }

    public void markFinished(int rank) {
        this.finished = true;
        this.finishRank = rank;
    }

    public int getFinishRank() {
        return finishRank;
    }
}
