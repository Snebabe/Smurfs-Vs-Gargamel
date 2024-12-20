package com.group9.model.entities.projectiles;

import com.group9.model.movement.Movable;
import com.group9.model.movement.MovementRule;
import com.group9.model.movement.ProjectileMovementRule;

/**
 * Represents a projectile that can be fired by a defence entity.
 */
public class Projectile implements Movable {
    private float currentLaneProgress;
    private final float startingLaneProgress;
    private final int speed;
    private final float damage;
    private final int range;
    private final String name;

    /**
     * Constructs a projectile with the specified attributes.
     *
     * @param name the name of the projectile
     * @param laneProgress the progress of the projectile along the lane
     * @param range the range of the projectile
     * @param speed the speed of the projectile
     * @param damage the damage dealt by the projectile
     */
    public Projectile(String name, float laneProgress, int range, int speed, float damage) {
        currentLaneProgress = laneProgress;
        startingLaneProgress = laneProgress;
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
        return currentLaneProgress;
    }

    @Override
    public void setLaneProgress(float laneProgress) {
        currentLaneProgress = laneProgress;
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
