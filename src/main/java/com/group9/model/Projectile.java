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
        if(laneProgress >= 1 || target.isDead()) {
            active = false;
        }

        float distance = (1-target.getLaneProgress()) - laneProgress;
        System.out.println("Distance: " + distance + ", Speed Increment: " + (float) speed / 100);

        if(distance < (float)speed/100) {
            target.takeDamage(damage);
            active = false;
            System.out.println("Projectile hit target. Damage dealt: " + damage);
        }
        else{
            laneProgress += (float)speed/100;
            System.out.println("Projectile moving. New laneProgress: " + laneProgress);
        }
    }

    public boolean isActive() {
        return active;
    }

    public float getLaneProgress() {
        return laneProgress;
    }
}
