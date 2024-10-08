package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ModelTest {
    @Test
    public void TestInitialGameState() {
        Model model = new Model();

      assertEquals(model.getGameState(),GameState.START);
    }

    @Test
    public void TestInvalidInput() {
        Model model = new Model();

        model.getGameState();
        model.recomputeBasedOnInput("Malte");
        assertEquals(model.getGameState(), GameState.INVALID_INPUT);
    }

    @Test
    public void TestTooHigh() {
        Model model = new Model(50);

        model.getGameState();
        model.recomputeBasedOnInput("90");
        assertEquals(model.getGameState(), GameState.TOO_HIGH);
    }
    @Test
    public void TestTooLow() {
        Model model = new Model(50);

        model.getGameState();
        model.recomputeBasedOnInput("10");
        assertEquals(model.getGameState(), GameState.TOO_LOW);
    }

    @Test
    public void TestTNumberOfGuessesWithValidInput() {
        Model model = new Model();

        model.recomputeBasedOnInput("2");
        model.recomputeBasedOnInput("9");
        model.recomputeBasedOnInput("100");
        assertEquals(model.getNumberOfGuesses(), 3);
    }

    @Test
    public void TestTNumberOfGuessesWithPartiallyInvalidInput() {
        Model model = new Model();

        model.recomputeBasedOnInput("2");
        model.recomputeBasedOnInput("X");
        model.recomputeBasedOnInput("100");
        model.recomputeBasedOnInput("Malte");
        model.recomputeBasedOnInput("10");
        assertEquals(model.getNumberOfGuesses(), 3);
    }
}
