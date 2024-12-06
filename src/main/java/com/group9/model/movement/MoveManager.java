package com.group9.model.movement;

import com.group9.model.board.Board;
import com.group9.model.board.Lane;
import com.group9.model.Observer;

import java.util.HashMap;
import java.util.Map;

public class MoveManager implements Observer {
    private int TICKS_PER_SECONDS;
    private Map<Movable, Lane> movables;
    private Board board; // Reference to the board or context
    public MoveManager(int TICKS_PER_SECONDS) {
        this.movables = new HashMap<>();
        this.TICKS_PER_SECONDS = TICKS_PER_SECONDS;
    }

    public void addMovable(Movable movable, Lane lane) {
        movables.put(movable, lane);
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
        for(Movable movable : movables.keySet()) {
            move(movable, movables.get(movable));
        }
    }
}
