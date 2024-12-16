package com.group9.model.entities.projectiles;

import com.group9.model.entities.attackers.AttackEntity;
import com.group9.model.entities.defenders.DefenceEntity;

public class ProjectileFactory {
    public static Projectile createProjectile(ProjectileType projectileType, float projectileLaneProgress, int range, int damage) {
        switch (projectileType) {
            case ARROW:
                return new Projectile(ProjectileType.ARROW, projectileLaneProgress, range, projectileType.getSpeed(), damage);
            case BULLET:
                return new Projectile(ProjectileType.BULLET, projectileLaneProgress, range, projectileType.getSpeed(), damage);
            default:
                throw new IllegalArgumentException("Invalid projectile type: " + projectileType);
        }
    }
}
