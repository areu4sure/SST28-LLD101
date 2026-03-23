package game;

import board.Board;
import board.BoardFactory;
import dice.Dice;
import dice.DiceFactory;
import difficulty.Difficulty;
import player.Player;
import player.PlayerFactory;
import rules.GameRules;
import rules.RulesFactory;

import java.util.List;

public class GameFactory {

    private GameFactory() {}

    public static Game createGame(int n, List<String> playerNames, Difficulty difficulty) {
        Board board = BoardFactory.createBoard(n);
        Dice dice = DiceFactory.createStandardDice();
        GameRules rules = RulesFactory.createRules(difficulty);
        List<Player> players = PlayerFactory.createPlayers(playerNames);
        return new Game(board, dice, rules, players);
    }
}
