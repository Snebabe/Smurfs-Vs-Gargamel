package com.group9.model;

import java.util.List;

public class Board {
    private List<Lane> lanes;
    private int laneAmount;
    private int cellSize;

    Board(int laneAmount, int laneSize, int cellSize) {
        this.laneAmount = laneAmount;
        this.laneSize = laneSize;
        this.cellSize = cellSize;

        for (int i = 0; i < laneAmount; i++) {
            this.lanes.add(new Lane(laneSize, cellSize));
        }
    }

    public List getLanes() {
        return lanes;
    }

    public int getCellDimension() {
        return cellSize;
    }

    public int getlaneSize() {
        return laneSize;
    }

    public int getLaneAmount() {
        return laneAmount;
    }


}
