package com.group9.model.entities.defenders;

import com.group9.model.entities.Entity;

public abstract class DefenceEntity extends Entity {
    private int cost;

    public DefenceEntity(int maxHealth, int attackDamage, int range, int cost) {
        super(maxHealth, attackDamage, range);


    }
}
