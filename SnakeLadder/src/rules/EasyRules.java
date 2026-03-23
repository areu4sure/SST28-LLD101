package rules;

import board.Board;
import board.BoardEntity;
import player.Player;

public class EasyRules implements GameRules {

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
        // Easy mode has no consecutive-six rule
        player.resetConsecutiveSixes();
        return false;
    }

    @Override
    public boolean hasWon(int position, int boardSize) {
        return position == boardSize;
    }
}
