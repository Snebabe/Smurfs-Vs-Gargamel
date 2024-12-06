package com.group9.model.entities.defenders;

import com.group9.model.entities.Entity;

public abstract class DefenceEntity extends Entity {
    private int cost;
    private boolean ranged;

    public DefenceEntity(int maxHealth, int attackDamage, int range, int cost, boolean ranged) {
        super(maxHealth, attackDamage, range);
        this.ranged = ranged;
    }

    public boolean isRanged() {
        return ranged;
    }

}
