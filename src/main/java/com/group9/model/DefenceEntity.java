package com.group9.model;

public abstract class DefenceEntity extends Entity {
    private int cost;

    DefenceEntity(int health, int attackDamage, int range, int cost) {
        super(health, attackDamage, range);

    }
}
