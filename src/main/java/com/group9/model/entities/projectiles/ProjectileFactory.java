package com.group9.model.entities.projectiles;

public class ProjectileFactory {
    public static Projectile createProjectile(ProjectileType projectileType) {
        switch (projectileType) {
            case ARROW:
                return new Projectile();
            default:
                throw new IllegalArgumentException("Invalid projectile type: " + projectileType);;
        }
    }
}
