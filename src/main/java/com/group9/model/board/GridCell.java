package com.group9.model.board;

import com.group9.model.entities.characters.defenders.DefenceEntity;

public class GridCell {

    private DefenceEntity defender;  // Reference to the defender entity placed in the grid cell
    private final int positionInLane;      // Position of the cell within its lane

    // Constructor to initialize the position of the cell in its lane
    public GridCell(int positionInLane) {
        this.positionInLane = positionInLane;

    }

    public int getPositionInLane() {
        return this.positionInLane;
    }

    public void setDefender(DefenceEntity defender) {
        if (defender == null) {
            throw new IllegalArgumentException("Defender cannot be null.");
        }
        this.defender = defender;
    }

    public void removeDefender() {
        this.defender = null;
    }

    public boolean hasDefender() {
        return this.defender!=null;
    }

    public DefenceEntity getDefender() {
        return this.defender;
    }
}
