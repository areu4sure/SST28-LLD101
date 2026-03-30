package com.snakeandladder.config;

/**
 * Configuration DTO for the Snake and Ladder game.
 * Stores board size, player count, and difficulty level.
 */
public class GameConfig {
    private final int boardSize;
    private final int playerCount;
    private final DifficultyLevel difficulty;

    public GameConfig(int boardSize, int playerCount, DifficultyLevel difficulty) {
        this.boardSize = boardSize;
        this.playerCount = playerCount;
        this.difficulty = difficulty;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public DifficultyLevel getDifficulty() {
        return difficulty;
    }

    public int getTotalCells() {
        return boardSize * boardSize;
    }
}
