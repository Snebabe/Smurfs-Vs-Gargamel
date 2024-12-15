package com.group9.model.movement;

import com.group9.model.board.Lane;
import com.group9.model.entities.projectiles.Projectile;
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
            return false;
        }
        float distance = (1-attackEntity.getLaneProgress()) - projectile.getLaneProgress();

        if(distance < 0.05) {
            attackEntity.takeDamage(projectile.getDamage());
            if (attackEntity.isDead()) {
                //TODO: Fix reward when enemy is killed
                lane.removeAttacker(attackEntity);
            }
            projectile.setActive(false);
            return false;
        }
        else {
            return true;
        }
    }
}
