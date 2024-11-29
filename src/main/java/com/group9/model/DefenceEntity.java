package com.group9.model;

public abstract class DefenceEntity extends Entity {
    private int cost;

    public DefenceEntity(int health, int attackDamage, int range, int level, int cost) {
        super(health, attackDamage, range);

    }
}
