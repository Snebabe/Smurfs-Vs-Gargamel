package com.group9.model;

public abstract class DefenceEntity extends Entity {
    private int cost;

    DefenceEntity(int maxHealth, int attackDamage, int range, int cost) {
        super(maxHealth, attackDamage, range);

    }
}
