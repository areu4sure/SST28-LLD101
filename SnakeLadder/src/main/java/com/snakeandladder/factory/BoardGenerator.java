package com.snakeandladder.factory;

import com.snakeandladder.model.BoardEntity;
import com.snakeandladder.model.Ladder;
import com.snakeandladder.model.Snake;

import java.util.*;

/**
 * Utility to generate a random board with snakes and ladders.
 */
public class BoardGenerator {
    private final Random rng = new Random();

    /**
     * Generates n snakes and n ladders.
     * @param count count of snakes and count of ladders (total n+n entities)
     * @param totalCells board size n^2
     */
    public List<BoardEntity> generateEntities(int count, int totalCells) {
        List<BoardEntity> entities = new ArrayList<>();
        Set<Integer> usedCells = new HashSet<>();
        
        // Reserve cell 1 (starting square) and cell totalCells (end square)
        usedCells.add(1);
        usedCells.add(totalCells);

        // Place Snakes
        for (int i = 0; i < count; i++) {
            entities.add(createSnake(usedCells, totalCells));
        }

        // Place Ladders
        for (int i = 0; i < count; i++) {
            entities.add(createLadder(usedCells, totalCells));
        }

        return entities;
    }

    private Snake createSnake(Set<Integer> usedCells, int totalCells) {
        while (true) {
            int head = rng.nextInt(totalCells - 2) + 2; // Avoiding 1
            int tail = rng.nextInt(head - 1) + 1; // tail < head

            if (!usedCells.contains(head) && !usedCells.contains(tail)) {
                usedCells.add(head);
                usedCells.add(tail);
                return new Snake(head, tail);
            }
        }
    }

    private Ladder createLadder(Set<Integer> usedCells, int totalCells) {
        while (true) {
            int bottom = rng.nextInt(totalCells - 2) + 2; // Avoiding cell 1 and end cell
            int top = rng.nextInt(totalCells - (bottom + 1)) + (bottom + 1); // top > bottom, top < totalCells

            if (!usedCells.contains(bottom) && !usedCells.contains(top)) {
                usedCells.add(bottom);
                usedCells.add(top);
                return new Ladder(bottom, top);
            }
        }
    }
}
