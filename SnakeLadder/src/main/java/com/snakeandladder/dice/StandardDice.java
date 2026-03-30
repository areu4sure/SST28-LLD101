package com.snakeandladder.dice;

import java.util.Random;

/**
 * Standard six-sided dice simulation.
 */
public class StandardDice {
    private final int faces;
    private final Random rng;

    public StandardDice() {
        this.faces = 6;
        this.rng = new Random();
    }

    /**
     * Rolls the dice and returns a random value.
     */
    public int roll() {
        return rng.nextInt(faces) + 1;
    }

    public int getFaces() {
        return faces;
    }
}
