package com.snakeandladder.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the nxn game board.
 * Manages grid layout and entity registration.
 */
public class Board {
    private final int dimension;
    private final Cell[][] grid;
    private final Map<Integer, BoardEntity> entityMap;

    public Board(int dimension) {
        this.dimension = dimension;
        this.grid = new Cell[dimension][dimension];
        this.entityMap = new HashMap<>();
        initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                // Determine board position (1 to dimension^2)
                int position;
                if (i % 2 == 0) {
                    position = (i * dimension) + (j + 1);
                } else {
                    position = ((i + 1) * dimension) - j;
                }
                grid[i][j] = new Cell(position);
            }
        }
    }

    public Cell getCell(int position) {
        if (position < 1 || position > dimension * dimension) return null;
        
        // Find cell in grid array (not used much for lookups, but good for rendering)
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (grid[i][j].getPosition() == position) {
                    return grid[i][j];
                }
            }
        }
        return null;
    }

    public void registerEntity(BoardEntity entity) {
        entityMap.put(entity.getEntryPoint(), entity);
        Cell cell = getCell(entity.getEntryPoint());
        if (cell != null) {
            cell.setOccupant(entity);
        }
    }

    public boolean hasEntity(int position) {
        return entityMap.containsKey(position);
    }

    public BoardEntity getEntity(int position) {
        return entityMap.get(position);
    }

    public int getDimension() {
        return dimension;
    }

    public int getTotalCells() {
        return dimension * dimension;
    }

    public Cell[][] getGrid() {
        return grid;
    }
}
