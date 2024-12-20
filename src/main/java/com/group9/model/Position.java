package com.group9.model;

/**
 * Represents a position on the game board.
 * The position is defined by a row and column, where row corresponds to the lane and
 * column corresponds to the position within that lane (or grid cell).
 */
public class Position {
    private final int col;
    private final int row;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
