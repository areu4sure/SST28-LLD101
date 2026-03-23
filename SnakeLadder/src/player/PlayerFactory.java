package player;

import java.util.ArrayList;
import java.util.List;

public class PlayerFactory {

    private PlayerFactory() {}

    public static Player createPlayer(int id, String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Player name cannot be empty.");
        }
        return new Player(id, name.trim());
    }

    public static List<Player> createPlayers(List<String> names) {
        if (names == null || names.size() < 2) {
            throw new IllegalArgumentException("There must be at least 2 players.");
        }
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            players.add(createPlayer(i + 1, names.get(i)));
        }
        return players;
    }
}
