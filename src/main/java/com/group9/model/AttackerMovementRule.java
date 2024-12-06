package com.group9.model;

public class AttackerMovementRule implements MovementRule {
    @Override
    public boolean canMove(Movable movable, Board board) {
        // Example: Check if there’s no obstacle at the next position
        return !board.isObstacleAhead(movable);
    }
}
