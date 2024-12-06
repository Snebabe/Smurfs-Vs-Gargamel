package com.group9.model.managers;

import com.group9.model.board.Board;
import com.group9.model.board.Lane;
import com.group9.model.entities.attackers.AttackEntity;

public class GameStateManager {
    private Board board;

    public GameStateManager(Board board) {
        this.board = board;
    }

    /**
     * Checks if any attacker has reached the end of the grid (index 0).
     * If so, it triggers the game over logic in the Model.
     */


    public boolean isGameOver() {
        for (Lane lane : board.getLanes()) {
            for (AttackEntity attacker : lane.getAttackers()) {
                // Check if the attacker has reached the end of the grid (index 0)
                if (attacker.getLaneProgress() >= 1.0) {
                    return true; // Exit as the game is over
                }
            }
        }
        return false;
    }

    public void resetBoard(Board board) {
        this.board = board;
    }
}