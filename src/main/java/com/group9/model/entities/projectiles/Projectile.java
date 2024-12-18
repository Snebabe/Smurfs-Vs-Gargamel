package com.group9.model.entities.projectiles;

import com.group9.model.movement.Movable;
import com.group9.model.movement.MovementRule;
import com.group9.model.movement.ProjectileMovementRule;

/*public class Projectile {
    private int damage;
    private int speed;
    private int currentCellIndex;
    private int targetCellIndex;
    private int lane;
    private boolean isDestroyed;

    public Projectile(int damage, int speed, int startCellIndex, int targetCellIndex, int lane) {
        this.damage = damage;
        this.speed = speed;
        this.currentCellIndex = startCellIndex;
        this.targetCellIndex = targetCellIndex;
        this.lane = lane;
        this.isDestroyed = false;
    }

    public void update() {
        // Logic to move the projectile
        if (currentCellIndex < targetCellIndex) {
            currentCellIndex += speed; // Move towards the target
        } else {
            isDestroyed = true; // Projectile reaches its target
        }
    }

    public boolean hasHitTarget() {
        return currentCellIndex >= targetCellIndex;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }
}*/

public class Projectile implements Movable {
    private float currentlaneProgress;
    private float startingLaneProgress;
    private int speed;
    private int damage;
    private int range;
    private String name;

    public Projectile(String name, float laneProgress, int range, int speed, int damage) {
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

    public int getDamage() {
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
