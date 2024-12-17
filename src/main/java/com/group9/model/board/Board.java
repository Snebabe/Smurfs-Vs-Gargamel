package com.group9.model.board;

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


    public Board(int laneAmount, int laneSize) {
        this.laneAmount = laneAmount;
        this.laneSize = laneSize;
        resetBoard();
    }

    public void resetBoard() {
        this.lanes = new ArrayList<>();
        for (int i = 0; i < laneAmount; i++) {
            this.lanes.add(new Lane(laneSize));
        }
    }

    public void setDefender(DefenceEntity defender, int row, int col) {
        Lane lane = this.lanes.get(row);
        lane.setDefender(defender, col);
    }

    public List<Lane> getLanes() {
        return lanes;
    }

    public int getLaneSize() {
        return laneSize;
    }

    public int getLaneAmount() {
        return laneAmount;
    }

}
