package com.group9.model.entities.projectiles;

import com.group9.model.entities.attackers.AttackEntity;
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
    private float laneProgress;
    private AttackEntity target;
    private int speed;
    private int damage;
    private boolean active;
    private ProjectileType projectileType;

    public Projectile(ProjectileType projectileType, float laneProgress, AttackEntity target, int speed, int damage) {
        this.laneProgress = laneProgress;
        this.target = target;
        this.speed = speed;
        this.damage = damage;
        this.active = true;
        this.projectileType = projectileType;
    }

    public ProjectileType getType() {
        return projectileType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getDamage() {
        return damage;
    }

    public AttackEntity getTarget() {
        return target;
    }

    @Override
    public float getLaneProgress() {
        return laneProgress;
    }

    @Override
    public void setLaneProgress(float laneProgress) {
        this.laneProgress = laneProgress;
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
