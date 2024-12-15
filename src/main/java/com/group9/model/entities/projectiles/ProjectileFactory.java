package com.group9.model.entities.projectiles;

import com.group9.model.entities.attackers.AttackEntity;
import com.group9.model.entities.defenders.DefenceEntity;

public class ProjectileFactory {
    public static Projectile createProjectile(ProjectileType projectileType, DefenceEntity defender, AttackEntity target, int damage) {
        switch (projectileType) {
            case ARROW:
                return new Projectile(ProjectileType.ARROW, defender.getLaneProgress(), target, projectileType.getSpeed(), damage);
            case BULLET:
                return new Projectile(ProjectileType.BULLET, defender.getLaneProgress(), target, projectileType.getSpeed(), damage);
            default:
                throw new IllegalArgumentException("Invalid projectile type: " + projectileType);
        }
    }
}
