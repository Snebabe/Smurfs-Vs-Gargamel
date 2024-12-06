package com.group9.model;

import com.group9.model.entities.defenders.DefenceEntity;

import java.util.ArrayList;
import java.util.List;

public class Board {

    // Board har X lanes.
    // Lane har Y rutor.
    // Borde det vara 🤓☝️

    private List<Lane> lanes;
    private int laneSize;
    private int laneAmount;
    private int cellSize;
    private MoveManager moveManager;


    Board(int laneAmount, int laneSize, int cellSize, int TICKS_PER_SECONDS) {
        this.laneAmount = laneAmount;
        this.laneSize = laneSize;
        this.cellSize = cellSize;
        this.lanes = new ArrayList<>();
        this.moveManager = new MoveManager(this,TICKS_PER_SECONDS);


        for (int i = 0; i < laneAmount; i++) {
            this.lanes.add(new Lane(laneSize, cellSize));
        }
    }

    public boolean isObstacleAhead(Projectile projectile, int row, int col) {
        if(projectile.getLaneProgress() >= 1 || projectile.getTarget().isDead()) {
            projectile.setActive(false);
        }

        if(projectile.isActive()) {
            return !CollisionHandler.checkCollisions(projectile, projectile.getTarget());
        }
        return false;

    }

    public boolean hasReachedTarget(Movable movable) {
        return movable.getLaneProgress() >= 1;
    }

    public void setDefender(DefenceEntity defender, int row, int col) {
        Lane lane = this.lanes.get(row);
        lane.setDefender(defender, col);
    }
    public void addMovable(Movable movable) {
        moveManager.addMovable(movable);
    }
    public List<Lane> getLanes() {
        return lanes;
    }

    public int getLaneSize() {
        return laneSize;
    }

    public int getCellSize() {
        return cellSize;
    }

    public int getLaneAmount() {
        return laneAmount;
    }

}
