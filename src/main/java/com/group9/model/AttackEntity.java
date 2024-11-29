package com.group9.model;

import jdk.jshell.spi.ExecutionControl;

public abstract class AttackEntity extends Entity{
    private int speed;
    private float laneProgress;

    AttackEntity(int health, int attackDamage, int range, int speed) {
        super(health, attackDamage,range);
        this.speed = speed;
        this.laneProgress = 0;
    }

    public float getLaneProgress() {
        return this.laneProgress;
    }

    //TODO
    public void move() {
        this.laneProgress += speed;
    }
}
