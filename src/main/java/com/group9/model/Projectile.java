package com.group9.model;

import com.group9.model.entities.attackers.AttackEntity;

public class Projectile {
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

    public void update() {
        // If target is dead or null, deactivate the projectile
        if (target == null || target.isDead()) {
            active = false;
            return;
        }

        // Calculate distance to the target
        float distance = (1 - target.getLaneProgress()) - laneProgress;

        if (distance < (float) speed / 100) {
            target.takeDamage(damage);
            active = false;
        } else {
            laneProgress += (float) speed / 100;
        }
    }

    public boolean isActive() {
        return active;
    }

    public void setTarget(AttackEntity target) {
        this.target = target;
    }

    public float getLaneProgress() {
        return laneProgress;
    }

    public AttackEntity getTarget() {
        return target;
    }
}
