package rules;

import difficulty.Difficulty;

public class RulesFactory {

    private RulesFactory() {}

    public static GameRules createRules(Difficulty difficulty) {
        switch (difficulty) {
            case EASY:
                return new EasyRules();
            case HARD:
                return new HardRules();
            default:
                throw new IllegalArgumentException("Unknown difficulty: " + difficulty);
        }
    }
}
