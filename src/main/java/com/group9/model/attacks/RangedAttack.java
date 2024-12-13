package com.group9.model.attacks;

import com.group9.model.attacks.AttackStrategy;
import com.group9.model.entities.Entity;
import com.group9.model.entities.projectiles.Projectile;

public class RangedAttack implements AttackStrategy {

    @Override
    public void useAttack(Entity target, int damage) {
        // Handle projectile logic
        Projectile projectile = new Projectile();
        lane.getProjectiles().add(projectile);
        board.addMovable(projectile, lane);
    }
}