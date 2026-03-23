package player;

public class Player {

    private final int id;
    private final String name;

    private int position;          // starts at 0 (outside the board)
    private int consecutiveSixes;  // tracks consecutive 6 rolls for hard mode
    private int wins;

    Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.position = 0;
        this.consecutiveSixes = 0;
        this.wins = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int getConsecutiveSixes() {
        return consecutiveSixes;
    }

    public int getWins() {
        return wins;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void incrementConsecutiveSixes() {
        this.consecutiveSixes++;
    }

    public void resetConsecutiveSixes() {
        this.consecutiveSixes = 0;
    }

    public void incrementWins() {
        this.wins++;
    }

    @Override
    public String toString() {
        return String.format("%-12s | Position: %3d", name, position);
    }
}
