package com.group9.model.board;

import com.group9.model.entities.characters.defenders.DefenceEntity;

import java.util.ArrayList;
import java.util.List;

public class Board {


    private List<Lane> lanes; // Represents all the lanes on the board.
    private final int laneSize; // Number of cells per lane.
    private final int laneAmount; // Number of lanes on the board.


    // Initializes the board with a specified number of lanes and lane size.
    public Board(int laneAmount, int laneSize) {
        this.laneAmount = laneAmount;
        this.laneSize = laneSize;
        resetBoard();
    }

    // Resets the board by creating new lanes with the specified size.
    public void resetBoard() {
        this.lanes = new ArrayList<>();
        for (int i = 0; i < laneAmount; i++) {
            this.lanes.add(new Lane(laneSize));
        }
    }

    // Places a defender in the specified row and column.
    public void setDefender(DefenceEntity defender, int row, int col) {
        if (row < 0 || row >= laneAmount || col < 0 || col >= laneSize) {
            throw new IllegalArgumentException("Invalid row or column index.");
        }
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
