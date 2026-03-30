package com.snakeandladder.strategy;

/**
 * Interface defining the game rules strategy.
 */
public interface DifficultyStrategy {
    /**
     * Determine if player should get an extra turn based on the current roll and history.
     */
    boolean shouldGrantExtraTurn(int consecutiveSixes);

    /**
     * Check if a proposed move is valid.
     */
    boolean isValidMove(int currentPos, int diceValue, int boardMax);

    /**
     * Compute the final position after a roll, accounting for overshoot rules.
     */
    int computeNewPosition(int currentPos, int diceValue, int boardMax);
}
