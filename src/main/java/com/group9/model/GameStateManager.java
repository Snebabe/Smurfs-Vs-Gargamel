package com.group9.model;

import com.group9.model.entities.attackers.AttackEntity;

public class GameStateManager {
    private Model model;
    private Board board;

    public GameStateManager(Model model, Board board) {
        this.model = model;
        this.board = board;
    }

    /**
     * Checks if any attacker has reached the end of the grid (index 0).
     * If so, it triggers the game over logic in the Model.
     */
    public void checkGameOverCondition() {
        for (Lane lane : board.getLanes()) {
            for (AttackEntity attacker : lane.getAttackers()) {
                // Check if the attacker has reached the end of the grid (index 0)
                if (attacker.getLaneProgress() >= 1.0) {
                    model.gameOver();
                    return; // Exit as the game is over
                }
            }
        }
    }
}