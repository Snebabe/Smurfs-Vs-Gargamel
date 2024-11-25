package com.group9.model;

public class GridCell {

    private DefenceEntity defender;
    private int size;
    private int laneIndex;
    private int positionInLane;

    GridCell(int size, int laneIndex, int positionInLane) {
        this.size = size;
        this.laneIndex = laneIndex;
        this.positionInLane = positionInLane;

    }

    public int getSize() {
        return this.size;
    }

    public int getLaneIndex() {
        return this.laneIndex;
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

    public DefenceEntity getDefender() {
        return this.defender;
    }
}
