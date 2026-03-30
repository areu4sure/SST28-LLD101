package com.snakeandladder.strategy;

/**
 * Implementation of game rules for EASY mode.
 * Rules:
 * - Extra turn on every 6.
 * - Any roll >= end square wins.
 */
public class EasyModeStrategy implements DifficultyStrategy {
    
    @Override
    public boolean shouldGrantExtraTurn(int consecutiveSixes) {
        return true; // Simple re-roll rule for 6
    }

    @Override
    public boolean isValidMove(int currentPos, int diceValue, int boardMax) {
        return true; // In easy mode, you can always move forward
    }

    @Override
    public int computeNewPosition(int currentPos, int diceValue, int boardMax) {
        int nextPos = currentPos + diceValue;
        return Math.min(nextPos, boardMax);
    }
}
