package com.group9.model.managers;

import com.group9.model.GameOverListener;
import com.group9.model.board.Board;
import com.group9.model.board.Lane;
import com.group9.model.entities.characters.attackers.AttackEntity;

import java.util.ArrayList;
import java.util.List;

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
     * If so, it triggers the game over logic in the Model.
     */

    public void addGameOverListener(GameOverListener listener) {
        this.gameOverListeners.add(listener);
    }

    public boolean isGameOver() {
        for (Lane lane : board.getLanes()) {
            for (AttackEntity attacker : lane.getAttackers()) {
                // Check if the attacker has reached the end of the grid (index 0)
                if (attacker.getLaneProgress() >= 1.0) {
                    // Notify all listeners that the game is over
                    for (GameOverListener listener : gameOverListeners) {
                        listener.onGameOver(waveManager.getWaveNumber()-1);
                    }
                    return true; // Exit as the game is over
                }
            }
        }
        return false;
    }
}