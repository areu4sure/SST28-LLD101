package dice;

public class DiceFactory {

    private DiceFactory() {}

    // Returns a standard 6-faced dice
    public static Dice createStandardDice() {
        return new Dice(6);
    }

    // Returns a dice with a custom number of faces
    public static Dice createCustomDice(int faces) {
        if (faces < 2) {
            throw new IllegalArgumentException("Dice must have at least 2 faces.");
        }
        return new Dice(faces);
    }
}
