package board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Board — Single Responsibility: maintains the grid and entity placement.
 * It does NOT know about players, dice, or rules (SRP).
 *
 * Cells are numbered 1 to n*n (0 is the "outside" start position).
 * Exactly n snakes and n ladders are placed randomly (requirement).
 */
public class Board {

    private final int size;          // total cells = n * n
    private final int n;             // board side length

    // O(1) lookup: cell number -> BoardEntity at that cell
    private final Map<Integer, BoardEntity> entityMap;

    // Ordered list for display purposes
    private final List<BoardEntity> snakes;
    private final List<BoardEntity> ladders;

    // Package-private constructor — creation is delegated to BoardFactory
    Board(int n) {
        this.n = n;
        this.size = n * n;
        this.entityMap = new HashMap<>();
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
    }

    /** Called by BoardFactory after construction to randomly place entities. */
    void placeEntitiesRandomly() {
        Random random = new Random();

        // Place n snakes
        int snakesPlaced = 0;
        while (snakesPlaced < n) {
            // Head must be at least in the second row so tail can be below (> n)
            int head = random.nextInt(size - n - 1) + n + 1;  // range [n+1 .. size-1]
            int tail = random.nextInt(head - 1) + 1;           // range [1 .. head-1]

            if (!entityMap.containsKey(head) && !entityMap.containsKey(tail)) {
                Snake snake = new Snake(head, tail);
                entityMap.put(head, snake);
                snakes.add(snake);
                snakesPlaced++;
            }
        }

        // Place n ladders
        int laddersPlaced = 0;
        while (laddersPlaced < n) {
            int start = random.nextInt(size - n - 1) + 1;          // range [1 .. size-n-1]
            int end = random.nextInt(size - start) + start + 1;    // range [start+1 .. size]

            if (!entityMap.containsKey(start) && !entityMap.containsKey(end) && end <= size) {
                Ladder ladder = new Ladder(start, end);
                entityMap.put(start, ladder);
                ladders.add(ladder);
                laddersPlaced++;
            }
        }
    }

    /**
     * Returns the BoardEntity at a given cell (snake head or ladder start),
     * or null if there is none.
     */
    public BoardEntity getEntityAt(int position) {
        return entityMap.get(position);
    }

    public int getSize() {
        return size;
    }

    public int getSideLength() {
        return n;
    }

    /** Display board configuration for the players. */
    public void display() {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║       BOARD CONFIGURATION        ║");
        System.out.printf( "║  Board: %dx%d  |  Total cells: %-4d║%n", n, n, size);
        System.out.println("╠══════════════════════════════════╣");

        System.out.println("║  SNAKES (" + snakes.size() + ")                        ║");
        for (BoardEntity s : snakes) {
            System.out.printf("║    %-30s║%n", s.describe());
        }

        System.out.println("╠══════════════════════════════════╣");
        System.out.println("║  LADDERS (" + ladders.size() + ")                       ║");
        for (BoardEntity l : ladders) {
            System.out.printf("║    %-30s║%n", l.describe());
        }
        System.out.println("╚══════════════════════════════════╝");
    }
}
