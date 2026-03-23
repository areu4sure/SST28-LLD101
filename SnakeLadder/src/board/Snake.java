package board;

// A snake has a head (where the player lands) and a tail (where the player gets sent)
// head is always at a higher cell number than the tail
public class Snake extends BoardEntity {

    public Snake(int headPosition, int tailPosition) {
        super(headPosition, tailPosition);
    }

    @Override
    public String getEntityType() {
        return "SNAKE";
    }

    @Override
    public String describe() {
        return "Snake  | Head: " + startPosition + " --> Tail: " + endPosition;
    }
}
