package com.group9.model.managers;

import com.group9.model.GameOverListener;
import com.group9.model.board.Board;
import com.group9.model.board.Lane;
import com.group9.model.entities.characters.attackers.AttackEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the game state by checking if the game is over
 */
public class GameStateManager {
    private Board board;
    private WaveManager waveManager;
    private List<GameOverListener> gameOverListeners;

    public GameStateManager(Board board, WaveManager waveManager) {
        this.board = board;
        this.waveManager = waveManager;
        this.gameOverListeners = new ArrayList<>();
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
                    for (GameOverListener listener : gameOverListeners) {
                        listener.onGameOver(waveManager.getWaveNumber()-1);
                    }
                    return true; // Return true as the game is over
                }
            }
        }
        return false;  // Return false if no attacker has reached the end
    }

    public void addGameOverListener(GameOverListener listener) {
        gameOverListeners.add(listener);
    }

}