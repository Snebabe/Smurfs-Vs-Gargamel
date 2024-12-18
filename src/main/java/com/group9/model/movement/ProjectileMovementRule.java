package com.group9.model.movement;

import com.group9.model.board.Lane;

// Defines the movement rule for projectiles
public class ProjectileMovementRule implements MovementRule {
    @Override
    public boolean canMove(Movable movable, Lane lane) {

        // If projectile is still within the range of the lane, it can move
        return movable.getLaneProgress() < 1;
    }
}
