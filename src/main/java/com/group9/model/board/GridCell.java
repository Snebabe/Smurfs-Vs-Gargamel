package com.group9.model.board;

import com.group9.model.entities.characters.defenders.DefenceEntity;

/**
 * Represents a single cell in a lane on the game board.
 */
public class GridCell {

    private DefenceEntity defender;  // Reference to the defence entity placed in the grid cell

    public void setDefender(DefenceEntity defender) {
        this.defender = defender;
    }

    public boolean hasDefender() {
        return this.defender!=null;
    }

    public DefenceEntity getDefender() {
        return this.defender;
    }
}
