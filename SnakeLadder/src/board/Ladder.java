package board;

// A ladder has a start (bottom) and an end (top)
// end is always at a higher cell number than start
public class Ladder extends BoardEntity {

    public Ladder(int startPosition, int endPosition) {
        super(startPosition, endPosition);
    }

    @Override
    public String getEntityType() {
        return "LADDER";
    }

    @Override
    public String describe() {
        return "Ladder | Start: " + startPosition + " --> End: " + endPosition;
    }
}
