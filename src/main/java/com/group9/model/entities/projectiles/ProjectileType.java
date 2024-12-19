package com.group9.model.entities.projectiles;

import com.group9.model.entities.EntityType;

/**
 * Class representing a projectile type.
 */
public class ProjectileType extends EntityType {

    private final int speed;

    /**
     * Constructs a ProjectileType with the attributes name and speed.
     *
     * @param name the name of the projectile type
     * @param speed the speed of the projectile type
     */
    public ProjectileType(String name, int speed) {
        super(name);
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}