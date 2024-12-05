package com.group9.model.entities.attackers;

import com.group9.model.entities.Entity;

public abstract class AttackEntity extends Entity {
    private int speed;
    private float laneProgress;
    private int resourceReward;

    public AttackEntity(int maxHealth, int attackDamage, int range, int speed, int resourceReward) {
        super(maxHealth, attackDamage,range);
        this.speed = speed;
        this.laneProgress = 0;
        this.resourceReward = resourceReward;
    }

    public float getLaneProgress() {
        return this.laneProgress;
    }

    public int getResourceReward()  {
        return resourceReward;
    }


        //TODO
    public void move() {
        this.laneProgress += (float) speed / 100;
    }
}
