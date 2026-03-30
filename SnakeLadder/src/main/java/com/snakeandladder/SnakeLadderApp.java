package com.snakeandladder;

import com.snakeandladder.config.DifficultyLevel;
import com.snakeandladder.config.GameConfig;
import com.snakeandladder.dice.StandardDice;
import com.snakeandladder.engine.GameEngine;
import com.snakeandladder.factory.BoardGenerator;
import com.snakeandladder.factory.StrategyFactory;
import com.snakeandladder.model.Board;
import com.snakeandladder.model.BoardEntity;
import com.snakeandladder.model.Participant;
import com.snakeandladder.renderer.ConsoleRenderer;
import com.snakeandladder.strategy.DifficultyStrategy;

import java.util.*;

/**
 * Main application entry point for Snake and Ladder LLD.
 */
public class SnakeLadderApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Snake and Ladder Game System ===");

        // Step 1: Input configuration
        System.out.print("Enter board size (n for nxn): ");
        int n = sc.nextInt();
        
        System.out.print("Enter number of players: ");
        int x = sc.nextInt();
        
        System.out.print("Enter difficulty (easy/hard): ");
        String diffInput = sc.next();
        DifficultyLevel level = DifficultyLevel.valueOf(diffInput.toUpperCase().trim());

        GameConfig config = new GameConfig(n, x, level);

        // Step 2: Initialize components
        Board board = new Board(n);
        BoardGenerator generator = new BoardGenerator();
        List<BoardEntity> entities = generator.generateEntities(n, config.getTotalCells());
        
        // Register entities to board
        for (BoardEntity entity : entities) {
            board.registerEntity(entity);
            System.out.println("Added: " + entity.getDescription());
        }

        // Create players
        List<Participant> players = new ArrayList<>();
        for (int i = 1; i <= x; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String name = sc.next();
            players.add(new Participant(i, name));
        }

        // Assemble game objects
        StandardDice dice = new StandardDice();
        DifficultyStrategy strategy = StrategyFactory.createStrategy(config.getDifficulty());
        ConsoleRenderer renderer = new ConsoleRenderer(board);

        // Render initial board
        renderer.renderBoard(players);

        // Step 3: Start engine
        GameEngine engine = new GameEngine(board, players, dice, strategy, renderer);
        engine.start();

        sc.close();
    }
}
