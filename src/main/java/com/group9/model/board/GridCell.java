package com.group9.model.board;

import com.group9.model.entities.characters.defenders.DefenceEntity;

public class GridCell {

    private DefenceEntity defender;
    private int positionInLane;

    GridCell(int positionInLane) {
        this.positionInLane = positionInLane;

    }

    public int getPositionInLane() {
        return this.positionInLane;
    }

    public void setDefender(DefenceEntity defender) {
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
