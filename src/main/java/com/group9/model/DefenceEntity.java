package com.group9.model;

public abstract class DefenceEntity extends Entity {
    private int level;
    private int cost;
    DefenceEntity(int health, int attack, Lane lane, int x, int level, int cost) {
        super(health, attack, lane, x);
        this.level = level;
    }
}
