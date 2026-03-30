package com.snakeandladder.engine;

import com.snakeandladder.config.GameConfig;
import com.snakeandladder.dice.StandardDice;
import com.snakeandladder.model.Board;
import com.snakeandladder.model.BoardEntity;
import com.snakeandladder.model.Participant;
import com.snakeandladder.renderer.ConsoleRenderer;
import com.snakeandladder.strategy.DifficultyStrategy;

import java.util.*;

/**
 * Core engine orchestrating the turns and rules.
 */
public class GameEngine {
    private final Board board;
    private final StandardDice dice;
    private final DifficultyStrategy strategy;
    private final Queue<Participant> activePlayers;
    private final List<Participant> leaderboard;
    private final ConsoleRenderer renderer;
    private int finishRankCounter = 1;

    public GameEngine(Board board, List<Participant> players, StandardDice dice, 
                      DifficultyStrategy strategy, ConsoleRenderer renderer) {
        this.board = board;
        this.activePlayers = new LinkedList<>(players);
        this.leaderboard = new ArrayList<>();
        this.dice = dice;
        this.strategy = strategy;
        this.renderer = renderer;
    }

    /**
     * Starts the main game loop.
     */
    public void start() {
        System.out.println("Starting the Game...");
        
        while (!isGameOver()) {
            Participant current = activePlayers.poll();
            if (current == null) break;

            executeTurn(current);

            if (checkWinCondition(current)) {
                current.markFinished(finishRankCounter++);
                leaderboard.add(current);
                renderer.renderWinner(current, current.getFinishRank());
            } else {
                activePlayers.offer(current); // Re-queue for next turn
            }
        }
        finishGame();
    }

    private void executeTurn(Participant player) {
        int consecutiveSixes = 0;
        int initialPos = player.getCurrentPosition();

        boolean turnActive = true;
        while (turnActive) {
            int roll = dice.roll();
            consecutiveSixes = (roll == 6) ? consecutiveSixes + 1 : 0;

            // Check if extra turn is allowed (Strategy pattern)
            if (roll == 6 && !strategy.shouldGrantExtraTurn(consecutiveSixes)) {
                System.out.println("  Rolled too many sixes! Turn ends.");
                player.setCurrentPosition(initialPos); // Reset to where they started the turn in hard mode
                break;
            }

            // Calculate new position
            int oldPos = player.getCurrentPosition();
            if (strategy.isValidMove(oldPos, roll, board.getTotalCells())) {
                int newPos = strategy.computeNewPosition(oldPos, roll, board.getTotalCells());
                player.setCurrentPosition(newPos);
                renderer.renderTurnInfo(player, roll, oldPos, newPos);

                // Apply Board Entity (Snake/Ladder)
                applyBoardEntity(player);
                
                // If player already won, break early
                if (player.getCurrentPosition() == board.getTotalCells()) {
                    break;
                }
            } else {
                renderer.renderTurnInfo(player, roll, oldPos, oldPos);
                System.out.println("  Overshot! Stay in place.");
            }

            // Determine if turn continues (standard re-roll on 6)
            turnActive = (roll == 6 && player.getCurrentPosition() < board.getTotalCells());
        }
    }

    private void applyBoardEntity(Participant player) {
        int currentPos = player.getCurrentPosition();
        if (board.hasEntity(currentPos)) {
            BoardEntity entity = board.getEntity(currentPos);
            int newPos = entity.getExitPoint();
            player.setCurrentPosition(newPos);

            // Display message based on type
            if (entity.getDisplacement() < 0) {
                renderer.renderSnakeBite(player, currentPos, newPos);
            } else {
                renderer.renderLadderClimb(player, currentPos, newPos);
            }
        }
    }

    private boolean checkWinCondition(Participant player) {
        return player.getCurrentPosition() == board.getTotalCells();
    }

    private boolean isGameOver() {
        // Game continues as long as there are at least 2 active players left
        // Wait, rule was "continue till at least 2 players still playing to win"
        // Meaning keep playing until only 1 player remains in the active queue.
        return activePlayers.size() <= 1;
    }

    private void finishGame() {
        System.out.println("\nGAME OVER.");
        renderer.renderLeaderboard(leaderboard);
        if (!activePlayers.isEmpty()) {
            Participant last = activePlayers.poll();
            System.out.println("Last player: " + last.getName());
        }
    }
}
