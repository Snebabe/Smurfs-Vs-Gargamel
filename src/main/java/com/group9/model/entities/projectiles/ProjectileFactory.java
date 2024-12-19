package com.group9.model.entities.projectiles;

/**
 * Factory class for creating projectiles.
 */
public class ProjectileFactory {

    /**
     * Creates a new projectile based of a projectile type.
     *
     * @param type the type of the projectile
     * @param projectileLaneProgress the progress of the projectile in the lane
     * @param range the range of the projectile
     * @param damage the damage of the projectile
     * @throws IllegalArgumentException if the projectile type is null
     * @return the new projectile
     */
    public static Projectile createProjectile(ProjectileType type, float projectileLaneProgress, int range, float damage) {
        if (type == null) {
            throw new IllegalArgumentException("Invalid projectile type");
        }
        return new Projectile(type.getName(), projectileLaneProgress, range, type.getSpeed(), damage);
    }
}
