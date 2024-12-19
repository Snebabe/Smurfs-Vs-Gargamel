package com.group9.model.entities.projectiles;

import com.group9.model.movement.Movable;
import com.group9.model.movement.MovementRule;
import com.group9.model.movement.ProjectileMovementRule;

/**
 * Represents a projectile that can be fired by a defence entity.
 */
public class Projectile implements Movable {
    private float currentlaneProgress;
    private float startingLaneProgress;
    private int speed;
    private float damage; // Damage dealt by the projectile
    private int range;
    private String name;

    public Projectile(String name, float laneProgress, int range, int speed, float damage) {
        this.currentlaneProgress = laneProgress;
        this.startingLaneProgress = laneProgress;
        this.speed = speed;
        this.damage = damage;
        this.range = range;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public float getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }

    public float getStartingLaneProgress() {
        return startingLaneProgress;
    }

    @Override
    public float getLaneProgress() {
        return currentlaneProgress;
    }

    @Override
    public void setLaneProgress(float laneProgress) {
        this.currentlaneProgress = laneProgress;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public MovementRule getMovementRule() {
        return new ProjectileMovementRule();
    }

}
