package com.group9.model;

/*
 * Interface for objects that want to be notified when the game is over.
 * Implementing classes can define what happens when the game ends (e.g., showing the score).
 */

public interface GameOverListener {
    // This method is called when the game is over, passing the number of waves survived
    public void onGameOver(int wavesSurvived);
}
