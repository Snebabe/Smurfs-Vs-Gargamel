package com.group9.model.managers;

import com.group9.model.observers.GameOverObserver;
import com.group9.model.board.Board;
import com.group9.model.board.Lane;
import com.group9.model.entities.characters.attackers.AttackEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the game state by checking if the game is over
 */
public class GameStateManager {
    private final Board board;
    private final WaveManager waveManager;
    private final List<GameOverObserver> gameOverObservers;

    /**
     * Constructs a GameStateManager with the specified game board and wave manager.
     *
     * @param board the game board
     * @param waveManager the wave manager
     */
    public GameStateManager(Board board, WaveManager waveManager) {
        this.board = board;
        this.waveManager = waveManager;
        gameOverObservers = new ArrayList<>();
    }

    /**
     * Checks if any attacker has reached the end of the grid (index 0).
     * If so, it sends out game over events to its listeners
     *
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        for (Lane lane : board.getLanes()) {
            for (AttackEntity attacker : lane.getAttackers()) {
                if (attacker.getLaneProgress() >= 1.0) {
                    // Notify all listeners that the game is over
                    for (GameOverObserver observer : gameOverObservers) {
                        observer.onGameOver(waveManager.getWaveNumber()-1);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public void addGameOverObserver(GameOverObserver observer) {
        gameOverObservers.add(observer);
    }

}