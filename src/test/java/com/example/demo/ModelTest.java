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
        model.recompute_based_on_input("Malte");
        assertEquals(model.getGameState(), GameState.INVALID_INPUT);
    }

    @Test
    public void TestTooHigh() {
        Model model = new Model(50);

        model.getGameState();
        model.recompute_based_on_input("90");
        assertEquals(model.getGameState(), GameState.TOO_HIGH);
    }
    @Test
    public void TestTooLow() {
        Model model = new Model(50);

        model.getGameState();
        model.recompute_based_on_input("10");
        assertEquals(model.getGameState(), GameState.TOO_LOW);
    }
}
