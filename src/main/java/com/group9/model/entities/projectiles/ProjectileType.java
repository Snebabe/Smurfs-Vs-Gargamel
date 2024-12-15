package com.group9.model.entities.projectiles;

public enum ProjectileType {
    ARROW(4),
    BULLET(6);

    private final int speed;

    ProjectileType(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

}
