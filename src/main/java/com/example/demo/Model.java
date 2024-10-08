package com.example.demo;

import lombok.Getter;

public class Model {

    int correctValue;
    @Getter
    Integer lastGuess;
    boolean initial;
    @Getter int numberOfGuesses;

    Model() {
        correctValue = (int) (Math.random() * 100);
        reset();
    }

    Model(int correct) {
        this.correctValue = correct;
        reset();
    }

    public void reset() {
        lastGuess = null;
        initial = true;
        numberOfGuesses = 0;
    }

    public void recomputeBasedOnInput(String guess_string) {
        try {
            lastGuess = Integer.parseInt(guess_string);
            numberOfGuesses++;
        } catch (NumberFormatException e) {
            lastGuess = null;
        }
    }

    public GameState getGameState() {
        if (initial) {
            initial = false;
            return GameState.START;
        } else if (lastGuess == null) {
            return GameState.INVALID_INPUT;
        } else if (lastGuess < correctValue) {
            return GameState.TOO_LOW;
        } else if (lastGuess > correctValue) {
            return GameState.TOO_HIGH;
        } else {
            return GameState.SOLVED;
        }
    }
}
