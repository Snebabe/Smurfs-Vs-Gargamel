package com.group9.model.movement;

import com.group9.model.PositionConverter;
import com.group9.model.board.GridCell;
import com.group9.model.board.Lane;

public class AttackerMovementRule implements MovementRule {
    @Override
    public boolean canMove(Movable movable, Lane lane) {
        // Calculate the attacker's cell index
        int attackerCellIndex = (int) PositionConverter.attackerToCellIndex(movable.getLaneProgress(), lane.getNumberOfCells());

        // Attackers spawn initially outside the grid bounds
        if (attackerCellIndex >= lane.getNumberOfCells()) {
            return true;
        } else if (attackerCellIndex < 0 ) {
            return true;
        }
        // Check if there's a defender at the same cell index
        GridCell gridcell = lane.getGridCells().get(attackerCellIndex);
        if (gridcell != null ) {
            return !gridcell.hasDefender();
        }

        return true;
    }
}
