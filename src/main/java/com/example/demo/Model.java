package com.example.demo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;
import lombok.Getter;

public class Model {

    int correctValue;
    @Getter
    Integer lastGuess;
    boolean initial;
    @Getter int numberOfGuesses;
    private static EntityManagerFactory factory;

    Model() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory("Winners");
        }
        correctValue = (int) (Math.random() * 100);
        reset();
    }

    Model(int correct) {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory("Winners");
        }
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

    public List<Winner> getWinners() {

        EntityManager em = factory.createEntityManager();

        Query q = em.createQuery("select w from Winner w");
        List<Winner> winnerList = q.getResultList();
        winnerList.sort((Winner a, Winner b) -> b.getNumberOfGuesses() - a.getNumberOfGuesses());

        return winnerList;
    }

    public void saveWithName(String name) {

        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        Winner winner = new Winner();
        winner.setName(name);
        winner.setNumberOfGuesses(numberOfGuesses);

        em.persist(winner);
        em.getTransaction().commit();
        em.close();
    }
}
