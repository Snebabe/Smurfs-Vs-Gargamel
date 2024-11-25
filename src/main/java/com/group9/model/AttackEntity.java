package com.group9.model;

import jdk.jshell.spi.ExecutionControl;

public abstract class AttackEntity extends Entity{
    private int speed;

    AttackEntity(int health, int attack, int range, Lane lane, int speed) {
        super(health, attack,range, lane, 0);
        this.speed = speed;
    }

    //TODO
    public boolean hasReachedTarget() {
        return false;
    }

    //TODO
    public void move() {

    }
}
