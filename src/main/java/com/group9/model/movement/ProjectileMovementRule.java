package com.group9.model.movement;

import com.group9.model.board.Lane;
import com.group9.model.entities.projectiles.Projectile;
import com.group9.model.entities.attackers.AttackEntity;

public class ProjectileMovementRule implements MovementRule {
    @Override
    public boolean canMove(Movable movable, Lane lane) {

        // If projectile in range, return true
        return movable.getLaneProgress() < 1;
    }
}
