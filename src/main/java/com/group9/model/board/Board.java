package com.group9.model.board;

import com.group9.model.entities.defenders.DefenceEntity;
import com.group9.model.movement.Movable;
import com.group9.model.movement.MoveManager;

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


    public Board(int laneAmount, int laneSize, int cellSize, int TICKS_PER_SECONDS) {
        this.laneAmount = laneAmount;
        this.laneSize = laneSize;
        this.cellSize = cellSize;
        this.lanes = new ArrayList<>();
        this.moveManager = new MoveManager(TICKS_PER_SECONDS);


        for (int i = 0; i < laneAmount; i++) {
            this.lanes.add(new Lane(laneSize, cellSize));
        }
    }

    public void setDefender(DefenceEntity defender, int row, int col) {
        Lane lane = this.lanes.get(row);
        lane.setDefender(defender, col);
    }
    public void addMovable(Movable movable, Lane lane) {
        moveManager.addMovable(movable, lane);
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
    public MoveManager getMoveManager() {
        return moveManager;
    }

}
