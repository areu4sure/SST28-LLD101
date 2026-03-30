package com.snakeandladder.factory;

import com.snakeandladder.config.DifficultyLevel;
import com.snakeandladder.strategy.DifficultyStrategy;
import com.snakeandladder.strategy.EasyModeStrategy;
import com.snakeandladder.strategy.HardModeStrategy;

/**
 * Factory class to create appropriate strategy implementation.
 */
public class StrategyFactory {
    public static DifficultyStrategy createStrategy(DifficultyLevel level) {
        switch (level) {
            case EASY:
                return new EasyModeStrategy();
            case HARD:
                return new HardModeStrategy();
            default:
                throw new IllegalArgumentException("Unknown difficulty level: " + level);
        }
    }
}
