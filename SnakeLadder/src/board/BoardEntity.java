package board;

public abstract class BoardEntity {

    protected final int startPosition;
    protected final int endPosition;

    protected BoardEntity(int startPosition, int endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public abstract String getEntityType();

    public abstract String describe();
}
