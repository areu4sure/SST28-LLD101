package board;

public class BoardFactory {

    private BoardFactory() {}

    public static Board createBoard(int n) {
        if (n < 2) {
            throw new IllegalArgumentException("Board side length must be at least 2.");
        }
        Board board = new Board(n);
        board.placeEntitiesRandomly();
        return board;
    }
}
