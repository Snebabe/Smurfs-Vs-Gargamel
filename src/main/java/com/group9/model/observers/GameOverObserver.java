package com.group9.model.observers;

/**
 * Interface for objects that want to be notified when the game is over.
 * Implementing classes can define what happens when the game ends (e.g., showing the waves survived).
 */
public interface GameOverObserver {
    void onGameOver(int wavesSurvived);
}
