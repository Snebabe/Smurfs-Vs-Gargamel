package com.group9.model;

import java.util.List;

public class Board {

    // Board har X lanes.
    // Lane har Y rutor.
    // Borde det vara 🤓☝️

    private List<Lane> lanes;
    private int laneSize;
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
