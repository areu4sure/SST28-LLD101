package rules;

import board.Board;
import player.Player;

public interface GameRules {

    // Check if the move keeps the player within the board
    boolean isValidMove(int currentPosition, int diceValue, int boardSize);

    // Calculate where the player ends up after the dice roll (including snakes/ladders)
    int calculateNewPosition(int currentPosition, int diceValue, Board board);

    // Called before moving — returns true if the player's turn should be skipped
    boolean handleConsecutiveSix(Player player, int diceValue);

    // Check if the player has reached the last cell
    boolean hasWon(int position, int boardSize);
}
