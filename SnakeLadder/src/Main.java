import difficulty.Difficulty;
import game.Game;
import game.GameFactory;
import observer.ConsoleNotifier;
import observer.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("===================================");
        System.out.println("      SNAKE & LADDER - LLD GAME   ");
        System.out.println("===================================");

        // Read board size
        int n = 0;
        while (n < 2) {
            System.out.print("\nEnter board size n (creates an n x n board, e.g. 10): ");
            if (scanner.hasNextInt()) {
                n = scanner.nextInt();
                if (n < 2) {
                    System.out.println("  Board size must be at least 2. Try again.");
                }
            } else {
                System.out.println("  Please enter a valid integer.");
                scanner.next();
            }
        }
        scanner.nextLine();

        // Read number of players
        int numPlayers = 0;
        while (numPlayers < 2) {
            System.out.print("Enter number of players (minimum 2): ");
            if (scanner.hasNextInt()) {
                numPlayers = scanner.nextInt();
                if (numPlayers < 2) {
                    System.out.println("  Need at least 2 players. Try again.");
                }
            } else {
                System.out.println("  Please enter a valid integer.");
                scanner.next();
            }
        }
        scanner.nextLine();

        // Read player names
        List<String> playerNames = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String name = scanner.nextLine().trim();
            while (name.isEmpty()) {
                System.out.print("  Name cannot be empty. Enter name for Player " + i + ": ");
                name = scanner.nextLine().trim();
            }
            playerNames.add(name);
        }

        // Read difficulty
        Difficulty difficulty = null;
        while (difficulty == null) {
            System.out.println("\nSelect difficulty:");
            System.out.println("  1. EASY  - standard rules");
            System.out.println("  2. HARD  - 3 consecutive 6s = turn lost");
            System.out.print("Enter choice (1 or 2): ");

            if (scanner.hasNextInt()) {
                int diffChoice = scanner.nextInt();
                scanner.nextLine();
                if (diffChoice == 1) {
                    difficulty = Difficulty.EASY;
                    System.out.println("  Difficulty set to EASY.");
                } else if (diffChoice == 2) {
                    difficulty = Difficulty.HARD;
                    System.out.println("  Difficulty set to HARD.");
                } else {
                    System.out.println("  Invalid choice. Please enter 1 or 2.");
                }
            } else {
                System.out.println("  Please enter a valid integer.");
                scanner.next();
            }
        }

        System.out.println("\n  Setting up game...");
        Game game = GameFactory.createGame(n, playerNames, difficulty);

        Observer notifier = new ConsoleNotifier();
        game.addObserver(notifier);

        game.play();

        scanner.close();
    }
}
