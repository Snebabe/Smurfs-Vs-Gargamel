package com.group9.model;

import java.util.ArrayList;
import java.util.List;

public class MoveManager implements Observer {
    private int TICKS_PER_SECONDS;
    private List<Movable> movables;
    private Board board; // Reference to the board or context
    public MoveManager(Board board, int TICKS_PER_SECONDS) {
        this.movables = new ArrayList<>();
        this.TICKS_PER_SECONDS = TICKS_PER_SECONDS;
    }

    public void addMovable(Movable movable) {
        movables.add(movable);
    }

    public void move(Movable movableEntity) {
        MovementRule rule = movableEntity.getMovementRule();
        if (rule.canMove(movableEntity, board)) {
            float laneProgress = movableEntity.getLaneProgress();
            laneProgress += (float) movableEntity.getSpeed() / (this.TICKS_PER_SECONDS*10);
            movableEntity.setLaneProgress(laneProgress);
        }
    }

    @Override
    public void update() {
        for(Movable movable : movables) {
            move(movable);
        }
    }
}
