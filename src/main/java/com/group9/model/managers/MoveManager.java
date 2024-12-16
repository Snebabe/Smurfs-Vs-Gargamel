package com.group9.model.managers;

import com.group9.model.board.Board;
import com.group9.model.board.Lane;
import com.group9.model.Observer;
import com.group9.model.entities.projectiles.Projectile;
import com.group9.model.movement.Movable;
import com.group9.model.movement.MovementRule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoveManager implements Observer {
    private int TICKS_PER_SECONDS;

    private Board board;

    public MoveManager(Board board, int TICKS_PER_SECONDS) {
        this.board = board;
        this.TICKS_PER_SECONDS = TICKS_PER_SECONDS;
    }

    public void move(Movable movableEntity, Lane lane) {
        MovementRule rule = movableEntity.getMovementRule();
        if (rule.canMove(movableEntity, lane)) {
            float laneProgress = movableEntity.getLaneProgress();
            laneProgress += (float) movableEntity.getSpeed() / (this.TICKS_PER_SECONDS*10);
            movableEntity.setLaneProgress(laneProgress);
        }
    }

    @Override
    public void update() {
        for(Lane lane : this.board.getLanes()) {
            for (Movable movable : lane.getMovables()) {
                move(movable, lane);
            }
        }
    }
}
