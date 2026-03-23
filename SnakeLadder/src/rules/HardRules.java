package rules;

import board.Board;
import board.BoardEntity;
import player.Player;

// Hard mode adds one extra rule on top of standard movement:
// if a player rolls 6 three times in a row, their turn is forfeited
public class HardRules implements GameRules {

    private static final int CONSECUTIVE_SIX_LIMIT = 3;
    private static final int SIX = 6;

    @Override
    public boolean isValidMove(int currentPosition, int diceValue, int boardSize) {
        return (currentPosition + diceValue) <= boardSize;
    }

    @Override
    public int calculateNewPosition(int currentPosition, int diceValue, Board board) {
        int landedPosition = currentPosition + diceValue;
        BoardEntity entity = board.getEntityAt(landedPosition);
        if (entity != null) {
            return entity.getEndPosition();
        }
        return landedPosition;
    }

    @Override
    public boolean handleConsecutiveSix(Player player, int diceValue) {
        if (diceValue != SIX) {
            player.resetConsecutiveSixes();
            return false;
        }

        player.incrementConsecutiveSixes();

        if (player.getConsecutiveSixes() >= CONSECUTIVE_SIX_LIMIT) {
            player.resetConsecutiveSixes();
            return true; // turn is skipped
        }

        return false;
    }

    @Override
    public boolean hasWon(int position, int boardSize) {
        return position == boardSize;
    }
}
