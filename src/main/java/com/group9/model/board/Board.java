package com.group9.model.board;

import com.group9.model.entities.characters.defenders.DefenceEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the game board consisting of multiple lanes.
 */
public class Board {

    private List<Lane> lanes; // Represents all the lanes on the board.
    private final int laneSize; // Number of cells per lane.
    private final int laneAmount; // Number of lanes on the board.

    /**
     * Initializes the board with a specified number of lanes and lane size.
     *
     * @param laneAmount the number of lanes on the board
     * @param laneSize the number of cells per lane
     */
    public Board(int laneAmount, int laneSize) {
        this.laneAmount = laneAmount;
        this.laneSize = laneSize;
        resetBoard();
    }

    /**
     * Resets the board by creating new empty lanes.
     */
    public void resetBoard() {
        lanes = new ArrayList<>();
        for (int i = 0; i < laneAmount; i++) {
            lanes.add(new Lane(laneSize));
        }
    }

    /**
     * Places a defender in the specified row and column.
     *
     * @param defender the defender entity to be placed
     * @param row the row index where the defender will be placed
     * @param col the column index where the defender will be placed
     * @throws IllegalArgumentException if the row or column index is invalid
     */
    public void setDefender(DefenceEntity defender, int row, int col) {
        if (row < 0 || row >= laneAmount || col < 0 || col >= laneSize) {
            throw new IllegalArgumentException("Invalid row or column index.");
        }
        Lane lane = lanes.get(row);
        lane.setDefender(defender, col);
    }

    public List<Lane> getLanes() {
        return lanes;
    }

    public int getLaneAmount() {
        return laneAmount;
    }

}
