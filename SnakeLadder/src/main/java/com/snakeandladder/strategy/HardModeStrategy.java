package com.snakeandladder.strategy;

/**
 * Implementation of game rules for HARD mode.
 * Rules:
 * - Extra turn on 6, but max 3 consecutive sixes allowed.
 * - If 3 consecutive sixes are rolled, the entire turn is canceled/ends.
 * - Must land exactly on end square; overshoot -> stay in place.
 */
public class HardModeStrategy implements DifficultyStrategy {
    private static final int MAX_CONSECUTIVE_SIXES = 3;

    @Override
    public boolean shouldGrantExtraTurn(int consecutiveSixes) {
        return consecutiveSixes < MAX_CONSECUTIVE_SIXES;
    }

    @Override
    public boolean isValidMove(int currentPos, int diceValue, int boardMax) {
        // You only move if you land exactly or below max
        return (currentPos + diceValue) <= boardMax;
    }

    @Override
    public int computeNewPosition(int currentPos, int diceValue, int boardMax) {
        int nextPos = currentPos + diceValue;
        if (nextPos > boardMax) {
            return currentPos; // Overshoot -> No move
        }
        return nextPos;
    }
}
