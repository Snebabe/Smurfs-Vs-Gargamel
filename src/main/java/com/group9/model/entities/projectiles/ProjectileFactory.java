package com.group9.model.entities.projectiles;

public class ProjectileFactory {

    // Creates a new projectile based on the type, progress, range, and damage
    public static Projectile createProjectile(ProjectileType type, float projectileLaneProgress, int range, float damage) {
        if (type == null) {
            throw new IllegalArgumentException("Invalid projectile type");
        }
        return new Projectile(type.getName(), projectileLaneProgress, range, type.getSpeed(), damage);
        /*switch (projectileType) {
            case ARROW:
                return new Projectile(ProjectileType.ARROW, projectileLaneProgress, range, projectileType.getSpeed(), damage);
            case BULLET:
                return new Projectile(ProjectileType.BULLET, projectileLaneProgress, range, projectileType.getSpeed(), damage);
            default:
                throw new IllegalArgumentException("Invalid projectile type: " + projectileType);
        }*/
    }
}
