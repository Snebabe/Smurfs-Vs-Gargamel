package com.group9.model;

import com.group9.model.entities.attackers.AttackEntity;

public class Projectile implements Movable {
    private float laneProgress;
    private AttackEntity target;
    private int speed;
    private int damage;
    private boolean active;

    public Projectile(float laneProgress, AttackEntity target, int speed, int damage) {
        this.laneProgress = laneProgress;
        this.target = target;
        this.speed = speed;
        this.damage = damage;
        this.active = true;
    }

    public boolean moveAllowed() {

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
