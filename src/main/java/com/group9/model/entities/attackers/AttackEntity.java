package com.group9.model.entities.attackers;

import com.group9.model.entities.Entity;

public abstract class AttackEntity extends Entity {
    private int speed;
    private float laneProgress;

    public AttackEntity(int maxHealth, int attackDamage, int range, int speed) {
        super(maxHealth, attackDamage,range);
        this.speed = speed;
        this.laneProgress = 0;
    }

    public float getLaneProgress() {
        return this.laneProgress;
    }

    //TODO
    public void move() {
        this.laneProgress += (float) speed / 100;
    }
}
