package com.group9.model.entities.projectiles;

import com.group9.model.entities.EntityType;

public class ProjectileType extends EntityType {

    private int speed;

    public ProjectileType(String name, int speed) {
        super(name);
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}