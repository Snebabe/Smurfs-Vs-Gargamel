package com.group9.model;

public class UnitCalculator {

    public Position getPositionFromGridcell(GridCell cell) {
        // Väldigt flummigt, men principen är legit

        int x = cell.getSize() * cell.getPositionInLane();
        int y = cell.getSize() * cell.getLaneIndex();

        return new Position(x, y);
    }

    public Position getPositionFromLaneAndX(Lane lane, int x) {

        int x = lane.getCellSize()*lane.get
    }
}
