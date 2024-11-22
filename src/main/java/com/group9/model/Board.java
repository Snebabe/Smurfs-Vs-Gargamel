package com.group9.model;

import java.util.List;

public class Board {
    private List<Lane> lanes;
    private int laneAmount;
    private int columnAmount;
    private int cellDimension;

    Board(int laneAmount, int columnAmount, int cellDimension) {
        this.laneAmount = laneAmount;
        this.columnAmount = columnAmount;
        this.cellDimension = cellDimension;

        for (int i = 0; i < laneAmount; i++) {
            this.lanes.add(new Lane(cellDimension));
        }
    }

    public List getLanes() {
        return lanes;
    }

    public int getCellDimension() {
        return cellDimension;
    }

    public int getColumnAmount() {
        return columnAmount;
    }

    public int getLaneAmount() {
        return laneAmount;
    }
}
