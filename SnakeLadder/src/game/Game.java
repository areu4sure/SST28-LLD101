package game;

import board.Board;
import board.BoardEntity;
import dice.Dice;
import observer.Observer;
import player.Player;
import rules.GameRules;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class Game {

    private final Board board;
    private final Dice dice;
    private final GameRules rules;
    private final Deque<Player> activePlayers;
    private final List<Observer> observers;

    Game(Board board, Dice dice, GameRules rules, List<Player> players) {
        this.board = board;
        this.dice = dice;
        this.rules = rules;
        this.activePlayers = new ArrayDeque<>(players);
        this.observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    private void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    private void printDivider() {
        System.out.println("--------------------------------------");
    }

    private void displayCurrentPositions() {
        System.out.println("\n  Current Positions:");
        for (Player player : activePlayers) {
            System.out.println("  -> " + player);
        }
    }

    public void play() {
        if (activePlayers.size() < 2) {
            System.out.println("Need at least 2 players to start the game!");
            return;
        }

        notifyObservers("Game started! Board size: " + board.getSize() + " cells.");
        board.display();

        Scanner scanner = new Scanner(System.in);
        int turnNumber = 0;

        // Game continues until only one player is still playing
        while (activePlayers.size() > 1) {
            Player currentPlayer = activePlayers.peekFirst();
            turnNumber++;

            printDivider();
            System.out.printf("Turn #%-3d  |  %s's turn (Position: %d)%n",
                    turnNumber, currentPlayer.getName(), currentPlayer.getPosition());
            System.out.println("  Press [ENTER] to roll the dice...");
            scanner.nextLine();

            int diceValue = dice.roll();
            System.out.println("  Dice rolled: " + diceValue);

            // Hard mode: check if 3 consecutive sixes were rolled
            boolean turnSkipped = rules.handleConsecutiveSix(currentPlayer, diceValue);
            if (turnSkipped) {
                System.out.println("  3 consecutive 6s! " + currentPlayer.getName() + "'s turn is LOST.");
                notifyObservers(currentPlayer.getName() + " rolled 6 three times in a row - turn forfeited!");
                rotateTurn();
                continue;
            }

            int currentPosition = currentPlayer.getPosition();

            // If the move would take the player beyond the board, skip
            if (!rules.isValidMove(currentPosition, diceValue, board.getSize())) {
                System.out.printf("  Cannot move! %d + %d = %d exceeds board size %d. Turn skipped.%n",
                        currentPosition, diceValue, currentPosition + diceValue, board.getSize());
                notifyObservers(currentPlayer.getName() + " could not move (overshoot). Stays at " + currentPosition + ".");
                rotateTurn();
                continue;
            }

            int intermediatePos = currentPosition + diceValue;
            int newPosition = rules.calculateNewPosition(currentPosition, diceValue, board);
            currentPlayer.setPosition(newPosition);

            BoardEntity entity = board.getEntityAt(intermediatePos);
            if (entity != null) {
                if (entity.getEntityType().equals("SNAKE")) {
                    System.out.printf("  SNAKE! Landed on %d -> slid down to %d!%n", intermediatePos, newPosition);
                    notifyObservers(currentPlayer.getName() + " hit a snake at " + intermediatePos + " -> fell to " + newPosition);
                } else {
                    System.out.printf("  LADDER! Landed on %d -> climbed up to %d!%n", intermediatePos, newPosition);
                    notifyObservers(currentPlayer.getName() + " found a ladder at " + intermediatePos + " -> climbed to " + newPosition);
                }
            } else {
                System.out.printf("  Moved from %d -> %d%n", currentPosition, newPosition);
                notifyObservers(currentPlayer.getName() + " moved from " + currentPosition + " to " + newPosition);
            }

            displayCurrentPositions();

            if (rules.hasWon(newPosition, board.getSize())) {
                currentPlayer.incrementWins();
                System.out.println("\n  " + currentPlayer.getName() + " has WON the game!");
                notifyObservers(currentPlayer.getName() + " has reached cell " + board.getSize() + " and WON!");
                activePlayers.removeFirst();
                continue;
            }

            rotateTurn();
        }

        printDivider();
        System.out.println("\nGame Over!");
        if (!activePlayers.isEmpty()) {
            System.out.println("  Last player remaining: " + activePlayers.peekFirst().getName());
        }
        notifyObservers("Game Over!");
        scanner.close();
    }

    // Move the current player to the back of the queue
    private void rotateTurn() {
        Player p = activePlayers.pollFirst();
        if (p != null) {
            activePlayers.addLast(p);
        }
    }
}
