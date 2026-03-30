package com.snakeandladder.renderer;

import com.snakeandladder.model.Board;
import com.snakeandladder.model.Cell;
import com.snakeandladder.model.Participant;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles text-based rendering of the game events and board state.
 */
public class ConsoleRenderer {
    private final Board board;

    public ConsoleRenderer(Board board) {
        this.board = board;
    }

    /**
     * Renders an nxn board with indices, player positions, and entities.
     */
    public void renderBoard(List<Participant> players) {
        int dimension = board.getDimension();
        Cell[][] grid = board.getGrid();
        
        System.out.println("\n--- Current Board State ---");
        for (int i = dimension - 1; i >= 0; i--) { // Render top-down
            System.out.print("| ");
            for (int j = 0; j < dimension; j++) {
                Cell cell = grid[i][j];
                int pos = cell.getPosition();
                
                // Identify players on this cell
                List<String> pNames = players.stream()
                        .filter(p -> p.getCurrentPosition() == pos)
                        .map(p -> "P" + p.getId())
                        .collect(Collectors.toList());

                String cellContent;
                if (!pNames.isEmpty()) {
                    cellContent = String.join(",", pNames);
                } else if (cell.hasOccupant()) {
                    cellContent = cell.getOccupant().getSymbol();
                } else {
                    cellContent = String.valueOf(pos);
                }

                // Padding content for alignment (max 6 chars)
                System.out.printf("%-6s | ", cellContent);
            }
            System.out.println();
        }
        System.out.println("---------------------------\n");
    }

    public void renderTurnInfo(Participant player, int roll, int oldPos, int newPos) {
        String msg = String.format("[%s] rolled %d: %d -> %d", player.getName(), roll, oldPos, newPos);
        System.out.println(msg);
    }

    public void renderSnakeBite(Participant player, int head, int tail) {
        System.out.printf("  Oops! %s got bit by a Snake at %d! Slid down to %d.\n", player.getName(), head, tail);
    }

    public void renderLadderClimb(Participant player, int bottom, int top) {
        System.out.printf("  Yay! %s found a Ladder at %d! Climbed up to %d.\n", player.getName(), bottom, top);
    }

    public void renderWinner(Participant player, int rank) {
        System.out.printf("\n*** %s HAS FINISHED! (Rank #%d) ***\n", player.getName().toUpperCase(), rank);
    }

    public void renderLeaderboard(List<Participant> leaderboard) {
        System.out.println("\n========= LEADERBOARD =========");
        for (Participant p : leaderboard) {
            System.out.printf("%d. %s\n", p.getFinishRank(), p.getName());
        }
        System.out.println("===============================\n");
    }
}
