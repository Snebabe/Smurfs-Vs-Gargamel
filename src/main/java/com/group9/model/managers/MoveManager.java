package com.group9.model.managers;

import com.group9.model.board.Board;
import com.group9.model.board.Lane;
import com.group9.model.Observer;
import com.group9.model.movement.Movable;
import com.group9.model.movement.MovementRule;

/**
 * Manages the movement of entities on the game board.
 * Implements the Observer interface to listen for when to update the positions of movable entities.
 */
public class MoveManager implements Observer {
    private final int TICKS_PER_SECONDS;

    private final Board board;

    /**
     * Constructs a MoveManager with the specified game board and ticks per second.
     *
     * @param board the game board
     * @param TICKS_PER_SECONDS the number of ticks per second
     */
    public MoveManager(Board board, int TICKS_PER_SECONDS) {
        this.board = board;
        this.TICKS_PER_SECONDS = TICKS_PER_SECONDS;
    }

    /**
     * Helper function to move the specified movable entity in the given lane if possible.
     *
     * @param movableEntity the entity to move
     * @param lane the lane in which the entity is moving
     */
    private void move(Movable movableEntity, Lane lane) {
        MovementRule rule = movableEntity.getMovementRule();
        if (rule.canMove(movableEntity, lane)) {
            float laneProgress = movableEntity.getLaneProgress();
            laneProgress += (float) movableEntity.getSpeed() / (TICKS_PER_SECONDS*10);
            movableEntity.setLaneProgress(laneProgress);
        }
    }

    /**
     * Updates the positions of all movable entities on the board.
     */
    @Override
    public void update() {
        for(Lane lane : board.getLanes()) {
            for (Movable movable : lane.getMovables()) {
                move(movable, lane);
            }
        }
    }
}
