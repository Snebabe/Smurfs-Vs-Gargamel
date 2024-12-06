package com.group9.model;

public class ProjectileMovementRule implements MovementRule {
    @Override
    public boolean canMove(Movable movable, Board board) {
        // Example: Always allow movement, or stop at a target
        return !board.hasReachedTarget(movable);
    }
}
