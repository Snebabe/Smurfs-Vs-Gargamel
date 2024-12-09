package com.group9.model.movement;

import com.group9.model.board.Lane;
import com.group9.model.entities.Projectile;
import com.group9.model.entities.attackers.AttackEntity;

public class ProjectileMovementRule implements MovementRule {
    @Override
    public boolean canMove(Movable movable, Lane lane) {
        Projectile projectile = (Projectile) movable;
        AttackEntity attackEntity = projectile.getTarget();
        if (!projectile.isActive()) {
            lane.getProjectiles().remove(projectile);
            return false;
        }
        if(projectile.getLaneProgress() >= 1 || attackEntity.isDead()) {
            projectile.setActive(false);
        }
        float distance = (1-attackEntity.getLaneProgress()) - projectile.getLaneProgress();

        if(distance < 0.05) {
            attackEntity.takeDamage(projectile.getDamage());
            projectile.setActive(false);
            return false;
        }
        else {
            return true;
        }
    }
}
