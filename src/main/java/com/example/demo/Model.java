package com.example.demo;

import javax.servlet.http.HttpServletRequest;

public class Model {

    int correct;
    public Integer last_guess;
    boolean initial;


    Model() {
        correct = (int) (Math.random() * 100);
        last_guess = null;
        initial = true;
    }

    Model(int correct) {
        this.correct = correct;
        initial = true;
    }

    public void recompute_based_on_input(String guess_string) {
        try {
            last_guess = Integer.parseInt(guess_string);
        } catch (NumberFormatException e) {
            last_guess = null;
        }
    }

    public GameState getGameState() {
        if (initial) {
            initial = false;
            return GameState.START;
        } else if (last_guess == null) {
            return GameState.INVALID_INPUT;
        } else if (last_guess < correct) {
            return GameState.TOO_LOW;
        } else if (last_guess > correct) {
            return GameState.TOO_HIGH;
        } else {
            return GameState.SOLVED;
        }
    }
}
